package ru.datateh.GWTACT.server;

import java.util.Date;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.datateh.GWTACT.server.domain.Client;

public interface ClientDao
{
    Client findById(Long id);
    void addNew(String fullName, Long dockNumber, Date birthDate, Boolean redEye);
    List<Client> getClients();
    void delete(Long id);
    void update(Client client);
}
