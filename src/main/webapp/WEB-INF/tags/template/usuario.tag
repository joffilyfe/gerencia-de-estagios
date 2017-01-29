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
			  
			  <a href="${pageContext.request.contextPath}/coordenacao/empresas/habilitar" class="list-group-item">Editar meu perfil</a>
			</div>
		</div>
 		<div class="col-sm-8">
 			<jsp:doBody />
		</div>
	</jsp:body>
</t:layout>