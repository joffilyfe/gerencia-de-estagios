<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template" %>


<t:usuario>
	<jsp:body>
		<h1 class="page-header" >Editar empresa ${empresa.nome}</h1>
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
		    <input type="text" class="form-control" id="cnpj-input" placeholder="Número do CNPJ (apenas números)" name="cnpj" onkeyup="numeros(this)" value="${cnpj}">
		  </div>
		  
		  <div class="form-group">
		    <label for="endereco-input">Endereço</label>
		    <input type="text" class="form-control" id="endereco-input" placeholder="" name="endereco" value="${endereco}">
		  </div>
		  
		   <div class="form-group" id="tipo-3">
		    <label for="numero-input">Número</label>
		    <input type="text" class="form-control" id="numero-input" placeholder="Nº" name="numero" onkeyup="numeros(this)"  value="${numero}" >
		   
		  </div>
		  
		  <div class="form-group" id="tipo-4">
		    <label for="complemento-input">Complemento</label>
		    <input type="text" class="form-control" id="complemento-input" placeholder="" name="complemento" value="${complemento}" >
		  </div>
		  
	      <div class="form-group" id="tipo-4">
		    <label for="bairro-input">Bairro</label>
		    <input type="text" class="form-control" id="bairro-input" placeholder="" name="bairro" value="${bairro}">
		  </div>
		  
		   <div class="form-group" id="tipo-5">
		    <label for="cidade-input">Cidade</label>
		    <input type="text" class="form-control" id="cidade-input" placeholder="" name="cidade" value="${cidade}">
		  </div>
		  
		  <div class="form-group" id="tipo-3">
		    <label for="estado-input">Estado</label>
		    <input type="text" class="form-control" id="estado-input" placeholder="" name="estado" value="${estado}">
		  </div>
		  
		  <div class="form-group" id="tipo-3">
		    <label for="cep-input">CEP</label>
		    <input type="text" class="form-control" id="cep-input" placeholder="" name="cep" onkeyup="numerosIdentificacao(this)" value="${cep}">
		  </div>
		  
		  <div class="form-group">
		    <label for="referencia-input">Ponto de referência</label>
		    <input type="text" class="form-control" id="referencia-input" placeholder="" name="referencia" value="${referencia}">
		  </div>
		  
		   <div class="form-group">
		    <label for="responsavel-input">Responsável pela empresa</label>
		    <input type="text" class="form-control" id="responsavel-input" placeholder="Nome do responsável" name="responsavel" value="${responsavel}">
		  </div>
		  
		   <div class="form-group">
		    <label for="cargoResponsavel-input">Cargo do responsável</label>
		    <input type="text" class="form-control" id="cargoResponsavel-input" placeholder="Cargo do responsável" name="cargoResponsavel" value="${cargoResponsavel}">
		  </div>
		  
		   <div class="form-group">
		    <label for="nomeContatoSelecao-input">Nome do contato para seleção de estágio</label>
		    <input type="text" class="form-control" id="nomeContatoSelecao-input" placeholder="Nome" name="nomeContatoSelecao" value="${nomeContatoSelecao}">
		  </div>
		  
		   <div class="form-group" id="tipo-2">
		    <label for="telefone-input">Telefone</label>
		    <input type="text" class="form-control" id="telefone-input" placeholder="Número do telefone" name="telefone" onkeyup="numerosTelefonicos(this)" value="${telefone}">
		  </div>
		  
		  <div class="form-group" id="tipo-2">
		    <label for="fax-input">FAX</label>
		    <input type="text" class="form-control" id="fax-input" placeholder="Número do FAX" name="fax" onkeyup="numerosTelefonicos(this)" value="${fax}" >
		  </div>
		  
		  <div class="form-group" id="tipo-4">
		    <label for="fax-input">E-mail</label>
		    <input type="email" class="form-control" id="email-input" placeholder="E-mail" name="email"  value="${email}">
		  </div>
		  
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