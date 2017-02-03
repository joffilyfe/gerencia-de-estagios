<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script>
function apagar()
{
    if (confirm('Tem certeza que deseja encerrar este estágio?'))
    {

    	$("#desabilita").on("click", "#remover", function(e){
    	    $(this).closest('tr').remove(); 
    	});
    }
}


</script>
<t:layout>

		

	<jsp:body>
		<t:menuCoordenador></t:menuCoordenador>
		<div class="col-sm-8">
			<h3>Alunos estagiando</h3>
				<table class="table table-hover">
  
					<c:choose>
			   			 <c:when test="${not empty alunos}">	
					 		<c:forEach var="aluno" items="${alunos}">
								<tr>
									
									<td>${aluno.nome}</td>
									<td>
									<form method="post" action="${pageContext.request.contextPath}/coordenacao/encerrarEstagio">
									<input type="hidden" name="id" value="${aluno.id}">
									<button type="submit" class="btn btn-danger btn-md" name="remover" onclick="apagar()">Encerrar estagio</button>
									</form>
									</td>
									
								</tr>
							</c:forEach> 
						</c:when>
						
							
    			<c:otherwise>
			        <p >Nenhum aluno estagiando</p>
			    </c:otherwise>
					</c:choose>
    				
    			
				</table>
		</div>
	</jsp:body>


</t:layout>

