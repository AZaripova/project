package ru.datateh.GWTACT.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import ru.datateh.GWTACT.client.ClientRequestFactory.ClientRequestContext;
import ru.datateh.GWTACT.client.gin.MyGinjector;
import ru.datateh.GWTACT.client.mapper.AppPlaceHistoryMapper;
import ru.datateh.GWTACT.client.place.ClientsShowPlace;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTACTEntryPoint implements EntryPoint 
{
  
    private static final MyGinjector ginjector = GWT.create(MyGinjector.class);
    private Place defaultPlace = new ClientsShowPlace();
  
  /**
   * This is the entry point method.
   */
    @Override
  public void onModuleLoad() 
  {
    ActivityManager activityManager = new ActivityManager(ginjector.getMyActivityMapper(), ginjector.getEventBus());

    AppPlaceHistoryMapper historyMapper= GWT.create(AppPlaceHistoryMapper.class);
    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
    historyHandler.register(ginjector.getMyPlaceController(), ginjector.getEventBus(), defaultPlace);

    activityManager.setDisplay(ginjector.getMainLayoutActivity().getPanel());

    RootLayoutPanel.get().add(ginjector.getMainLayoutActivity().getMainLayout());

    historyHandler.handleCurrentHistory();
  } 

  public static ClientRequestContext createContext() 
  {
    ClientRequestFactory factory = GWT.create(ClientRequestFactory.class);
    factory.initialize(ginjector.getEventBus());
    return factory.context();
  }
}
