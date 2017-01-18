<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Titulo da vaga?">
	<jsp:body>
		<h1 class="page-header">Título da vaga</h1>
		<p>${vaga.descricao}</p>
		<p>${vaga.valorDaBolsa}</p>
		<p>${vaga.empresa.nome}</p>
	</jsp:body>
</t:layout>