package ru.datateh.GWTACT.server.spring;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import java.util.Date;
import ru.datateh.GWTACT.server.domain.Client;
import ru.datateh.GWTACT.server.internal.ClientLocator;

@ProxyFor(value = Client.class, locator = ClientLocator.class)
public interface ClientProxy extends ValueProxy
{
    public Long getId();
    public void setId(Long id);


    public String getFullName();
    public void setFullName(String fullName);


    public Date getBirthDate();
    public void setBirthDate(Date birthDate);


    public Long getDockNumber();
    public void setDockNumber(Long docNumber);


    public Boolean getRedEye();
    public void setRedEye(Boolean redEye);
    
    public Long getVersion();
    public void setVersion(Long version);
}

