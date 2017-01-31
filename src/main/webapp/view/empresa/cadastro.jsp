<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template" %>


<t:usuario>
	<jsp:body>
		<h1 class="page-header" >Cadastro de Oportunidades</h1>
		<h3 >Dados da Empresa</h3>
		
		<!-- Não sei se essa é melhor estrategia pra dividir os campos... Há muita repetição de codigo, porem com form inline não 
		ficou legal -->

		<form method="POST" >
		
		  <div class="form-group" id="tipo-1">
		    <label for="nome-input">Nome</label>
		    <input type="text" class="form-control" id="nome-input" placeholder="Nome completo" name="nome"  value="${nome}">
		  </div>
		  
		  <div class="form-group" id="tipo-2">
		    <label for="cnpj-input">CNPJ</label>
		    <input type="text" class="form-control" id="cnpj-input" placeholder="Número do CNPJ (apenas números)" name="cnpj" onkeyup="numeros(this)" value="">
		  </div>
		  
		  <div class="form-group">
		    <label for="endereco-input">Endereço</label>
		    <input type="text" class="form-control" id="endereco-input" placeholder="" name="endereco" value="">
		  </div>
		  
		   <div class="form-group" id="tipo-3">
		    <label for="numero-input">Número</label>
		    <input type="text" class="form-control" id="numero-input" placeholder="Nº" name="numero" onkeyup="numeros(this)"  value="" >
		   
		  </div>
		  
		  <div class="form-group" id="tipo-4">
		    <label for="complemento-input">Complemento</label>
		    <input type="text" class="form-control" id="complemento-input" placeholder="" name="complemento" value="">
		  </div>
		  
		    <div class="form-group" id="tipo-4">
		    <label for="bairro-input">Bairro</label>
		    <input type="text" class="form-control" id="bairro-input" placeholder="" name="bairro" value="">
		  </div>
		  
		   <div class="form-group" id="tipo-5">
		    <label for="cidade-input">Cidade</label>
		    <input type="text" class="form-control" id="cidade-input" placeholder="" name="cidade" value="">
		  </div>
		  
		  <div class="form-group" id="tipo-3">
		    <label for="estado-input">Estado</label>
		    <input type="text" class="form-control" id="estado-input" placeholder="" name="estado" value="">
		  </div>
		  
		  <div class="form-group" id="tipo-3">
		    <label for="cep-input">CEP</label>
		    <input type="text" class="form-control" id="cep-input" placeholder="" name="cep" onkeyup="numerosIdentificacao(this)" value="">
		  </div>
		  
		  <div class="form-group">
		    <label for="referencia-input">Ponto de referência</label>
		    <input type="text" class="form-control" id="referencia-input" placeholder="" name="referencia" value="">
		  </div>
		  
		   <div class="form-group">
		    <label for="responsavel-input">Responsável pela empresa</label>
		    <input type="text" class="form-control" id="responsavel-input" placeholder="Nome do responsável" name="responsavel" value="">
		  </div>
		  
		   <div class="form-group">
		    <label for="cargoResponsavel-input">Cargo do responsável</label>
		    <input type="text" class="form-control" id="cargoResponsavel-input" placeholder="Cargo do responsável" name="cargoResponsavel" value="">
		  </div>
		  
		   <div class="form-group">
		    <label for="nomeContatoSelecao-input">Nome do contato para seleção de estágio</label>
		    <input type="text" class="form-control" id="nomeContatoSelecao-input" placeholder="Nome" name="nomeContatoSelecao" value="">
		  </div>
		  
		   <div class="form-group" id="tipo-2">
		    <label for="telefone-input">Telefone</label>
		    <input type="text" class="form-control" id="telefone-input" placeholder="Número do telefone" name="telefone" onkeyup="numerosTelefonicos(this)" value="">
		  </div>
		  
		  <div class="form-group" id="tipo-2">
		    <label for="fax-input">FAX</label>
		    <input type="text" class="form-control" id="fax-input" placeholder="Número do FAX" name="fax" onkeyup="numerosTelefonicos(this)" value="">
		  </div>
		  
		  <div class="form-group" id="tipo-4">
		    <label for="fax-input">E-mail</label>
		    <input type="email" class="form-control" id="email-input" placeholder="E-mail" name="email"  value="${email }">
		  </div>
		  
		  <h3>Dados do Estágio</h3>
		  
		  <div class="form-group" >
		    <label for="areaEstagio-input">Área do estágio/Curso de formação do aluno</label>
		    <input type="text" class="form-control" id="areaEstagio-input" placeholder="" name="areaEstagio"  value="">
		  </div>
		  
		  <div class="form-group" >
		    <label for="setor-input">Setor da Empresa</label>
		    <input type="text" class="form-control" id="setor-input" placeholder="Nome do Setor" name="setor"  value="">
		  </div>
		  
		  <div class="form-group" >
		  	<label for="horario-input">Horário de Trabalho</label>
		  </div>
		  
		  <div class="form-group" id="tipo-6">
		  	<label for="horarioEntrada-input">Entrada</label>
		    <input type="time" class="form-control" id="horarioEntrada-input"  name="horarioEntrada"  value="">
		  </div>
		  
		  <div class="form-group" id="tipo-6">
		  <label for="horarioSaida-input">Saída</label>
		    <input type="time" class="form-control" id="horarioSaida-input"  name="horarioSaida"  value="">
		  </div>
		  
		  <div class="form-group" id="tipo-6">
		    <label for="valorBolsa-input">Valor da bolsa</label>
		    <input type="text" class="form-control" id="valorBolsa-input" placeholder="" name="valorBolsa" onkeyup="numerosMonetario(this)" value="">
		  </div>
		  
		  <div class="form-group" id="tipo-6">
		    <label for="vagas-input">Número de vagas</label>
		    <input type="text" class="form-control" id="vagas-input" placeholder="" name="vagas" onkeyup="numeros(this)" value="">
		  </div>
		  
		  <div class="form-group"  >
		 	 <label for="beneficios-input">Outros benefícios</label>
		  	<textarea  class="form-control noresize" id="beneficios-input" placeholder="" name="beneficios"></textarea>
		  </div>
		  
		   <div class="form-group" >
		   <label for="numeroAlunosSelecao-input">Número de alunos para a seleção</label>
		    <input type="text" class="form-control" id="numeroAlunosSelecao-input" placeholder="" name="numeroAlunosSelecao"  value="">
		 </div>
		  
		  <div class="form-group"  >
		   <label for="periodoDivulgacaoFim-input">Peridodo de divulgação</label>
		  </div>
		  
		  <div class="form-group" id="tipo-6">
		   <label for="periodoDivulgacaoFim-input">De:</label>
		    <input type="date" class="form-control" id="periodoDivulgacaoInicio-input" placeholder="(dd/mm/aaaa)" name="periodoDivulgacaoInicio"  value="">
		 </div>
		  
		  <div class="form-group" id="tipo-6">
		    <label for="periodoDivulgacaoInicio-input">Até:</label>
		     <input type="date" class="form-control" id="periodoDivulgacaoFim-input" placeholder="(dd/mm/aaaa)" name="periodoDivulgacaoFim"  value="">
		  </div> 
		  
		   <div class="form-group"  >
		   <label for="dataEntrevista-input">Data da Entrevista</label>
		   <input type="date" class="form-control" id="dataEntrevista-input" placeholder="(dd/mm/aaaa)" name="dataEntrevista"  value="">
		  </div>
		  
		  <div class="form-group"  >
		   <label  for="principaisAtividades-input">Principais atividades a serem desenvolvidas na empresa:</label>
		  	<textarea  class="form-control noresize" id="principaisAtividades-input" placeholder="" name="principaisAtividades" ></textarea>
		  </div>

			<div class="clearfix"></div>
		
		  <button type="submit" class="btn btn-primary" >Cadastrar</button>
		</form>
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