<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template" %>

<t:usuario>
	<jsp:body>
		<c:choose>

			<c:when test="${usuario.empresa && !usuario.habilitada}">
				<h1 class="page-header" >Por favor aguarde avaliação do coordenador para postar vagas de estagio.</h1>
			</c:when>

			<c:otherwise>
				<h1 class="page-header" >Cadastro de Oportunidades</h1>
				
				<!-- Não sei se essa é melhor estrategia pra dividir os campos... Há muita repetição de codigo, porem com form inline não 
				ficou legal -->
		
				<form method="POST" >
				  <h3>Dados do Estágio</h3>
				  
				  <div class="form-group" >
				    <label for="titulo-input">Título da vaga</label>
				    <input type="text" class="form-control" id="titulo-input" placeholder="Título" name="titulo"  value="${titulo}">
				  </div>

				  <div class="form-group" >
				    <label for="areaEstagio-input">Área do estágio/Curso de formação do aluno</label>
				    <input type="text" class="form-control" id="areaEstagio-input" placeholder="Área onde o aluno atuará" name="areaEstagio"  value="${areaEstagio}">
				  </div>
				  
				  <div class="form-group" >
				    <label for="setor-input">Setor da Empresa</label>
				    <input type="text" class="form-control" id="setor-input" placeholder="Nome do Setor" name="setor"  value="${setor}">
				  </div>
				  
				  <div class="form-group" >
				  	<h3>Horário de Trabalho</h3>
				  </div>
				  
				  <div class="form-group" id="tipo-6">
				  	<label for="horarioEntrada-input">Entrada</label>
				    <input type="time" class="form-control" id="horarioEntrada-input"  name="horarioEntrada"  value="${horarioEntrada}">
				  </div>
				  
				  <div class="form-group" id="tipo-6">
				    <label for="horarioSaida-input">Saída</label>
				    <input type="time" class="form-control" id="horarioSaida-input"  name="horarioSaida"  value="${horarioSaida}">
				  </div>
				  
				  <div class="form-group" id="tipo-6">
				    <label for="valorBolsa-input">Valor da bolsa</label>
				    <input type="text" class="form-control" id="valorBolsa-input" placeholder="900" name="valorBolsa" onkeyup="numerosMonetario(this)" value="${valorBolsa}">
				  </div>
				  
				  <div class="form-group" id="tipo-6">
				    <label for="vagas-input">Número de vagas</label>
				    <input type="text" class="form-control" id="vagas-input" placeholder="10" name="vagas" onkeyup="numeros(this)" value="${vagas}">
				  </div>
				  
				  <div class="form-group"  >
				    <label for="beneficios-input">Outros benefícios</label>
				  	<textarea  class="form-control noresize" id="beneficios-input" placeholder="Vale alimentação, passe estudantil, área de lazer, café." name="beneficios">${beneficios}</textarea>
				  </div>
				  
				  <div class="form-group" >
				    <label for="numeroAlunosSelecao-input">Número de alunos para a seleção</label>
				    <input type="text" class="form-control" id="numeroAlunosSelecao-input" placeholder="" name="numeroAlunosSelecao"  value="${numeroAlunosSelecao}">
				  </div>
				  
				  <div class="form-group"  >
				   <h3>Peridodo de divulgação</h3>
				  </div>
				  
				  <div class="form-group" id="tipo-6">
				    <label for="periodoDivulgacaoFim-input">De:</label>
				    <input type="text" class="form-control" id="periodoDivulgacaoInicio-input" placeholder="(dd/mm/aaaa)" name="periodoDivulgacaoInicio-data"  value="${periodoDivulgacaoInicio}">
				  </div>
				  
				  <div class="form-group" id="tipo-6">
				    <label for="periodoDivulgacaoInicio-input">Até:</label>
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