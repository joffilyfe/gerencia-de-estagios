<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>

	<jsp:body>
	  <div class="col-sm-4">
			 <div class="list-group header">
			  <a href="#" class="list-group-item disabled"><strong>Menu</strong></a>
			  
			  <%-- Menu para empresas --%>
			  <c:if test="${usuario.empresa}">
			  	<a href="${pageContext.request.contextPath}/#" class="list-group-item">Vagas ofertadas</a>
			  	<a href="${pageContext.request.contextPath}/#" class="list-group-item">Cadastrar vaga</a>
			  </c:if>
			  
			  <%-- Edição do perfil --%>
			  <c:if test="${usuario.empresa}">
			    <a href="${pageContext.request.contextPath}/empresa/cadastro" class="list-group-item">Editar meu perfil</a>
			  </c:if>

			  <c:if test="${usuario.aluno}">
			    <a href="${pageContext.request.contextPath}/usuario/perfil/editar" class="list-group-item">Editar meu perfil</a>
			  </c:if>

			</div>
		</div>
 		<div class="col-sm-8">
 			<jsp:doBody />
		</div>
	</jsp:body>
</t:layout>