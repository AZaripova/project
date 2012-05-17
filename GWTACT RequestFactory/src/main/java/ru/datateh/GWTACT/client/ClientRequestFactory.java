package ru.datateh.GWTACT.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import java.util.Date;
import java.util.List;
import ru.datateh.GWTACT.server.ClientDao;
import ru.datateh.GWTACT.server.internal.DaoLocator;

@RemoteServiceRelativePath("springGwtServices/clientRequestFactory")
public interface ClientRequestFactory extends RequestFactory 
{

  @Service(value = ClientDao.class, locator = DaoLocator.class)
  public interface ClientRequestContext extends RequestContext 
  {
    Request<ClientProxy> findById(Long id);
    Request<Void> addNew(String fullName, Long dockNumber, Date birthDate, Boolean redEye);
    Request<List<ClientProxy>> getClients();
    Request<Void>delete(Long id);
    Request<Void>update(ClientProxy client);
  }
  
  ClientRequestContext context();
}