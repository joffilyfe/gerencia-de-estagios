<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" required="false" %>

<c:if test="${empty title}" >
 <c:set var="title" value="Internship IFPB" />
</c:if>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Estágios - ${title}</title>
		<link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet">
	</head>
	<body>
		<div class="header-default">
			<div class="container">
				<div class="header clearfix">
			        <nav>
			          <ul class="nav nav-pills pull-right">
						<c:if test="${not empty usuario}">
							<li><a>Logado como ${usuario.nome}</a></li>
						</c:if>
						<c:if test="${usuario.coordenador}">
							<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/coordenacao">Painel Coordenador</a></li>
						</c:if>
			            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
						<c:if test="${not empty usuario}">
							<li role="presentation"><a href="${pageContext.request.contextPath}/usuario/logout">Sair</a></li>
						</c:if>
						<c:if test="${empty usuario}">
							<li role="presentation"><a href="${pageContext.request.contextPath}/usuario/login">Login</a></li>
							<li role="presentation"><a href="${pageContext.request.contextPath}/usuario/cadastro">Cadastro</a></li>
						</c:if>
			          </ul>
			        </nav>
	      		</div>
			</div>
			<div class="container">
				<h1 class="title">${title}</h1>
			</div>
		</div>
		<div class="container">
			<c:if test="${not empty errors}">
				<div align="left">
					<div>
						<c:forEach var="msg" items="${errors}">
							<div class="alert alert-danger" role="alert">${msg}</div>
						</c:forEach>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty infos}">
				<div align="left">
					<div>
						<c:forEach var="msg" items="${infos}">
							<div class="alert alert-info" role="alert">${msg}</div>
						</c:forEach>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty success}">
				<div align="left">
					<div>
						<c:forEach var="msg" items="${success}">
							<div class="alert alert-success" role="alert">${msg}</div>
						</c:forEach>
					</div>
				</div>
			</c:if>
		</div>
<!-- 		<div class="container">
			<div class="header clearfix">
		        <nav>
		          <ul class="nav nav-pills pull-right">
					<c:if test="${not empty usuario}">
						<li><a>Logado como ${usuario.nome}</a></li>
					</c:if>
		            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
					<c:if test="${not empty usuario}">
						<li role="presentation"><a href="${pageContext.request.contextPath}/usuario/logout">Sair</a></li>
					</c:if>
					<c:if test="${empty usuario}">
						<li role="presentation"><a href="${pageContext.request.contextPath}/usuario/login">Login</a></li>
						<li role="presentation"><a href="${pageContext.request.contextPath}/usuario/cadastro">Cadastro</a></li>
					</c:if>
		          </ul>
		        </nav>
        	<h3 class="text-muted">Estágios</h3>
      		</div>
		</div> -->
		<div class="container">
			<div class="row">
				<jsp:doBody />
			</div>
		</div>
	<script src="${pageContext.request.contextPath}/assets/js/bootstrap.js"></script>
	</body>
</html>