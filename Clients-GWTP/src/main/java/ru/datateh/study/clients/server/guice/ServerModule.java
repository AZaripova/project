package ru.datateh.study.clients.server.guice;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import ru.datateh.study.clients.shared.SendTextToServer;
import ru.datateh.study.clients.server.SendTextToServerActionHandler;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {

		bindHandler(SendTextToServer.class, SendTextToServerActionHandler.class);
	}
}
