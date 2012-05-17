package ru.datateh.GWTACT.server.internal;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.datateh.GWTACT.server.ClientDao;
import ru.datateh.GWTACT.server.domain.Client;
import ru.datateh.GWTACT.server.helpers.ClientMapper;

@Service("clientRequestFactory")
public class ClientDaoImpl implements ClientDao 
{

    @Autowired
    private ClientMapper clientMapper;
    
    @Autowired
    private SqlSessionFactoryBean sqlSessionFactory;
    
    @Override
    public Client findById(Long id )
    {
        return clientMapper.findById(id);
    }
    
    @Override
    public void addNew(String fullName, Long dockNumber, Date birthDate, Boolean redEye)
    {
        try
        {
            
            clientMapper.addNew(fullName, dockNumber, birthDate, redEye);
        }
        catch (Throwable th)
        {
            String str = null;
        }
        String as = null;
    }
    
    @Override
    public List<Client> getClients()
    {
        return clientMapper.getClients();
    }
    
    @Override
    public void delete(Long id)
    {
        clientMapper.delete(id);
    }

    @Override
    public void update(Client client)
    {
        clientMapper.update(client);
    }
}
