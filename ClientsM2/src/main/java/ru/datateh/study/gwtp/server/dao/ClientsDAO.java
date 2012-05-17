package ru.datateh.study.gwtp.server.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ru.datateh.study.gwtp.server.mapper.ClientsMapper;
import ru.datateh.study.gwtp.server.model.Client;
import ru.datateh.study.gwtp.shared.dto.ClientDTO;

@Transactional
public class ClientsDAO extends AbstractDAO {
	
	public ClientsDAO() {
	}

	public void addClient(final ClientDTO client) throws Exception {
		getMapper(ClientsMapper.class).addClient(getMapper().map(client, Client.class));
	}

	public void deleteClient(final ClientDTO client) throws Exception {
		getMapper(ClientsMapper.class).deleteClient(getMapper().map(client, Client.class));
	}

	public void updateClient(final ClientDTO client) throws Exception {
		getMapper(ClientsMapper.class).updateClient(getMapper().map(client, Client.class));
	}

	@Transactional(readOnly=true)
	public List<ClientDTO> getClients() throws Exception {
		final List<ClientDTO> clients = new ArrayList<ClientDTO>();
		final List<Client> list = getMapper(ClientsMapper.class).getClients();
		for (Client cl : list) {
			clients.add(getMapper().map(cl, ClientDTO.class));
		}
		return clients;
	}

}
