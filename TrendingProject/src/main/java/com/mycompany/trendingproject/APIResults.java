package com.mycompany.trendingproject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nwhelehan
 */
public class APIResults {
    
    private JSONObject trendResults;
    private String trendingWord;
    
    public APIResults(){
        
    }
    
    public APIResults(String trendingWord){
        this.trendingWord=trendingWord;
    }
    
    /* method for setting the url path of the soccer headlines JSONObject using ESPN Sport's API*/
    public void setSoccerObject() throws JSONException{
        WebResource trendingResource = new Client().resource("http://api.espn.com/v1/sports/soccer/news/headlines?apikey=4e4em9h7yygh4aemhquhv3gd");
        ClientResponse trendingResponse = trendingResource.accept("application/json").get(ClientResponse.class);

        //Throw exception if GET fails
        if (trendingResponse.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code :: " + trendingResponse.getStatus());
        }
        trendResults = new JSONObject( trendingResponse.getEntity(String.class) );
        
    }
    
    /* method for setting the url path of the soccer headlines JSONObject using Faroo.com's API*/
    public void setNewsObject(String trendingWord) throws JSONException{
        WebResource trendingResource = new Client().resource("http://www.faroo.com/api?q="+trendingWord+"&start=1&length=10&l=en&src=news&f=json&key=UJ5Zg7h5OEbQu5DP2HJb6qSopJs_");
        ClientResponse trendingResponse = trendingResource.accept("application/json").get(ClientResponse.class);

        //Throw exception if GET fails
        if (trendingResponse.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code :: " + trendingResponse.getStatus());
        }
        trendResults = new JSONObject( trendingResponse.getEntity(String.class) );
    }
    
    public void setTrends() throws JSONException{
        WebResource trendingResource = new Client().resource("http://www.faroo.com/api?q=games&start=1&length=10&l=en&src=trends&f=json&key=UJ5Zg7h5OEbQu5DP2HJb6qSopJs_&kwic=sports");
        ClientResponse trendingResponse = trendingResource.accept("application/json").get(ClientResponse.class);

        //Throw exception if GET fails
        if (trendingResponse.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code :: " + trendingResponse.getStatus());
        }
        trendResults = new JSONObject( trendingResponse.getEntity(String.class) );
    }
    
    /* return the results of the api call*/
    public String getResultString(){
     
        return String.valueOf(trendResults);
    }
}
