<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Empresa Criada</title>
</head>
<body>

	<c:if test="${not empty nome_empresa }">
		Empresa Cadastrada ${nome_empresa } com sucesso, ok!!
	</c:if>

	<c:if test="${empty nome_empresa }">
		Nenhuma Empresa Cadastrada!!
	</c:if>


</body>
</html>