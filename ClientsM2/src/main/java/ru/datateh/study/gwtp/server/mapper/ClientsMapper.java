/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.datateh.study.gwtp.server.mapper;

import java.util.List;
import ru.datateh.study.gwtp.server.model.Client;

/**
 *
 * @author rshamsutdinov
 */
public interface ClientsMapper {
	
	public void addClient(Client client);

	public void deleteClient(Client client);

	public void updateClient(Client client);

	public List<Client> getClients();
}
