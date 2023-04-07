package controller.gamesocket;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

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
	public static Vector<GameUserDto> connectPlayerList = new Vector<>();

	// 라켓 정보(모든 라켓 정보 가져오기
	public static Vector<RacketDto> raketList = GameDao.getInstans().getRacketList();

	@OnOpen
	public synchronized void enterServer(Session session, @PathParam("gNo") int gno, @PathParam("mno") int mno) throws Exception {
		System.out.println("게임방 들어옴 : " + gno + " : " + mno);
		
		 int checkCount = 1;
		 
		 int rno = (int)(Math.random()*raketList.size())+1;
		 
		GameUserDto dto = new GameUserDto(session, 1, gno, mno);
		dto.setRno(rno);
		
		connectPlayerList.add(dto);
		
		int count = 0;
		for (GameUserDto userdto : connectPlayerList) {
			if (userdto.getGno() == dto.getGno()) {
				dto.setUser(checkCount);
				count++;
				checkCount++;
			}
		}
		System.out.println(dto);
		System.out.println(count);
		
		if (count == 2) {
			
			msgServer(null, ""+gno+"");
		} else {
			// session.close();
		}

	}

	@OnClose
	public synchronized void outServer(Session session) throws Exception {
		for(GameUserDto dto : connectPlayerList) {
			if(dto.getSession() == session) {
				connectPlayerList.remove(dto);
			}
			
		}
	}

	@OnError
	public synchronized void errorServer(Session session, Throwable e) throws Exception {
		System.out.println(session);
	}

	@OnMessage
	public synchronized void msgServer(Session session, String msg) throws Exception {
		
		 ObjectMapper mapper = new ObjectMapper(); 
		 String json = null;

		 System.out.println(msg);
		 
		 if(!msg.contains("{")) {
			 
			 int checkGno = Integer.parseInt(msg);
			 
			 for(GameUserDto Typedto : connectPlayerList) {
				 
				 if(Typedto.getType() == 1 ) { //접속
					 
					 System.out.println(connectPlayerList);
					 
					 GameUserDto userDto = new GameUserDto(250, 0);
					 userDto.setMno(Typedto.getMno());

					 
			
	
					 for(GameUserDto dto : connectPlayerList) {
						 if(dto.getGno() == checkGno ) {
							userDto.setType(dto.getType());
						 	userDto.setGno(dto.getGno());
						 	userDto.setRno(Typedto.getRno());
						 	userDto.setUser(Typedto.getUser());
							System.out.println("for문 : " + userDto); 
							json = mapper.writeValueAsString(userDto); 
							dto.getSession().getBasicRemote().sendText(json);
						} 
						
					 }
				 }
			 }
		}else { //움직였을때 보내는 데이터  + 스매싱
			
			GameUserDto dto = mapper.readValue(msg, GameUserDto.class);
			System.out.println(dto);
			if(dto.getType() == 2) { //움직임
				for(GameUserDto gameDto : connectPlayerList) {
					if(gameDto.getMno() == dto.getMno()) {
						dto.setRno(gameDto.getRno());
						dto.setUser(gameDto.getUser());
					}
				}
			}else if(dto.getType() == 3) { //스매싱
				for(GameUserDto gameDto : connectPlayerList) {
					if(gameDto.getMno() == dto.getMno()) {
						dto.setRno(gameDto.getRno());
						dto.setUser(gameDto.getUser());
					}
				}
			}
			
			
			for(GameUserDto userdto : connectPlayerList) {
				if(dto.getGno() == userdto.getGno()) {
					System.out.println("보내야하는 데이터 : " + dto);
					json = mapper.writeValueAsString(dto);
					userdto.getSession().getBasicRemote().sendText(json);
				}
			}
		}
	}

}