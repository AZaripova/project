package ru.datateh.study.gwtp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import ru.datateh.study.gwtp.client.gin.ClientGinjector;

public class Clients implements EntryPoint {

	private final ClientGinjector GINJECTOR = GWT.create(ClientGinjector.class);

	@Override
	public void onModuleLoad() {
		// This is required for Gwt-Platform proxy's generator
		DelayedBindRegistry.bind(GINJECTOR);
	
		GINJECTOR.getPlaceManager().revealCurrentPlace();	
	}
	
	public static ClientGinjector getGinjector() {
		return (ClientGinjector) DelayedBindRegistry.getGinjector();
	}
}
