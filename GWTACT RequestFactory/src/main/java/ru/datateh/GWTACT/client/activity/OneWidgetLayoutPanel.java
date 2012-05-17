package ru.datateh.GWTACT.client.activity;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTMLPanel;

public class OneWidgetLayoutPanel extends HTMLPanel implements AcceptsOneWidget
{
    
    private IsWidget widget = null;

    public OneWidgetLayoutPanel(String str)
    {
        super("");
    }
    
    @Override
    public void setWidget(IsWidget w) 
    {
        if( widget != null) super.remove(widget);
		widget = w;
		if(w != null) super.add(w);
    }
}
