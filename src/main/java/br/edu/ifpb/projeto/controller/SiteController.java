package br.edu.ifpb.projeto.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SiteController {

	public RequestDispatcher index(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/site/index.jsp");
		return dispatcher;
	}	
}
