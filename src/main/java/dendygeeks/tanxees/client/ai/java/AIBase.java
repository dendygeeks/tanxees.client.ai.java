package dendygeeks.tanxees.client.ai.java;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class AIBase {
	protected void connect(String playerId, ClientStateControllerBase clientStateController) {

        WebSocketClient client = new WebSocketClient();
        try
        {
    		URI uri = URI.create("ws://localhost:9876/player?playerId=" + URLEncoder.encode(playerId, "UTF-8"));

            try
            {
                client.start();
                // The socket that receives events
                ClientStateControllerWebSocket socket = new ClientStateControllerWebSocket(clientStateController);
                // Attempt Connect
                Future<Session> fut = client.connect(socket,uri);
                // Wait for Connect
                Session session = fut.get();
                System.out.println("Connected succesfully to " + session.getRemoteAddress().toString());
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
        catch (UnsupportedEncodingException e) {
        	e.printStackTrace(System.err);
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
	}
}