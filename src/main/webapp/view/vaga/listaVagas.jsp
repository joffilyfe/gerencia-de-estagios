<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:usuario>
	<jsp:body>
		<div class="col-sm-12">
			<c:if test="${not empty vagas}">
				<h3>Vagas abertas</h3>
				<table class="table table-hover">
				    	<thead>
							<tr>
								<th>Título</th>
								<th>Divulgação</th>
								<th>Empresa</th>
								<th>Descrição</th>
								<th>Nº de vagas</th>								
								<th></th>
							</tr>
		    			</thead>
		    			<c:forEach var="vaga" items="${vagas}">
		    				<c:if test="${vaga.qtdVagas gt 0}">
			    				<tr>
			    					<td>${vaga.titulo}</td>
			    					<td><fmt:formatDate value="${vaga.dataDivulgacaoInicio }" pattern="dd/MM/yyyy" /></td>
			    					<td>${vaga.empresa.nome}</td>
			    					<td>${vaga.descricao}</td>
			    					<td>${vaga.qtdVagas}</td>
			    					<td>
			    						<form method="post" action="${pageContext.request.contextPath}/vagas/candidatos">
											<input type="hidden" name="idvaga" value="${vaga.id}">
											<button type="submit" class="btn btn-primary btn-md" name="vercandidatos" value="true">Ver candidatos</button>
										</form>
									</td>
			    				</tr>
			    			</c:if>
		    			</c:forEach>
				</table>
			</c:if>
			<c:if test="${empty vagas}">
				<p>Nenhuma vaga cadastrada no momento</p>
			</c:if>
			<ul class="pager">
				<c:if test="${sessionScope.usuario.coordenador}">
					<li class="previous"><a href="${pageContext.request.contextPath}/coordenacao">Voltar</a></li>
				</c:if>
				<c:if test="${sessionScope.usuario.tipo_usuario eq 'Empresa'}">
					<li class="previous"><a href="${pageContext.request.contextPath}/empresa">Voltar</a></li>
				</c:if>
  			</ul>
		</div>

	</jsp:body>
</t:usuario>