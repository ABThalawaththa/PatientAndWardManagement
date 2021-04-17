package patientAndWard.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.Schema.CreateCollectionOptions;

import com.mysql.cj.xdevapi.Schema.CreateCollectionOptions;

import com.mysql.cj.xdevapi.Schema.CreateCollectionOptions;

import patientAndWard.Services.PatientAndWardImpl;
import patientAndWard.Services.PatientAndWardInterface;
import patientAndWard.entities.Unit;
import patientAndWard.entities.Ward;

/**
 * Servlet implementation class addNewUnitController
 */
public class addNewUnitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addNewUnitController() {
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
		// get unit details from user
		String unitName = request.getParameter("UnitName").toLowerCase();
		int noOfWardsPerUnit = Integer.parseInt(request.getParameter("NoOfWards"));
		int maxPatientsPerWard = Integer.parseInt(request.getParameter("MaxPatientsPerWard"));

		Unit unit = new Unit();
		unit.setUnitName(unitName);

		// Create array of ward objects
		ArrayList<Ward> listOfWards = new ArrayList<Ward>();

		// add ward objects to the array of wards
		for (int i = 0; i < noOfWardsPerUnit; i++) {
			Ward ward = new Ward();
			ward.setWardNumber(unitName + "w" + (i + 1));
			ward.setOccupiedBeds(0);
			ward.setMaxPatientsAllowed(maxPatientsPerWard);
			listOfWards.add(ward);
		}

		PatientAndWardInterface patientAndWardInterface = new PatientAndWardImpl();
		// add new unit and the wards belonged to the unit
		HashMap<String, String> messageReturned = patientAndWardInterface.addNewUnit(unit, listOfWards);

		if (!messageReturned.isEmpty()) {
			// get error or success message
			String message = messageReturned.get("message");
			if (message.equalsIgnoreCase("Unit is successfully added")) {
				// set success message
				request.setAttribute("AddUnitSuccess", message);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainInterfaceController");
				dispatcher.forward(request, response);
			} else {
				// set error message
				request.setAttribute("AddUnitFailure", message);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainInterfaceController");
				dispatcher.forward(request, response);
			}

		}
	}

}
