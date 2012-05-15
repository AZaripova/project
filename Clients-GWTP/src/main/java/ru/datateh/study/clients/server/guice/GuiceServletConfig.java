package ru.datateh.study.clients.server.guice;

import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.Injector;
import com.google.inject.Guice;
import ru.datateh.study.clients.server.guice.ServerModule;
import ru.datateh.study.clients.server.guice.DispatchServletModule;

public class GuiceServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice
				.createInjector(new ServerModule(), new DispatchServletModule());
	}
}
