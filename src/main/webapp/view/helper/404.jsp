<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:body>
		<style>
			.error-template {padding: 40px 15px;text-align: center;}
			.error-actions {margin-top:15px;margin-bottom:15px;}
			.error-actions .btn { margin-right:10px; }
		</style>
	    <div class="row">
	        <div class="col-md-12">
	            <div class="error-template">
	                <h1>Oops!</h1>
	                <h2>404 Not Found</h2>
	                <div class="error-details">
	                    A página que você está procurando não foi encontrada.
	                </div>
	            </div>
	        </div>
	    </div>
	</jsp:body>
</t:layout>