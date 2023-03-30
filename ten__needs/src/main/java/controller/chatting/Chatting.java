package controller.chatting;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dto.ChatMessageDto;
import model.dto.ChatUserDto;

@ServerEndpoint("/tenneeds/chatting/{gNo}/{mid}")
public class Chatting {
	
	// 접속자 저장
	public static ArrayList< ChatUserDto > connectList = new ArrayList<>();
	
	// onOpen Method
	@OnOpen
	public void OnOpen( Session session, @PathParam("gNo") int gNo, @PathParam("mid") String mid ) throws Exception {
			System.out.println("입장" + session);
		
		int count = 0; // --- 변수 추가(이유: gNO에 접속되어 있는 인원이 2명 넘지 않도록 제어하기 위함)
		    for (ChatUserDto dto : connectList) {
		        if (dto.getgNo() == gNo) { count++; }
		    }
		if (count >= 2) {
			session.close();    return;
		}
		connectList.add(new ChatUserDto(session, mid, gNo));
	    OnMessage(session, "user");
	}
	
	// OnMessage Method
	// 23.03.30, gNo(게임방 번호) 동일한 유저에게만 메시지 전송 적용
	@OnMessage
	public void OnMessage( Session session, String msg ) throws Exception{
		
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		
		// ------------------ 접속자 ------------------
		if( msg.equals("user")) {
			
			ArrayList<ChatMessageDto> messageList = new ArrayList<>();
			// messageList 생성
			for( ChatUserDto dto : connectList ) {
			// ChatUserDto type의 인스턴스를 connectList 전체 인덱스 회전
				messageList.add( new ChatMessageDto(dto.getSession(), msg) );
				// 수행동작: messageList에 user정보와 메시지 정보 저장
			}
			json = mapper.writeValueAsString( messageList );
		// ------------------ 메시지 ------------------	
		} else {
			ChatMessageDto messageDto = new ChatMessageDto(session, msg);
			json = mapper.writeValueAsString(messageDto);
			
			int senderGno = 0; //--- 변수 추가(이유: gNO 일치 여부 확인을 위함) 
			for (ChatUserDto dto : connectList) { //--- 코드 추가: gNO 
		            if (dto.getSession() == session) {
		                senderGno = dto.getgNo();	break;
		            }
		     }
			for( ChatUserDto dto : connectList ) {
				 if (dto.getgNo() == senderGno) {
				dto.getSession().getBasicRemote().sendText(json);
				 }
			}
		}	
	}
	
	// onClose Method
	@OnClose
	public void onClose( Session session ) throws Exception {
		
		for( ChatUserDto dto : connectList ) {
			
			if( dto.getSession() == session ) {
				connectList.remove(dto);
				
				OnMessage( session, "user" );
				break;
			}
		}
	}
}
