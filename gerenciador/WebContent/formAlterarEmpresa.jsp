<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:url value="/alteraEmpresa" var="linkServletNovaEmpersa" />	
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FORM NOVA EMPRESA - JSP</title>
</head>
<body>
	<form action="${linkServletNovaEmpersa}" method="POST">
	
		Nome: <input type="text" name="nome" value="${empresa.nome}" />
		Data Abertura <input type="text" name="data" 
		 value="<fmt:formatDate pattern="dd/MM/yyyy" value="${empresa.dataAbertura}"/>"/>
		<input type="hidden" name="id" value="${empresa.id }"  >
		<input type="submit" />
	
	</form>
</body>
</html>