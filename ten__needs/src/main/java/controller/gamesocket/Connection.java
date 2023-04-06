package controller.gamesocket;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.jws.soap.SOAPBinding;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.GameDao;
import model.dto.GameUserDto;
import model.dto.RacketDto;

@ServerEndpoint("/game/{gNo}/{mno}")
public class Connection {

	// 해당 게임 화면에 들어온 플레이어 저장하는 리스트
	public static ArrayList<GameUserDto> connectPlayerList = new ArrayList<>();

	// 라켓 정보(모든 라켓 정보 가져오기
	public static ArrayList<RacketDto> raketList = GameDao.getInstans().getRacketList();

	@OnOpen
	public void enterServer(Session session, @PathParam("gNo") int gno, @PathParam("mno") int mno) throws Exception {
		System.out.println("게임방 들어옴 : " + gno + " : " + mno);

		int rno = (int)(Math.random()*raketList.size())+1;
		GameUserDto dto = new GameUserDto(session, 1, gno, mno, rno);
		
		
		int count = 0;
		for (GameUserDto userdto : connectPlayerList) {
			if (userdto.getGno() == dto.getGno()) {
				count++;
			}
		}
		System.out.println(dto);
		System.out.println(count);
		
		if (count <= 2) {
			connectPlayerList.add(dto);
			if (count == 1) {
				msgServer(null, ""+gno+"");
			}
		} else {
			// session.close();
		}

	}

	@OnClose
	public void outServer(Session session) throws Exception {
		System.out.println(session);
	}

	@OnError
	public void errorServer(Session session, Throwable e) throws Exception {
		System.out.println(session);
	}

	@OnMessage
	public void msgServer(Session session, String msg) throws Exception {
		
		 ObjectMapper mapper = new ObjectMapper(); 
		 String json = null;
		 StringTokenizer st = new StringTokenizer(msg, " ");
		 
		 int count = st.countTokens();
		 
		 if(count == 1) {
			 int checkGno = Integer.parseInt(st.nextToken());
			 
			 for(GameUserDto Typedto : connectPlayerList) {
				 
				 if(Typedto.getType() == 1 ) { //접속
					 
					 System.out.println(connectPlayerList);
					 
					 GameUserDto userDto = new GameUserDto(250, 0);
					 
					 userDto.setMno(Typedto.getMno());
					 
					 for(GameUserDto dto : connectPlayerList) { 
						 if(dto.getGno() == checkGno ) {
							 	userDto.setGno(dto.getGno()); 
								System.out.println("for문 : " + userDto); 
								json = mapper.writeValueAsString(userDto); 
								dto.getSession().getBasicRemote().sendText(json);
						} 
						
					 }
				 }
			 }
		}else if(count >= 2) { //움직임 공치기
			int type = Integer.parseInt(st.nextToken());
			
			if(type == 2) { //움직임
				System.out.println("움직였다.");
			}else if(type == 3) { //공치기 
				System.out.println("공쳤다.");
			}
		}
	}

}