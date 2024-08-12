package theOracle.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;


@Service
public class ApiCallService {

    String apiURL = "https://api.sofascore.app/api/v1/";
    private Logger log = LoggerFactory.getLogger(ApiCallService.class);

    public String scheduledEvents(String date){

        HttpResponse<String> response = null;
        JSONObject responseBodyJSON = null;
        JSONArray responseArray = null;

        String apiCallURI = apiURL+"sport/football/scheduled-events/"+date;
        log.info("The apiCall is ready:{"+apiCallURI+"}");

        try {
            log.info("Try to call the apiCall......");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiCallURI))
                    .header("content-type", "application/octet-stream")
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();

            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            log.info("Request completed.....");

            log.info("Initializing JSONObject....");
            responseBodyJSON = new JSONObject(response.body());

            responseArray = responseBodyJSON.getJSONArray("events");

        }catch(Exception e){
            log.error(e.getMessage());
        }

        if(responseArray == null){
            return "";
        }
        return responseArray.toString();
    }
}
