package br.edu.ifpb.projeto.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class SingleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] path = request.getRequestURI().split("/");
		String controllerPackage = "br.edu.ifpb.projeto.controller.";
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/site/index.jsp");

		if (path.length > 2 && path[2] != null) {

			String controllerName = path[2] + "Controller";
			String methodName = "index";
			
			controllerName = controllerName.substring(0, 1).toUpperCase() + controllerName.substring(1);
			
			if (path.length >= 4 && path[3] != null) {
				methodName = path[3];
			}

			try {
				Class<?> controller = Class.forName(controllerPackage + controllerName);
				Object obj = controller.newInstance();
				Method method = controller.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
				dispatcher = (RequestDispatcher) method.invoke(obj, request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!response.isCommitted()) {
			dispatcher.forward(request, response);	
		}
	}
}
