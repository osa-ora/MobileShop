<%@page import="java.util.List"%>
<%@page import="main.java.com.oracle.beans.MobileDevice"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mobile Shop - Shopping Cart</title>
</head>
<body>
<br>
<b>Shopping Cart</b>
<hr>
<center>
<br><a id="back" href="index.jsp"><img width=200 src="images/continue.png"></a>
<br><a id="checkout" href="checkout.jsp"><img width=200 src="images/checkout.png"></a>
<table>
<% if(request.getSession().getAttribute("CART")==null) out.print("Empty!"); else {
					List devices=(List<MobileDevice>) request.getSession().getAttribute("CART");
					if(devices.isEmpty()) out.print("Empty!");
					for(int i=0;i<devices.size();i++){
						switch(((MobileDevice)devices.get(i)).getId()){
							case 1:
							%>
								<tr><td>Samsung<br>
									<img src="images/samsung.jpg"><br>
									<a href="RemoveFromCart?id=<%=i%>"><img src="images/trash.png"></a>									
									</td>
								</tr>
							<%
							break;
							case 2:
							%>
								<tr><td>iPhone<br>
									<img src="images/iphone.jpg"><br>
									<a href="RemoveFromCart?id=<%=i%>"><img src="images/trash.png"></a>									
									</td>
								</tr>
							<%
							break;
							case 3:
							%>
								<tr><td>HTC<br>
									<img src="images/htc.jpg"><br>
									<a href="RemoveFromCart?id=<%=i%>"><img src="images/trash.png"></a>									
									</td>
								</tr>
							<%
							break;
							case 4:
							%>
								<tr><td>Sony<br>
									<img src="images/sony.jpg"><br>
									<a href="RemoveFromCart?id=<%=i%>"><img src="images/trash.png"></a>									
									</td>
								</tr>
							<%
							break;							
						}
						
						%>
						
					<%}
			} %>
</table>
<hr>
Copyright 2017 by AnyCompany.com
</center>
</body>
</html>