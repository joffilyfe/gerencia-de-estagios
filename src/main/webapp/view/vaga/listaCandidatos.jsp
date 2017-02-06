<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>    

<t:layout>
	<jsp:body>	
		<c:if test="${sessionScope.usuario.coordenador}">
			<t:menuCoordenador></t:menuCoordenador>
		</c:if>
		<c:if test="${sessionScope.usuario.tipo_usuario eq 'Empresa'}">
			<t:menuEmpresa></t:menuEmpresa>
		</c:if>
		<div class="col-sm-8">
			<h3>Alunos Candidatos</h3>			
			<c:if test="${not empty candidatos}">
					<h4>Vaga: ${vaga.titulo}</h4>
					<table class="table table-hover">
				    	<thead>
							<tr>
								<th>Nome</th>
								<th>Matrícula</th>
								<th>Competências</th>
								<th>Ações</th>
							</tr>
				    	</thead>
				    	<c:forEach var="aluno" items="${candidatos}">
				    		<tr>
				    			<td>${aluno.nome}</td>
				    			<td>${aluno.matricula}</td>
				    			<td>${aluno.competencias}</td>	
				    			<td>
				    				<form method="post" action="${pageContext.request.contextPath}/estagiarios/admissao">
										<input type="hidden" name="idaluno" value="${aluno.id}">
										<input type="hidden" name="idvaga" value="${vaga.id}">

										<c:if test="${usuario.tipo_usuario eq 'Empresa' and not aluno.admitido}">
											<button type="submit" class="btn btn-success btn-md" name="admitircandidato" value="true">Admitir</button>											
										</c:if>

										<c:if test="${aluno.admitido}">
											<button class="btn btn-default" disabled>Já admitido</button>
										</c:if>
									</form>		    						
				    			</td>		
				    		</tr>
				    	</c:forEach>
					</table>
			</c:if>
			<c:if test="${empty candidatos}">
				<p>Nenhum candidato até o momento</p>
			</c:if>
			<ul class="pager">
				<li class="previous"><a href="${pageContext.request.contextPath}/vagas/visualizarvagas">Voltar</a></li>
		  	</ul>
	  	</div>
	</jsp:body>
</t:layout>