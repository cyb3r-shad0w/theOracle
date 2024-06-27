package theOracle.old;


public class CalcolatoreQuoteReali {

	final static int ULTIME_5 = 5;
	double quotaB1, quotaB2, quotaBX;// quote bookmaker
	double marketValueB1, marketValueB2, marketValueBX;// percentuali bookmaker
	double fairProbability1, fairProbability2, fairProbabilityX;// percentuali reali
	double fairvalueR1, fairvalueR2, fairvalueRX;// quote reali
	double aggio;// allibramento bookmaker
	double vittorieTotaliCasa, vittorieTotaliOspite;// sono le vittorie totali in campionato
	double sconfitteTotaliCasa, sconfitteTotaliOspite;// sono le sconfitte totali in campionato
	double vittUltime5inCasa, vittUltime5inTrasfOspite;// vittorie ultime 5 partite in casa per l'ospitante e le ultime 5
													// vitt in trasferta dell'ospite
	double sconfUlt5inCasa, sconfUlt5inTraOspite;// sconfitte ultime 5 partite in casa ospitante e sconfitte ultime 5
												// trasferte ospite
	double vittUltime5TotCasa, vittUltime5TotOspite;// vittorie negli ultimi 5 match
	double sconfUltime5Casa, sconfitteUltime5Ospite;// sconfitte negli utlimi 5 match
	double vittCasaTot, vittTrasTot;// vittorie solo in casa squadra ospitante e vitt solo in trasferta squadra
									// ospite
	double sconfCasaTot, sconfTrasTot;// sconfitte solo casa e solo in trasferta
	double partiteTotCasa, partiteTotOspite;// partite totali in campionato

	double percPartiteTot1, percPartiteTot2, percPartiteTotX;
	double percUltime51, percUltime52, percUltime5X;// percentuali Ultime 5 partite disputate
	double totatliPartiteCasaInCasa, totaliPartiteOspiteInTrasferta;
	double percgarecatra1, percgarecatra2, percgarecatraX;// percentuali gare solo in casa e trasferta
	double percultime5catra1, percultime5catra2, percultime5catraX;
	double valueBet1, valueBet2, valueBetX;
	double investimento;

	public CalcolatoreQuoteReali() {
	}

	public void probabilitaImplicita() {// PASSO 1
		marketValueB1 = 100 / quotaB1;
		marketValueB2 = 100 / quotaB2;
		marketValueBX = 100 / quotaBX;
		aggio = (marketValueB1 + marketValueB2 + marketValueBX) - 100;
		System.out.println("Il valore delle PERCENTUALI dato dal BOOKMAKER è:");
		System.out.println("La probabilità per la squadra di CASA di vincere secondo i BM è: " + marketValueB1+"%");
		System.out.println("La probabilità per la squadra in TRASFERTA di vincere secondo i BM è: " + marketValueB2+"%");
		System.out.println("La probabilità di PAREGGIARE secondo i BM è: " + marketValueBX+"%");
		System.out.println("L'allibramento sulla seguente partita è del " + aggio+"%");
	}

	public void probabilitaPartiteTotali() {// PASSO 2
		double partitetotali = partiteTotCasa + partiteTotOspite;
		percPartiteTot1 = ((vittorieTotaliCasa + sconfitteTotaliOspite) / partitetotali)*100;
		percPartiteTot2 = ((vittorieTotaliOspite + sconfitteTotaliCasa) / partitetotali)*100;
		percPartiteTotX = 100 - (percPartiteTot1 + percPartiteTot2);
		System.out.println("Il valore dato dal calcolo delle PARTITE TOTALI è:");
		System.out.println("La probabilità per la squadra di CASA è: " + percPartiteTot1+"%");
		System.out.println("La probabilità per la squadra in TRASFERTA è: " + percPartiteTot2+"%");
		System.out.println("La probabilità di PAREGGIARE è: " + percPartiteTotX+"%");
	}

	public void probabilitaUltime5() {// PASSO 3
		double partitetotali = ULTIME_5 * 2;
		percUltime51 = ((vittUltime5TotCasa + sconfitteUltime5Ospite) / partitetotali)*100;
		percUltime52 = ((vittUltime5inTrasfOspite + sconfUltime5Casa) / partitetotali)*100;
		percUltime5X = 100 - (percUltime51 + percUltime52);
		System.out.println("Il valore dato dal calcolo delle ULTIME 5 PARTITE è:");
		System.out.println("La probabilità per la squadra di CASA è: " + percUltime51+"%");
		System.out.println("La probabilità per la squadra in TRASFERTA è: " + percUltime52+"%");
		System.out.println("La probabilità di PAREGGIARE è: " + percUltime5X+"%");
	}

	public void percentualiSoloGareCasaTrasferta() {// PASSO 4
		double partitetotali = totatliPartiteCasaInCasa + totaliPartiteOspiteInTrasferta;
		percgarecatra1 = ((vittCasaTot + sconfTrasTot) / partitetotali)*100;
		percgarecatra2 = ((vittTrasTot + sconfCasaTot) / partitetotali)*100;
		percgarecatraX = 100 - (percgarecatra1 + percgarecatra2);
		System.out.println("Il valore dato dal calcolo delle sole VITTORIE/SCONFITTE IN CASA/TRASFERTA è:");
		System.out.println("La probabilità per la squadra di CASA è: " + percgarecatra1+"%");
		System.out.println("La probabilità per la squadra in TRASFERTA è: " + percgarecatra2+"%");
		System.out.println("La probabilità di PAREGGIARE è: " + percgarecatraX+"%");
	}

	public void percentualiUltime5InCasaTrasferta() {// PASSO 5
		int partitetotali = ULTIME_5 * 2;
		percultime5catra1 = ((vittUltime5inCasa + sconfUlt5inTraOspite) / partitetotali)*100;
		percultime5catra2 = ((vittUltime5inTrasfOspite + sconfUlt5inCasa) / partitetotali)*100;
		percultime5catraX = 100 - (percultime5catra1 + percultime5catra2);
		System.out.println("Il valore dato dal calcolo delle sole ULTIME 5 VITTORIE/SCONFITTE IN CASA/TRASFERTA è:");
		System.out.println("La probabilità per la squadra di CASA è: " + percultime5catra1+"%");
		System.out.println("La probabilità per la squadra in TRASFERTA è: " + percultime5catra2+"%");
		System.out.println("La probabilità di PAREGGIARE è: " + percultime5catraX+"%");
	}

	public void percentualiReali() {// PASSO 6
		fairProbability1 = (percPartiteTot1 + percUltime51 + percgarecatra1 + percultime5catra1) / 4.0;
		fairProbability2 = (percPartiteTot2 + percUltime52 + percgarecatra2 + percultime5catra2) / 4.0;
		fairProbabilityX = (percPartiteTotX + percUltime5X + percgarecatraX + percultime5catraX) / 4.0;
		System.out.println(
				"Il valore delle PERCENTUALI REALI,dato dall'anilisi statistica solo sulle vittorie/sconfitte, è:");
		System.out.println("La probabilità reale per la squadra di CASA di vincere è: " + fairProbability1+"%");
		System.out.println("La probabilità reale per la squadra in TRASFERTA di vincere è: " + fairProbability2+"%");
		System.out.println("La probabilità reale di PAREGGIARE è: " + fairProbabilityX+"%");
	}

	public void quoteReali() {// PASSO 7
		fairvalueR1 = 100 / fairProbability1;
		fairvalueR2 = 100 / fairProbability2;
		fairvalueRX = 100 / fairProbabilityX;
		System.out.println("Le QUOTE REALI risultano quindi essere:");
		System.out.println("Quota CASA : " + fairvalueR1);
		System.out.println("Quota TRASFERTA : " + fairvalueR2);
		System.out.println("Quota PAREGGIO : " + fairvalueRX);
	}

	public void tipologiaDiPartita() {// PASSO 8
		double differenzaquota1 = fairvalueR1 - quotaB1;
		double differenzaquota2 = fairvalueR2 - quotaB2;
		double differenzaquotaX = fairvalueRX - quotaBX;
		System.out.println("Differenza QUOTA REALE CASA - QUOTA BM = " + differenzaquota1);
		System.out.println("Differenza QUOTA REALE TRASFERTA - QUOTA BM = " + differenzaquota2);
		System.out.println("Differenza QUOTA REALE X - QUOTA BM = " + differenzaquotaX);
		double percquota1 = Math.abs((differenzaquota1 / fairvalueR1) * 100);
		double percquota2 = Math.abs((differenzaquota2 / fairvalueR2) * 100);
		double percquotaX = Math.abs((differenzaquotaX / fairvalueRX) * 100);
		double totalediff = percquota1 + percquota2 + percquotaX;
		System.out.println("La % di differenza tra quotaReale1-quotaBM1 = "+percquota1+"%");
		System.out.println("La % di differenza tra quotaReale2-quotaBM2 = "+percquota2+"%");
		System.out.println("La % di differenza tra quotaRealeX-quotaBMX = "+percquotaX+"%");
		if (totalediff <= 17) {
			System.out.println(
					"Analizzando la differenza tra Percentuale Reale e Percentuale BM questa partita risulta essere una PARTITA LINEARE(STATISTICA)!");
			System.out.println(
					"CONSIGLIO: Si possono studiare tutti i tipi di mercato, però se una delle differenze risulta essere negativa bisogna valutare anche una doppia chance");
			System.out.println("INOLTRE: ");
			if ((fairvalueR1 < fairvalueRX && fairvalueRX < fairvalueR2)
					|| (fairvalueR2 < fairvalueRX && fairvalueRX < fairvalueR1)) {
				System.out.println(
						"Poichè  quota1<quotaX<quota2 oppure quota2<quotaX<quota1 POSSO giocare il RISULTATO ESATTO");
			}
			if (((fairvalueR1 < fairvalueRX && fairvalueR1 < fairvalueR2) && fairvalueRX > fairvalueR2)
					|| ((fairvalueR1 < fairvalueRX && fairvalueR1 > fairvalueR2) && fairvalueRX > fairvalueR2)) {
				System.out.println(
						"Poichè  quota1<quota2<quotaX oppure quota2<quota1<quotaX NON POSSO giocare il RISULTATO ESATTO");

			} else {
				System.out.println("NIENTE");
			}
		}
		if (totalediff > 17 && totalediff < 30) {
			System.out.println(
					"Analizzando la differenza tra Percentuale Reale e Percentuale BM questa partita risulta essere una PARTITA NON LINEARE(NON STATISTICA)!");
			System.out.println(
					"CONSIGLIO: Si possono studiare tutti i mercati dei GOL-NOGOL,UNDER-OVER,MULTIGOL dopo uno studio preventivo dell'andamento STATISTICO DEI GOL possibilmente con la Formula di POISSON");
		}

		if (totalediff >= 30) {
			System.out.println(
					"Analizzando la differenza tra Percentuale Reale e Percentuale BM questa partita risulta essere una PARTITA CON UNA FORTE FAVORITA(NON LINEARE CASA/TRASFERTA)!");
			System.out.println("CONSIGLIO: Si possono giocare i mercati dell' 1-X-2, quindi risultato fisso ");
		}

	}

	public void valueBet() {// PASSO 9
		System.out.println("VALUE BET:");
		System.out.println("La VALUE BET è una scommessa in cui le probabilità di ottenere un dato risultato" + "/n"
				+ " è maggiore di quanto mostrino le quote offerte.");
		double vb1 = marketValueB1 - fairProbability1;
		double vb2 = marketValueB2 - fairProbability2;
		double vbX = marketValueBX - fairProbabilityX;
		double vbtotal = vb1 + vb2 + vbX;
		System.out.println("ValueBet 1= " + vb1+"%");
		System.out.println("ValueBet 2= " + vb2+"%");
		System.out.println("ValueBet X= " + vbX+"%");
		System.out.println("La valueBet con valore POSITIVO ci mostra una papabile quota su cui giocare");
		System.out.println(
				"La ValueBet con maggior valore NEGATIVO ci mostra dove il BM sta cercando di fare più guadagno e quindi l'evento che molto probabilmente NON si verificherà!");
		System.out.println("ValueBet Totale= " + vbtotal+"%"+ " = Allibramento = " + aggio+"%");

	}

	public void expectedValue() {// PASSO 10
		System.out.println("EXPECTED VALUE:");
		System.out.println("Il VALORE ATTESO(EXPECTED VALUE) viene usato per valutare quale opzione scegliere" + "/n"
				+ "affinchè possano essere massimizzati i profitti e minimizzate le perdite." + "/n"
				+ "Può essere positivo o negativo consentendo così di determinare quale investimento è proficuo e quale no");
//		double possibileVincita=%vincinta sulla quota scelta X valoreQuotaBM su cui scommetto
//		double possibilePerdita=somma %delle altre due quote che non gioco X investimento
//		double ev=investimento+(possibileVincita - possibilePerdita);
//		System.out.println("EXPECTED VALUE:" + ev);
	}

	public double getQuotaB1() {
		return quotaB1;
	}

	public void setQuotaB1(double quotaB1) {
		this.quotaB1 = quotaB1;
	}

	public double getQuotaB2() {
		return quotaB2;
	}

	public void setQuotaB2(double quotaB2) {
		this.quotaB2 = quotaB2;
	}

	public double getQuotaBX() {
		return quotaBX;
	}

	public void setQuotaBX(double quotaBX) {
		this.quotaBX = quotaBX;
	}

	public double getVittorieTotaliCasa() {
		return vittorieTotaliCasa;
	}

	public void setVittorieTotaliCasa(double vittorieTotaliCasa) {
		this.vittorieTotaliCasa = vittorieTotaliCasa;
	}

	public double getVittorieTotaliOspite() {
		return vittorieTotaliOspite;
	}

	public void setVittorieTotaliOspite(double vittorieTotaliOspite) {
		this.vittorieTotaliOspite = vittorieTotaliOspite;
	}

	public double getSconfitteTotaliCasa() {
		return sconfitteTotaliCasa;
	}

	public void setSconfitteTotaliCasa(double sconfitteTotaliCasa) {
		this.sconfitteTotaliCasa = sconfitteTotaliCasa;
	}

	public double getSconfitteTotaliOspite() {
		return sconfitteTotaliOspite;
	}

	public void setSconfitteTotaliOspite(double sconfitteTotaliOspite) {
		this.sconfitteTotaliOspite = sconfitteTotaliOspite;
	}

	public double getVittUltime5inCasa() {
		return vittUltime5inCasa;
	}

	public void setVittUltime5inCasa(double vittUltime5inCasa) {
		this.vittUltime5inCasa = vittUltime5inCasa;
	}

	public double getVittUltime5inTrasfOspite() {
		return vittUltime5inTrasfOspite;
	}

	public void setVittUltime5inTrasfOspite(double vittUltime5inTrasfOspite) {
		this.vittUltime5inTrasfOspite = vittUltime5inTrasfOspite;
	}

	public double getSconfUlt5inCasa() {
		return sconfUlt5inCasa;
	}

	public void setSconfUlt5inCasa(double sconfUlt5inCasa) {
		this.sconfUlt5inCasa = sconfUlt5inCasa;
	}

	public double getSconfUlt5inTraOspite() {
		return sconfUlt5inTraOspite;
	}

	public void setSconfUlt5inTraOspite(double sconfUlt5inTraOspite) {
		this.sconfUlt5inTraOspite = sconfUlt5inTraOspite;
	}

	public double getVittUltime5TotCasa() {
		return vittUltime5TotCasa;
	}

	public void setVittUltime5TotCasa(double vittUltime5TotCasa) {
		this.vittUltime5TotCasa = vittUltime5TotCasa;
	}

	public double getVittUltime5TotOspite() {
		return vittUltime5TotOspite;
	}

	public void setVittUltime5TotOspite(double vittUltime5TotOspite) {
		this.vittUltime5TotOspite = vittUltime5TotOspite;
	}

	public double getSconfUltime5Casa() {
		return sconfUltime5Casa;
	}

	public void setSconfUltime5Casa(double sconfUltime5Casa) {
		this.sconfUltime5Casa = sconfUltime5Casa;
	}

	public double getSconfitteUltime5Ospite() {
		return sconfitteUltime5Ospite;
	}

	public void setSconfitteUltime5Ospite(double sconfitteUltime5Ospite) {
		this.sconfitteUltime5Ospite = sconfitteUltime5Ospite;
	}

	public double getVittCasaTot() {
		return vittCasaTot;
	}

	public void setVittCasaTot(double vittCasaTot) {
		this.vittCasaTot = vittCasaTot;
	}

	public double getVittTrasTot() {
		return vittTrasTot;
	}

	public void setVittTrasTot(double vittTrasTot) {
		this.vittTrasTot = vittTrasTot;
	}

	public double getSconfCasaTot() {
		return sconfCasaTot;
	}

	public void setSconfCasaTot(double sconfCasaTot) {
		this.sconfCasaTot = sconfCasaTot;
	}

	public double getSconfTrasTot() {
		return sconfTrasTot;
	}

	public void setSconfTrasTot(double sconfTrasTot) {
		this.sconfTrasTot = sconfTrasTot;
	}

	public double getPartiteTotCasa() {
		return partiteTotCasa;
	}

	public void setPartiteTotCasa(double partiteTotCasa) {
		this.partiteTotCasa = partiteTotCasa;
	}

	public double getPartiteTotOspite() {
		return partiteTotOspite;
	}

	public void setPartiteTotOspite(double partiteTotOspite) {
		this.partiteTotOspite = partiteTotOspite;
	}

	public double getTotatliPartiteCasaInCasa() {
		return totatliPartiteCasaInCasa;
	}

	public void setTotatliPartiteCasaInCasa(double totatliPartiteCasaInCasa) {
		this.totatliPartiteCasaInCasa = totatliPartiteCasaInCasa;
	}

	public double getTotaliPartiteOspiteInTrasferta() {
		return totaliPartiteOspiteInTrasferta;
	}

	public void setTotaliPartiteOspiteInTrasferta(double totaliPartiteOspiteInTrasferta) {
		this.totaliPartiteOspiteInTrasferta = totaliPartiteOspiteInTrasferta;
	}

}
