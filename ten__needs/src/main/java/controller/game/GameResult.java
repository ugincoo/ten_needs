package controller.game;

import java.io.IOException;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.GameDao;
import model.dao.MemberDao;
import model.dto.GameResultDto;
import model.dto.GameUserDto;
import model.dto.MemberDto;
import model.dto.RacketDto;


@WebServlet("/game/result")
public class GameResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GameResult() {
       
    }

	//게임에 필요한 정보를 가져오는
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type"));
		ObjectMapper mapper = new ObjectMapper();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		request.setCharacterEncoding("UTF-8");
		
		System.out.println(type);
		if(type == 1) { //라켓의 정보를 가져오는
			int rno = Integer.parseInt(request.getParameter("rno"));
			RacketDto dto = GameDao.getInstans().getRacket(rno);
		
			
			String jsonArray = mapper.writeValueAsString(dto);
			
			
			response.getWriter().print(jsonArray);
		}else if(type == 2) { //통계

			ArrayList<GameResultDto> memberRankingList = GameDao.getInstans().getRanking();
				
			String jsonArray = mapper.writeValueAsString(memberRankingList);
			
			response.getWriter().print(jsonArray);
		}else if(type == 3) { //회원 정보 가져오기
			String mid = request.getParameter("mid");
			
			MemberDto dto = MemberDao.getInstance().getMember(mid);
			response.getWriter().print(dto);
		}
	}

	
	//게임 결과 DB 전송 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post 들어옴");
		request.setCharacterEncoding("UTF-8");
		
		
		int winner = Integer.parseInt(request.getParameter("winner"));
		int loser = Integer.parseInt(request.getParameter("loser"));
		
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		double winnergsAccute = Math.round(Double.parseDouble(request.getParameter("winnergsAccute"))*100)/100.0;
		
		System.out.println("winnerAccute : " + winnergsAccute);
		
		double losergsAccute = Math.round(Double.parseDouble(request.getParameter("losergsAccute"))*100)/100.0;
		
		System.out.println("losergsAccute : " + losergsAccute);
		int winnerRno = Integer.parseInt(request.getParameter("winnerRno"));
		int loserRno = Integer.parseInt(request.getParameter("loserRno"));
		
		GameResultDto dto =  new GameResultDto(winner, loser, winnergsAccute, losergsAccute, gno, winnerRno, loserRno);
		System.out.println(dto);
		
		boolean result = GameDao.getInstans().endGame(dto);
		
		response.getWriter().print(result);
		
	}

}
