package controller.gamesocket;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.dto.BallDto;
import model.dto.GameUserBallTestDto;

@ServerEndpoint("/ball/{gno}/{mno}")
public class BallConnection {
	
	// 접속자 저장
	public static ArrayList< GameUserBallTestDto > connectList = new ArrayList<>();
	
	// private BallDto balldto;

	@OnOpen
	public void enterServer( Session session , @PathParam("gno") int gno , @PathParam("mno") int mno ) throws Exception {
		
		int count = 0;
		for( GameUserBallTestDto dto : connectList ) {
			if( dto.getGno() == gno ) { count++; }
		}
		
		if( count >= 2 ) { session.close(); return; }
		
		connectList.add( new GameUserBallTestDto(session, mno, gno) );
		
		/*
		 * if (balldto == null) { balldto = new BallDto(300, 400, 10, 5, 5, 5, "yellow",
		 * 0); }
		 */
		
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
			balldto = new BallDto(310, 120, 10, 5, 0, 0, "yellow", 0);
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
		}else if( msg.contains("player1TouchBall") ){
				// System.out.println(msg); //--- 확인 완료
			JsonNode msgNode = mapper.readTree(msg);
			// 목적: msg 문자열을 JsonNode 객체 msgNode로 파싱 작업
			
			BallDto updateBall = new BallDto();
			// 목적: update용 BallDto 객체 updateBall 생성
			
			// updateBall 필드 msgNode에서 가져온 값으로 초기화 작업
			updateBall.setX((int)msgNode.get("data").get("x").asDouble());
			updateBall.setY((int)msgNode.get("data").get("y").asDouble());
			updateBall.setRadius((int)msgNode.get("data").get("radius").asDouble());
			updateBall.setSpeed((int)msgNode.get("data").get("speed").asDouble());
			updateBall.setVelocityX((int)msgNode.get("data").get("velocityX").asDouble());
			updateBall.setVelocityY((int)msgNode.get("data").get("velocityY").asDouble());
			updateBall.setColor("yellow");
			updateBall.setBallState(1);
			
			// balldto 업데이트 작업
			balldto = new BallDto( updateBall.getX(), updateBall.getY(), updateBall.getRadius(),
									updateBall.getSpeed(), updateBall.getVelocityX(), updateBall.getVelocityY(),
									updateBall.getColor(), updateBall.getBallState());
			
				// System.out.println( updateBall.toString() ); //--- 확인 완료
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
	    } else if (msg.contains("player2TouchBall")) {
	    	System.out.println(msg);
			JsonNode msgNode = mapper.readTree(msg);

			BallDto updateBall = new BallDto();
			updateBall.setX((int)msgNode.get("data").get("x").asDouble());
			updateBall.setY((int)msgNode.get("data").get("y").asDouble());
			updateBall.setRadius((int)msgNode.get("data").get("radius").asDouble());
			updateBall.setSpeed((int)msgNode.get("data").get("speed").asDouble());
			updateBall.setVelocityX((int)msgNode.get("data").get("velocityX").asDouble());
			updateBall.setVelocityY((int)msgNode.get("data").get("velocityY").asDouble());
			updateBall.setColor("yellow");
			updateBall.setBallState(2);
			
			balldto = new BallDto( updateBall.getX(), updateBall.getY(), updateBall.getRadius(),
									updateBall.getSpeed(), updateBall.getVelocityX(), updateBall.getVelocityY(),
									updateBall.getColor(), updateBall.getBallState());
			
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
	    } else if (msg.contains("player1ResetBall")) {
	        System.out.println("player1ResetBall");

	     	balldto = new BallDto(310, 120, 10, 5, 0, 0, "yellow", 3); //--- 리셋 작업 (상황에 맞게 값 조정하여 사용)
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
	    } else if (msg.contains("player2ResetBall")) {
	        System.out.println("player2ResetBall");
	        
	     	balldto = new BallDto(310, 690, 10, 5, 0, 0, "yellow", 4); //--- 리셋 작업 (상황에 맞게 값 조정하여 사용)
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
	    }
	}
	
}
