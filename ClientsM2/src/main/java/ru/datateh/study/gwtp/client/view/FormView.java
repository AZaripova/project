package ru.datateh.study.gwtp.client.view;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import ru.datateh.study.gwtp.client.presenter.FormViewPresenter.MyView;
import ru.datateh.study.gwtp.client.ui.BirthdateEditor;
import ru.datateh.study.gwtp.client.ui.RedEyeEditor;
import ru.datateh.study.gwtp.shared.dto.ClientDTO;

public class FormView extends ViewWithUiHandlers<FormUiHandlers> implements MyView, Editor<ClientDTO> {
	
	interface FormViewUiBinder extends UiBinder<Widget, FormView> {
	}
	
	private static FormViewUiBinder uiBinder = GWT
			.create(FormViewUiBinder.class);
	
	@UiField Grid form;

	@UiField
	ValueBoxEditorDecorator<String> fullname;

	@UiField
	BirthdateEditor birthdate;
	
	@UiField
	ValueBoxEditorDecorator<Long> docNumber;

	@UiField
	RedEyeEditor redEye;
	
	@UiField Button formButton;
	
	private final Widget widget;

	@Inject
	public FormView() {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@UiHandler("formButton")
	void onFormButtonClick(ClickEvent event) {
		formButton.setEnabled(false);
		getUiHandlers().addOrEditClient(
			new Callback<Object, Object>() {
				@Override
				public void onSuccess(Object result) {
					done();
				}
				
				@Override
				public void onFailure(Object reason) {
					done();
				}
				
				private void done() {
					formButton.setEnabled(true);
				}
		});
	}

	@Override
	public void setButtonLabel(String label) {
		formButton.setText(label);
	}
}
