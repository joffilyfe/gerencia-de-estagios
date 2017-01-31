<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<t:layout title="Listagem de Candidatos">

<jsp:body>
	
				<table align="center" width=400 height=400 ">
  
						
							<c:forEach var="aluno" items="${alunos}">
								<tr>
									
									<td>${usuario.nome}</td>
									<td><a href="/estagios/usuario/fichaAluno?id=${aluno.id}">Ficha do Aluno </a> | </td> 
									<td><a href="/estagios/usuario/fichaEmpresa?id=${aluno.id}">Ficha da Empresa </a> |</td>
									<td><a href="/estagios/usuario/oferta?id=${aluno.id}">Oferta</a></td>
								</tr>
							</c:forEach>
    			
	</table>
		
	</jsp:body>


</t:layout>