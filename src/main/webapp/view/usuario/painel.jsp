<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template" %>

<t:usuario>
	<jsp:body>
		<h1 class="page-header">Bem-vindo(a) ${usuario.nome}</h1>
	</jsp:body>
</t:usuario>