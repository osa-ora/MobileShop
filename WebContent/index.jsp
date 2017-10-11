<%@page import="java.util.List"%>
<%@page import="main.java.com.oracle.beans.MobileDevice"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mobile Shop 1.0</title>
</head>
<body>
<br>
<center>
<hr>
<img src="images/oracle-logo.png"><br>
<hr>
Welcome to Mobile Shop <br>
<hr>
<b>Kindly Select You Mobile</b><br>
<hr>
<br><a id="cart" href="cart.jsp"><img src="images/cart.jpg"> (<% if(request.getSession().getAttribute("CART")==null) out.print("0"); else {
					List devices=(List<MobileDevice>) request.getSession().getAttribute("CART");
					out.print(devices.size());
			} %>
			)</a>
<table>
<tr>
<td>Samsung</td><td>iPhone</td>
<td>HTC</td><td>Sony</td>
</tr>

<tr>
<td>120 $<br><img src="images/samsung.jpg"></td><td>130 $<br><img src="images/iphone.jpg"></td>
<td>85 $<br><img src="images/htc.jpg"></td><td>100 $<br><img src="images/sony.jpg"></td>
</tr>
<tr>
<td><a id="1" href="AddToCart?id=1"><img src="images/add.gif"></a></td><td><a id="2" href="AddToCart?id=2"><img src="images/add.gif"></a></td>
<td><a id="3" href="AddToCart?id=3"><img src="images/add.gif"></a></td><td><a id="4" href="AddToCart?id=4"><img src="images/add.gif"></a></td>
</tr>
</table>
</center>
</body>
</html>