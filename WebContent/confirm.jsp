<%@page import="java.util.List"%>
<%@page import="main.java.com.oracle.beans.MobileDevice"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mobile Shop - Confirmation</title>
</head>
<body>
<br>
<b>Confirmation</b>
<hr>
<center>
<br><a id="back" href="index.jsp"><img width=200 src="images/continue.png"></a>
<form method="post" action="/Checkout">
<table>
<%  double totalPrice=0;
	if(request.getSession().getAttribute("CART")==null) out.print("Empty!"); else {
					List devices=(List<MobileDevice>) request.getSession().getAttribute("CART");
					if(devices.isEmpty()) out.print("Empty!");
					
					for(int i=0;i<devices.size();i++){
						totalPrice+=((MobileDevice)devices.get(i)).getPrice();
					}	
			} %>
		<tr>
			<td>Total Price</td>
			<td><%=totalPrice %></td>
		</tr>
		<tr>
			<td>Name</td>
			<td><%= request.getParameter("name") %></td>
		</tr>
		<tr>
			<td>Address</td>
			<td><%= request.getParameter("address") %></td>
		</tr>
		<% session.invalidate(); %>
</table>
</form>			
</center>
</body>
</html>