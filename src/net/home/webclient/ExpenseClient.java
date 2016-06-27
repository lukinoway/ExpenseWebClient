package net.home.webclient;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import konto.data.model.Category;
import konto.data.model.Konto;
import konto.data.model.Transaktion;
import net.home.handler.CategoryHandler;
import net.home.handler.KontoHandler;
import net.home.handler.TransaktionHandler;

public class ExpenseClient {

    public static void main(String[] args) {
	System.out.println("get data via rest");
	
	KontoHandler kHandler = new KontoHandler();
	kHandler.getKontos("test", "test");
	
	TransaktionHandler tHandler = new TransaktionHandler();
	tHandler.getAllTransaktions("test", "test");
	
	CategoryHandler cHandler = new CategoryHandler();
	
	try {
	    // Transaktion Tests
	    Transaktion transaktion = new Transaktion(new Date().getTime(), 20.0, "webservice", 5, 1);
	    tHandler.addTransaktion(transaktion);
	    transaktion.setTransaktionsBetrag(40.0);
	    tHandler.updateTransaktion(transaktion);
	    tHandler.deleteTransaktion(transaktion);
	    
	    // Konto Tests
	    Konto konto = new Konto("1234567890", "webserviceKonto", 1, true, "","");
	    kHandler.addKonto(konto);
	    konto.setKontoName("updated Name");
	    kHandler.updateKonto(konto);
	    kHandler.deleteKonto(konto);
	    
	    // category test
	    Category category = new Category("rest category");
	    cHandler.addCategory(category);
	    category.setTypeText("updated Text");
	    cHandler.updateCategory(category);
	    //cHandler.deleteCategory(category);
	    
	} catch (NoSuchAlgorithmException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
