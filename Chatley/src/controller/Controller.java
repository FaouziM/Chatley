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
		Person ik = null;
		ik = (Person) request.getSession().getAttribute("user");
		
		if (action==null){
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
		}
		
		
		switch (action) {
		
		case "login":
			login(request, response);
			break;
			
		case "getStatus":
			String status = ik.getStatus();
			response.getWriter().write(status);
			break;
			
		case "updateStatus":
			String nieuweStatus = request.getParameter("nieuweStatus");
			ik.setStatus(nieuweStatus);
			response.getWriter().write(ik.getStatus());
			break;
			
		case "addVriend":
			String username = request.getParameter("username");
			Person vriend = null;
			try{
				vriend = service.getPerson(username);
				ik.addVriend(vriend);
			}catch (Exception e){
				response.getWriter().write("{ \"Error\": " + "\"" + e.getMessage() + "\" }");
				break;
			}
			response.getWriter().write(service.getVriendenAlsJSON(ik));
			break;
			
		case "getFriends":
			response.getWriter().write(service.getVriendenAlsJSON(ik));
			break;
		
		case "getOudeBerichten":
			Person partner = service.getPerson(request.getParameter("partner"));
			String json = ik.getBerichtenVanPartnerAlsJSON(partner);
			response.getWriter().write(json);
			break;
			
		case "getNieuweBerichten":
			Person partner1 = service.getPerson(request.getParameter("partner"));
			String nieuweBerichtenJSON = ik.getNieuweBerichtenVanPartnerAlsJSON(partner1);
			response.getWriter().write(nieuweBerichtenJSON);
			break;
		}
		
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Person person = null;
		
		try {
			person = service.getPersonAlsWachtwoordCorrect(username, password);
		} catch (Exception e) {
			request.setAttribute("fout", e.getMessage());
			request.setAttribute("user", null);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("user", person);
		response.sendRedirect("Controller");
		return;
	}
	

}
