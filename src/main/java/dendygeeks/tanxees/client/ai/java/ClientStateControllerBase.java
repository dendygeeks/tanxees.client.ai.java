package dendygeeks.tanxees.client.ai.java;

import dendygeeks.tanxees.api.java.model.ClientStateModel;
import dendygeeks.tanxees.api.java.model.DebugDataModel;
import dendygeeks.tanxees.api.java.model.PlayerKeysModel;
import dendygeeks.tanxees.api.java.model.TheStateModel;

public abstract class ClientStateControllerBase {
	private ClientStateModel<DebugDataModel> clientStateModel;
	
	public ClientStateModel<DebugDataModel> getClientStateModel() {
		return clientStateModel;
	}
	
	public ClientStateControllerBase() {
		clientStateModel = new ClientStateModel<DebugDataModel>(new PlayerKeysModel(), new DebugDataModel(null));
	}
	
	/**
	 * @param theState the current game state received from the server
	 * @return <code>true</code> if the state is updated and needed to be sent to the server, 
	 * <code>false</code> otherwise. 
	 */
	public abstract boolean updateClientState(TheStateModel theState);
}
