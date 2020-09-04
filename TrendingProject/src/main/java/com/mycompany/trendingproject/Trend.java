package com.mycompany.trendingproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 *
 * @author Niall
 */


@WebServlet(name = "trend", urlPatterns = {"/trend"}) //this is the url to call trending news headlines
public class Trend extends HttpServlet {
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws org.json.JSONException
     */
    
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {

        APIResults results=new APIResults();        //APIResults is the class that calls the API request
        results.setTrends();                    //Saves the JSONObject results of the API call to 'results'
        String trending=results.getResultString();  //saves the JSONObject to a string
        TrendingResource tR=new TrendingResource(trending); //this is class to manipulate the results of the JSON to String object
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                       
            /* returns the html code, including the scripst for the tag cloud*/
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Trend</title>");  
            out.println("<link rel='stylesheet' type='text/css' href='http://localhost:8080/TrendingProject/css/main.css' />");
            out.println("<script src='js/modernizr-1.5.min.js'></script>");
            out.println("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js' type='text/javascript' charset='utf-8'></script>");
            out.println("<script src='tagCloud.js'></script>");
            out.println("<script>");
            out.println("</script>");
            out.println("<script type=\"text/javascript\">function loadNews(clicked_id){alert(clicked_id);}</script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='list'>");
            out.println("<ul>");
            /* create a loop to print each headline and the corresponding url*/
            for(int i=0;i<10;i++){
                String temp=URLEncoder.encode(tR.printHeadline(i), "UTF-8");
                String URL=temp.replaceAll("%0A", "");
                out.println("<li><a href='#' id='"+URL+"' onclick='loadNews(this.id)'>"+tR.printHeadline(i)+"</a><br/></li>");
                out.println("<br/>");
                
            }
            out.println("</ul>");
            out.println("</<div>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(Trend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(Trend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
