<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template" %>

<t:usuario>
	<jsp:body>
 		<div class="col-sm-8">
 		<h3>Vagas abertas</h3>
			<c:choose>
			    <c:when test="${not empty vagas}">
			    	<table class="table table-hover">
				    	<thead>
							<tr>
								<th>#</th>
								<th>Título</th>
								<th>Empresa</th>
								<th>Alunos Interessados</th>
							</tr>
		    			</thead>
				        <c:forEach var="vaga" items="${vagas}" varStatus="loop">
						<tbody>
							<tr>
								<td>${loop.count}</td>
								<td>
									<a href="${pageContext.request.contextPath}/coordenacao/vagas/detalhes?id=${vaga.id}">${vaga.titulo}</a>
								</td>
								<td>${vaga.empresa.nome}</td>
								<td>${fn:length(vaga.alunos)}</td>
							</tr>
						</tbody>
				        </c:forEach>
			        </table>
			    </c:when>     
			    <c:otherwise>
			        <p>Nenhuma empresa cadastrada no momento</p>
			    </c:otherwise>
			</c:choose>
			<ul class="pager">
  				<li class="previous"><a href="${pageContext.request.contextPath}/coordenacao">Voltar</a></li>
  			</ul>
		</div>
	</jsp:body>
</t:usuario>