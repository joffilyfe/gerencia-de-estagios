<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<t:layout title="Ficha da Empresa">

<jsp:body>
	
				<table align="center" width=400 height=400">
  
						<tr>
							<td><h2>Nome :</h2> ${usuario.nome}</td><br>
							<td><h3>Competencias do aluno:</h3> ${aluno.competencias}</td>
						</tr>
						
				</table>
		
	</jsp:body>


</t:layout>