package controller.gamesocket;

import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import model.dto.GameUserBallTestDto;

@ServerEndpoint("/alarm/{gno}/{mno}")
public class AlarmConnection {

	// 접속자 저장
	public static Vector< GameUserBallTestDto > connectList = new Vector<>();
	
	@OnOpen
	public void enterServer( Session session , @PathParam("gno") int gno , @PathParam("mno") int mno ) {
		try{
			int count = 0; // === 같은 방에 접속한 인원 체크용 (단점: 게임방이 많아질수록, 로딩이 길어지는 현상 발생될 수 있음)
			for( GameUserBallTestDto dto : connectList ) {
				if( dto.getGno() == gno ) { count++; }
			}
			if( count >= 2 ) { session.close(); return; }
			connectList.add( new GameUserBallTestDto(session, mno, gno) );		
			msgServer( session, "startAlarm" );  // --- 게임 시작 조건이 2명이기 때문에 부가 옵션 설정 안함
		}catch (Exception e) { System.out.println(e);}
	}
	
	@OnMessage
	public void msgServer( Session session , String msg ) throws Exception {
		
	}
	
	@OnClose
	public void outServer( Session session ) throws Exception {
		for(GameUserBallTestDto dto : connectList) {
			if(dto.getSession() == session) {
				connectList.remove(dto);
			}
		}
	}
	
	@OnError
	public void errorServer( Session session , Throwable e ) throws Exception {
		System.out.println( session );
	}
}
