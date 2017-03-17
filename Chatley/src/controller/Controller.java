package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Person;
import service.ChatleyService;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChatleyService service;
	
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		watDoen(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		watDoen(request, response);
	}
	
	public void init() throws ServletException{
		super.init();
		service = new ChatleyService();
	}
	
	private void watDoen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		if (action==null){
			if (request.getSession().getAttribute("user") != null){
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}else {
				action = "loginPage";
			}
		}
		
		switch (action) {
		case "loginPage":
			loginPage(request, response);
			break;
		
		case "login":
			login(request, response);
			break;
			
		case "getStatus":
			Person person = (Person) request.getSession().getAttribute("user");
			String status = person.getStatus();
			response.getWriter().write(status);
			break;
			
		case "updateStatus":
			String nieuweStatus = request.getParameter("nieuweStatus");
			Person user = (Person) request.getSession().getAttribute("user");
			user.setStatus(nieuweStatus);
			response.getWriter().write(user.getStatus());
			break;
		}
		
	}
	
	private void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return;
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Person person = null;
		
		try {
			person = service.getPersonAlsWachtwoordCorrect(username, password);
		} catch (Exception e) {
			request.setAttribute("fout", e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("user", person);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		return;
	}
	

}
