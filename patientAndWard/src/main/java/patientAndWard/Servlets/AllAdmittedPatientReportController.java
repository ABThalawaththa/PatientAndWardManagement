package patientAndWard.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import patientAndWard.Services.PatientAndWardImpl;
import patientAndWard.Services.PatientAndWardInterface;

/**
 * Servlet implementation class AllAdmittedPatientReportController
 */
public class AllAdmittedPatientReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllAdmittedPatientReportController() {
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
		
		PatientAndWardInterface patientAndWardInterface = new PatientAndWardImpl();
		//call function to generate report of all the patients admitted 
		patientAndWardInterface.generateAllAdmittedPatientsReport();
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainInterfaceController");
		dispatcher.forward(request,response);
	}

}
