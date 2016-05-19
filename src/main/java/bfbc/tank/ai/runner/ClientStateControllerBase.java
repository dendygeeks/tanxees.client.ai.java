package bfbc.tank.ai.runner;

import bfbc.tank.api.model.ClientStateModel;
import bfbc.tank.api.model.DebugDataModel;
import bfbc.tank.api.model.PlayerKeysModel;
import bfbc.tank.api.model.TheStateModel;

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
