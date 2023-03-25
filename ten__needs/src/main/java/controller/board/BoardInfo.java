package controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.BoardDao;
import model.dto.BoardDto;
import model.dto.PageDto;


@WebServlet("/board/info")
public class BoardInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BoardInfo() {
        
    }

    //게시물 출력
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
			int startRow = (page-1)*10; //10개씩 출력

			//게시물 수 구하기
			int totalSize = BoardDao.getInstance().getTotalSize(key, keyword);
			//총 페이지
			int totalpage = (totalSize%10 == 0) ? (totalSize/10) : (totalSize/10 +1);

			
			//버튼 최대 개수 
			int btnSize = 5;
			int startBtn = ((page-1) / btnSize)*btnSize+1;
			int endBtn = startBtn + (btnSize-1);
			
			if(endBtn > totalpage) {
				endBtn = totalpage;
			}
			
			ArrayList<BoardDto> result = BoardDao.getInstance().getBoardList(startRow, key, keyword);
			

			
			PageDto pageDto = new PageDto(page, startRow, totalSize, totalpage, btnSize, startBtn, endBtn, result);
			
			String jsonArray = mapper.writeValueAsString(pageDto);
			
			
			response.getWriter().print(jsonArray);
			
		}else if(type == 2) { //개별 출력
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}