package net.home.handler;

import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import konto.data.model.Konto;

public class KontoHandler {
    
/**
 * get all Kontos for User
 * @param user
 * @param pass
 */
    public void getKontos(String user, String pass) {
	try {
	    Client client = Client.create();
	    WebResource resource = client.resource("http://localhost:8081/ExpenseWebservice/konto/user/" + user + "/pass/" + pass);
	    ClientResponse response = resource.accept("application/json").get(ClientResponse.class);
	    if (response.getStatus() != 200) {
		throw new RuntimeException("Failed: HTTP ERROR : " + response.getStatus());
	    }
	    
	    String input = response.getEntity(String.class);
	    JSONObject inObj = new JSONObject(input);
	    JSONArray kontos = inObj.getJSONArray("konto");
	    for (int i = 0 ; i < kontos.length(); i++) {
		JSONObject jsonobject = kontos.getJSONObject(i);
		String kontoNr = jsonobject.getString("kontoNr");
		String kontoName = jsonobject.getString("kontoName");
		boolean visible = jsonobject.getBoolean("visible");
		int kontoId = jsonobject.getInt("kontoId");
		int userId = jsonobject.getInt("userId");
		
		System.out.println(LocalDateTime.now() + " - found konto: Name=" + kontoName + "; NR=" + kontoNr );
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void addKonto(Konto konto) {
	try {
	    String url = "http://localhost:8081/ExpenseWebservice/kontohandler/addkonto";

	    
	    Client client = Client.create();
	    WebResource resource = client.resource(url);
	    ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, new JSONObject(konto).toString());
	    
	    if (response.getStatus() != 200) {
		throw new RuntimeException("Failed: HTTP ERROR : " + response.getStatus() + "detail: " + response.toString());
	    }
	    
	    String input = response.getEntity(String.class);
	    System.out.println(input);
	    
	    // to some magic 
	    int index = input.lastIndexOf("=");
	    konto.setKontoId(Integer.parseInt(input.substring(index + 1)));
	    
	} catch(Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void updateKonto(Konto konto) {
	try {
	    String url = "http://localhost:8081/ExpenseWebservice/kontohandler/updatekonto";

	    
	    Client client = Client.create();
	    WebResource resource = client.resource(url);
	    ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, new JSONObject(konto).toString());
	    
	    if (response.getStatus() != 200) {
		throw new RuntimeException("Failed: HTTP ERROR : " + response.getStatus() + "detail: " + response.toString());
	    }
	    
	    String input = response.getEntity(String.class);
	    System.out.println(input);
	    
	} catch(Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void deleteKonto(Konto konto) {
	try {
	    String url = "http://localhost:8081/ExpenseWebservice/kontohandler/deletekonto";

	    
	    Client client = Client.create();
	    WebResource resource = client.resource(url);
	    ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, new JSONObject(konto).toString());
	    
	    if (response.getStatus() != 200) {
		throw new RuntimeException("Failed: HTTP ERROR : " + response.getStatus() + "detail: " + response.toString());
	    }
	    
	    String input = response.getEntity(String.class);
	    System.out.println(input);
	    
	} catch(Exception e) {
	    e.printStackTrace();
	}
    }

}
