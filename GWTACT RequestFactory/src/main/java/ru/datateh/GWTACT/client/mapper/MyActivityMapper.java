package ru.datateh.GWTACT.client.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.datateh.GWTACT.client.activity.clientadd.ClientAddActivity;
import ru.datateh.GWTACT.client.activity.clientsshow.ClientsShowActivity;
import ru.datateh.GWTACT.client.activity.mainlayout.MainLayoutActivity;
import ru.datateh.GWTACT.client.place.ClientAddPlace;
import ru.datateh.GWTACT.client.place.ClientsShowPlace;

@Singleton
public class MyActivityMapper implements ActivityMapper
{
    
    private final MainLayoutActivity mainLayoutActivity;
    private final ClientsShowActivity clientsShowActivity;
    private final ClientAddActivity clientAddActivity;
    
    @Inject
    public MyActivityMapper (MainLayoutActivity mainLayoutActivity, ClientsShowActivity clientsShowActivity, ClientAddActivity clientAddActivity)
    {
        super();
        this.mainLayoutActivity = mainLayoutActivity;
        this.clientAddActivity = clientAddActivity;
        this.clientsShowActivity = clientsShowActivity;
    }
    
    @Override
    public Activity getActivity(Place place) 
    {
        if (place instanceof ClientAddPlace)
        {
            mainLayoutActivity.setAddButtonEnabled(false);
            mainLayoutActivity.setShowButtonEnabled(true);
            clientAddActivity.beforeShow(place);
            return clientAddActivity;
        }
        else if (place instanceof ClientsShowPlace)
        {
            mainLayoutActivity.setAddButtonEnabled(true);
            mainLayoutActivity.setShowButtonEnabled(false);
            clientsShowActivity.beforeShow(place);
            return clientsShowActivity;
        }
        return null;
    }
    
}
