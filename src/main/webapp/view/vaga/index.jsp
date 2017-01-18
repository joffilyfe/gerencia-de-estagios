<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Vagas">
	<jsp:body>
		<div class="col-md-12">
			<c:if test="${not empty vagas}">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">Listagem de vagas</div>

					<!-- Table -->
					<table class="table">
						<thead>
							<tr>
								<th>#</th>
								<th>Empresa</th>
								<th>Descrição curta</th>
								<th>Bolsa</th>
								<th>Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="vaga" items="${vagas}">
								<tr>
									<td>#</td>
									<td>${vaga.empresa.nome}</td>
									<td>${vaga.descricao}</td>
									<td>R$ ${vaga.valorDaBolsa}</td>
									<td><a href="/estagios/vaga/detalhes?id=${vaga.id}">Detalhes</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>
	</jsp:body>
</t:layout>