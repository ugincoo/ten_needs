package controller.gamesocket;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dto.BallDto;
import model.dto.GameUserBallTestDto;

@ServerEndpoint("/ball/{gno}/{mno}")
public class BallConnection {
	
	// 접속자 저장
	public static ArrayList< GameUserBallTestDto > connectList = new ArrayList<>();

	@OnOpen
	public void enterServer( Session session , @PathParam("gno") int gno , @PathParam("mno") int mno ) throws Exception {
		
		int count = 0;
		for( GameUserBallTestDto dto : connectList ) {
			if( dto.getGno() == gno ) { count++; }
		}
		
		if( count >= 2 ) { session.close(); return; }
		
		connectList.add( new GameUserBallTestDto(session, mno, gno) );
		msgServer( session, "startBall" );  // --- 게임 시작 조건이 2명이기 때문에 부가 옵션 설정 안함
	
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
		System.out.println( session );
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		
		BallDto balldto;
		
		if( msg.contains("startBall")){
			System.out.println( "유저 정상 입장 / 볼 생성" );
			
			// 입장 확인 후 공 생성
			balldto = new BallDto(300, 400, 10, 5, 5, 5, "yellow", 0);
			json = mapper.writeValueAsString(balldto);
			
			int senderGno = 0; //--- 다른방 유저에게 메시지 중복으로 전달 방지
			for( GameUserBallTestDto dto: connectList ) {
				if( dto.getSession() == session ) {
					senderGno = dto.getGno(); break;
				}
			}
			for( GameUserBallTestDto dto : connectList ) {
				if( dto.getGno() == senderGno ) {
					dto.getSession().getBasicRemote().sendText(json);
				}
			}
		} else if( msg.contains("player1") ){
			
			
			
			
			/*
			// JSON 문자열			
			String ballJson = msg;
			// ObjectMapper를 사용하여 JSON 문자열을 DTO 객체로 변환
			ObjectMapper objectMapper = new ObjectMapper();
			balldto = objectMapper.readValue(ballJson, BallDto.class);

			// DTO 객체에 값을 설정한 후 사용
			System.out.println(balldto.getX());  // 10
			System.out.println(balldto.getY());
			*/
			
		} else if( msg.contains("player2") ){
				System.out.println( msg );
		} else if( msg.contains("player1ResetBall")) {
			System.out.println("play1리셋볼");
		} else if( msg.contains("player2ResetBall")) {
			System.out.println( "play2리셋볼" );
		}
		
	}
	
}
