package ru.datateh.study.gwtp.client.gin;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import ru.datateh.study.gwtp.client.presenter.FormViewPresenter;
import ru.datateh.study.gwtp.client.presenter.ListViewPresenter;
import ru.datateh.study.gwtp.client.presenter.MainViewPresenter;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();
	
	Provider<MainViewPresenter> getHomeViewPresenter();
	AsyncProvider<FormViewPresenter> getFormViewPresenter();
	AsyncProvider<ListViewPresenter> getListViewPresenter();
}
