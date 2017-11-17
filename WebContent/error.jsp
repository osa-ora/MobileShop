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
		<tr>
                    <td><b>Bad Luck, Error during order submission!</b></td>
                </tr>
                <tr>
                        <td><% Exception ex=(Exception)session.getAttribute("ERROR"); %>
                            <br>
                            <% out.print(ex.getMessage()); %>
                        </td>
		</tr>
		<% session.invalidate(); %>
</table>
</form>			
</center>
</body>
</html>