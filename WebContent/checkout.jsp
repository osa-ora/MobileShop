<%@page import="java.util.List"%>
<%@page import="main.java.com.oracle.beans.MobileDevice"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mobile Shop - Checkout</title>
<script language="Javascript">
function IsEmpty(){ 
	if(document.forms[0].name.value == "") {
		alert("Please enter valid name!");
		return false;
	}
	if(document.forms[0].address.value == "") {
		alert("Please enter valid address!");
		return false;
	}
    document.forms[0].submit();
}
</script>
</head>
<body>
<br>
<b>Payment Details</b>
<hr>
<center>
<br><a id="back" href="index.jsp"><img width=200 src="images/continue.png"></a>
<form method="get" action="SaveServlet">
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
			<td><font color="red">*</font>Name</td>
			<td><input id="name" name="name"></td>
		</tr>
		<tr>
			<td><font color="red">*</font>Address</td>
			<td><input id="address" name="address"></td>
		</tr>
		<tr>
			<td><font color="red">*</font>DB</td>
			<td><input type="checkbox" name="save" value="1" checked> Skip DB Save<br></td>
		</tr>
		<tr>
			<td colspan="2"><input id="insert" onclick="return IsEmpty();" type="submit" value="Buy"/></td>
		</tr>
</table>
</form>			
</center>
</body>
</html>
