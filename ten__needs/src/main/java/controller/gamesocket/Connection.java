package controller.gamesocket;

import java.util.ArrayList;
import java.util.StringTokenizer;

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
	StringTokenizer st;

	@OnOpen
	public void enterServer(Session session, @PathParam("gNo") int gno, @PathParam("mno") int mno) throws Exception {
		System.out.println("게임방 들어옴 : " + gno + " : " + mno);

		GameUserDto dto = new GameUserDto(session, gno, mno);
		
		int count = 0;
		
		for (GameUserDto userdto : connectPlayerList) {
			if (userdto.getGno() == dto.getGno()) {
				count++;
			}
		}
		if (count <= 2) {
			System.out.println("count : " + count);
			connectPlayerList.add(dto);
			if (count == 1) {
				String info = 1 + " " + gno;
				System.out.println(info);
				msgServer(null, info);
			}
			System.out.println(connectPlayerList);
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
		
		 ObjectMapper mapper = new ObjectMapper(); String json = null;
		 st = new StringTokenizer(msg, " ");
		 int type = Integer.parseInt(st.nextToken());
		 
		 System.out.println("type : " + type);
		 
		 if(type == 1) { //접속
			 
			 GameUserDto userDto = new GameUserDto(250, 0);
			 
			 int gno = Integer.parseInt(st.nextToken());
			 
			 
			 for(GameUserDto dto : connectPlayerList) { 
				 if(dto.getGno() == gno) {
					 	userDto.setRno((int)(Math.random()*raketList.size())+1); 
					 	userDto.setGno(dto.getGno()); 
					 	userDto.setMno(dto.getMno());
						System.out.println(userDto); 
						json = mapper.writeValueAsString(userDto); 
						dto.getSession().getBasicRemote().sendText(json);
				 } 
			 }
		 }else if(type == 2) { //움직임
			 
		 }
	}

}
