package ru.datateh.GWTACT.client.activity.mainlayout;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.datateh.GWTACT.client.activity.OneWidgetLayoutPanel;
import ru.datateh.GWTACT.client.gin.MyGinjector;
import ru.datateh.GWTACT.client.place.ClientAddPlace;
import ru.datateh.GWTACT.client.place.ClientsShowPlace;

@Singleton
public class MainLayoutActivity
{
    private final MainLayoutView mainLayout;
    private final PlaceController placeController;

    @Inject
    public MainLayoutActivity(MyGinjector ginjectorToSet, PlaceController placeController)
    {
        mainLayout = new MainLayoutView(this);
        this.placeController = placeController;
    }

    public MainLayoutView getMainLayout()
    {
        return mainLayout;
    }

    public OneWidgetLayoutPanel getPanel()
    {
        return mainLayout.getPanel();
    }
    
    public void onAddClick() 
    {
        placeController.goTo(new ClientAddPlace(""));
    }
    
    public void onShowClick() 
    {
        placeController.goTo(new ClientsShowPlace());
    }
    
    /*
     * делает кнопку "Добавить" неактивной/активной
     */
    public void setAddButtonEnabled(Boolean value)
    {
        mainLayout.setAddButtonEnabled(value);
    }
    
    /*
     * Делает кнопку "Показать" неактивной/активной
     */
    public void setShowButtonEnabled(Boolean value)
    {
        mainLayout.setShowButtonEnabled(value);
    }
    
}
