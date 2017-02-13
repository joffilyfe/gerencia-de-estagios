<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:body>
	 <t:menuCoordenador></t:menuCoordenador>
 		<div class="col-sm-8">
 		<h3>Editando Estágio</h3>
 			<form method="POST">
 				<input type="hidden" name="id" value="${estagio.id}">
				  <div class="form-group">
				    <label for="obrigatorio-input">Obrigatóriedade</label>
				    <select class="form-control" name="obrigatorio" id="obrigatorio-input">
				    	<option value="">Selecione o tipo de estágio</option>
				    	<c:if test="${estagio.obrigatorio}">
				    		<option value="true" selected>Obrigatório</option>
				    		<option value="false">Não obrigatório</option>
				    	</c:if>
				    	<c:if test="${not estagio.obrigatorio}">
				    		<option value="true">Obrigatório</option>
				    		<option value="false" selected>Não obrigatório</option>
				    	</c:if>
				    </select>
				  </div>
				  
				  <div class="form-group">
				    <label for="encerrado-input">O estágio está encerrado?</label>
				    <select class="form-control" name="encerrado" id="encerrado-input">
				    	<option value="">Selecione o estado de estágio</option>
				    	<c:if test="${estagio.encerrado}">
							<option value="true" selected>Encerrado</option>
				    		<option value="false">Não Encerrado</option>
				    	</c:if>
				    	<c:if test="${not estagio.encerrado}">
							<option value="true">Encerrado</option>
				    		<option value="false" selected>Não Encerrado</option>
				    	</c:if>

				    </select>
				  </div>
				  <button class="btn btn-success">Salvar</button>
				  <a class="btn btn-default" href="${pageContext.request.contextPath}/coordenacao/estagios/listar">Cancelar</a>
 			</form>
		</div>
	</jsp:body>
</t:layout>