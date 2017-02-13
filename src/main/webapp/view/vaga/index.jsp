<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Vagas">
	<jsp:body>
		<div class="col-md-12">
			<h1 class="page-header">Vagas abertas</h1>
			<c:if test="${not empty vagas}">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">Listagem de vagas</div>

					<!-- Table -->
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>Título</th>
								<th>Empresa</th>
								<th>Descrição curta</th>
								<th>Bolsa</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="vaga" items="${vagas}" varStatus="loop">
								<tr>
									<td>${loop.count}</td>
									<td>${vaga.titulo}</td>
									<td>
										<a href="${pageContext.request.contextPath}/empresa/perfil?id=${vaga.empresa.id}" target="_blank">${vaga.empresa.nome}</a>
									</td>
									<td>${vaga.descricao}</td>
									<td>R$ ${vaga.valorDaBolsa}</td>
									<td><a class="btn btn-default" href="${pageContext.request.contextPath}/vaga/detalhes?id=${vaga.id}">Detalhes</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>
	</jsp:body>
</t:layout>