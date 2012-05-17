package ru.datateh.GWTACT.client.helpers;

import com.google.gwt.i18n.client.DateTimeFormat;
import ru.datateh.GWTACT.client.ClientProxy;


public class ParameterBuilder 
{
    private String parameters;
    
    public ParameterBuilder (ClientProxy client)
    {
        parameters = "";
        if (client.getId()!=null)
            parameters+="id="+client.getId().toString()+";";
        if (client.getFullName()!=null)
            parameters+="fullName="+client.getFullName()+";";
        if (client.getDockNumber()!=null)
            parameters+="dockNumber="+client.getDockNumber().toString()+";";
        if (client.getBirthDate()!=null)
        {
            DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
            parameters+="birthDate="+dateTimeFormat.format(client.getBirthDate())+";";
        }
        if (client.getRedEye()!=null)
            parameters+="redEye="+client.getRedEye().toString()+";";
        if (client.getVersion()!=null)
            parameters+="version="+client.getVersion().toString()+";";
    }
    
    public String getParameters()
    {
        return parameters;
    }
}
