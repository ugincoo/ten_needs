package controller.game;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.GameroomDao;
import model.dto.GameroomDto;
import model.dto.PageDto;

@WebServlet("/tenneeds/creategame")
public class CreateGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CreateGame() {
        super();
    }
    
    // 게임방 리스트 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		int type = Integer.parseInt(request.getParameter("type"));
		
		if(type == 1) { //1. 전체 출력
			String key = request.getParameter("key"); //키
			String keyword = request.getParameter("keyword"); //키워드

			//페이지 처리
			int page = Integer.parseInt(request.getParameter("page"));
				System.out.println( page );
			int countPerPage = 10;
			int startRow = (page-1)*countPerPage; //10개씩 출력

			//게시물 수 구하기
			int totalSize = GameroomDao.getInstance().getTotalSize(key, keyword);
			//총 페이지
			int totalpage = (totalSize%countPerPage == 0) ? (totalSize/countPerPage) : (totalSize/countPerPage +1);
			
			//버튼 최대 개수 
			int btnSize = 5;
			int startBtn = ((page-1) / btnSize)*btnSize+1;
			int endBtn = startBtn + (btnSize-1);
			
			if(endBtn > totalpage) {
				endBtn = totalpage;
			}
			
			ArrayList<GameroomDto> result = GameroomDao.getInstance().gameList(startRow, key, keyword);
				
				// System.out.println( "result size:" + result.size() );
			
			PageDto pageDto = new PageDto(page, startRow, totalSize, totalpage, btnSize, startBtn, endBtn, result, 1); // 1: game에 사용
			
			String jsonArray = mapper.writeValueAsString(pageDto);
			
			response.getWriter().print(jsonArray);
		} else if( type == 2) {
			int gno = Integer.parseInt(request.getParameter("gno"));
			
			GameroomDto result = GameroomDao.getInstance().getGame(gno);
			String json = mapper.writeValueAsString(result);
			response.getWriter().print(json);
		}
	}
		

	// 게임방 생성
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String gTitle = request.getParameter("gTitle");
			System.out.println("gTitle:" + gTitle);
		
		String mid = (String)request.getSession().getAttribute("login");
		
		GameroomDto dto = new GameroomDto( gTitle, mid );
		boolean result = GameroomDao.getInstance().createGame(dto);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
		
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
