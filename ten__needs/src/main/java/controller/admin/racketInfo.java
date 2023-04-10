package controller.admin;

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

import model.dao.RacketDao;
import model.dto.PageDto;
import model.dto.RacketDto;

@WebServlet("/admin/racket")
public class racketInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public racketInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String getType = request.getParameter("getType");
		
		if( getType.equals("racket") ) {
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
				int totalSize = RacketDao.getInstance().getTotalSize(key, keyword);
				//총 페이지
				int totalpage = (totalSize%countPerPage == 0) ? (totalSize/countPerPage) : (totalSize/countPerPage +1);
				
				//버튼 최대 개수 
				int btnSize = 5;
				int startBtn = ((page-1) / btnSize)*btnSize+1;
				int endBtn = startBtn + (btnSize-1);
				
				if(endBtn > totalpage) {
					endBtn = totalpage;
				}
				
				ArrayList<RacketDto> result = RacketDao.getInstance().racketList(startRow, key, keyword);
					
					// System.out.println( "result size:" + result.size() );
				
				PageDto pageDto = new PageDto(countPerPage, startRow, totalSize, totalpage, btnSize, startBtn, endBtn, result, "racket");
				
				String jsonArray = mapper.writeValueAsString(pageDto);
				
				response.getWriter().print(jsonArray);
			} else if( type == 2) {
				int rNo = Integer.parseInt(request.getParameter("rNo"));
				
				RacketDto result = RacketDao.getInstance().getRacket(rNo);
				String json = mapper.writeValueAsString(result);
				response.getWriter().print(json);
			}
		} else if( getType.equals("check") ) {
			String rName = request.getParameter("rName");
			
			boolean result = RacketDao.getInstance().racketCheck(rName);
			response.getWriter().print(result);
		}
	}
	
	// 라켓등록 (확인 완료)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String uploadpath = request.getSession().getServletContext().getRealPath("tenneeds/jsp/game/img/rimg");
		MultipartRequest multi = new MultipartRequest(request, uploadpath, 1024*1024*10, "UTF-8" , new DefaultFileRenamePolicy() );
			// System.out.println(uploadpath); //---확인 완료
		
		String rName = multi.getParameter("rName");
		int rLevel = Integer.parseInt(multi.getParameter("rLevel"));
		int rSize_x = Integer.parseInt(multi.getParameter("rSize_x"));
		int rSize_y = Integer.parseInt(multi.getParameter("rSize_y"));
		String rimg = multi.getFilesystemName("rimg");
		
		RacketDto dto = new RacketDto(rSize_y, rName, rimg, rLevel, rSize_x, rSize_y);
		
		boolean result = RacketDao.getInstance().addRacket(dto);
		response.getWriter().print(result);
	}
	
	// 라켓 수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String uploadpath = request.getSession().getServletContext().getRealPath("tenneeds/jsp/game/img/rimg");
		MultipartRequest multi = new MultipartRequest(request, uploadpath, 1024*1024*10, "UTF-8" , new DefaultFileRenamePolicy() );
			// System.out.println(uploadpath); //---확인 완료
		
		int rNo = Integer.parseInt(multi.getParameter("rNo"));
		String rName = multi.getParameter("rName");
		int rLevel = Integer.parseInt(multi.getParameter("rLevel"));
		int rSize_x = Integer.parseInt(multi.getParameter("rSize_x"));
		int rSize_y = Integer.parseInt(multi.getParameter("rSize_y"));
		String rimg = multi.getFilesystemName("rimg");
		
		RacketDto dto = new RacketDto(rNo, rName, rimg, rLevel, rSize_x, rSize_y);
		
		boolean result = RacketDao.getInstance().addRacket(dto);
		response.getWriter().print(result);
	}
	
	// 라켓 삭제
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dName = request.getParameter("dName");
		System.out.println(dName);
		boolean result = RacketDao.getInstance().onDelete(dName);
		response.getWriter().print(result);
	}
}
