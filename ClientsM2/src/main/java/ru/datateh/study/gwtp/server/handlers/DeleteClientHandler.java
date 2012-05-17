package ru.datateh.study.gwtp.server.handlers;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.datateh.study.gwtp.server.dao.ClientsDAO;
import ru.datateh.study.gwtp.shared.command.DeleteClientAction;
import ru.datateh.study.gwtp.shared.command.DeleteClientResult;

public class DeleteClientHandler implements ActionHandler<DeleteClientAction, DeleteClientResult> {

	@Autowired
	private ClientsDAO clientsDAO;

	public DeleteClientHandler() {
	}

	@Override
	public DeleteClientResult execute(DeleteClientAction action, ExecutionContext context) throws ActionException {
		try {
			clientsDAO.deleteClient(action.getClient());
			return new DeleteClientResult();
		} catch (Exception ex) {
			throw new ActionException(ex);
		}
	}

	@Override
	public Class<DeleteClientAction> getActionType() {
		return DeleteClientAction.class;
	}

	@Override
	public void undo(DeleteClientAction action, DeleteClientResult result, ExecutionContext context) throws ActionException {
	}

}
