package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MypageDao;
import model.dto.MypageGameDto;



@WebServlet("/tenneeds/member/mypage")
public class Mypage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Mypage() {
        super();
    }
    
    // 게임 정보 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. mID 가져오기
		// String mId = (String)request.getSession().getAttribute("login");
		
		int mNo = Integer.parseInt(request.getParameter("mNo"));
		
		// 2. DB 결과값 (객체로 전달)
		MypageGameDto result = MypageDao.getInstance().printGameInfo( mNo );
		
		// 3. DB 결과 형변환
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(result);
		
		// 3. 결과 전송
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	// 정보수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. mId, mPw, mImg, mEmail, mPhone 이용해서 DB 검토 후 업데이트 처리
		
		// 2. DB 결과값
		
		// 3. 결과 전송
		
	}

	//회원탈퇴
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. mId, mPw 이용해서 DB 검토 후 삭제 처리
		String mId = (String)request.getSession().getAttribute("login");
		String mPw = request.getParameter("mPw");
		
		// 2. DB 결과값
		boolean result = MypageDao.getInstance().onDelete(mId, mPw);
		
		// 3. 결과 전송
		response.getWriter().print(result);
	}

}
