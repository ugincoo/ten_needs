package controller.gamesocket;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.result.IntegerValueFactory;

import model.dao.GameDao;
import model.dao.MemberDao;
import model.dto.GameUserDto;
import model.dto.MemberDto;
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

		GameUserDto dto = new GameUserDto(session, gno, mno, 1);
		
		int count = 0;
		for (GameUserDto userdto : connectPlayerList) {
			if (userdto.getGno() == dto.getGno()) {
				count++;
			}
		}
		if (count <= 2) {
			connectPlayerList.add(dto);
			if (count == 1) {
				msgServer(null, dto);
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
	public void msgServer(Session session, GameUserDto msgDto) throws Exception {
		
		 ObjectMapper mapper = new ObjectMapper(); String json = null;
		  
		 if(msgDto.getType() == 1) { //접속
			 System.out.println(raketList.size());
			 
			 System.out.println(connectPlayerList);
			 
			 GameUserDto userDto = new GameUserDto(250, 0);
			 
			 
			 for(GameUserDto dto : connectPlayerList) { 
				 if(dto.getGno() == msgDto.getGno()) {
					 	userDto.setRno((int)(Math.random()*raketList.size())+1); 
					 	userDto.setGno(dto.getGno()); 
					 	userDto.setMno(dto.getMno());
						 System.out.println(userDto); 
						 json = mapper.writeValueAsString(userDto); 
						 dto.getSession().getBasicRemote().sendText(json);
				 } 
			 }
		 }else if(msgDto.getType() == 2) { //움직임
			 
		 }
	}

}
