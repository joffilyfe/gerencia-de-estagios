<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:body>
	 <t:menuCoordenador></t:menuCoordenador>
 		<div class="col-sm-8">
 		<h3>Empresas</h3>
			<c:choose>
			    <c:when test="${not empty empresas}">
			    	<table class="table table-hover">
				    	<thead>
							<tr>
								<th>Nome</th>
								<th>Email</th>
								<th>Situação</th>
								<th>Responsável</th>
								<th>Telefone</th>
								<th>E-mail</th>
							</tr>
		    			</thead>
				        <c:forEach var="empresa" items="${empresas}">
						<tbody>
							<tr>
								<td>${empresa.nome}</td>
								<td>${empresa.email}</td>
								<c:choose>
									<c:when test="${empresa.habilitada}">
										<td>Liberada</td>
									</c:when>
								</c:choose>
								<td>${empresa.responsavel}</td>
								<td>${empresa.telefone}</td>
								<td>${empresa.email}</td>
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
</t:layout>