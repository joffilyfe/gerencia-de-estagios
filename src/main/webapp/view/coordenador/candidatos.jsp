<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<t:layout>

	<jsp:body>
		<t:menuCoordenador></t:menuCoordenador>
		<div class="col-sm-8">
		<h3>Alunos candidatos a vaga</h3>
				<table class="table table-hover">
  
					<c:choose>
			   			 <c:when test="${not empty alunos}">	
					 		<c:forEach var="aluno" items="${alunos}">
								<tr>
									
									<td>${aluno.nome}</td>
									<td><a href="#">Ficha do Aluno</a></td>
									<td>      <a href="#">Ficha da Empresa </a> </td>
									<td><a href="#">Oferta</a></td>
								</tr>
							</c:forEach> 
						</c:when>
						
							
    			<c:otherwise>
			        <p >Nenhum aluno candidato a vaga</p>
			    </c:otherwise>
					</c:choose>
    				
    			
				</table>
		</div>
	</jsp:body>


</t:layout>