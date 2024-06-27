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
							@RequestParam("quotaB2") String quotaB2
							) {
		String [] probabilitaImplicita= service.probabilitaImplicita(quotaB1, quotaBX, quotaB2);
		model.addAttribute("marketValueB1", probabilitaImplicita[0]);
		model.addAttribute("marketValueBX", probabilitaImplicita[1]);
		model.addAttribute("marketValueB2", probabilitaImplicita[2]);
		model.addAttribute("aggio", probabilitaImplicita[3]);
	
		return "result";
	}
	
}
