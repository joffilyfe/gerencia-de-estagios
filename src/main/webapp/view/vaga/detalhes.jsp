<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout title="Detalhes de vaga">
	<jsp:body>
		<h1 class="page-header">${vaga.empresa.nome }</h1>

		<div class="col-md-6">
			<c:if test="${not empty vaga.empresa.nome }">
				<h4>Empresa</h4>
				<div class="well well-sm">
					${vaga.empresa.nome}
				</div>
			</c:if>
	
			<c:if test="${not empty vaga.valorDaBolsa }">
				<h4>Valor da bolsa</h4>
				<div class="well well-sm">R$ ${vaga.valorDaBolsa }</div>
			</c:if>
	
			<c:if test="${not empty vaga.descricao }">
				<h4>Descrição</h4>
				<div class="well well-sm">
					${vaga.descricao}
				</div>
			</c:if>
	
			<c:if test="${not empty vaga.beneficios }">
				<h4>Benefícios</h4>
				<div class="well well-sm">${vaga.beneficios }</div>
			</c:if>
		</div>
		
		<div class="col-md-6">
			<c:if test="${not empty vaga.qtdVagas }">
				<h4>Vagas abertas</h4>
				<div class="well well-sm">${vaga.qtdVagas }</div>
			</c:if>
	
			<c:if
				test="${not empty vaga.horarioEntrada and not empty vaga.horarioSaida }">
				<h4>Horários</h4>
				<div class="well well-sm">
					<ul>
						<li>Entrada: ${vaga.horarioEntrada}</li>
						<li>Saída: ${vaga.horarioSaida}</li>
					</ul>
				</div>
			</c:if>
	
			<c:if test="${not empty vaga.dataEntrevista }">
				<h4>Data da entrevista</h4>
				<div class="well well-sm">
					<fmt:formatDate value="${vaga.dataEntrevista }" pattern="dd/MM/yyyy" />
				</div>
			</c:if>
			
			<div>
				<ul class="pager">
	  				<li class="previous"><a href="${pageContext.request.contextPath}/vagas">Voltar para vagas</a></li>
	  			</ul>
			</div>
		</div>
	</jsp:body>
</t:layout>