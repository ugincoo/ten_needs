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
import model.dao.MemberDao;
import model.dto.ReplyDto;


@WebServlet("/board/reply")
public class BoardReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BoardReply() {
        
    }
    //댓글 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		ArrayList<ReplyDto> result = BoardDao.getInstance().getReplyList(bno);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonArray = mapper.writeValueAsString(result);
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
		
		
		
	}

	//댓글 작성
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		/*
		 * int mno =
		 * MemberDao.getInstance().getMno((String)request.getSession().getAttribute(
		 * "login"));
		 */
		
		int mno = 3; 
		
		String reContent = request.getParameter("reContent");
		
		ReplyDto dto = new ReplyDto(reContent, bno, mno);
		
		boolean result = BoardDao.getInstance().writeReply(dto);
		
		response.getWriter().print(result);
		
	}

	//댓글 수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int reNo = Integer.parseInt(request.getParameter("reNo"));
		String reContent = request.getParameter("reContent");
		
		ReplyDto dto = new ReplyDto(reContent, reNo);

		boolean result = BoardDao.getInstance().updateReply(dto);
		
		response.getWriter().print(result);
	}
	
	//댓글 삭제
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reNo = Integer.parseInt(request.getParameter("reNo"));
		
		boolean result = BoardDao.getInstance().deleteReply(reNo);
		
		response.getWriter().print(result);
		
	}

}
