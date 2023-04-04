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

import model.dao.GameDao;
import model.dao.MemberDao;
import model.dto.GameUserDto;
import model.dto.MemberDto;
import model.dto.RacketDto;

@ServerEndpoint("/game/{gNo}/{mno}")
public class Connection {
	
	//해당 게임 화면에 들어온 플레이어 저장하는 리스트	
	public static ArrayList<GameUserDto> connectPlayerList = new ArrayList<>();
	
	
	@OnOpen
	public void enterServer( Session session , @PathParam("gNo") int gno, @PathParam("mno") int mno) throws Exception {
		System.out.println("게임방 들어옴 : " + gno +  " : " + mno );
		GameUserDto dto = new GameUserDto(session, gno, mno);
		int count = 0;
		for(GameUserDto userdto : connectPlayerList) {
			if(userdto.getGno() == dto.getGno()) {
				count++;
			}
		}
		if(count < 2) {
			connectPlayerList.add(dto);
			if(count == 1) {
				msgServer(session, "userin");
			}
		}else {
			msgServer(session, "dontin");
		}
	
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
		
		//라켓 정보(모든 라켓 정보 가져오기
		ArrayList<RacketDto> raketList = GameDao.getInstans().getRacketList();
		
		System.out.println(raketList.size());
		
		int pickRno = (int)(Math.random()*raketList.size())+1;
		
		System.out.println(pickRno);
		
		System.out.println(GameDao.getInstans().getRacket(pickRno));
		
		if(msg.contains("userin")) { //접속했을 때 해당 플레이어의 정보를 자바스크립트에 넘겨준다.
			System.out.println("player정보를 보내려고 합니다.");
			
			
			GameUserDto dto = new GameUserDto(250, 0, 0, 80, 80, false, 0, 0, pickRno);
			System.out.println(dto);
			
			int checkGno = 0;
			
			for(GameUserDto userDto : connectPlayerList) { //해당 gno를 찾기 위해!
				if(userDto.getSession() == session) {
					checkGno = userDto.getGno();
					dto.setGno(checkGno);
					dto.setSession(session);
					dto.setMno(userDto.getMno());
					System.out.println(dto);
					break;
				}
			}
			
			json = mapper.writeValueAsString(dto);
			System.out.println(connectPlayerList);
			for(GameUserDto userDto : connectPlayerList) {
				if(userDto.getGno() == checkGno) {
					userDto.getSession().getBasicRemote().sendText(json);
				}
			}
		
			
		}
	}
	
}
