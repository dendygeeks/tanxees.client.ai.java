package bfbc.tank.ai.runner;

import java.net.URI;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class Main {
	public static void main(String[] args)
    {
        URI uri = URI.create("ws://localhost:9876/player?playerId=bot1");

        WebSocketClient client = new WebSocketClient();
        try
        {
            try
            {
            	
                client.start();
                // The socket that receives events
                AIPlayerClientWebSocket socket = new AIPlayerClientWebSocket();
                // Attempt Connect
                Future<Session> fut = client.connect(socket,uri);
                // Wait for Connect
                Session session = fut.get();
                
                while (session.isOpen()) {
                	Thread.sleep(100);
	                // Send a message
	                //session.getRemote().sendString("Hello");
	                // Close session
                }
                session.close();
            }
            finally
            {
                client.stop();
            }
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
    }
}