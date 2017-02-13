<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
	<jsp:body>
		<h1 class="page-header">Empresa</h1>
		<c:if test="${not empty empresa.nome }">			
			<div class="col-md-6">
				<h4>Nome</h4>
				<div class="well well-sm">
					<p>${empresa.nome}</p>					
				</div>
			</div>
		</c:if>

		<c:if test="${not empty empresa.cnpj }">			
			<div class="col-md-6">
				<h4>CNPJ</h4>
				<div class="well well-sm">
					<p>${empresa.cnpj}</p>					
				</div>
			</div>
		</c:if>

		<div>
			<!-- cidade -->
			<c:if test="${not empty empresa.cidade }">			
				<div class="col-md-6">
					<h4>Localização</h4>
					<div class="well well-sm">
						<p>${empresa.endereco} ${empresa.numero}, ${empresa.bairro}, ${empresa.cidade}, ${empresa.estado} - ${empresa.cep}</p>					
					</div>
				</div>
			</c:if>
			<!-- bairro -->
			<c:if test="${not empty empresa.nomeDoContato}">			
				<div class="col-md-6">
					<h4>Informações para contato</h4>
					<div class="well well-sm">
						<ul>
							<li>Nome do contato: ${empresa.nomeDoContato}</li>
							<li>Cargo: ${empresa.cargoResponsavel}</li>
							<li>Telefone: ${empresa.telefone}</li>
						</ul>					
					</div>
				</div>
			</c:if>
		</div>
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
									<td>${vaga.empresa.nome}</td>
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