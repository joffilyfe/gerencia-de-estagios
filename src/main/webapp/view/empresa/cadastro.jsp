<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template" %>

<t:usuario>
	<jsp:body>
		<c:choose>

			<c:when test="${usuario.empresa && !usuario.habilitada}">
				<h1 class="page-header" >Por favor aguarde avalia��o do coordenador para postar vagas de estagio.</h1>
			</c:when>

			<c:otherwise>
				<h1 class="page-header" >Cadastro de Oportunidades</h1>
				
				<!-- N�o sei se essa � melhor estrategia pra dividir os campos... H� muita repeti��o de codigo, porem com form inline n�o 
				ficou legal -->
		
				<form method="POST" >
				  <h3>Dados do Est�gio</h3>
				  
				  <div class="form-group" >
				    <label for="titulo-input">T�tulo da vaga</label>
				    <input type="text" class="form-control" id="titulo-input" placeholder="T�tulo" name="titulo"  value="${titulo}">
				  </div>

				  <div class="form-group" >
				    <label for="areaEstagio-input">�rea do est�gio/Curso de forma��o do aluno</label>
				    <input type="text" class="form-control" id="areaEstagio-input" placeholder="�rea onde o aluno atuar�" name="areaEstagio"  value="${areaEstagio}">
				  </div>
				  
				  <div class="form-group" >
				    <label for="setor-input">Setor da Empresa</label>
				    <input type="text" class="form-control" id="setor-input" placeholder="Nome do Setor" name="setor"  value="${setor}">
				  </div>
				  
				  <div class="form-group" >
				  	<h3>Hor�rio de Trabalho</h3>
				  </div>
				  
				  <div class="form-group" id="tipo-6">
				  	<label for="horarioEntrada-input">Entrada</label>
				    <input type="time" class="form-control" id="horarioEntrada-input"  name="horarioEntrada"  value="${horarioEntrada}">
				  </div>
				  
				  <div class="form-group" id="tipo-6">
				    <label for="horarioSaida-input">Sa�da</label>
				    <input type="time" class="form-control" id="horarioSaida-input"  name="horarioSaida"  value="${horarioSaida}">
				  </div>
				  
				  <div class="form-group" id="tipo-6">
				    <label for="valorBolsa-input">Valor da bolsa</label>
				    <input type="text" class="form-control" id="valorBolsa-input" placeholder="900" name="valorBolsa" onkeyup="numerosMonetario(this)" value="${valorBolsa}">
				  </div>
				  
				  <div class="form-group" id="tipo-6">
				    <label for="vagas-input">N�mero de vagas</label>
				    <input type="text" class="form-control" id="vagas-input" placeholder="10" name="vagas" onkeyup="numeros(this)" value="${vagas}">
				  </div>
				  
				  <div class="form-group"  >
				    <label for="beneficios-input">Outros benef�cios</label>
				  	<textarea  class="form-control noresize" id="beneficios-input" placeholder="Vale alimenta��o, passe estudantil, �rea de lazer, caf�." name="beneficios">${beneficios}</textarea>
				  </div>
				  
				  <div class="form-group" >
				    <label for="numeroAlunosSelecao-input">N�mero de alunos para a sele��o</label>
				    <input type="text" class="form-control" id="numeroAlunosSelecao-input" placeholder="" name="numeroAlunosSelecao"  value="${numeroAlunosSelecao}">
				  </div>
				  
				  <div class="form-group"  >
				   <h3>Peridodo de divulga��o</h3>
				  </div>
				  
				  <div class="form-group" id="tipo-6">
				    <label for="periodoDivulgacaoFim-input">De:</label>
				    <input type="text" class="form-control" id="periodoDivulgacaoInicio-input" placeholder="(dd/mm/aaaa)" name="periodoDivulgacaoInicio-data"  value="${periodoDivulgacaoInicio}">
				  </div>
				  
				  <div class="form-group" id="tipo-6">
				    <label for="periodoDivulgacaoInicio-input">At�:</label>
				    <input type="text" class="form-control" id="periodoDivulgacaoFim-input" placeholder="(dd/mm/aaaa)" name="periodoDivulgacaoFim-data"  value="">
				  </div> 
				  
				  <div class="form-group"  >
				    <label for="dataEntrevista-input">Data da Entrevista</label>
				    <input type="text" class="form-control" id="dataEntrevista-input" placeholder="(dd/mm/aaaa)" name="dataEntrevista-data"  value="">
				  </div>
				  
				  <div class="form-group"  >
				   <label  for="principaisAtividades-input">Principais atividades a serem desenvolvidas na empresa:</label>
				  	<textarea  class="form-control noresize" id="principaisAtividades-input" placeholder="Desenvolver sistemas na linguagem Ruby utilizando frameworks como Sinatra e Ruby on Rails. Trabalhar com ReactJS." name="principaisAtividades" >${principaisAtividades}</textarea>
				  </div>
		
				  <div class="clearfix"></div>
				
				  <button type="submit" class="btn btn-primary" >Cadastrar</button>
				</form>
			</c:otherwise>

		</c:choose>	

	<script>
		//Para numeros como: cep, cnpj ...
	    function numerosIdentificacao(num) {
	        var rgx = /[^0-9.-]/;
	        rgx.lastIndex = 0;
	        var campo = num;
	        if (rgx.test(campo.value)) {
	          campo.value = "";
	        }
	    }
	    
	   // Para fax e telefone 
	    function numerosTelefonicos(num) {
	        var rgx = /[^0-9-]/;
	        rgx.lastIndex = 0;
	        var campo = num;
	        if (rgx.test(campo.value)) {
	          campo.value = "";
	        }
	    }
	    
	    //Para numeros sem caracteres especiais
	    function numeros(num) {
	        var rgx = /[^0-9]/;
	        rgx.lastIndex = 0;
	        var campo = num;
	        if (rgx.test(campo.value)) {
	          campo.value = "";
	        }
	    }
	    
	   //Para numeros do tipo monetario
	    function numerosMonetario(num) {
	        var rgx = /[^0-9.]/;
	        rgx.lastIndex = 0;
	        var campo = num;
	        if (rgx.test(campo.value)) {
	          campo.value = "";
	        }
	    }
	</script>

	</jsp:body>
</t:usuario>