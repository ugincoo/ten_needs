package controller.chatting;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.GameroomDao;
import model.dto.ChatMessageDto;
import model.dto.ChatUserDto;

@ServerEndpoint("/tenneeds/chatting/{gNo}/{mid}")
public class Chatting {
	
	// 접속자 저장
	public static ArrayList< ChatUserDto > connectList = new ArrayList<>();
	
	
	@OnOpen // onOpen Method
	public void OnOpen( Session session, @PathParam("gNo") int gNo, @PathParam("mid") String mid ) throws Exception {
			System.out.println("입장" + session);
			
		int count = 0; // --- 변수 추가(이유: gNO에 접속되어 있는 인원이 2명 넘지 않도록 제어하기 위함)
		    for (ChatUserDto dto : connectList) {
		        if (dto.getgNo() == gNo) { count++; }
		    }
		if (count >= 2) {
			session.close();    return;
		}
		
		connectList.add(new ChatUserDto(session, mid, gNo, false));
	    OnMessage(session, "user");
	}
	
	// OnMessage Method
	// 23.03.30, gNo(게임방 번호) 동일한 유저에게만 메시지 전송 적용
	@OnMessage
	public void OnMessage( Session session, String msg ) throws Exception{
		
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		
		if(msg.contains("user")) { // --- 접속자 정보 (전체한테 뿌려지는지 확인해야함)
			System.out.println( "user확인" );
			ArrayList<ChatMessageDto> messageList = new ArrayList<>();
			
			int senderGno = 0;
			for (ChatUserDto dto : connectList) { //--- 코드 추가: gNO 
	            if (dto.getSession() == session) {
	                senderGno = dto.getgNo();	break;
	            }
			}
			for( ChatUserDto dto : connectList ) {
				if (dto.getgNo() == senderGno) {
				messageList.add( new ChatMessageDto(dto.getSession(), msg) );
				}
			}
			json = mapper.writeValueAsString( messageList );
			
			
			for( ChatUserDto dto : connectList ) {
				if (dto.getgNo() == senderGno) {
				dto.getSession().getBasicRemote().sendText(json);
				}
			}
		} else if( msg.contains("game")) { // --- 게임 레디
			ChatMessageDto messageDto = new ChatMessageDto(session, msg);
			json = mapper.writeValueAsString(messageDto);
			
			for( ChatUserDto dto : connectList ) {
				if( dto.getSession() == session ) {
					if( msg.contains("true") ) {
						dto.setreadyState(true);
					} else {
						dto.setreadyState(false);
					} break;
				}
			}
			
			int senderGno = 0;
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
		} else if( msg.contains("chat") || msg.contains("alarm") ) { // --- 메시지 전송
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
		} else if(  msg.contains("out") ) { // --- 메시지 전송
			System.out.println("ssssss");
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
	public void onClose( Session session, @PathParam("gNo") int gNo, @PathParam("mid") String mid ) throws Exception {
		
		for( ChatUserDto dto : connectList ) {
			
			if( dto.getSession() == session ) {
				connectList.remove(dto);
				
				String msg = "{\"type\":\"out\",\"data\":\""+dto.getmId()+"님이 게임방을 나갔습니다.\"}";
				// 형태: { "필드명" : "데이터", "필드명" : 데이터 }
				// 해석: 큰따음표 사용을 위에 이스케이프 문자 사용
				
				System.out.println(msg);
				
				OnMessage( session, msg);
				
				// 모든 접속명단에게 연결 끊긴 클라이언트 소캣을 알림 [접속목록]
				OnMessage( session, "user" );
				session.close();  break;
			}
		}
		
		int count = 0; // --- 변수 추가(이유: gNO에 접속되어 있는 인원이 2명 넘지 않도록 제어하기 위함)
		for (ChatUserDto dto : connectList) {
		        if (dto.getgNo() == gNo) { count++; }
		    }
		if (count == 0) {
			GameroomDao.getInstance().onDelete(gNo);
			session.close();    return;
		}
	}
}
