package com.nis.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.softech.FileUpload;

import com.nis.controller.VehicleController;
import com.nis.model.Agency;
import com.nis.model.AgencyVehicle;

/**
 * Servlet implementation class VehicleSubmit
 */
@WebServlet("/VehicleSubmit")
@MultipartConfig(fileSizeThreshold=1024*1024*2,//2MB
maxFileSize=1024*1024*10,//10MB
maxRequestSize=1024*1024*50)//50 MB

public class VehicleSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleSubmit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		
		HttpSession ses=request.getSession();
		
		////////////Session invoke/////////////
		Agency A;
		try	{
				A =(Agency)ses.getValue("SAGENCY");
				String ltime=(String)ses.getValue("SLTIME");
				@SuppressWarnings("unused")
				String navigbar="<h4><font color=darkblue>Agency Id:"+A.getAgencyid()+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"+A.getAgencyname()+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"+ltime+"</font>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<img src=picture/"+A.getLogo()+" width=40 height=40 valign=bottom>&emsp;&emsp;&emsp;<a href=AgencyLogout>Logout</a></h4><hr color=red>";
		}	
		catch(Exception e){		//	out.println(e);
			response.sendRedirect("AgencyLogin");	}
		////////////////////////////////////////

		
		out.println("<html>");
		AgencyVehicle V=new AgencyVehicle();
		
		V.setAgencyid(request.getParameter("aid"));
		V.setVehicleid(request.getParameter("vid"));
		V.setVehiclename(request.getParameter("vname"));
		V.setModelyear(request.getParameter("myear"));
		V.setVehiclecolor(request.getParameter("vcolor"));
		V.setVehicletype(request.getParameter("vtype"));
		V.setVehiclecapacity(request.getParameter("vcapacity"));
		V.setInsurance(request.getParameter("insurance"));
		V.setAc(request.getParameter("ac"));
		V.setFueltype(request.getParameter("fuel"));
						
	//	V.setVehiclepicture(request.getParameter("vpicture"));
		
		Part p=request.getPart("vpicture");
		String path="M:\\numeric\\ProjectM\\BookMyCab\\WebContent\\vehiclepicture";
		FileUpload F=new FileUpload(p,path);
		V.setVehiclepicture(F.filename);
		
		boolean st=VehicleController.AddNewRecord(V);
		if(st)
		{	
			out.println("<b>Record Submitted</b>");
		}	
		else { out.println("<b>Fail to submit Record</b>"); }
			
		out.println("</html>");
		out.flush();
	}

}