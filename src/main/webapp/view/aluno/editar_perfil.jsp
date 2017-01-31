<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template" %>

<t:usuario>
	<jsp:body>
		<h1 class="page-header">Editar Perfil</h1>
		
		<div class="col-md-12">
			<div class="row">
			<form method="POST">
			  <div class="form-group">
			    <label for="matricula-input">Matrícula</label>
			    <input type="number" class="form-control" id="matricula-input" placeholder="2016000000" name="matricula" value="${matricula}">
			  </div>
			  <div class="form-group">
			    <label for="competencias-input">Cometências</label>
			    <textarea class="form-control" id="competencias-input" placeholder="Habilidade em programação, habilidade em PHP." name="competencias">${competencias}</textarea>
			  </div>
			  <button class="btn btn-success">Atualizar</button>	
			</form>
			</div>
		</div>
	</jsp:body>
</t:usuario>