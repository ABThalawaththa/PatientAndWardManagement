package patientAndWard.Servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.criteria.internal.expression.function.SubstringFunction;

import patientAndWard.Services.PatientAndWardImpl;
import patientAndWard.Services.PatientAndWardInterface;
import patientAndWard.entities.PatientAdmitted;

/**
 * Servlet implementation class updatePatientDetialsController
 */
public class updatePatientDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatePatientDetailsController() {
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
		//get the updated inputs 
		int updatePatientId = Integer.parseInt(request.getParameter("PatientID"));
		String updatePatientFirstName = request.getParameter("update-patient-first-name").toLowerCase();		
		String updatePatientLastName = request.getParameter("update-patient-last-name").toLowerCase();
		int updatePatientAge = Integer.parseInt(request.getParameter("update-patient-age"));
		String updatePatientContact = request.getParameter("update-patient-contact");
		String updatePatientAddress = request.getParameter("update-patient-address");
		
		//create object with updated inputs
		PatientAdmitted updatePatientAdmitted = new PatientAdmitted();
		updatePatientAdmitted.setPatientId(updatePatientId);
		updatePatientAdmitted.setPatient_firstName(updatePatientFirstName);
		updatePatientAdmitted.setPatient_lastName(updatePatientLastName);
		updatePatientAdmitted.setAge(updatePatientAge); 
		updatePatientAdmitted.setContactNo(updatePatientContact);
		updatePatientAdmitted.setAddress(updatePatientAddress);
		
		PatientAndWardInterface patientAndWardInterface = new PatientAndWardImpl();
		
		//call function to update patient details
		HashMap<String,String> hashMap = patientAndWardInterface.updatePatientDetails(updatePatientAdmitted);
		String messageString = hashMap.get("message");
		
		if (messageString.equalsIgnoreCase("Successfully Updated")) {
			//set update success message 
			request.setAttribute("updateMessageSuccess", messageString); 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainInterfaceController");
			dispatcher.forward(request,response);
		}else {
			//set update failure message
			request.setAttribute("updateMessageFailure", messageString); 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainInterfaceController");
			dispatcher.forward(request,response);
		}
		
		
	}

}
