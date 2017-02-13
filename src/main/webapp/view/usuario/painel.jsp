<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template" %>

<t:usuario>
	<jsp:body>
		<c:choose>
			<c:when test="${usuario.empresa && !usuario.habilitada}">
				<h1 class="page-header">Bem-vindo(a) ${usuario.nome}. Por favor aguarde avaliação do coordenador para postar vagas de estagio.</h1>				
	  		</c:when>
	  		<c:otherwise>
	  			<h1 class="page-header">Bem-vindo(a) ${usuario.nome}</h1>
	  		</c:otherwise>
  		</c:choose>
		
	</jsp:body>
</t:usuario>