package com.park.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.park.beans.ServerError;
import com.park.core.Dispatcher;
import com.park.core.PostMessage;

/**
 * Servlet implementation class RequestServlet
 */
@WebServlet("/Request")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String protoName = request.getParameter("protoName");
		String data = request.getParameter("data");
		boolean bSuc = Dispatcher.getInstance().request_dispatch(response, protoName, data);
		if(!bSuc)
		{
			ServerError error = new ServerError();
			error.setError(-1);	
			PostMessage.error(response, error);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
