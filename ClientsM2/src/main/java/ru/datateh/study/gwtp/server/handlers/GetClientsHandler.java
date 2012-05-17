/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.datateh.study.gwtp.server.handlers;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.datateh.study.gwtp.server.dao.ClientsDAO;
import ru.datateh.study.gwtp.shared.command.GetClientsAction;
import ru.datateh.study.gwtp.shared.command.GetClientsResult;

/**
 *
 * @author rshamsutdinov
 */
public class GetClientsHandler implements ActionHandler<GetClientsAction, GetClientsResult> {
	
	@Autowired
	private ClientsDAO clientsDAO;
	
	public GetClientsHandler() {
	}

	@Override
	public GetClientsResult execute(GetClientsAction a, ExecutionContext ec) throws ActionException {
		try {
			return new GetClientsResult(clientsDAO.getClients());
		} catch (Exception ex) {
			throw new ActionException(ex.toString());
		}
	}

	@Override
	public Class<GetClientsAction> getActionType() {
		return GetClientsAction.class;
	}

	@Override
	public void undo(GetClientsAction a, GetClientsResult r, ExecutionContext ec) throws ActionException {
	}
	
}
