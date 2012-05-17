/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.datateh.study.gwtp.shared.command;

import com.gwtplatform.dispatch.shared.Result;
import java.io.Serializable;
import java.util.List;
import ru.datateh.study.gwtp.shared.dto.ClientDTO;

/**
 *
 * @author rshamsutdinov
 */
public class GetClientsResult implements Result, Serializable {
	
	private List<ClientDTO> clients; // for serialization?
	
	private GetClientsResult() {
	}

	public GetClientsResult(List<ClientDTO> clients) {
		this.clients = clients;
	}

	public List<ClientDTO> getClients() {
		return clients;
	}
}
