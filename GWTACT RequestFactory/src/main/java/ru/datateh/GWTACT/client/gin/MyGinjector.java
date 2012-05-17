package ru.datateh.GWTACT.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;
import ru.datateh.GWTACT.client.activity.clientadd.ClientAddActivity;
import ru.datateh.GWTACT.client.activity.clientsshow.ClientsShowActivity;
import ru.datateh.GWTACT.client.activity.mainlayout.MainLayoutActivity;
import ru.datateh.GWTACT.client.mapper.MyActivityMapper;

@GinModules({MyGinModule.class})
public interface MyGinjector extends Ginjector
{
    EventBus getEventBus();
    MyPlaceController getMyPlaceController();
    MyActivityMapper getMyActivityMapper();
    
    MainLayoutActivity getMainLayoutActivity();
    ClientAddActivity getClientAddActivity();
    ClientsShowActivity getClientsShowActivity();
}