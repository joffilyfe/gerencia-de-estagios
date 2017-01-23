<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>
	<jsp:body>
		<c:if test="${usuario.coordenador}">
			<p>Bem-vindo coordenador</p>
		</c:if>
		
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
										<button type="button" class="btn btn-primary btn-md" disabled>Liberada</button>
									</td>
								</c:when>
								<c:otherwise>
									<td>
										<form method="post" action="${pageContext.request.contextPath}/coordenacao/habilitar">
											<input type="hidden" name="id" value="${empresa.id}">
											<button type="submit" class="btn btn-primary btn-md">Liberar</button>
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
	</jsp:body>
</t:layout>