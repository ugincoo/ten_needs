package controller.testgamesockat;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import model.dto.ChatUserDto;

@ServerEndpoint("/test/sockat/{mid}")
public class TestGame {
	
	// onOpen Method
		@OnOpen
		public void OnOpen( Session session, @PathParam("mid") String mid ) throws Exception {
				System.out.println("입장" + session);
			
		}

}
