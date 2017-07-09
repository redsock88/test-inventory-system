package com.gar.pkg;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import com.mysql.jdbc.Driver;

public class Garment {

	public long garmentID;
	public String type;
	public String size;
	public String color;
	public String timePeriod;
	public Date createdDate;
	public Date checkoutDate;
	public String status;
	public long age;
	private Connection connection;

	public Garment() {
		garmentID = 0;
		type = null;
		size = null;
		color = null;
		timePeriod = null;
		createdDate = null;
		checkoutDate = null;
		status = null;
		age = 0;
	}

	public Garment(long id, String typeStr, String sizeStr, String colorStr, String timePeriodStr, Date creation,
			Date checkout, String statusStr, long days) {
		garmentID = id;
		type = typeStr;
		size = sizeStr;
		color = colorStr;
		timePeriod = timePeriodStr;
		createdDate = creation;
		checkoutDate = checkout;
		status = statusStr;
		age = days;
	}


	// Add a garment to the database
	public void add(Garment garment) throws SQLException {
		garment.garmentID = getNextUnique();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(
				"INSERT INTO garments (garmentID, type, size, color, timePeriod, createdDate, checkoutDate, status) "
						+ "VALUES (" + garment.garmentID + ", " + garment.type + ", " + garment.size + ", "
						+ garment.color + ", " + garment.timePeriod + ", " + garment.createdDate + ", "
						+ garment.checkoutDate + ", " + garment.status + ")");
	}

	// Delete a garment from the database
	public void delete(Garment garment) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("DELETE FROM garments WHERE garmentID = " + garment.garmentID);
	}

	// Edit a garment in the database
	public void edit(Garment garment) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("UPDATE garments SET garmentID = " + garment.garmentID + ", type = "
				+ garment.type + ", size = " + garment.size + ",  color = " + garment.color + ", timePeriod = "
				+ garment.timePeriod + ", createdDate = " + garment.createdDate + ", checkoutDate = "
				+ garment.checkoutDate + ", status = " + garment.status + " WHERE garmentID = " + garment.garmentID);
	}

	// check in a garment to inventory
	public void checkIn(Garment garment) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement
				.executeQuery("UPDATE garments SET status = 'In' WHERE garmentID = " + garment.garmentID);
	}

	// check out a garment from inventory
	public void checkOut(Garment garment) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement
				.executeQuery("UPDATE garments SET status = 'Out' WHERE garmentID = " + garment.garmentID);
	}

	// Get the amount of days the garment has been in the inventory
	public int getAge(Garment garment) {
		Date currentDate = new Date();
		Long diff = currentDate.getTime() - garment.createdDate.getTime();
		return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	// Connect to the database
	private void connectToDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Get the next unique primary key
	private int getNextUnique() throws SQLException {
		int nextUnique = 0;
		Statement statement = connection.createStatement();
		ResultSet rs = statement
				.executeQuery("SELECT MAX(garmentID) FROM garments");
		
		while (rs.next()) {
			nextUnique = rs.getInt("garmentID") + 1;
		}
		return nextUnique;
	}
}
