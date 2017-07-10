package com.gar.pkg;

import java.sql.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
	private Connection connection = DBConnection.connection;

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
	public void add(Garment garment) {
		try {
			garment.garmentID = getNextUnique();
			garment.createdDate = new Date();
			garment.status = "In";
			garment.checkoutDate = null;
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate(
					"INSERT INTO garments (garmentID, type, size, color, timePeriod, createdDate, checkoutDate, status) "
							+ "VALUES (" + garment.garmentID + ", '" + garment.type + "', '" + garment.size + "', '"
							+ garment.color + "', '" + garment.timePeriod + "', current_date(), NULL, '"
							+ garment.status + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Delete a garment from the database
	public void delete(int garmentID) {
		try {
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate("DELETE FROM garments WHERE garmentID = " + garmentID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Edit a garment in the database
	public void edit(Garment garment) {
		try {
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate("UPDATE garments SET type = '" + garment.type + "', size = '"
					+ garment.size + "',  color = '" + garment.color + "', timePeriod = '" + garment.timePeriod
					+ "' WHERE garmentID = " + garment.garmentID);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// check in a garment to inventory
	public void checkIn(int garmentID) {
		try {
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate(
					"UPDATE garments SET status = 'In', checkoutDate = NULL WHERE garmentID = " + garmentID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// check out a garment from inventory
	public void checkOut(int garmentID) {
		try {
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate(
					"UPDATE garments SET status = 'Out', checkoutDate = current_date() WHERE garmentID = " + garmentID);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Get the amount of days the garment has been in the inventory
	public int getAge(Garment garment) {
		Date currentDate = new Date();
		Long diff = currentDate.getTime() - garment.createdDate.getTime();
		return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	// Get the next unique primary key
	public int getNextUnique() throws SQLException {
		int nextUnique = 0;
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT MAX(garmentID) garmentID FROM garments");

		while (rs.next()) {
			nextUnique = rs.getInt("garmentID") + 1;
		}
		return nextUnique;
	}
}
