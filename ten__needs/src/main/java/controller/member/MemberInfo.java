package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.MemberDao;
import model.dto.MemberDto;

/**
 * Servlet implementation class MemberInfo
 */
@WebServlet("/memberinfo")
public class MemberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberInfo() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	// 회원가입
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uploadpath = request.getSession().getServletContext().getRealPath("/tenneeds/member/mimg");
		MultipartRequest multi = new MultipartRequest(request, uploadpath, 1024*1024*10, "UTF-8" , new DefaultFileRenamePolicy() );
		
		String mid = multi.getParameter("mid");
		String mpw = multi.getParameter("mpw");
		String mphone = multi.getParameter("mphone");
		String memail = multi.getParameter("memail");
		String mimg = multi.getParameter("mimg");
		
		MemberDto dto = new MemberDto(0, mid, mpw, mimg, memail, mphone);
		
		boolean result = MemberDao.getInstance().signup(dto);
		response.getWriter().print(result);
				
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}