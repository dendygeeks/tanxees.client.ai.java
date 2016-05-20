package dendygeeks.tanxees.client.ai.java;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import dendygeeks.tanxees.api.java.model.ClientStateModel;
import dendygeeks.tanxees.api.java.model.DebugDataModel;
import dendygeeks.tanxees.api.java.model.PlayerKeysModel;
import dendygeeks.tanxees.api.java.model.TheStateModel;

class ClientStateControllerWebSocket extends WebSocketAdapter
{
	private ClientStateControllerBase clientStateController;
	
    public ClientStateControllerWebSocket(ClientStateControllerBase clientStateController) {
		this.clientStateController = clientStateController;
	}

	@Override
    public void onWebSocketConnect(Session sess)
    {
        super.onWebSocketConnect(sess);
        System.out.println("Socket Connected: " + sess);
    }
    
    @Override
    public void onWebSocketText(String message)
    {
        super.onWebSocketText(message);
        //System.out.println("Received TEXT message: " + message);
        TheStateModel theStateModel = TheStateModel.fromJson(message);
        //System.out.println(theStateModel.getGameModel().getPlayers().get("player1").getUnit().getPosX());
        
        //PlayerKeysModel pkm = new PlayerKeysModel(false, false, false, true, false);
        //ClientStateModel<DebugDataModel> csm = new ClientStateModel<DebugDataModel>(pkm, new DebugDataModel(""));
        
        boolean hasToUpdate = clientStateController.updateClientState(theStateModel);
        
        if (hasToUpdate) {
	        try {
				this.getRemote().sendString(clientStateController.getClientStateModel().toJson());
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
    
    @Override
    public void onWebSocketClose(int statusCode, String reason)
    {
        super.onWebSocketClose(statusCode,reason);
        System.out.println("Socket Closed: [" + statusCode + "] " + reason);
    }
    
    @Override
    public void onWebSocketError(Throwable cause)
    {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
    }
}