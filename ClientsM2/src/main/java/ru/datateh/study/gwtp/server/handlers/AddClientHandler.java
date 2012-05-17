package ru.datateh.study.gwtp.server.handlers;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.datateh.study.gwtp.shared.dto.ClientDTO;
import ru.datateh.study.gwtp.server.dao.ClientsDAO;
import ru.datateh.study.gwtp.shared.command.AddClientAction;
import ru.datateh.study.gwtp.shared.command.AddClientResult;

public class AddClientHandler implements ActionHandler<AddClientAction, AddClientResult> {

	@Autowired
	private ClientsDAO clientsDAO;

	public AddClientHandler() {
	}

	@Override
	public AddClientResult execute(AddClientAction action, ExecutionContext context) throws ActionException {
		try {
			final ClientDTO client = action.getClient();
			clientsDAO.addClient(client);
			return new AddClientResult(client);
		} catch (Exception ex) {
			throw new ActionException(ex);
		}
	}

	@Override
	public Class<AddClientAction> getActionType() {
		return AddClientAction.class;
	}

	@Override
	public void undo(AddClientAction action, AddClientResult result, ExecutionContext context) throws ActionException {
	}

}
