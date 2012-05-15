package ru.datateh.study.clients.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import ru.datateh.study.clients.client.place.ClientPlaceManager;
import ru.datateh.study.clients.client.core.MainPagePresenter;
import ru.datateh.study.clients.client.core.MainPageView;
import ru.datateh.study.clients.client.place.DefaultPlace;
import ru.datateh.study.clients.client.place.NameTokens;
import ru.datateh.study.clients.client.core.ResponsePresenter;
import ru.datateh.study.clients.client.core.ResponseView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class,
				MainPageView.class, MainPagePresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.main);

		bindPresenter(ResponsePresenter.class, ResponsePresenter.MyView.class,
				ResponseView.class, ResponsePresenter.MyProxy.class);
	}
}
