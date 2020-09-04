
/**
 *
 * @author nwhelehan
 */   package com.mycompany.trendingproject;


import com.jayway.restassured.path.json.JsonPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;
import org.json.JSONException;
import com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;





/**
 * REST Web Service
 *
 * @author Niall
 */
@Path("news")
public class NewsResource {
    private static final Logger logger = Logger.getLogger(TrendingResource.class.getName());

    @Context
    private UriInfo context;
    private String trendResults;

    /**
     * Creates a new instance of trendingResource
     * @param trending
     */
    public NewsResource(String trending) { //the string that is fed in here should be in the format of a jsonobject
        trendResults=trending;
    }

    
    
    /* this method takes an integer and retuns the corresponding 'results.title' from the JSONObject*/
    public String getTrendingNews(int i) throws JSONException {        
        JsonPath json=new JsonPath(trendResults.toString());
        String x=String.valueOf(json.get("results.title["+i+"]")); 
        
        
        logger.info("this is a test"); // I installed a lot of these early to help out with troubleshooting
        
        return x;

        
        
    }
    
    /* this method takes an integer and retuns the corresponding 'results.url' from the JSONObject*/
    public String getURL(int i) throws JSONException{
        JsonPath json=new JsonPath(trendResults.toString());
        String x=String.valueOf(json.get("results.url["+i+"]"));
        

        logger.info("this is a test");
        
        return x;
    }
    
    public String printHeadline(int i) throws JSONException{
        return this.getTrendingNews(i)+"\n"; //returns the ith headline from the JSONObject
    }
    
    public String printUrl(int i) throws JSONException{
        return this.getURL(i);      //returns the corresponding url according to i
    }
}
 
