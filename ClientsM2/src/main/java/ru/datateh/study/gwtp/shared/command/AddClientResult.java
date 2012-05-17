package ru.datateh.study.gwtp.shared.command;

import com.gwtplatform.dispatch.shared.Result;
import ru.datateh.study.gwtp.shared.dto.ClientDTO;

public class AddClientResult implements Result {

	private ClientDTO	client;

	private AddClientResult() {
	}

	public AddClientResult(ClientDTO client) {
		this.client = client;
	}
	
	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public ClientDTO getClient() {
		return client;
	}
	
}
