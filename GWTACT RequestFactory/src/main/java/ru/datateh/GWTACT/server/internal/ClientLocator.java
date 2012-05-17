package ru.datateh.GWTACT.server.internal;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import ru.datateh.GWTACT.server.ClientDao;
import ru.datateh.GWTACT.server.domain.Client;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ClientLocator extends Locator<Client, Long> implements ServiceLocator
{

  @Override
  public Client create( Class<? extends Client> clazz ) {
    return new Client();
  }

  @Override
  public Client find( Class<? extends Client> clazz, Long id ) {
    ClientDao dao = new ClientDaoImpl();
    return dao.findById( id );
  }


  @Override
  public Class<Client> getDomainType() {
    return Client.class;
  }

  @Override
  public Long getId( Client domainObject ) {
    return domainObject.getId();
  }

  @Override
  public Class<Long> getIdType() {
    return Long.class;
  }

  @Override
  public Object getVersion( Client domainObject ) {
    return domainObject.getVersion();
  }

    @Override
    public Object getInstance(Class<?> clazz) 
    {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(RequestFactoryServlet.getThreadLocalServletContext());
        return context.getBean(clazz);
    }
}
