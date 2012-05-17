package ru.datateh.study.gwtp.client.presenter;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.TabContentProxyPlace;
import ru.datateh.study.gwtp.client.Clients;
import ru.datateh.study.gwtp.client.place.NameTokens;
import ru.datateh.study.gwtp.client.util.DialogBoxUtil;
import ru.datateh.study.gwtp.client.view.FormUiHandlers;
import ru.datateh.study.gwtp.client.view.FormView;
import ru.datateh.study.gwtp.shared.command.AddClientAction;
import ru.datateh.study.gwtp.shared.command.AddClientResult;
import ru.datateh.study.gwtp.shared.command.EditClientAction;
import ru.datateh.study.gwtp.shared.command.EditClientResult;
import ru.datateh.study.gwtp.shared.dto.ClientDTO;

public class FormViewPresenter extends Presenter<FormViewPresenter.MyView, FormViewPresenter.MyProxy> implements FormUiHandlers {
	
	public static final String FIELD_FULLNAME	= "fullname";
	public static final String FIELD_BIRTHDATE	= "birthdate";
	public static final String FIELD_DOC_NUMBER	= "docNumber";
	public static final String FIELD_RED_EYE	= "redEye";
	public static final String FIELD_ID			= "id";
	

	@ProxyCodeSplit
	@NameToken(NameTokens.FORM)
	@TabInfo(container=MainViewPresenter.class, label="Информация о клиенте", priority=1)
	public interface MyProxy extends TabContentProxyPlace<FormViewPresenter> {
	}

	public interface MyView extends View, HasUiHandlers<FormUiHandlers> {
		void setButtonLabel(String label);
	}
	
	interface Driver extends SimpleBeanEditorDriver<ClientDTO, FormView> {}
	private final Driver driver;

	private final DispatchAsync dispatcher;
	
	@Inject
	public FormViewPresenter(EventBus eventBus, MyView view, MyProxy proxy, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		view.setUiHandlers(this);
		driver = GWT.create(Driver.class);
		driver.initialize((FormView) view);
	}
	
	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainViewPresenter.TYPE_SetTabContent, this);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		final String clientId = request.getParameter(FIELD_ID, null);
		driver.edit(
				new ClientDTO(
					(clientId == null) ? null : Long.valueOf(clientId),
					request.getParameter(FIELD_FULLNAME,	""),
					request.getParameter(FIELD_BIRTHDATE,	null),
					Long.valueOf(request.getParameter(FIELD_DOC_NUMBER,	"0")),
					Boolean.valueOf(request.getParameter(FIELD_RED_EYE,	"false"))
				));
		getView().setButtonLabel((clientId == null) ? "Добавить" : "Изменить");
	}

	@Override
	public void addOrEditClient(final Callback<Object, Object> callback) {
		final ClientDTO client = driver.flush();
		if (client.getId() == null) {
			dispatcher.execute(
				new AddClientAction(client),
				new AsyncCallback<AddClientResult>() {
					@Override
					public void onFailure(Throwable caught) {
						callback.onFailure(caught);
						DialogBoxUtil.error("Не удалось добавить клиента", caught);
					}

					@Override
					public void onSuccess(AddClientResult result) {
						callback.onSuccess(result);
						Clients.getGinjector().getPlaceManager().revealPlace(new PlaceRequest(NameTokens.LIST));
					}
					
				}
			);
		} else {
			dispatcher.execute(
				new EditClientAction(client),
				new AsyncCallback<EditClientResult>() {
					@Override
					public void onFailure(Throwable caught) {
						callback.onFailure(caught);
						DialogBoxUtil.error("Не удалось обновить информацию о клиенте", caught);
					}

					@Override
					public void onSuccess(EditClientResult result) {
						callback.onSuccess(result);
						Clients.getGinjector().getPlaceManager().revealPlace(new PlaceRequest(NameTokens.LIST), true);
					}

				}
			);
		}
	}

}
