package controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
			int startRow = (page-1)*3; //3개씩 출력

			//게시물 수 구하기
			int totalSize = BoardDao.getInstance().getTotalSize(key, keyword);
			//총 페이지
			int totalpage = (totalSize%3 == 0) ? (totalSize/3) : (totalSize/3 +1);

			
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
			int bno = Integer.parseInt(request.getParameter("bno"));
			
			BoardDto result = BoardDao.getInstance().getBoard(bno);
			
			String json =  mapper.writeValueAsString(result);

			response.getWriter().print(json);
		}
		
	}
	
	//게시물 등록[글쓰기]
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String path = request.getSession().getServletContext().getRealPath("/tenneeds/jsp/board/bimg");
		
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				path,
				1024*1024*10,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		String bTitle = multi.getParameter("bTitle");
		String bContent = multi.getParameter("bContent");
		
		BoardDto dto = new BoardDto(bTitle, bContent);
		
		boolean result = BoardDao.getInstance().writeBoard(dto);
		
		response.getWriter().print(result);
	}

	//게시물 수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String path = request.getSession().getServletContext().getRealPath("/tenneeds/jsp/board/bimg");
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				path,
				1024*1024*10,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		int bno = Integer.parseInt(multi.getParameter("bno"));
		
		
		String bTitle = multi.getParameter("bTitle");
		String bContent = multi.getParameter("bContent");
		
		BoardDto dto = new BoardDto(bno, bTitle, bContent);
		
		
		boolean result = BoardDao.getInstance().updateBoard(dto);
		
		response.getWriter().print(result);
	}

	//게시물 삭제
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		System.out.println("bno" + bno);
		boolean result = BoardDao.getInstance().deleteBoard(bno);
		
		response.getWriter().print(result);
	}

}