package ru.datateh.study.gwtp.client.view;

import com.google.gwt.core.client.Callback;
import com.gwtplatform.mvp.client.UiHandlers;

public interface FormUiHandlers extends UiHandlers {

	void addOrEditClient(Callback<Object, Object> callback);
}
