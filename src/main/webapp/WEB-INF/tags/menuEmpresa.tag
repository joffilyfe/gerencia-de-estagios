<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" required="false" %>

	  <div class="col-sm-4">
			<c:if test="${usuario.tipo_usuario eq 'Empresa'}">
				<h2>Vagas ${usuario.nome}</h2>
			</c:if>
			 <div class="list-group">
			  <a href="${pageContext.request.contextPath}/empresa" class="list-group-item active"><strong>Opções</strong></a>
			  <a href="${pageContext.request.contextPath}/vagas/visualizarvagas" class="list-group-item">Visualizar Vagas</a>
			</div>
		</div>
