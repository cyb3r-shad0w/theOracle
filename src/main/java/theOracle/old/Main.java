package theOracle.old;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		CalcolatoreQuoteReali cqr=new CalcolatoreQuoteReali();
		Scanner sc=new Scanner(System.in);
		System.out.println("Benvenuto nell'Applicazione usata per calcolare il Picchetto tecnico, la Value Bet e la Expected Value delle Partite");
		System.out.println("-----------------PASSO 1/10----------------");
		System.out.println("Inserisci le Quote Del BookMaker:");
		System.out.println("Quota 1 :");
		cqr.setQuotaB1(sc.nextDouble());
		System.out.println("Quota 2");
		cqr.setQuotaB2(sc.nextDouble());
		System.out.println("Quota X");
		cqr.setQuotaBX(sc.nextDouble());
		System.out.println("Calcolo le probabilità date dal BookMaker:");
		cqr.probabilitaImplicita();
		System.out.println("-----------------PASSO 2/10----------------");
		System.out.println("Inserisci le VITTORIE TOTALI fatte in campionato dalla squadra di CASA:");
		cqr.setVittorieTotaliCasa(sc.nextDouble());
		System.out.println("Inserisci le SCONFITTE TOTALI fatte in campionato dalla squadra OSPITE");
		cqr.setSconfitteTotaliOspite(sc.nextDouble());
		System.out.println("Inserisci le VITTORIE TOTALI fatte in campionato dalla squadra OSPITE:");
		cqr.setVittorieTotaliOspite(sc.nextDouble());
		System.out.println("Inserisci le SCONFITTE TOTALI fatte in campionato dalla squadra di CASA");
		cqr.setSconfitteTotaliCasa(sc.nextDouble());
		System.out.println("Inserisci il numero di PARTITE TOTALI in campionato(e volendo anche quelle fuori dal campionato) giocate dalla squadra in CASA:");
		cqr.setPartiteTotCasa(sc.nextDouble());
		System.out.println("Inserisci il numero di PARTITE TOTALI in campionato(e volendo anche quelle fuori dal campionato) giocate dalla squadra OSPITE:");
		cqr.setPartiteTotOspite(sc.nextDouble());
		System.out.println("Calcolo il valore delle percentuali sulle PARTITE TOTALI:");
		cqr.probabilitaPartiteTotali();
		System.out.println("-----------------PASSO 3/10----------------");
		System.out.println("Inserisci le VITTORIE NELLE ULTIME 5 PARTITE fatte in campionato dalla squadra di CASA:");
		cqr.setVittUltime5TotCasa(sc.nextDouble());
		System.out.println("Inserisci le SCONFITTE NELLE ULTIME 5 PARTITE fatte in campionato dalla squadra OSPITE:");
		cqr.setSconfitteUltime5Ospite(sc.nextDouble());
		System.out.println("Inserisci le VITTORIE NELLE ULTIME 5 PARTITE fatte in campionato dalla squadra OSPITE:");
		cqr.setVittUltime5inTrasfOspite(sc.nextDouble());
		System.out.println("Inserisci le SCONFITTE NELLE ULTIME 5 PARTITE fatte in campionato dalla squadra di CASA:");
		cqr.setSconfUltime5Casa(sc.nextDouble());
		System.out.println("Calcolo il valore delle percentuali sulle ULTIME 5 PARTITE:");
		cqr.probabilitaUltime5();
		System.out.println("-----------------PASSO 4/10----------------");
		System.out.println("Inserisci le VITTORIE TOTALI fatte in CASA nel campionato dalla squadra di CASA:");
		cqr.setVittCasaTot(sc.nextDouble());
		System.out.println("Inserisci le SCONFITTE TOTALI fatte in TRASFERTA nel campionato dalla squadra OSPITE");
		cqr.setSconfTrasTot(sc.nextDouble());
		System.out.println("Inserisci le VITTORIE TOTALI fatte in TRASFERTA nel campionato dalla squadra OSPITE:");
		cqr.setVittTrasTot(sc.nextDouble());
		System.out.println("Inserisci le SCONFITTE TOTALI fatte in CASA nel campionato dalla squadra di CASA");
		cqr.setSconfCasaTot(sc.nextDouble());
		System.out.println("Inserisci il numero di PARTITE TOTALI IN CASA nel campionato(e volendo anche quelle fuori dal campionato) giocate dalla squadra in CASA:");
		cqr.setTotatliPartiteCasaInCasa(sc.nextDouble());
		System.out.println("Inserisci il numero di PARTITE TOTALI IN TRASFERTA nel campionato(e volendo anche quelle fuori dal campionato) giocate dalla squadra OSPITE:");
		cqr.setTotaliPartiteOspiteInTrasferta(sc.nextDouble());
		System.out.println("Calcolo il valore delle percentuali sulle PARTITE TOTALI:");
		cqr.percentualiSoloGareCasaTrasferta();
		System.out.println("-----------------PASSO 5/10----------------");
		System.out.println("Inserisci le VITTORIE NELLE ULTIME 5 PARTITE fatte in CASA nel campionato dalla squadra di CASA:");
		cqr.setVittUltime5inCasa(sc.nextDouble());
		System.out.println("Inserisci le SCONFITTE NELLE ULTIME 5 PARTITE fatte in TRASFERTA nel campionato dalla squadra OSPITE:");
		cqr.setSconfUlt5inTraOspite(sc.nextDouble());
		System.out.println("Inserisci le VITTORIE NELLE ULTIME 5 PARTITE fatte in TRASFERTA nel campionato dalla squadra OSPITE:");
		cqr.setVittUltime5inTrasfOspite(sc.nextDouble());
		System.out.println("Inserisci le SCONFITTE NELLE ULTIME 5 PARTITE fatte in CASA nel campionato dalla squadra di CASA:");
		cqr.setSconfUlt5inCasa(sc.nextDouble());
		System.out.println("Calcolo il valore delle percentuali sulle ULTIME 5 PARTITE:");
		cqr.percentualiUltime5InCasaTrasferta();
		System.out.println("-----------------PASSO 6/10----------------");
		cqr.percentualiReali();
		System.out.println("-----------------PASSO 7/10----------------");
		cqr.quoteReali();
		System.out.println("-----------------PASSO 8/10----------------");
		cqr.tipologiaDiPartita();
		System.out.println("-----------------PASSO 9/10----------------");
		cqr.valueBet();
		System.out.println("-----------------PASSO 10/10----------------");
		System.out.println("Calcola l'EXPECTED VALUE!");
		System.out.println("-----------------COMING SOON---------------");
		
		sc.close();
	}
}
