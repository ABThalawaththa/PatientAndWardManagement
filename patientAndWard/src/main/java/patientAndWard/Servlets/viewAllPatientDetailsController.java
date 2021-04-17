package patientAndWard.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class viewAllPatientDetailsController
 */
public class viewAllPatientDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewAllPatientDetailsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PatientAndWardInterface patientAndWardInterface = new PatientAndWardImpl();
		
		//call function to get details of all the patients admitted
		List<PatientAdmitted> listOfAllPatientAdmitted = patientAndWardInterface.getAllThePatients();
		
		//set list of patient to front end 
		request.setAttribute("ListOfAllPatients",listOfAllPatientAdmitted);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/viewAllPatientDetails.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the patient id to delete the patient 
		int PatientID = Integer.parseInt(request.getParameter("PatientID"));
		
		PatientAndWardInterface patientAndWardInterface = new PatientAndWardImpl();
		
		//call function to delete record of specific patient
		boolean flag = patientAndWardInterface.deletePatientRecord(PatientID);
		if( flag == true) {
			//set delete success message
			request.setAttribute("deleteRecordSuccess", "Record Successfully Deleted");
			doGet(request, response);
		}
		else {
			//set delete failure message
			request.setAttribute("deleteRecordError", "Can not delete patient details");
			doGet(request, response);
		}
	}

}
