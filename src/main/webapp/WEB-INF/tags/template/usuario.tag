<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:body>
	  <div class="col-sm-3">
			 <div class="list-group header">
			  <a href="#" class="list-group-item disabled"><strong>Menu</strong></a>

			  <%-- Menu para empresas --%>
			  <c:if test="${usuario.empresa}">
				<a href="${pageContext.request.contextPath}/vagas/visualizarvagas" class="list-group-item">Vagas ofertadas</a>
			  	<c:if test="${usuario.habilitada}">
		  			<a href="${pageContext.request.contextPath}/empresa/cadastro" class="list-group-item">Cadastrar vaga</a>
		  		</c:if>
		  		<a href="${pageContext.request.contextPath}/empresa/editar" class="list-group-item">Editar meu perfil</a>
			  </c:if>

			  <%-- Menu para aluno --%>
			  <c:if test="${usuario.aluno}">
			    <a href="${pageContext.request.contextPath}/aluno/perfil/editar" class="list-group-item">Editar meu perfil</a>
			  </c:if>
			  
			  <%-- Menu para coordenador --%>
			  <c:if test="${usuario.coordenador}">
				<a href="${pageContext.request.contextPath}/coordenacao/empresas/listar" class="list-group-item">Empresas</a>
			  	<a href="${pageContext.request.contextPath}/coordenacao/vagas/listar" class="list-group-item">Vagas</a>
			  	<a href="${pageContext.request.contextPath}/coordenacao/estagios/listar" class="list-group-item">Estágios</a>
              </c:if>
			</div>
		</div>
 		<div class="col-sm-9">
 			<jsp:doBody />
		</div>
	</jsp:body>
</t:layout>
