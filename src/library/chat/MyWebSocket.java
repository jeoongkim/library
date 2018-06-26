package library.chat;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

// https://static.javadoc.io/org.java-websocket/Java-WebSocket/1.3.4/org/java_websocket/server/WebSocketServer.html
public class MyWebSocket extends WebSocketServer {
	
	private Set<WebSocket> clientSockets = new HashSet<>();

	public static void main(String[] args) {
		MyWebSocket ws = new MyWebSocket(new InetSocketAddress(1337));
		ws.start();
		System.out.println("Start WebSocket ...");
	}

	public MyWebSocket(InetSocketAddress address) {
		super(address);
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println("onClose");
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		System.out.println("onError" + ex.getMessage());
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		System.out.println("onMessage: " + message);
		for (WebSocket item : clientSockets) {
			item.send(message);
		}
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		System.out.println("onOpen");
		clientSockets.add(conn);
	}

}
