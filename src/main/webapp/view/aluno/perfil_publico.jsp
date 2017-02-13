<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
	<jsp:body>
		<h1 class="page-header">Perfil Público</h1>

		<div class="col-md-12">
				<h4>Nome</h4>
				<div class="well well-sm">
					${aluno.nome}
				</div>
				
				<h4>Competências</h4>
				<div class="well well-sm">
					<c:if test="${not empty aluno.competencias }">
						${aluno.competencias}
					</c:if>
					<c:if test="${empty aluno.competencias }">
						<p>Este não informou suas competências.</p>
					</c:if>					
				</div>
		</div>
	</jsp:body>
</t:layout>