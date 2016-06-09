package net.home.webclient;

import net.home.handler.KontoHandler;
import net.home.handler.TransaktionHandler;

public class ExpenseClient {

    public static void main(String[] args) {
	System.out.println("get data via rest");
	
	KontoHandler kHandler = new KontoHandler();
	kHandler.getKontos("test", "test");
	
	TransaktionHandler tHandler = new TransaktionHandler();
	tHandler.getAllTransaktions("test", "test");

    }

}
