package patientAndWard.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patientAndWard.Services.PatientAndWardImpl;
import patientAndWard.Services.PatientAndWardInterface;
import patientAndWard.entities.PatientAdmitted;
import patientAndWard.entities.Unit;
import patientAndWard.entities.Ward;

/**
 * Servlet implementation class searchPatientController
 */
public class searchPatientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchPatientController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get patient's first name and last name
		String searchPatientFirstName = request.getParameter("SearchPatientFirstName").toLowerCase();
		String searchPatientLastName = request.getParameter("SearchPatientLastName").toLowerCase();
		
		PatientAndWardInterface patientAndWardInterface = new PatientAndWardImpl();
		//call function to search patient by using first name and last name
		PatientAdmitted patientReceived = patientAndWardInterface.searchPatientByNAme(searchPatientFirstName, searchPatientLastName);
		
		if(patientReceived != null) {
			//get ward where patient admitted
			Ward wardAdmitted = patientReceived.getWardPatient();
			
			//get unit where ward is belonged to 
			Unit AdmittedUnit = wardAdmitted.getUnitWard();
			
			//set attributes for front end 
			request.setAttribute("PatientReturn",patientReceived);
			request.setAttribute("PatientAdmittedUnit",  AdmittedUnit);
			request.setAttribute("PatientAdmittedWard", wardAdmitted);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/searchPatientResults.jsp");
			dispatcher.forward(request,response);
		}
		else {
			//set no patient found message 
			request.setAttribute("NoSearchPatientResults", "No Patient Found"); 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainInterfaceController");
			dispatcher.forward(request,response);
		}
		
	}

}
