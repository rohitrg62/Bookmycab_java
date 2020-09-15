package com.nis.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.nis.controller.ClientController;
import com.nis.model.Client;

/**
 * Servlet implementation class ClientLoginCheckForJS
 */
@WebServlet("/ClientLoginCheckForJS")
public class ClientLoginCheckForJS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientLoginCheckForJS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	@SuppressWarnings("deprecation")
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Client C=ClientController.checkPassword(request.getParameter("uid"), request.getParameter("pwd"));
	       PrintWriter out=response.getWriter();
			 
	       if(C!=null)
	       { 
	    	   HttpSession ses=request.getSession();
	    	   ses.putValue("SCLIENT", C);
	    	   ses.putValue("LTIME", new java.util.Date().toString());
	    	   ses.setMaxInactiveInterval(15*60); //15 minute
	    	   
	    	   JSONObject obj=new JSONObject();
			    try {
					obj.put("res", "1");
				    out.println(obj);
			    } catch (JSONException e) {
					e.printStackTrace();
				}
			}
			else
			{  
				JSONObject obj=new JSONObject();
				try {
					obj.put("res", "0");
					out.println(obj);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		
	}

}
