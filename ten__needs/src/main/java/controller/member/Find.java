package controller.member;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDao;

/**
 * Servlet implementation class Find
 */
@WebServlet("/find")
public class Find extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Find() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String type = request.getParameter("type");
		String result = null;
		if( type.equals("1")) {
			String memail = request.getParameter("memail");
			result = MemberDao.getInstance().findmid( memail );
		}else if( type.equals("2")) {
			String mid = request.getParameter("mid");
			String memail = request.getParameter("memail");
			
			// 임시비밀번호
			Random random = new Random();
			String ranStr = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789";
			String updatempw = "";
			
			for( int i = 0; i<12 ; i++) {
				int ran = random.nextInt( ranStr.length() );
				updatempw += ranStr.charAt( ran );
			}
			System.out.println( "updatempw : "+ updatempw );
			result = MemberDao.getInstance().findmpw( mid , memail , updatempw );
		}
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
