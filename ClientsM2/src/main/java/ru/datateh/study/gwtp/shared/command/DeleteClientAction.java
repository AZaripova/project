package ru.datateh.study.gwtp.shared.command;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;
import ru.datateh.study.gwtp.shared.dto.ClientDTO;

public class DeleteClientAction extends UnsecuredActionImpl<DeleteClientResult> {

	private ClientDTO client;

	private DeleteClientAction() {
	}
	
	public DeleteClientAction(ClientDTO client) {
		this.client = client;
	}

	public ClientDTO getClient() {
		return client;
	}

}
