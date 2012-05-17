package ru.datateh.study.gwtp.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import ru.datateh.study.gwtp.client.place.ClientPlaceManager;
import ru.datateh.study.gwtp.client.place.DefaultPlace;
import ru.datateh.study.gwtp.client.place.NameTokens;
import ru.datateh.study.gwtp.client.presenter.FormViewPresenter;
import ru.datateh.study.gwtp.client.presenter.ListViewPresenter;
import ru.datateh.study.gwtp.client.presenter.MainViewPresenter;
import ru.datateh.study.gwtp.client.view.FormView;
import ru.datateh.study.gwtp.client.view.ListView;
import ru.datateh.study.gwtp.client.view.MainView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));
		
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.LIST);

		bindPresenter(MainViewPresenter.class, MainViewPresenter.MyView.class, MainView.class, MainViewPresenter.MyProxy.class);
		bindPresenter(FormViewPresenter.class, FormViewPresenter.MyView.class, FormView.class, FormViewPresenter.MyProxy.class);
		bindPresenter(ListViewPresenter.class, ListViewPresenter.MyView.class, ListView.class, ListViewPresenter.MyProxy.class);
	}
}
