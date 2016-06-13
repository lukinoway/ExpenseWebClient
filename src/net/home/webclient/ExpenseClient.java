package net.home.webclient;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import konto.data.model.Konto;
import konto.data.model.Transaktion;
import net.home.handler.KontoHandler;
import net.home.handler.TransaktionHandler;

public class ExpenseClient {

    public static void main(String[] args) {
	System.out.println("get data via rest");
	
	KontoHandler kHandler = new KontoHandler();
	kHandler.getKontos("test", "test");
	
	TransaktionHandler tHandler = new TransaktionHandler();
	tHandler.getAllTransaktions("test", "test");
	
	try {
	    Transaktion transaktion = new Transaktion(new Date(), 20.0, "webservice", 5, 1);
	    tHandler.addTransaktion(transaktion);
	    
	    Konto konto = new Konto("1234567890", "webserviceKonto", 1, true, "","");
	    kHandler.addKonto(konto);
	} catch (NoSuchAlgorithmException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
