package net.home.handler;

import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import konto.data.model.Transaktion;

public class TransaktionHandler {
    
    public void getAllTransaktions(String user, String pass) {
	try {
	    String url = "http://localhost:8081/ExpenseWebservice/transaktionlist/all/user/" + user + "/pass/" + pass;
	    
	    Client client = Client.create();
	    WebResource resource = client.resource(url);
	    ClientResponse response = resource.accept("application/json").get(ClientResponse.class);
	    if (response.getStatus() != 200) {
		throw new RuntimeException("Failed: HTTP ERROR : " + response.getStatus());
	    }
	    
	    String input = response.getEntity(String.class);
	    JSONObject inObj = new JSONObject(input);
	    ArrayList<Transaktion> all = buildTransaktions(inObj.getJSONArray("transaktion"));
	    System.out.println("found <" + all.size() + "> transaktions");
	    
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void getLast10Transaktions(String user, String pass) {
	try {
	    String url = "http://localhost:8081/ExpenseWebservice/transaktionlist/last10/user/" + user + "/pass/" + pass;
	    
	    Client client = Client.create();
	    WebResource resource = client.resource(url);
	    ClientResponse response = resource.accept("application/json").get(ClientResponse.class);
	    if (response.getStatus() != 200) {
		throw new RuntimeException("Failed: HTTP ERROR : " + response.getStatus());
	    }
	    
	    String input = response.getEntity(String.class);
	    JSONObject inObj = new JSONObject(input);
	    ArrayList<Transaktion> all = buildTransaktions(inObj.getJSONArray("transaktion"));
	    System.out.println("found <" + all.size() + "> transaktions");
	    
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void addTransaktion(Transaktion transaktion) {
	try {
	    String url = "http://localhost:8081/ExpenseWebservice/transaktionhandler/addtransaktion";
	    
	    Client client = Client.create();
	    WebResource resource = client.resource(url);
	    System.out.println(transaktion.toString());
	    ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, new JSONObject(transaktion).toString());
	    
	    if (response.getStatus() != 200) {
		throw new RuntimeException("Failed: HTTP ERROR : " + response.getStatus() + "detail: " + response.toString());
	    }
	    
	    String input = response.getEntity(String.class);
	    System.out.println(input);
	    
	    // to some magic 
	    int index = input.lastIndexOf("=");
	    transaktion.setTransaktionsId(Integer.parseInt(input.substring(index + 1)));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void updateTransaktion(Transaktion transaktion) {
	try {
	    String url = "http://localhost:8081/ExpenseWebservice/transaktionhandler/updatetransaktion";
	    
	    Client client = Client.create();
	    WebResource resource = client.resource(url);
	    System.out.println(transaktion.toString());
	    ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, new JSONObject(transaktion).toString());
	    
	    if (response.getStatus() != 200) {
		throw new RuntimeException("Failed: HTTP ERROR : " + response.getStatus() + "detail: " + response.toString());
	    }
	    
	    String input = response.getEntity(String.class);
	    System.out.println(input);
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void deleteTransaktion(Transaktion transaktion) {
	try {
	    String url = "http://localhost:8081/ExpenseWebservice/transaktionhandler/updatetransaktion";
	    
	    Client client = Client.create();
	    WebResource resource = client.resource(url);
	    System.out.println(transaktion.toString());
	    ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, new JSONObject(transaktion).toString());
	    
	    if (response.getStatus() != 200) {
		throw new RuntimeException("Failed: HTTP ERROR : " + response.getStatus() + "detail: " + response.toString());
	    }
	    
	    String input = response.getEntity(String.class);
	    System.out.println(input);
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    
    private ArrayList<Transaktion> buildTransaktions(JSONArray array) {
	ArrayList<Transaktion> transaktionList = new ArrayList<Transaktion>();
	try {
	    for(int i = 0 ; i < array.length() ; i++) {
		JSONObject elem = array.getJSONObject(i);
		
		ObjectMapper mapper = new ObjectMapper();
		Transaktion transaktion = mapper.readValue(elem.toString(), Transaktion.class);
		
		transaktionList.add(transaktion);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return transaktionList;
    }

}
