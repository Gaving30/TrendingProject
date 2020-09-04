package com.mycompany.trendingproject;

import com.jayway.restassured.path.json.JsonPath;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import org.json.JSONException;

/**
 * REST Web Service
 *
 * @author Niall
 */
@Path("soccer")
public class SoccerResource {
    private final Logger logger = Logger.getLogger(SoccerResource.class.getName());

    @Context
    private UriInfo context;
    private final String soccerResults;

    /**
     * Creates a new instance of soccerResource
     * @param trending
     * @param soccer
     */
    public SoccerResource(String trending) { //the string that is fed in here should be in the format of a jsonobject
        soccerResults=trending;
    }

    
    
     /* this method takes an integer and retuns the corresponding 'headlines.headline' from the JSONObject*/
    public String getSoccerNews(int i) throws JSONException {        
        JsonPath json=new JsonPath(soccerResults.toString());
        String x=String.valueOf(json.get("headlines["+i+"].headline"));
        
        
        logger.info("this is a test"); //just another debug helper
        
        return x;

        
        
    }
     /* this method takes an integer and retuns the corresponding 'headlines.links.web.href' from the JSONObject*/
    public String getURL(int i) throws JSONException{
        JsonPath json=new JsonPath(soccerResults.toString());
        String x=String.valueOf(json.get("headlines["+i+"].links.web.href"));
        

        logger.info("this is a test");
        
        return x;
    }
    
    public String printHeadline(int i) throws JSONException{
        return this.getSoccerNews(i);  //returns the ith headline from the JSONObject
    }
    
    public String printUrl(int i) throws JSONException{
        return this.getURL(i);  //returns the corresponding url according to i
    }
}
