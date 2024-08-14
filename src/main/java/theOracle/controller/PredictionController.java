package theOracle.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import theOracle.service.ApiCallService;
import theOracle.service.PredictionService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

@Controller
public class PredictionController {
	
	@Autowired
	PredictionService service;
	@Autowired
	ApiCallService apiCallService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/scheduledEvents")
	public String scheduledEvents(Model model) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
		LocalDate localDate = LocalDate.now();
		String date = localDate.format(dateTimeFormatter);

		LinkedHashMap<Integer, JSONObject> events = apiCallService.scheduledEvents(date);

		model.addAttribute("events",events);

		return "scheduledEvents";
	}

	@PostMapping("/dataReader")
	public String dataReader(Model model,
							@RequestParam("quotaB1") String quotaB1,
							@RequestParam("quotaBX") String quotaBX,
							@RequestParam("quotaB2") String quotaB2,
							@RequestParam("partiteTotCasa") String partiteTotCasa,
							@RequestParam("partiteTotOspite") String partiteTotOspite,
							@RequestParam("vittorieTotaliCasa") String vittorieTotaliCasa,
							@RequestParam("sconfitteTotaliCasa") String sconfitteTotaliCasa,
							@RequestParam("vittorieTotaliOspite") String vittorieTotaliOspite,
							@RequestParam("sconfitteTotaliOspite") String sconfitteTotaliOspite,
							@RequestParam("vittUltime5TotCasa") String vittUltime5TotCasa,
							@RequestParam("sconfUltime5Casa") String sconfUltime5Casa,
							@RequestParam("vittUltime5inTrasfOspite") String vittUltime5inTrasfOspite,
							@RequestParam("sconfitteUltime5Ospite") String sconfitteUltime5Ospite,
							@RequestParam("totatliPartiteCasaInCasa") String totatliPartiteCasaInCasa,
							@RequestParam("totaliPartiteOspiteInTrasferta") String totaliPartiteOspiteInTrasferta,
							@RequestParam("vittCasaTot") String vittCasaTot,
							@RequestParam("sconfTrasTot") String sconfTrasTot,
							@RequestParam("vittTrasTot") String vittTrasTot,
							@RequestParam("sconfCasaTot") String sconfCasaTot,
							@RequestParam("vittUltime5inCasa")String vittUltime5SoloinCasa,
							@RequestParam("sconfUlt5inCasa")String sconfUlt5SoloinCasa,
							@RequestParam("vittUltime5inTrasfOspite")String vittUltime5SoloinTrasfOspite,
							@RequestParam("sconfUlt5inTraOspite")String sconfUlt5SoloinTraOspite
							) {
		LinkedHashMap<String,Double> predictionResult = service.prediction(
				//passo1
				quotaB1, quotaBX, quotaB2,
				//passo2
				partiteTotCasa, partiteTotOspite,vittorieTotaliCasa,sconfitteTotaliCasa,vittorieTotaliOspite,sconfitteTotaliOspite,
				//passo3
				vittUltime5TotCasa,sconfUltime5Casa,vittUltime5inTrasfOspite,sconfitteUltime5Ospite,
				//passo4
				totatliPartiteCasaInCasa,totaliPartiteOspiteInTrasferta, vittCasaTot, sconfTrasTot,	vittTrasTot, sconfCasaTot,
				//passo5
				vittUltime5SoloinCasa,sconfUlt5SoloinCasa,vittUltime5SoloinTrasfOspite,sconfUlt5SoloinTraOspite
		);

		String tipologiaDiPartita ="";

		if( predictionResult.get("tipologiaDiPartita") >= 1.0 || predictionResult.get("tipologiaDiPartita") <2.0){
			tipologiaDiPartita="Analizzando la differenza tra Percentuale Reale e Percentuale BM questa partita risulta essere " +
					"una PARTITA LINEARE(STATISTICA)!"+
					"CONSIGLIO: Si possono studiare tutti i tipi di mercato, però se una delle differenze risulta " +
					"essere negativa bisogna valutare anche una doppia chance";
			if(predictionResult.get("tipologiaDiPartita") == 1.1){
				tipologiaDiPartita +=";INOLTRE: Poichè quota1<quotaX<quota2 oppure quota2<quotaX<quota1 POSSO giocare il RISULTATO ESATTO,consultare la Formula di POISSON";
			}
			if(predictionResult.get("tipologiaDiPartita") == 1.2){
				tipologiaDiPartita +=";INOLTRE: Poichè quota1<quota2<quotaX oppure quota2<quota1<quotaX NON POSSO giocare il RISULTATO ESATTO";
			}
		}else if(predictionResult.get("tipologiaDiPartita") == 2.0){
			tipologiaDiPartita =
					"Analizzando la differenza tra Percentuale Reale e Percentuale BM questa partita risulta essere una " +
							"PARTITA NON LINEARE(NON STATISTICA)!"+
							"CONSIGLIO: Si possono studiare tutti i mercati dei GOL-NOGOL,UNDER-OVER,MULTIGOL dopo uno studio " +
							"preventivo dell'andamento STATISTICO DEI GOL possibilmente con la Formula di POISSON";
		}else if (predictionResult.get("tipologiaDiPartita") == 3.0){
			tipologiaDiPartita =
					"Analizzando la differenza tra Percentuale Reale e Percentuale BM questa partita risulta essere una " +
							"PARTITA CON UNA FORTE FAVORITA(NON LINEARE CASA/TRASFERTA)!"+
							"CONSIGLIO: Si possono giocare i mercati dell' 1-X-2, quindi risultato fisso ";
		}

		model.addAttribute("marketValueB1", predictionResult.get("marketValueB1"));
		model.addAttribute("marketValueBX", predictionResult.get("marketValueBX"));
		model.addAttribute("marketValueB2", predictionResult.get("marketValueB2"));
		model.addAttribute("aggio", predictionResult.get("aggio"));
		model.addAttribute("fairProbability1", predictionResult.get("fairProbability1"));
		model.addAttribute("fairProbabilityX", predictionResult.get("fairProbabilityX"));
		model.addAttribute("fairProbability2", predictionResult.get("fairProbability2"));
		model.addAttribute("tipologiaDiPartita", tipologiaDiPartita);
		model.addAttribute("differenzaquota1", predictionResult.get("differenzaquota1"));
		model.addAttribute("differenzaquotaX", predictionResult.get("differenzaquotaX"));
		model.addAttribute("differenzaquota2", predictionResult.get("differenzaquota2"));
		model.addAttribute("vb1", predictionResult.get("vb1"));
		model.addAttribute("vbX", predictionResult.get("vbX"));
		model.addAttribute("vb2", predictionResult.get("vb2"));
		model.addAttribute("vbtotal", predictionResult.get("vbtotal"));
		model.addAttribute("aggio1", predictionResult.get("aggio1"));
		model.addAttribute("aggioX", predictionResult.get("aggioX"));
		model.addAttribute("aggio2", predictionResult.get("aggio2"));







		return "result";
	}
	
}
