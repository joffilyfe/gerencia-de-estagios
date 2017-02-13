<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:body>
	 <t:menuCoordenador></t:menuCoordenador>
 		<div class="col-sm-8">
 		<h3>${vaga.titulo} - Candidatos</h3>
			<c:choose>
			    <c:when test="${not empty alunos}">
			    	<table class="table table-hover">
				    	<thead>
							<tr>
								<th>#</th>
								<th>Nome</th>
								<th>Competências</th>
								<th>Admitido</th>
								<th>Estagiando</th>
								<th>Ações</th>
							</tr>
		    			</thead>
				        <c:forEach var="aluno" items="${alunos}" varStatus="loop">
						<tbody>
							<tr>
								<td>${loop.count}</td>
								<td>${aluno.nome }</td>
								<td>${aluno.competencias}</td>
								<td>
									<c:if test="${aluno.admitido}">
										<span class="label label-success">Sim</span>
									</c:if>
									<c:if test="${not aluno.admitido}">
										<span class="label label-default">Não</span>
									</c:if>
								</td>
								<td>
									<c:if test="${aluno.estagiando}">
										<span class="label label-success">Sim</span>
									</c:if>

									<c:if test="${not aluno.estagiando}">
										<span class="label label-default">Não</span>
									</c:if>
								</td>
								<td>
									<c:if test="${not aluno.estagiando}">
										<a class="btn btn-primary" href="${pageContext.request.contextPath}/coordenacao/estagio/habilitar?aluno=${aluno.id}&vaga=${vaga.id}">Transformar em estágio</a>
									</c:if>
								</td>
							</tr>
						</tbody>
				        </c:forEach>
			        </table>
			    </c:when>     
			    <c:otherwise>
			        <p>Nenhum aluno interessado nesta vaga</p>
			    </c:otherwise>
			</c:choose>
			<ul class="pager">
  				<li class="previous"><a href="${pageContext.request.contextPath}/coordenacao/vagas/listar">Voltar</a></li>
  			</ul>
		</div>
	</jsp:body>
</t:layout>