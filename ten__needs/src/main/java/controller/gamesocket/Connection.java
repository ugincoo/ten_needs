package controller.gamesocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/game/{gno}/{user1}/{user2}")
public class Connection {
	
	
	@OnOpen
	public void enterServer( Session session , @PathParam("gno") int gno , @PathParam("user1") int user1 , @PathParam("user2") int user2 ) throws Exception {
		System.out.println( session );
		System.out.println(gno +" , "+ user1 +" , "+  user2 );
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
	}
}
