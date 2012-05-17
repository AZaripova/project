package ru.datateh.study.gwtp.client.presenter;

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
import java.util.List;
import ru.datateh.study.gwtp.client.Clients;
import ru.datateh.study.gwtp.shared.dto.ClientDTO;
import ru.datateh.study.gwtp.client.place.NameTokens;
import ru.datateh.study.gwtp.client.util.DialogBoxUtil;
import ru.datateh.study.gwtp.client.view.ListUiHandlers;
import ru.datateh.study.gwtp.shared.command.DeleteClientAction;
import ru.datateh.study.gwtp.shared.command.DeleteClientResult;
import ru.datateh.study.gwtp.shared.command.GetClientsAction;
import ru.datateh.study.gwtp.shared.command.GetClientsResult;

public class ListViewPresenter extends Presenter<ListViewPresenter.MyView, ListViewPresenter.MyProxy> implements ListUiHandlers {

	@ProxyCodeSplit
	@NameToken(NameTokens.LIST)
	@TabInfo(container=MainViewPresenter.class, label="Клиенты", priority=0)
	public interface MyProxy extends TabContentProxyPlace<ListViewPresenter> {
	}
	
	public interface MyView extends View, HasUiHandlers<ListUiHandlers> {
		void setRowData(List<ClientDTO> data);
	}

	private DispatchAsync dispatcher;
	
	@Inject
	public ListViewPresenter(EventBus eventBus, MyView view, MyProxy proxy, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		view.setUiHandlers(this);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainViewPresenter.TYPE_SetTabContent, this);
	}

	@Override
	public boolean useManualReveal() {
		return true;
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);

		refreshList(true);
	}
	
	@Override
	public void deleteClient(final ClientDTO client) {
		dispatcher.execute(
			new DeleteClientAction(client),
			new AsyncCallback<DeleteClientResult>() {
				@Override
				public void onFailure(Throwable caught) {
					DialogBoxUtil.error("Не удалось удалить клиента", caught);
				}

				@Override
				public void onSuccess(DeleteClientResult result) {
					refreshList(false);
				}
			}
		);
	}

	@Override
	public void editClient(ClientDTO client) {
		PlaceRequest request = new PlaceRequest(NameTokens.FORM);
		request = request
					.with(FormViewPresenter.FIELD_ID,			client.getId().toString())
					.with(FormViewPresenter.FIELD_FULLNAME,		client.getFullname())
					.with(FormViewPresenter.FIELD_BIRTHDATE,	client.getBirthdate())
					.with(FormViewPresenter.FIELD_DOC_NUMBER,	client.getDocNumber().toString())
					.with(FormViewPresenter.FIELD_RED_EYE,		client.getRedEye().toString());
		Clients.getGinjector().getPlaceManager().revealPlace(request, true);
	}
	
	private void refreshList(final boolean reveal) {
		dispatcher.execute(
			new GetClientsAction(),
			new AsyncCallback<GetClientsResult>() {
				@Override
				public void onFailure(Throwable caught) {
					DialogBoxUtil.error("Не удалось получить список клиентов", caught);
					if (reveal) {
						ListViewPresenter.this.getProxy().manualRevealFailed();
					}
				}

				@Override
				public void onSuccess(GetClientsResult result) {
					ListViewPresenter.this.getView().setRowData(result.getClients());
					if (reveal) {
						ListViewPresenter.this.getProxy().manualReveal(ListViewPresenter.this);
					}
				}
			}
		);
	}
	
}
