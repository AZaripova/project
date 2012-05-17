package ru.datateh.study.gwtp.shared.command;


import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;
import ru.datateh.study.gwtp.shared.dto.ClientDTO;

public class EditClientAction extends UnsecuredActionImpl<EditClientResult> {

	private ClientDTO client;

	private EditClientAction() {
	}
	
	public EditClientAction(ClientDTO client) {
		this.client = client;
	}

	public ClientDTO getClient() {
		return client;
	}
}
