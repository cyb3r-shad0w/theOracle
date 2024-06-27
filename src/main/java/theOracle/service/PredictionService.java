package theOracle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredictionService {


	public void probabilitaImplicita(Double quotaB1,Double quotaB2,Double quotaBX) {// PASSO 1
		
		Double marketValueB1 = 100 / quotaB1;
		Double marketValueB2 = 100 / quotaB2;
		Double marketValueBX = 100 / quotaBX;
		Double aggio = (marketValueB1 + marketValueB2 + marketValueBX) - 100;
		System.out.println("Il valore delle PERCENTUALI dato dal BOOKMAKER è:");
		System.out.println("La probabilità per la squadra di CASA di vincere secondo i BM è: " + marketValueB1+"%");
		System.out.println("La probabilità per la squadra in TRASFERTA di vincere secondo i BM è: " + marketValueB2+"%");
		System.out.println("La probabilità di PAREGGIARE secondo i BM è: " + marketValueBX+"%");
		System.out.println("L'allibramento sulla seguente partita è del " + aggio+"%");
	}

}
