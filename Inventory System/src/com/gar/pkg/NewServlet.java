package com.gar.pkg;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GarmentServlet
 */
@WebServlet("/NewServlet")
public class NewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
    }  
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Garment garment = new Garment();
		garment.type = request.getParameter("type");
		garment.size = request.getParameter("size");
		garment.color = request.getParameter("color");
		garment.timePeriod = request.getParameter("timePeriod");

		garment.add(garment);
		response.sendRedirect("/Inventory_System");
	}

}
