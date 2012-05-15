package ru.datateh.study.clients.shared;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;
import ru.datateh.study.clients.shared.SendTextToServerResult;

public class SendTextToServer extends
		UnsecuredActionImpl<SendTextToServerResult> {

	private String textToServer;

	@SuppressWarnings("unused")
	private SendTextToServer() {
		// For serialization only
	}

	public SendTextToServer(String textToServer) {
		this.textToServer = textToServer;
	}

	public String getTextToServer() {
		return textToServer;
	}
}
