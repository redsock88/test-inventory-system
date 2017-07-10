<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="com.gar.pkg.Garment"%>
<%@ page import="com.gar.pkg.DBConnection"%>
<!DOCTYPE HTML">
<html>
	<head>
		<script src="sorttable.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Inventory Management System</title>
	</head>
	<body>
		<h1>Inventory Management System</h1>
		<table class="sortable" cellpadding="15" border="1">
		   <tr bgcolor= "grey">
				<th>Garment ID</th>
				<th>Type</th>
				<th>Size</th>
				<th>Color</th>
				<th>Time Period</th>
				<th>Age (in days)</th>
				<th>Checkout Date (YYYY-MM-DD)</th>
				<th>Status</th>
				<th>
					<form action="new.jsp">
			            <input type="submit" value="Add New">
			        </form>
		        <th>
		   </tr>
		   <%try{
				Connection conn = DBConnection.connection;
				String query="SELECT * FROM garments ORDER BY garmentID";
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery(query);
				while(rs.next()){
		
					Garment garment = new Garment(rs.getInt("garmentID"), rs.getString("type"), rs.getString("size"),
								rs.getString("color"), rs.getString("timePeriod"), rs.getDate("createdDate"),
								rs.getDate("checkoutDate"), rs.getString("status"), 0); 
					garment.age = garment.getAge(garment);
					String color = "green";
					if(garment.status.equalsIgnoreCase("out")){
						color = "red";
					}%>
					<tr bgcolor="<%=color %>">
					<td><%out.println(garment.garmentID); %></td>
					<td><%out.println(garment.type); %></td>
					<td><%out.println(garment.size); %></td>
					<td><%out.println(garment.color); %></td>
					<td><%out.println(garment.timePeriod); %></td>
					<td><%out.println(garment.age); %></td>
					<td><%out.println(garment.checkoutDate); %></td>
					<td><%out.println(garment.status); %></td>
					<td>
					     <form action="IndexServlet" method="POST">
					         <input type="hidden" name="garmentID" value=<%=garment.garmentID%> />
					      <input type="hidden" name="status" value=<%=garment.status%> />
					        <input type="submit" value="Check In/Out">
					    </form>
					</td>
					<td>
					    <form action="edit.jsp">
					        <input type="hidden" name="garmentID" value=<%=garment.garmentID%>/>
					      <input type="hidden" name="type" value=<%=garment.type%>/>
					      <input type="hidden" name="size" value=<%=garment.size%>/>
					      <input type="hidden" name="color" value=<%=garment.color%>/>
					      <input type="hidden" name="timePeriod" value=<%=garment.timePeriod%>/>
					      <input type="hidden" name="age" value=<%=garment.age%>/>
					      <input type="hidden" name="checkoutDate" value=<%=garment.checkoutDate%>/>
					      <input type="hidden" name="status" value=<%=garment.status%>/>
					        <input type="submit" value="Edit">
					    </form>
					</td>
					<td>
					    <form action="IndexServlet" method="POST">
					        <input type="hidden" name="garmentID" value=<%=garment.garmentID%>/>
					            <input type="hidden" name="action" value="delete"/>
					            <input type="submit" value="Delete">
					        </form>
					    </td>
					</tr>
					
					<%}
				}catch(Exception e){
				     e.printStackTrace();
				}%>
		</table>
	</body>
</html>