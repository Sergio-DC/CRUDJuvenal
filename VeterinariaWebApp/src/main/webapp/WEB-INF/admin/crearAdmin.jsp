<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<s:form action="admin_registrado_jsp">
		<s:textfield name="admin.username" label="Usuario"/>
		<s:textfield name="admin.password" label="Password"/>
		<s:submit value="enviar"/>
</s:form>
</body>
</html>