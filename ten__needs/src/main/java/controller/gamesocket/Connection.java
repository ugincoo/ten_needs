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

import model.dao.MemberDao;
import model.dto.GameUserDto;
import model.dto.MemberDto;

@ServerEndpoint("/game/{gno}/{user1}/{user2}")
public class Connection {
	
	//해당 게임 화면에 들어온 플레이어 저장하는 리스트	
	public static ArrayList<GameUserDto> connectPlayerList = new ArrayList<>();
	
	@OnOpen
	public void enterServer( Session session , @PathParam("gno") int gno , @PathParam("user1") int user1 , @PathParam("user2") int user2 ) throws Exception {
		System.out.println( session );
		System.out.println(gno +" , "+ user1 +" , "+  user2 );
		
		MemberDto player1 = MemberDao.getInstance().getMember(user1);
		MemberDto player2 = MemberDao.getInstance().getMember(user2);
		
		connectPlayerList.add(new GameUserDto(session, player1.getMno(), 0, 0, 0, false, 0, 0, 0));
		connectPlayerList.add(new GameUserDto(session, player2.getMno(), 0, 0, 0, false, 0, 0, 0));
		
		System.out.println(connectPlayerList);
		
		msgServer(session, "userlist"); //접속했을때 해당 플레이어(2명 모두)의 정보를 보내주기 위해
		
	}
	
	@OnClose
	public void outServer( Session session ) throws Exception {
		System.out.println( session );
	}
	
	@OnError
	public void errorServer( Session session , Throwable e ) throws Exception {
		System.out.println( session );
	}
	
	@OnMessage
	public void msgServer( Session session , String msg ) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		
		if(msg.contains("userlist")) { //접속했을 때 해당 플레이어의 정보를 자바스크립트에 넘겨준다.
			System.out.println("player정보를 보내려고 합니다.");
			
			json = mapper.writeValueAsString(connectPlayerList);
			
		}
		
		
		for(GameUserDto dto : connectPlayerList) {
			dto.getSession().getBasicRemote().sendText(json);
			System.out.println(dto.toString());
		}
	}
	
}
