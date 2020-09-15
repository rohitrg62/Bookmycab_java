package com.nis.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AgencyLogout
 */
@WebServlet("/AgencyLogout")
public class AgencyLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgencyLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out=response.getWriter();
		out.println("<html>");
		
	//	get current session and don't create new one if not exist	
		HttpSession ses=request.getSession(false);
		
	// print the session id
		if(ses != null){
			out.println("<br>Session Id : "+ses.getId());
			synchronized(ses){
			ses.invalidate();
	//destroying session		
			out.println("<br><br><b>successfully Logged out </b>");
			}
		}
		out.print("<br><br><a href=AgencyLogin>Back</a></html>");
		out.close();
	}

}
