package patientAndWard.Servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patientAndWard.Services.PatientAndWardImpl;
import patientAndWard.Services.PatientAndWardInterface;
import patientAndWard.entities.PatientAdmitted;
import patientAndWard.entities.Ward;



public class patientInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public patientInputController() {
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
		//get patient's details from the user
		String patientFirstName = request.getParameter("patient_first_name").toLowerCase();
		String patientLastName = request.getParameter("patient_last_name").toLowerCase();
		
		PatientAndWardInterface patientAndWardInterface = new PatientAndWardImpl();
		if(patientAndWardInterface.searchPatientByNAme(patientFirstName, patientLastName) == null) {
			String patientAge = request.getParameter("patient_age");
			String patientContactNo = request.getParameter("patient_contact");
			String patientAddress = request.getParameter("patient_address");
			String admittedByFirstName = request.getParameter("Admittedby_first-name").toLowerCase();
			String admittedByLastName = request.getParameter("Admittedby_last-name").toLowerCase();
			String causeOfAdmission = request.getParameter("admitted_Cause");
			int admittedUnit = Integer.parseInt(request.getParameter("unitAdmitted"));
			long millis = System.currentTimeMillis();
			Date date = new java.sql.Date(millis);

			
			//Create patient object and set values 
			PatientAdmitted patientAdmitted = new PatientAdmitted();
			patientAdmitted.setPatient_firstName(patientFirstName);
			patientAdmitted.setPatient_lastName(patientLastName);
			patientAdmitted.setAge(Integer.parseInt(patientAge));
			patientAdmitted.setContactNo(patientContactNo);
			patientAdmitted.setAddress(patientAddress);
			patientAdmitted.setAdmittedByFirstName(admittedByFirstName);
			patientAdmitted.setAdmittedByLastName(admittedByLastName);
			patientAdmitted.setCauseOfAdmission(causeOfAdmission);
			patientAdmitted.setDateOfAdmission(date);
			
			request.getSession().setAttribute("patientDetails",patientAdmitted);
			
			//call function to get the list of wards belonged to specific unit
			ArrayList<Ward> wards = patientAndWardInterface.getWards(admittedUnit);
			
			if(wards.isEmpty()) {
				//set no ward available message
				request.setAttribute("NoWard","No Available Wards");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainInterfaceController");
				dispatcher.forward(request,response);
			}
			
			else {
				//set ward list to display to user
				request.setAttribute("wardList",wards);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admitPatient.jsp");
				dispatcher.forward(request,response);
			}
		}else {
			//set duplicate record error message 
			request.setAttribute("DuplicateRecord", "Entry you are trying to insert is already available"); 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainInterfaceController");
			dispatcher.forward(request,response);
		}
		
	}

}
