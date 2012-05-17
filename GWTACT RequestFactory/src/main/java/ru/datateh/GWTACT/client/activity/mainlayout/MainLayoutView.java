package ru.datateh.GWTACT.client.activity.mainlayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import ru.datateh.GWTACT.client.activity.OneWidgetLayoutPanel;

public class MainLayoutView extends Composite
{
    interface MainLayoutUiBinder extends UiBinder<Widget, MainLayoutView>{}
    
    private static final MainLayoutUiBinder uiBinder = GWT.create(MainLayoutUiBinder.class);
    
    @UiField
    Button addButton;
    @UiField Button showButton;
    @UiField OneWidgetLayoutPanel panel;
        
    private static MainLayoutActivity mainLayoutActivity;
    
    MainLayoutView(MainLayoutActivity mainLayoutActivityToSet) 
    {
        initWidget(uiBinder.createAndBindUi(this));
        if (mainLayoutActivity==null)
        {
            mainLayoutActivity = mainLayoutActivityToSet;
        }
    }
    
    /*
     * возвращает панельку, в которой будем отображать виджеты
     */
    OneWidgetLayoutPanel getPanel()
    {
        return this.panel;
    }

    /*
     * делает кнопку "Добавить" неактивной/активной
     */
    void setAddButtonEnabled(Boolean value)
    {
        addButton.setEnabled(value);
    }
    
    /*
     * Делает кнопку "Показать" неактивной/активной
     */
    void setShowButtonEnabled(Boolean value)
    {
        showButton.setEnabled(value);
    }
    
    @UiHandler("addButton")
    void onAddClick(ClickEvent event) 
    {
        mainLayoutActivity.onAddClick();
    }
    
    @UiHandler("showButton")
    void onShowClick(ClickEvent event) 
    {
        mainLayoutActivity.onShowClick();
    }
}