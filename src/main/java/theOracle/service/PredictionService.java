package theOracle.service;

import org.springframework.stereotype.Service;

@Service
public class PredictionService {


	public String[] probabilitaImplicita(String quotaB1,String quotaBX,String quotaB2)
	{// PASSO 1
		
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

	public String[] probabilitaPartiteTotali(String partiteTotCasa, String partiteTotOspite,
											 String vittorieTotaliCasa, String sconfitteTotaliCasa,
											 String vittorieTotaliOspite, String sconfitteTotaliOspite)
	{// PASSO 2
		Double partitetotali =  Double.valueOf(partiteTotCasa) +  Double.valueOf(partiteTotOspite);
		Double percPartiteTot1 = ((Double.valueOf(vittorieTotaliCasa) + Double.valueOf(sconfitteTotaliOspite)) / partitetotali)*100;
		Double percPartiteTot2 = ((Double.valueOf(vittorieTotaliOspite) + Double.valueOf(sconfitteTotaliCasa)) / partitetotali)*100;
		Double percPartiteTotX = 100 - (percPartiteTot1 + percPartiteTot2);
		String [] result = new String [3];
		result[0]= Double.toString(percPartiteTot1);
		result[1]= Double.toString(percPartiteTotX);
		result[2]= Double.toString(percPartiteTot2);
		return result;

	}

}