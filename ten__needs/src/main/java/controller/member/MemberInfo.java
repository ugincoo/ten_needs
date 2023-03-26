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
@WebServlet("/member/info")
public class MemberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberInfo() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String mid = request.getParameter("mid");
		
		boolean result = MemberDao.getInstance().idcheck(mid);
		
		response.getWriter().print(result);
		
	}

	
	// 회원가입
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uploadpath = request.getSession().getServletContext().getRealPath("tenneeds/jsp/member/mimg");
		MultipartRequest multi = new MultipartRequest(request, uploadpath, 1024*1024*10, "UTF-8" , new DefaultFileRenamePolicy() );
		
		String mid = multi.getParameter("mid");
		String mpw = multi.getParameter("mpw");
		String mphone = multi.getParameter("mphone");
		String memail = multi.getParameter("memail");
		String mimg = multi.getFilesystemName("mimg");
		
		MemberDto dto = new MemberDto(0, mid, mpw, mimg, memail, mphone);
		
		boolean result = MemberDao.getInstance().signup(dto);
		response.getWriter().print(result);
				
	}

	// 회원정보 수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uploadpath = request.getSession().getServletContext().getRealPath("tenneeds/jsp/member/mimg");
		MultipartRequest multi = new MultipartRequest(request, uploadpath, 1024*1024*10, "UTF-8" , new DefaultFileRenamePolicy() );
		
		String mid = (String)request.getSession().getAttribute("login");
		String mpw = multi.getParameter("mpw");
		String newmpw = multi.getParameter("newmpw");
		String mphone = multi.getParameter("mphone");
		String memail = multi.getParameter("memail");
		String mimg = multi.getFilesystemName("mimg");
		String defaultimg = multi.getParameter("defaultimg");
		
		if( mimg == null ) {
			mimg = MemberDao.getInstance().getMember(mid).getMimg();
		}
		if(defaultimg.equals("true")) {
			mimg = null;
		}
		if( newmpw.equals("")) {
			newmpw = mpw;
		}
		
		boolean result = MemberDao.getInstance().update(mid, mpw, newmpw, mphone, memail, mimg);
		
		response.getWriter().print(result);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}