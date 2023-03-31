package controller.game;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.GameDao;


@WebServlet("/game/result")
public class GameResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GameResult() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	//게임 결과 DB 전송 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post 들어옴");
		request.setCharacterEncoding("UTF-8");
		
		
		int winner = Integer.parseInt(request.getParameter("winner"));
		int loser = Integer.parseInt(request.getParameter("loser"));
		
		double winnergsAccute = Double.parseDouble(request.getParameter("winnergsAccute"));
		
		double losergsAccute = Double.parseDouble(request.getParameter("losergsAccute"));
		
		System.out.println(winnergsAccute);
		//boolean result = GameDao.getInstance().endGame(winner);
		
		//response.getWriter().print(result);
		
	}

}
