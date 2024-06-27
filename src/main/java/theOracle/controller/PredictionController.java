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
							@RequestParam("sconfitteTotaliOspite") String sconfitteTotaliOspite
							) {
		//passo 1
		String [] probabilitaImplicita= service.probabilitaImplicita(quotaB1, quotaBX, quotaB2);
		model.addAttribute("marketValueB1", probabilitaImplicita[0]);
		model.addAttribute("marketValueBX", probabilitaImplicita[1]);
		model.addAttribute("marketValueB2", probabilitaImplicita[2]);
		model.addAttribute("aggio", probabilitaImplicita[3]);

		//passo 2
		String[] probabilitaPartiteTotali = service.probabilitaPartiteTotali(partiteTotCasa, partiteTotOspite,vittorieTotaliCasa,
																			sconfitteTotaliCasa,vittorieTotaliOspite,
																			sconfitteTotaliOspite);
		model.addAttribute("percPartiteTot1", probabilitaPartiteTotali[0]);
		model.addAttribute("percPartiteTotX", probabilitaPartiteTotali[1]);
		model.addAttribute("percPartiteTot2", probabilitaPartiteTotali[2]);

		return "result";
	}
	
}
