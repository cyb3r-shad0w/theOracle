package theOracle.service;

import org.springframework.stereotype.Service;

@Service
public class PredictionService {


	public String[] probabilitaImplicita(String quotaB1,String quotaBX,String quotaB2) {// PASSO 1
		
		Double marketValueB1 = 100 / Double.valueOf(quotaB1);
		Double marketValueBX = 100 / Double.valueOf(quotaBX);
		Double marketValueB2 = 100 / Double.valueOf(quotaB2);
		Double aggio = (marketValueB1 + marketValueB2 + marketValueBX) - 100;
		String [] result = new String [4];
		result[0]=Double.toString(marketValueB1);
		result[1]=Double.toString(marketValueBX);
		result[2]=Double.toString(marketValueB2);
		result[3]=Double.toString(aggio);
		return result;
	}

}
