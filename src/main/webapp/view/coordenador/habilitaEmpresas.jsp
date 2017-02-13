<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template" %>

<t:usuario>
	<jsp:body>
 		<div class="col-sm-8">
 		<h3>Habilitar empresa</h3>
			<c:choose>
			    <c:when test="${not empty empresas}">
			    	<table class="table table-hover">
				    	<thead>
							<tr>
								<th>Nome</th>
								<th>Email</th>
								<th>Situação</th>
							</tr>
		    			</thead>
				        <c:forEach var="empresa" items="${empresas}">
						<tbody>
							<tr>
								<td>${empresa.nome}</td>
								<td>${empresa.email}</td>
								<c:choose>
									<c:when test="${empresa.habilitada}">
										<td>
											<form method="post" action="${pageContext.request.contextPath}/coordenacao/empresas/habilitar">
												<button type="button" class="btn btn-primary btn-md" disabled>Habilitada</button>
												<input type="hidden" name="id" value="${empresa.id}">
												<button type="submit" class="btn btn-danger btn-md" name="habilitar" value ="false" active>Desabilitar</button>
											</form>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<form method="post" action="${pageContext.request.contextPath}/coordenacao/empresas/habilitar">
												<input type="hidden" name="id" value="${empresa.id}">
												<button type="submit" class="btn btn-primary btn-md" name="habilitar" value="true">Habilitar</button>
											</form>
										</td>
									</c:otherwise>
								</c:choose>
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