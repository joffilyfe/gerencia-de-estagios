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

				// Create a new classed with request and response objects
				Object obj = controller.getDeclaredConstructor(HttpServletRequest.class, HttpServletResponse.class).newInstance(request, response);

				// Call the method
				Method method = controller.getMethod(methodName);

				// dispatch with that result of method invoked previously 
				dispatcher = (RequestDispatcher) method.invoke(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!response.isCommitted()) {
			dispatcher.forward(request, response);	
		}
	}
}
