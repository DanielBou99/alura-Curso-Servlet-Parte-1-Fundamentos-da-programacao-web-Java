<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:url value="/novaEmpresa" var="linkServletNovaEmpersa" />	

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FORM NOVA EMPRESA - JSP</title>
</head>
<body>
	<form action="${linkServletNovaEmpersa}" method="POST">
	
		Nome: <input type="text" name="nome" />
		Data Abertura <input type="text" name="data"/>
		<input type="submit" />
	
	</form>
</body>
</html>