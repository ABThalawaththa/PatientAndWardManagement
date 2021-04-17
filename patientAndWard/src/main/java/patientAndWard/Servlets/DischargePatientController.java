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

/**
 * Servlet implementation class DischargePatientController
 */
public class DischargePatientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DischargePatientController() {
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
		// get id of the patient needed to discharge
		int patientId = Integer.parseInt(request.getParameter("PatientID"));
		PatientAndWardInterface patientAndWardInterface = new PatientAndWardImpl();
		// call function to discharge specific patient
		HashMap<String, String> messageHashMap = patientAndWardInterface.dischargePatient(patientId);

		if (!messageHashMap.isEmpty()) {
			String message = messageHashMap.get("message");
			if (message.equalsIgnoreCase("Patient is successfully discharged")) {
				request.setAttribute("DischargeSuccess", message);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainInterfaceController");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("DischargeFailure", message);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainInterfaceController");
				dispatcher.forward(request, response);
			}
		} 
	}

}
