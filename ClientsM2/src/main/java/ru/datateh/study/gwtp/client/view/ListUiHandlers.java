package ru.datateh.study.gwtp.client.view;

import com.gwtplatform.mvp.client.UiHandlers;
import ru.datateh.study.gwtp.shared.dto.ClientDTO;

public interface ListUiHandlers extends UiHandlers {

	void deleteClient(ClientDTO client);
	void editClient(ClientDTO client);
}
