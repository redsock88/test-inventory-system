<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.gar.pkg.Garment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Inventory Management System</title>
	</head>
	<%
	int garmentID = Integer.parseInt(request.getParameter("garmentID").replaceAll("/", ""));
	String type = request.getParameter("type").replaceAll("/", "");
	String size = request.getParameter("size").replaceAll("/", "");
	String color = request.getParameter("color").replaceAll("/", "");
	String timePeriod = request.getParameter("timePeriod").replaceAll("/", "");
	int age = Integer.parseInt(request.getParameter("age").replaceAll("/", ""));
	String checkoutDate = request.getParameter("checkoutDate").replaceAll("/", "");
	String status = request.getParameter("status").replaceAll("/", "");
	%>
	<body>
		<h1>Edit Existing Garment</h1>
		<FORM METHOD=POST ACTION="EditServlet">
			<h3>Garment ID: <%out.println(garmentID);%>
			</h3>
			<h3>Type: <%out.println(type);%>
				<select name="type">
		    		<option value="Hat">Hat</option>
		    		<option value="Shirt">Shirt</option>
		    		<option value="Pants">Pants</option>
		    		<option value="Belt">Belt</option>
		    		<option value="Shoes">Shoes</option>
		    		<option value="Coat">Coat</option>
		    		<option value="Dress">Dress</option>
		  		</select>
	  		</h3>
	  		<h3>Size: <%out.println(size);%>
		  		<select name="size">
		    		<option value="X Large">X Large</option>
		    		<option value="Large">Large</option>
		    		<option value="Medium">Medium</option>
		    		<option value="Small">Small</option>
		    		<option value="Kid">Kid</option>
		  		</select>
	  		</h3>
	  		<h3>Color: <%out.println(color);%>
		  		<select name="color">
		  			<option value="Red">Red</option>
		    		<option value="Orange">Orange</option>
		    		<option value="Yellow">Yellow</option>
		    		<option value="Green">Green</option>
		    		<option value="Blue">Blue</option>
		    		<option value="Purple">Purple</option>
		    		<option value="Black">Black</option>
		    		<option value="Brown">Brown</option>
		    		<option value="White">White</option>
		  		</select>
	  		</h3>
	  		<h3>Time Period: <%out.println(timePeriod);%>
		  		<select name="timePeriod">
		    		<option value="Modern">Modern</option>
		    		<option value="Colonial">Colonial</option>
		    		<option value="Victorian">Victorian</option>
		    		<option value="World War 1">World War 1</option>
		    		<option value="World War 2">World War 2</option>
		  		</select>
	  		</h3>
	  		<h3>Age: <%out.println(age);%>
	  		</h3>
	  		<h3>Checkout Date: <%out.println(checkoutDate);%>
	  		</h3>
	  		<h3>Status: <%out.println(status);%>
	  		</h3>
	  		<input type="hidden" name="garmentID" value=<%=garmentID%>/>
		<INPUT TYPE=SUBMIT value="Save">
		<button type="button" name="back" onclick="history.back()">Back</button>
	</body>
</html>