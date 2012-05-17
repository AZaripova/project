package ru.datateh.study.gwtp.server.handlers;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.datateh.study.gwtp.server.dao.ClientsDAO;
import ru.datateh.study.gwtp.shared.command.EditClientAction;
import ru.datateh.study.gwtp.shared.command.EditClientResult;

public class EditClientHander implements ActionHandler<EditClientAction, EditClientResult> {

	@Autowired
	private ClientsDAO clientsDAO;

	public EditClientHander() {
	}

	@Override
	public EditClientResult execute(EditClientAction action, ExecutionContext context) throws ActionException {
		try {
			clientsDAO.updateClient(action.getClient());
			return new EditClientResult();
		} catch (Exception ex) {
			throw new ActionException(ex);
		}
	}

	@Override
	public Class<EditClientAction> getActionType() {
		return EditClientAction.class;
	}

	@Override
	public void undo(EditClientAction action, EditClientResult result, ExecutionContext context) throws ActionException {
	}
	
}
