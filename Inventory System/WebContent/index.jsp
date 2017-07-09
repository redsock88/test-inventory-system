<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="com.gar.pkg.Garment"%>
<!DOCTYPE HTML">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inventory Management System</title>
</head>
<body>
<h1>Inventory Management System</h1>
<table cellpadding="15" border="1">
   <tr bgcolor= "grey">
		<th>Garment ID</th>
		<th>Type</th>
		<th>Size</th>
		<th>Color</th>
		<th>Time Period</th>
		<th>Age (in days)</th>
		<th>Checkout Date (YYYY-MM-DD)</th>
		<th>Status</th>
   </tr>
   <%try
   {
       Class.forName("com.mysql.jdbc.Driver");
       String url="jdbc:mysql://127.0.0.1:3306/test";
       String username="root";
       String password="root";
       String query="SELECT * FROM garments ORDER BY garmentID";
       Connection conn=DriverManager.getConnection(url, username, password);
       Statement stmt=conn.createStatement();
       ResultSet rs=stmt.executeQuery(query);
       while(rs.next())
       {
 
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
    </tr>

   <%}%>
   </table>
   <%rs.close();
        stmt.close();
        conn.close();
   }
   catch(Exception e)
   {
        e.printStackTrace();
   }%>
</body>
</html>