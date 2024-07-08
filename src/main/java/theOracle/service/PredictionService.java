package theOracle.service;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class PredictionService {
	//TODO: quando sarà completo tutto il programma, provare una run con il vecchio vs il nuovo per vedere che i risultati ottenuti siano uguali
	final private int ULTIME_5 = 5;

	public LinkedHashMap<String,Double> prediction(String quotaB1,String quotaBX,String quotaB2,
							   String partiteTotCasa, String partiteTotOspite,
							   String vittorieTotaliCasa, String sconfitteTotaliCasa,
							   String vittorieTotaliOspite, String sconfitteTotaliOspite,
							   String vittUltime5TotCasa,String sconfUltime5Casa,
							   String vittUltime5inTrasfOspite, String sconfitteUltime5Ospite,
							   String totatliPartiteCasaInCasa, String totaliPartiteOspiteInTrasferta,
							   String vittCasaTot, String sconfTrasTot,
							   String vittTrasTot, String sconfCasaTot,
							   String vittUltime5SoloinCasa, String sconfUlt5SoloinCasa,
							   String vittUltime5SoloinTrasfOspite, String sconfUlt5SoloinTraOspite)
	{
		LinkedHashMap<String,Double> mapping = new LinkedHashMap<>();

		//passo1
		Double[] probabilitaImplicita = probabilitaImplicita( Double.valueOf(quotaB1), Double.valueOf(quotaBX), Double.valueOf(quotaB2));
		mapping.put("marketValueB1",probabilitaImplicita[0]);
		mapping.put("marketValueBX",probabilitaImplicita[1]);
		mapping.put("marketValueB2",probabilitaImplicita[2]);
		mapping.put("aggio",probabilitaImplicita[3]);

		//passo2
		Double[] probabilitaPartiteTotali = probabilitaPartiteTotali( Double.valueOf(partiteTotCasa),  Double.valueOf(partiteTotOspite), Double.valueOf(vittorieTotaliCasa),
															Double.valueOf(sconfitteTotaliCasa),Double.valueOf(vittorieTotaliOspite), Double.valueOf(sconfitteTotaliOspite));
		mapping.put("percPartiteTot1",probabilitaPartiteTotali[0]);
		mapping.put("percPartiteTotX",probabilitaPartiteTotali[1]);
		mapping.put("percPartiteTot2",probabilitaPartiteTotali[2]);

		//passo3
		Double[] probabilitaUltime5 = probabilitaUltime5( Double.valueOf(vittUltime5TotCasa), Double.valueOf(sconfUltime5Casa),
														  Double.valueOf(vittUltime5inTrasfOspite),Double.valueOf(sconfitteUltime5Ospite));
		mapping.put("percUltime51",probabilitaUltime5[0]);
		mapping.put("percUltime5X",probabilitaUltime5[1]);
		mapping.put("percUltime52",probabilitaUltime5[2]);

		//passo4
		Double[] percentualiSoloGareCasaTrasferta = percentualiSoloGareCasaTrasferta(Double.valueOf(totatliPartiteCasaInCasa),Double.valueOf(totaliPartiteOspiteInTrasferta),
				 														             Double.valueOf(vittCasaTot),  Double.valueOf(sconfTrasTot),
																				     Double.valueOf(vittTrasTot),  Double.valueOf(sconfCasaTot));
		mapping.put("percgarecatra1",percentualiSoloGareCasaTrasferta[0]);
		mapping.put("percgarecatraX",percentualiSoloGareCasaTrasferta[1]);
		mapping.put("percgarecatra2",percentualiSoloGareCasaTrasferta[2]);

		//passo5
		Double[] percentualiUltime5InCasaTrasferta = percentualiUltime5InCasaTrasferta( Double.valueOf(vittUltime5SoloinCasa),  Double.valueOf(sconfUlt5SoloinCasa),
																				     Double.valueOf(vittUltime5SoloinTrasfOspite),  Double.valueOf(sconfUlt5SoloinTraOspite));
		mapping.put("percultime5catra1",percentualiUltime5InCasaTrasferta[0]);
		mapping.put("percultime5catraX",percentualiUltime5InCasaTrasferta[1]);
		mapping.put("percultime5catra2",percentualiUltime5InCasaTrasferta[2]);

		//passo6
		Double[] percentualiReali =  percentualiReali(mapping.get("percPartiteTot1"), mapping.get("percUltime51"), mapping.get("percgarecatra1"),
													  mapping.get("percultime5catra1"),mapping.get("percPartiteTot2"),mapping.get("percUltime52"),
												      mapping.get("percgarecatra2"), mapping.get("percultime5catra2"),mapping.get("percPartiteTotX"),
												      mapping.get("percUltime5X"), mapping.get("percgarecatraX"), mapping.get("percultime5catraX"));
		mapping.put("fairProbability1",percentualiReali[0]);
		mapping.put("fairProbabilityX",percentualiReali[1]);
		mapping.put("fairProbability2",percentualiReali[2]);

		//passo7
		Double[] quoteReali = quoteReali(mapping.get("fairProbability1"),mapping.get("fairProbabilityX"),mapping.get("fairProbability2"));
		mapping.put("fairvalueR1",quoteReali[0]);
		mapping.put("fairvalueRX",quoteReali[1]);
		mapping.put("fairvalueR2",quoteReali[2]);

		//passo8
		Double tipologiaDiPartita = tipologiaDiPartita(Double.valueOf(quotaB1), Double.valueOf(quotaBX), Double.valueOf(quotaB2),
												  	   mapping.get("fairvalueR1"),mapping.get("fairvalueRX"),mapping.get("fairvalueR2"));
		mapping.put("tipologiaDiPartita",tipologiaDiPartita);

		//passo9
		Double[] valueBet = valueBet(mapping.get("marketValueB1"),mapping.get("marketValueBX"),mapping.get("marketValueB2"),
				mapping.get("fairProbability1"),mapping.get("fairProbabilityX"), mapping.get("fairProbability2"));
		mapping.put("vb1",valueBet[0]);
		mapping.put("vbX",valueBet[1]);
		mapping.put("vb2",valueBet[2]);
		mapping.put("vbtotal",valueBet[3]);


		return mapping;
	}

	private Double[] probabilitaImplicita(Double quotaB1,Double quotaBX,Double quotaB2)
	{// PASSO 1
		
		Double marketValueB1 = 100 / quotaB1;
		Double marketValueBX = 100 / quotaBX;
		Double marketValueB2 = 100 / quotaB2;
		Double aggio = (marketValueB1 + marketValueB2 + marketValueBX) - 100;

		Double [] result = new Double [4];
		result[0]=marketValueB1;
		result[1]=marketValueBX;
		result[2]=marketValueB2;
		result[3]=aggio;

		return result;
	}

	private Double[] probabilitaPartiteTotali(Double  partiteTotCasa, Double partiteTotOspite,
											  Double vittorieTotaliCasa, Double sconfitteTotaliCasa,
											  Double vittorieTotaliOspite, Double sconfitteTotaliOspite)
	{// PASSO 2
		Double partitetotali =  partiteTotCasa + partiteTotOspite;
		Double percPartiteTot1 = ((vittorieTotaliCasa + sconfitteTotaliOspite) / partitetotali)*100;
		Double percPartiteTot2 = ((vittorieTotaliOspite + sconfitteTotaliCasa) / partitetotali)*100;
		Double percPartiteTotX = 100 - (percPartiteTot1 + percPartiteTot2);

		Double [] result = new Double [3];
		result[0]= percPartiteTot1;
		result[1]= percPartiteTotX;
		result[2]= percPartiteTot2;

		return result;

	}

	public Double[] probabilitaUltime5(Double vittUltime5TotCasa,Double sconfUltime5Casa,
									   Double vittUltime5inTrasfOspite, Double sconfitteUltime5Ospite)
	{// PASSO 3
		double partitetotali = ULTIME_5 * 2;
		Double percUltime51 = ((vittUltime5TotCasa + sconfitteUltime5Ospite) / partitetotali)*100;
		Double percUltime52 = ((vittUltime5inTrasfOspite + sconfUltime5Casa) / partitetotali)*100;
		Double percUltime5X = 100 - (percUltime51 + percUltime52);

		Double [] result = new Double [3];
		result[0]= percUltime51;
		result[1]= percUltime5X;
		result[2]= percUltime52;

		return result;
	}

	public Double[] percentualiSoloGareCasaTrasferta(Double totatliPartiteCasaInCasa, Double totaliPartiteOspiteInTrasferta,
													 Double vittCasaTot, Double sconfTrasTot,
													 Double vittTrasTot, Double sconfCasaTot)
	{// PASSO 4
		Double partitetotali = totatliPartiteCasaInCasa + totaliPartiteOspiteInTrasferta;
		Double percgarecatra1 = ((vittCasaTot + sconfTrasTot) / partitetotali)*100;
		Double percgarecatra2 = ((vittTrasTot + sconfCasaTot) / partitetotali)*100;
		Double percgarecatraX = 100 - (percgarecatra1 + percgarecatra2);

		Double [] result = new Double [3];
		result[0]= percgarecatra1;
		result[1]= percgarecatraX;
		result[2]= percgarecatra2;

		return result;
	}

	public Double[] percentualiUltime5InCasaTrasferta(Double vittUltime5SoloinCasa, Double sconfUlt5SoloinCasa,
													  Double vittUltime5SoloinTrasfOspite, Double sconfUlt5SoloinTraOspite)
	{// PASSO 5
		int partitetotali = ULTIME_5 * 2;
		Double percultime5catra1 = ((vittUltime5SoloinCasa + sconfUlt5SoloinTraOspite) / partitetotali) * 100;
		Double percultime5catra2 = ((vittUltime5SoloinTrasfOspite + sconfUlt5SoloinCasa) / partitetotali) * 100;
		Double percultime5catraX = 100 - (percultime5catra1 + percultime5catra2);

		Double [] result = new Double [3];
		result[0]= percultime5catra1;
		result[1]= percultime5catraX;
		result[2]= percultime5catra2;

		return result;
	}

	public Double[] percentualiReali(Double percPartiteTot1,Double percUltime51,Double percgarecatra1,Double percultime5catra1,
									 Double percPartiteTot2,Double percUltime52,Double percgarecatra2,Double percultime5catra2,
									 Double percPartiteTotX,Double percUltime5X,Double percgarecatraX,Double percultime5catraX)
	{// PASSO 6

		Double fairProbability1 = (percPartiteTot1 + percUltime51 +percgarecatra1 +percultime5catra1) / 4.0;
		Double fairProbability2 = (percPartiteTot2 + percUltime52 + percgarecatra2 +percultime5catra2) / 4.0;
		Double fairProbabilityX = (percPartiteTotX + percUltime5X + percgarecatraX +percultime5catraX) / 4.0;

		Double [] result = new Double [3];
		result[0]= fairProbability1;
		result[1]= fairProbabilityX;
		result[2]= fairProbability2;

		return result;

	}

	public Double[] quoteReali(Double fairProbability1,Double fairProbabilityX,Double fairProbability2)
	{// PASSO 7
		Double fairvalueR1 = 100 / fairProbability1;
		Double fairvalueR2 = 100 / fairProbability2;
		Double fairvalueRX = 100 / fairProbabilityX;

		Double [] result = new Double [3];
		result[0]= fairvalueR1;
		result[1]= fairvalueRX;
		result[2]= fairvalueR2;

		return result;

	}

	public Double tipologiaDiPartita(Double quotaB1,Double quotaBX,Double quotaB2,Double fairvalueR1,Double fairvalueRX,Double fairvalueR2)
	{// PASSO 8
		double differenzaquota1 = fairvalueR1 - quotaB1;
		double differenzaquota2 = fairvalueR2 - quotaB2;
		double differenzaquotaX = fairvalueRX - quotaBX;

		double percquota1 = Math.abs((differenzaquota1 / fairvalueR1) * 100);
		double percquota2 = Math.abs((differenzaquota2 / fairvalueR2) * 100);
		double percquotaX = Math.abs((differenzaquotaX / fairvalueRX) * 100);
		double totalediff = percquota1 + percquota2 + percquotaX;

		Double result = 0.0;

		if (totalediff <= 17) {

			result =1.0;
			if ((fairvalueR1 < fairvalueRX && fairvalueRX < fairvalueR2)
					|| (fairvalueR2 < fairvalueRX && fairvalueRX < fairvalueR1)) {
				result = 1.1;
			}
			if (((fairvalueR1 < fairvalueRX && fairvalueR1 < fairvalueR2) && fairvalueRX > fairvalueR2)
					|| ((fairvalueR1 < fairvalueRX && fairvalueR1 > fairvalueR2) && fairvalueRX > fairvalueR2)) {
				result = 1.2;

			} else {
				System.out.println("NIENTE");
			}
		}
		if (totalediff > 17 && totalediff < 30) {
			result = 2.0;
		}

		if (totalediff >= 30) {
			result =3.0;
		}

		return result;

	}

	public Double[] valueBet(Double marketValueB1, Double marketValueBX,Double marketValueB2,
						   Double fairProbability1, Double fairProbabilityX, Double fairProbability2)
	{// PASSO 9

		Double vb1 = marketValueB1 - fairProbability1;
		Double vb2 = marketValueB2 - fairProbability2;
		Double vbX = marketValueBX - fairProbabilityX;
		Double vbtotal = vb1 + vb2 + vbX;

		Double[] result = new Double[4];
		result[0]= vb1;
		result[1]= vbX;
		result[2]= vb2;
		result[3]= vbtotal;

		return result;

	}

	public void expectedValue() {// PASSO 10
//		double possibileVincita=%vincinta sulla quota scelta X valoreQuotaBM su cui scommetto
//		double possibilePerdita=somma %delle altre due quote che non gioco X investimento
//		double ev=investimento+(possibileVincita - possibilePerdita);
	}

	public void formulaPoisson(){//PASSO 11


		/* utilizzerò la formula di Poisson se nel passo

		 per usare la formula di Poisson servono:
		- squadra casa: gol fatti(gfC), gol subiti(gsC),numero partite giocate(tpC) -- nel campionato in esame o in generale? da testare per capire con quale è più precisa la formula
		- squadra ospite: gol fatti(gfT), gol subiti(gsT), numero partite giocate(tpT)
		poi f(x)= (λ^x∙e^-λ)/x!
		Data A = squadra casa, B = squadra trasferta
		A=[(gfC/tpC)+(gsT/tpT)]/2
		B=[(gsC/tpC)+(gfT/tpT)]/2
		e=A+B
		x= numero di gol per cui estrarre la precentuale, ad esempio fino al 6 gol squadra Casa/Trasferta, quindi
		fino al risultato di 6-6
		Per A=λ la formula di Poisson diventa
		x=0 ,x!=1, f(0) = (A^x∙e^-A)/x!
		.....
		x=6, x!=720, f(6)=(A^x∙e^-A)/x!

		Invece per B=λ avremo:
		x=0 ,x!=1, f(0) = (B^x∙e^-B)/x!
		.....
		x=6, x!=720, f(6)=(B^x∙e^-B)/x!

		Quindi la probabilità che il risultato sia ad esempio di 0-0 è dato da:
		[fA(0)+fB(0)]*100

		 */



	}

}