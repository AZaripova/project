package ru.datateh.study.gwtp.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import java.util.List;
import ru.datateh.study.gwtp.client.presenter.ListViewPresenter.MyView;
import ru.datateh.study.gwtp.shared.ClientColumn;
import ru.datateh.study.gwtp.shared.dto.ClientDTO;

public class ListView extends ViewWithUiHandlers<ListUiHandlers> implements MyView {

	private static ListViewUiBinder	uiBinder	= GWT.create(ListViewUiBinder.class);
	
	@UiField(provided=true) CellTable<ClientDTO> cellTable = new CellTable<ClientDTO>();

	interface ListViewUiBinder extends UiBinder<Widget, ListView> {
	}
	
	private final DialogBox dialogBox = new DialogBox(true);
	private final MenuBar 	menuBar = new MenuBar(true);
	private final Widget	widget;

	@Inject
	public ListView() {
		widget = uiBinder.createAndBindUi(this);
		
		final ContextMenuHandler contextMenuHandler = new ContextMenuHandler() {
			@Override
			public void onContextMenu(ContextMenuEvent event) {
				event.preventDefault();
				event.stopPropagation();
			}
		};
		
		widget.addDomHandler(contextMenuHandler, ContextMenuEvent.getType());
		dialogBox.addDomHandler(contextMenuHandler, ContextMenuEvent.getType());
		menuBar.setAnimationEnabled(true);
		dialogBox.setHTML("<center>Меню</center>");
		dialogBox.add(menuBar);
		
		final ClientColumn columns[] = new ClientColumn[] {
			new ClientColumn(ClientColumn.ClientColumnType.FULLNAME),
			new ClientColumn(ClientColumn.ClientColumnType.BIRTHDATE),
			new ClientColumn(ClientColumn.ClientColumnType.DOC_NUMBER),
			new ClientColumn(ClientColumn.ClientColumnType.RED_EYE)
		};
		
		for (ClientColumn col : columns) {
			cellTable.addColumn(col, col.getHeader());
		}
		
		cellTable.addCellPreviewHandler(
			new CellPreviewEvent.Handler<ClientDTO>() {
				@Override
				public void onCellPreview(final CellPreviewEvent<ClientDTO> event) {
					if (event.getNativeEvent().getType().endsWith("mousedown") && event.getNativeEvent().getButton() == NativeEvent.BUTTON_RIGHT) {
						dialogBox.hide();
						menuBar.clearItems();
						
						menuBar.addItem("Изменить", false,
								new Command() {
									@Override
									public void execute() {
										dialogBox.hide();
										getUiHandlers().editClient(event.getValue());
									}
								}
						);
						menuBar.addItem("Удалить", false,
								new Command() {
									@Override
									public void execute() {
										dialogBox.hide();
										getUiHandlers().deleteClient(event.getValue());
									}
								}
						);
						
						dialogBox.setPopupPosition(event.getNativeEvent().getClientX() + Document.get().getBodyOffsetLeft(), event.getNativeEvent().getClientY() + Document.get().getBodyOffsetTop());
						dialogBox.show();
					}
				}
			}
		);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setRowData(List<ClientDTO> data) {
		cellTable.setRowData(data);
		cellTable.redraw();
	}
	
}
