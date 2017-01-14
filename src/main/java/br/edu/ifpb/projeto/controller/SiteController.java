package br.edu.ifpb.projeto.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SiteController extends ApplicationController {

	public SiteController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public RequestDispatcher index() {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/site/index.jsp");
		return dispatcher;
	}	
}
