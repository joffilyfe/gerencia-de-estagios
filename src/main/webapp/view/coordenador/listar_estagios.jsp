<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:body>
	 <t:menuCoordenador></t:menuCoordenador>
 		<div class="col-sm-8">
 		<h3>Estágios</h3>
			<c:choose>
			    <c:when test="${not empty estagios}">
			    	<table class="table table-hover">
				    	<thead>
							<tr>
								<th>#</th>
								<th>Aluno</th>
								<th>Vaga</th>
								<th>Empresa</th>
								<th>Obrigatório</th>
								<th>Encerrado</th>
								<th>Ações</th>
							</tr>
		    			</thead>
				        <c:forEach var="estagio" items="${estagios}" varStatus="loop">
						<tbody>
							<tr>
								<td>${loop.count}</td>
								<td>${estagio.aluno.nome}</td>
								<td>
									<a href="${pageContext.request.contextPath}/vaga/detalhes?id=${estagio.vaga.id}" target="blank">${estagio.vaga.titulo}</a>
								</td>
								<td>${estagio.empresa.nome}</td>
								<td>
									<c:if test="${estagio.obrigatorio}">
										<span class="label label-success">Sim</span>
									</c:if>
									<c:if test="${not estagio.obrigatorio}">
										<span class="label label-default">Não</span>
									</c:if>
								</td>
								<td>
									<c:if test="${estagio.encerrado}">
										<span class="label label-success">Sim</span>
									</c:if>
									<c:if test="${not estagio.encerrado}">
										<span class="label label-default">Não</span>
									</c:if>
								</td>
								<td>
									<a class="btn btn-warning" href="${pageContext.request.contextPath}/coordenacao/estagios/editar?id=${estagio.id}">Editar</a>
								</td>
							</tr>
						</tbody>
				        </c:forEach>
			        </table>
			    </c:when>     
			    <c:otherwise>
			        <h3>Não há estágios ativos no momento</h3>
			    </c:otherwise>
			</c:choose>
			<ul class="pager">
  				<li class="previous"><a href="${pageContext.request.contextPath}/coordenacao">Voltar</a></li>
  			</ul>
		</div>
	</jsp:body>
</t:layout>