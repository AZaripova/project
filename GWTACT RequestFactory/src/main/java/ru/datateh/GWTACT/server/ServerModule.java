package ru.datateh.GWTACT.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import ru.datateh.GWTACT.server.helpers.ClientMapper;

public class ServerModule 
{
    
    @Autowired
    ClientMapper clientMapper;
    
    @Bean
    public ClientMapper getClientMapper()
    {
        return clientMapper;
    }
    
}
