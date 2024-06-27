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
	public String dataReader(Model model,@RequestParam("quotaB1") String quotaB1) {
		
		
		
		
		model.addAttribute("quotaB1", quotaB1);
		return "result";
	}
	
}
