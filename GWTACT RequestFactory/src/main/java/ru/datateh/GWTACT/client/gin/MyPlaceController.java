package ru.datateh.GWTACT.client.gin;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;

@Singleton
public class MyPlaceController extends PlaceController
{
    
    private static MyGinjector ginjector;
    
    @Inject
    public MyPlaceController(EventBus eventBus, MyGinjector ginjectorToSet)
    {
        super(eventBus);
        if (ginjector==null)
        {
            ginjector=ginjectorToSet;
        }
    }
}
