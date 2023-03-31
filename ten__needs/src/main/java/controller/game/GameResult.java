package controller.game;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.GameDao;
import model.dto.GameResultDto;
import model.dto.RacketDto;


@WebServlet("/game/result")
public class GameResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GameResult() {
       
    }

	//게임에 필요한 정보를 가져오는
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type"));
		System.out.println(type);
		if(type == 1) { //라켓의 정보를 가져오는
			ArrayList<RacketDto> racketList = GameDao.getInstans().getRacketList();
			
			ObjectMapper mapper = new ObjectMapper();
			
			String jsonArray = mapper.writeValueAsString(racketList);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}
	}

	
	//게임 결과 DB 전송 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post 들어옴");
		request.setCharacterEncoding("UTF-8");
		
		
		int winner = Integer.parseInt(request.getParameter("winner"));
		int loser = Integer.parseInt(request.getParameter("loser"));
		
		double winnergsAccute = Double.parseDouble(request.getParameter("winnergsAccute"));
		
		double losergsAccute = Double.parseDouble(request.getParameter("losergsAccute"));
		
		GameResultDto dto =  new GameResultDto(winner, loser, winnergsAccute, losergsAccute);
		System.out.println(dto);
		//boolean result = GameDao.getInstance().endGame(dto);
		
		//response.getWriter().print(result);
		response.getWriter().print(true);
		
	}

}
