package ru.datateh.GWTACT.client.helpers;

import java.util.HashMap;

public class ParameterParser 
{
    private HashMap<String, String> parametersMap;
    
    public ParameterParser(String parameters) 
    {
        parse(parameters);
    }
    
    private void parse(String parameters)
    {
        parametersMap = new HashMap<String, String>();
        String[] keyValue = parameters.split(";");
        for (int i=0;i<keyValue.length;i++)
        {
            String[] tmp = keyValue[i].split("=");
            parametersMap.put(tmp[0], tmp[1]);
        }
    }
    
    public String getId()
    {
        return parametersMap.get("id");
    }
    
    public String getFullName()
    {
        return parametersMap.get("fullName");
    }
    
    public String getDockNumber()
    {
        return parametersMap.get("dockNumber");
    }
    
    public String getBirthDate()
    {
        return parametersMap.get("birthDate");
    }
    
    public String getRedEye()
    {
        return parametersMap.get("redEye");
    }
    
    public String getVersion()
    {
        return parametersMap.get("version");
    }
    
}
