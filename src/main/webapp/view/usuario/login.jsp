<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:body>
		<h1 class="page-header">Login</h1>
		<form method="POST">
		  <div class="form-group">
		    <label for="email-input">E-mail</label>
		    <input type="email" class="form-control" id="email-input" placeholder="Email" name="email" value="${email}">
		  </div>
		  <div class="form-group">
		    <label for="senha-input">Senha</label>
		    <input type="password" class="form-control" id="senha-input" placeholder="Senha" name="senha" value="${senha}">
		  </div>
		  <button type="submit" class="btn btn-primary">Fazer login</button>
		</form>
	</jsp:body>
</t:layout>