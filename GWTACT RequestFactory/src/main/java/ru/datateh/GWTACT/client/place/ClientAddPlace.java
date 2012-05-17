package ru.datateh.GWTACT.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.Prefix;

/**
 *
 * @author Vadim
 */
public class ClientAddPlace extends Place
{
    
    private String token;
    
    public ClientAddPlace(String token)
    {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    
    @Prefix("clientAdd")
    public static class Tokenizer implements PlaceTokenizer<ClientAddPlace>
    {

        @Override
        public ClientAddPlace getPlace(String token) 
        {
             return new ClientAddPlace(token);
        }

        @Override
        public String getToken(ClientAddPlace place) 
        {
           return place.getToken();
        }
        
    }
    
}
