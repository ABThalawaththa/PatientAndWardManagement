package patientAndWard.Servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import patientAndWard.Services.PatientAndWardImpl;
import patientAndWard.Services.PatientAndWardInterface;
import patientAndWard.entities.PatientAdmitted;

/**
 * Servlet implementation class patientInputController2
 */
public class patientInputController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public patientInputController2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get selected ward
		int ward = Integer.parseInt(request.getParameter("wardSelected"));

		// get patient object stored in the session
		PatientAdmitted patientAdmitted = (PatientAdmitted) request.getSession(false).getAttribute("patientDetails");
	
		
		PatientAndWardInterface patientAndWardInterface = new PatientAndWardImpl();
		// Calling function to admit patient (insert patient details)
		HashMap<String, String> messageHashMap = patientAndWardInterface.admitPatient(ward, patientAdmitted);

		if (!messageHashMap.isEmpty()) {
			request.getSession(false).removeAttribute("patientDetails");
			String message = messageHashMap.get("message");
			System.out.println(message);
			
			//set success message
			if (message.equalsIgnoreCase("Patient is successfully admitted")) {
				request.setAttribute("AdmitPatientSuccess", message);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainInterfaceController");
				dispatcher.forward(request, response);
			} else {
				//set error message
				request.setAttribute("AdmitPatientFailure", message);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainInterfaceController");
				dispatcher.forward(request, response);
			}
		}
	}

}
