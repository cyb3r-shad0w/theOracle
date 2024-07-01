package theOracle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import theOracle.service.PredictionService;

@Controller
public class PredictionController {
	
	@Autowired
	PredictionService service;
	
	@GetMapping("/")
	public String index() {
		return "index";
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
		String [] predictionResult = service.prediction(
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

		model.addAttribute("", "");





		return "result";
	}
	
}
