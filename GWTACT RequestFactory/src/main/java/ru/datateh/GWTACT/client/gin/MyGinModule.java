package ru.datateh.GWTACT.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class MyGinModule extends AbstractGinModule 
{

    @Override
    protected void configure() 
    {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceController.class).to(MyPlaceController.class).in(Singleton.class);
    }
    
}
