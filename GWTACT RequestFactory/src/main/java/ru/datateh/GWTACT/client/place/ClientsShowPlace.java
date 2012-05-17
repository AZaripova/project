package ru.datateh.GWTACT.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ClientsShowPlace extends Place
{    
    @Inject
    public ClientsShowPlace()
    {
    }
    
    @Prefix("clientsShow")
    public static class Tokenizer implements PlaceTokenizer<ClientsShowPlace>
    {

        @Override
        public ClientsShowPlace getPlace(String token) 
        {
             return new ClientsShowPlace();
        }

        @Override
        public String getToken(ClientsShowPlace place) 
        {
           return "";
        }
        
    }
}
