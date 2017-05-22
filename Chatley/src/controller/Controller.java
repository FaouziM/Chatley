package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.sun.corba.se.spi.activation.ORBAlreadyRegisteredHelper;

import jdk.nashorn.internal.parser.JSONParser;
import model.Bericht;
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
			String email = request.getParameter("email");
			Person vriend = null;
			try{
				vriend = service.getPerson(email);
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
			Person partner = service.getPersonMetVolledigeNaam(request.getParameter("partner"));
			String json = ik.getBerichtenVanPartnerAlsJSON(partner);
			response.getWriter().write(json);
			break;
			
		case "getNieuweBerichten":
			Person partner1 = service.getPersonMetVolledigeNaam(request.getParameter("partner"));
			String nieuweBerichtenJSON = ik.getNieuweBerichtenVanPartnerAlsJSON(partner1, ik);
			response.getWriter().write(nieuweBerichtenJSON);
			break;
			
		case "zendBericht":
			String jsonBericht = request.getParameter("b");
			JSONObject jsonObject = new JSONObject(jsonBericht);
			Person ontvanger = service.getPersonMetVolledigeNaam((String)jsonObject.get("ontvanger"));
			Bericht b = new Bericht((String)jsonObject.get("bericht"), ik, ontvanger);
			ik.addBericht(b);
			ontvanger.addBericht(b);
			break;
			
		case "logout":
			request.getSession().invalidate();
			response.sendRedirect("Controller");
			break;
			
		case "getRegisterPage":
			request.getRequestDispatcher("registreren.jsp").forward(request, response);
			break;
		
		case "registreren":
			registreren(request, response);
			
			break;
		}
		
	}
	
	private void registreren(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String naam = request.getParameter("naam");
		String voornaam = request.getParameter("voornaam");
		String email = request.getParameter("email");
		char geslacht = request.getParameter("geslacht").charAt(0);
		int leeftijd = Integer.parseInt(request.getParameter("leeftijd"));
		String passwoord = request.getParameter("passwoord");
		String passwoord2 = request.getParameter("passwoord2");
		try {
			this.service.addPerson(naam, voornaam, email, geslacht, leeftijd, passwoord, passwoord2);
		} catch (IllegalArgumentException e){
			response.sendRedirect("Controller?action=getRegisterPage");
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("email");
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
