package ru.datateh.GWTACT.client.activity.clientadd;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.datepicker.client.DateBox;
import java.util.Date;

public class DataBoxView extends Composite implements LeafValueEditor<Date>
{

    private final DateBox dateBox;
    
    public DataBoxView()
    {
        dateBox = new DateBox();
        dateBox.getTextBox().setReadOnly(true);
        initWidget(dateBox);
        dateBox.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd.MM.yyyy")));
    }
    
    @Override
    public void setValue(Date value) 
    {
        if (value!=null)
            dateBox.setValue(value);
    }

    @Override
    public Date getValue() 
    {
        return dateBox.getValue();
    }
    
}
