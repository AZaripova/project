package ru.datateh.GWTACT.client.mapper;

import ru.datateh.GWTACT.client.place.*;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ClientAddPlace.Tokenizer.class, ClientsShowPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper
{
    
}
