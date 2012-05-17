package ru.datateh.study.gwtp.shared.command;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;
import ru.datateh.study.gwtp.shared.dto.ClientDTO;

public class AddClientAction extends UnsecuredActionImpl<AddClientResult> {

	private ClientDTO client;

	private AddClientAction() {
	}

	public AddClientAction(ClientDTO client) {
		super();
		this.client = client;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}
}
