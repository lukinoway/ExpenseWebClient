package net.home.handler;

import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import konto.data.model.Category;

public class CategoryHandler {
    
    public void addCategory(Category category) {
	try {
	    String url = "http://localhost:8081/ExpenseWebservice/categoryhandler/addcategory";
	    
	    Client client = Client.create();
	    WebResource resource = client.resource(url);
	    System.out.println(category.toString());
	    ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, new JSONObject(category).toString());
	    
	    if (response.getStatus() != 200) {
		throw new RuntimeException("Failed: HTTP ERROR : " + response.getStatus() + "detail: " + response.toString());
	    }
	    
	    String input = response.getEntity(String.class);
	    System.out.println(input);
	    
	    // to some magic 
	    int index = input.lastIndexOf("=");
	    category.setTypeId(Integer.parseInt(input.substring(index + 1)));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void updateCategory(Category category) {
	try {
	    String url = "http://localhost:8081/ExpenseWebservice/categoryhandler/updatecategory";
	    
	    Client client = Client.create();
	    WebResource resource = client.resource(url);
	    System.out.println(category.toString());
	    ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, new JSONObject(category).toString());
	    
	    if (response.getStatus() != 200) {
		throw new RuntimeException("Failed: HTTP ERROR : " + response.getStatus() + "detail: " + response.toString());
	    }
	    
	    String input = response.getEntity(String.class);
	    System.out.println(input);
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void deleteCategory(Category category) {
	try {
	    String url = "http://localhost:8081/ExpenseWebservice/categoryhandler/deletecategory";
	    
	    Client client = Client.create();
	    WebResource resource = client.resource(url);
	    System.out.println(category.toString());
	    ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, new JSONObject(category).toString());
	    
	    if (response.getStatus() != 200) {
		throw new RuntimeException("Failed: HTTP ERROR : " + response.getStatus() + "detail: " + response.toString());
	    }
	    
	    String input = response.getEntity(String.class);
	    System.out.println(input);
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
