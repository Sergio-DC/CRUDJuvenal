<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Empleado Registrado</h1>
	
	<label><b>N�mero de Empleado</b></label><br>
	<s:property value="empleado.no_empleado"/><br>
	<label><b>Nombre Completo</b></label><br>
	<s:property value="empleado.nombre_completo"/><br>
	<label><b>Direcci�n</b></label><br>
	<s:property value="empleado.direccion"/><br>
	<label><b>Tel�fono</b></label><br>
	<s:property value="empleado.telefono"/><br>
	<label><b>Puesto</b></label><br>
	<s:property value="empleado.puesto"/><br>
	<label><b>Especialidad M�dica</b></label><br>
	<s:property value="empleado.especialidad"/><br>
	<label><b>Estado</b></label><br>
	<s:property value="empleado.estado"/><br>
</body>
</html>