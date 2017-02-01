package br.edu.ifpb.projeto.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "", "/" })
public class SingleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String controllerPackage = "br.edu.ifpb.projeto.controller.";
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/site/index.jsp");

		Router router = new Router();
		String requestedURL = request.getServletPath();

		if (requestedURL.equals("")) {
			requestedURL = "/";
		}

		ArrayList<String> rcontroller = router.getURL(requestedURL);

		if (rcontroller == null) {
			dispatcher = request.getRequestDispatcher("/view/helper/404.jsp");
			return;
		}

		request.setAttribute("router", router);

		try {
			Class<?> controller = Class.forName(controllerPackage + rcontroller.get(0));

			// Create a new classed with request and response objects
			Object obj = controller.getDeclaredConstructor(HttpServletRequest.class, HttpServletResponse.class)
					.newInstance(request, response);

			// Call the method
			Method method = controller.getMethod(rcontroller.get(1));

			// dispatch with that result of method invoked previously
			dispatcher = (RequestDispatcher) method.invoke(obj);
		} catch (Exception e) {
			dispatcher = request.getRequestDispatcher("/view/helper/404.jsp");
			e.printStackTrace();
		}

		if (!response.isCommitted()) {
			dispatcher.forward(request, response);
		}

	}
}
