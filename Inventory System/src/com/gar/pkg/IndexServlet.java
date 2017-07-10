package com.gar.pkg;

import java.io.*;
import com.gar.pkg.Garment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GarmentServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String status = request.getParameter("status");
		String action = request.getParameter("action");
		int garmentID = Integer.parseInt(request.getParameter("garmentID").replaceAll("/", ""));
		Garment garment = new Garment();

		if (action != null && action.equalsIgnoreCase("delete")) {
			garment.delete(garmentID);
		} else {
			if (status.equalsIgnoreCase("In")) {
				garment.checkOut(garmentID);
			} else {

				garment.checkIn(garmentID);
			}
		}
		response.sendRedirect("/Inventory_System");
	}

}
