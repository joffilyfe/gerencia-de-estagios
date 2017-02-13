<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template" %>

<t:usuario>
	<jsp:body>
 		<div class="col-sm-8">
 		<h3>Transformar vaga em portunidade de estágio</h3>
 			<form method="POST">
 				<input type="hidden" name="aluno" value="${aluno.id}">
 				<input type="hidden" name="vaga" value="${vaga.id}">
 				
				  <div class="form-group">
				    <label for="obrigatorio-input">Obrigatóriedade</label>
				    <select class="form-control" name="obrigatorio" id="obrigatorio-input">
				    	<option value="">Selecione o tipo de estágio</option>
				    	<option value="true">Obrigatório</option>
				    	<option value="false">Não obrigatório</option>
				    </select>
				  </div>
				  
				  <div class="form-group">
				    <label for="encerrado-input">O estágio está encerrado?</label>
				    <select class="form-control" name="encerrado" id="encerrado-input">
				    	<option value="">Selecione o estado de estágio</option>
				    	<option value="true">Encerrado</option>
				    	<option value="false">Não Encerrado</option>
				    </select>
				  </div>
				  <button class="btn btn-success">Autorizar estágio</button>
				  <a class="btn btn-default" href="${pageContext.request.contextPath}/coordenacao/vagas/detalhes?id=${vaga.id }">Cancelar</a>
 			</form>
		</div>
	</jsp:body>
</t:usuario>