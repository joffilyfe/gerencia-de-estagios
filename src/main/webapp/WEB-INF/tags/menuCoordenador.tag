<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" required="false" %>

	  <div class="col-sm-4">
			<c:if test="${usuario.coordenador}">
				<h2>Bem-vindo coordenador</h2>
			</c:if>
			 <div class="list-group">
			  <a href="${pageContext.request.contextPath}/coordenacao" class="list-group-item active"><strong>Opções</strong></a>
			  <a href="${pageContext.request.contextPath}/coordenacao/empresas/listar" class="list-group-item">Visualizar empresas</a>
			  <a href="${pageContext.request.contextPath}/coordenacao/empresas/habilitar" class="list-group-item">Habilitar empresa</a>
			  <a href="${pageContext.request.contextPath}/coordenacao/vagas/listar" class="list-group-item">Visualizar Vagas</a>
			  <a href="#" class="list-group-item">Habilitar vaga</a>
			</div>
		</div>
