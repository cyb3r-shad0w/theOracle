package theOracle.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;


@Service
public class ApiCallService {

    String apiURL = "https://api.sofascore.app/api/v1/";
    private Logger log = LoggerFactory.getLogger(ApiCallService.class);

    public LinkedHashMap<Integer,Object[]> scheduledEvents(String date){

        JSONArray eventsArray = null;
        JSONObject responseBodyJSON = null;

        String eventsFilePath = "C:\\Users\\Antonio\\Desktop\\DataBaseSofaScore\\scheduledEvents\\events_"+date+".json";

        LinkedHashMap<Integer,Object[]> events = null;

        try {

            log.info("Checking if db file exist alredy for today before calling the service.....");

            File eventsFile = new File(eventsFilePath);

            if (eventsFile.exists()){

                Scanner myReader = new Scanner(eventsFile);

                while (myReader.hasNextLine()) {
                    responseBodyJSON = new JSONObject(myReader.nextLine());
                    eventsArray = responseBodyJSON.getJSONArray("events");
                }

                myReader.close();

                log.info("File for today alredy exists.......");

            }else {

               // eventsArray = scheduledEventsApiCall(date,null); non funziona bene questo metodo, spesso non ritorna nulla l'api
                //optato per fare la chiamata manualmente tramite postman e poi carico i dati su un file manualmente

            }
            events = new LinkedHashMap<>();

            for (int i = 0; i < eventsArray.length(); i++) { // iterate over array to get inner JSON objects and
                // extract values inside
                // TODO:  inserire qui una stringa dove concatenare i dati che mi interessano e li inserisco in events
                // con chiave il valore i, quindi Integer,String , poi scomporrò i dati quando arrivano, si ma come li scompongo
                // poi con thymeleaf----> faccio degli oggetti che contengono i dati, tipo oggetto Tournament etc e uno
                // generico per contenere quei dati che non sono oggetti json e poi li metto tutti in un array che poi
                // metto nella mappa events con id l'id dell'evento

                JSONObject event = eventsArray.getJSONObject(i); // each item of Array is a JSON object

                Object[] evento = new Object[11];

                JSONObject tournament = null;
                if (event.has("tournament")) {
                    tournament = event.getJSONObject("tournament");
                    evento[0] = tournament;
                }

                JSONObject season = null;
                if (event.has("season")) {
                    season = event.getJSONObject("season");
                    evento[1] = season;
                }

                JSONObject roundInfo = null;
                if (event.has("roundInfo")) {
                    roundInfo = event.getJSONObject("roundInfo");
                    evento[2] = roundInfo;
                }

                JSONObject status = null;
                if (event.has("status")) {
                    status = event.getJSONObject("status");
                    evento[3] = status;
                }

                String winnerCode = "N";
                if (event.has("winnerCode")) {
                    winnerCode = event.get("winnerCode").toString();
                    evento[4] = winnerCode;
                }

                JSONObject homeTeam = null;
                if (event.has("homeTeam")) {
                    homeTeam = event.getJSONObject("homeTeam");
                    evento[5] = homeTeam;
                }
                JSONObject awayTeam = null;
                if (event.has("awayTeam")) {
                    awayTeam = event.getJSONObject("awayTeam");
                    evento[6] = awayTeam;
                }

                JSONObject homeScore = null; // qui sono contenuti i dati sui goal
                if (event.has("homeScore")) {
                    homeScore = event.getJSONObject("homeScore");
                    evento[7] = homeScore;
                }
                JSONObject awayScore = null;
                if (event.has("awayScore")) {
                    awayScore = event.getJSONObject("awayScore");
                    evento[8] = awayScore;
                }

                String id = "N";
                if (event.has("id")) {
                    id = event.get("id").toString();
                    evento[9] = id;
                }

                String startTimestamp = "N";
                if (event.has("startTimestamp")) {
                    startTimestamp = event.get("startTimestamp").toString();
                    evento[10] = startTimestamp;
                }

                events.put(i,evento);

            }

        }catch(Exception e){
            log.error(e.getMessage());
        }

        return events;
    }
    private JSONArray scheduledEventsApiCall (String date, JSONArray eventsArray){
        //TODO: da sistemare la chiamata perche attualmente  non funziona.
        log.info("Try to call the scheduledEvents apiCall......");

        JSONObject responseBodyJSON = null;

        String eventsFilePath = "C:\\Users\\Antonio\\Desktop\\DataBaseSofaScore\\scheduledEvents\\events_"+date+".json";
        String apiCallURI = apiURL+"sport/football/scheduled-events/"+date;
        log.info("The apiCall is ready:{"+apiCallURI+"}");

        HttpResponse<String> response = null;

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiCallURI))
                    .header("Host", "api.sofascore.app")
                    .GET()
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            log.info("Request completed.....");

            log.info("Initializing JSONObject....");

            responseBodyJSON = new JSONObject(response.body());

            eventsArray = responseBodyJSON.getJSONArray("events");

            if (eventsArray != null) {

                FileWriter myWriter = new FileWriter(eventsFilePath);
                myWriter.write(response.body());
                myWriter.close();
                log.info("Successfully wrote events to the file.....");

            }
        }catch (Exception e){
            log.error(e.getMessage());
        }

        if(eventsArray == null){
            scheduledEventsApiCall(date,eventsArray);
        }

        return eventsArray;

    }
}
