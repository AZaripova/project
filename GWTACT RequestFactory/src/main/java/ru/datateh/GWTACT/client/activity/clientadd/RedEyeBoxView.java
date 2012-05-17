package ru.datateh.GWTACT.client.activity.clientadd;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;

public class RedEyeBoxView extends Composite implements LeafValueEditor<Boolean>
{

    private final CheckBox redEye;
    
    public RedEyeBoxView()
    {
        redEye = new CheckBox();
        redEye.setText(":)");
        initWidget(redEye);
    }
    
    @Override
    public void setValue(Boolean value) 
    {
        if (value != null) redEye.setValue(value);
    }

    @Override
    public Boolean getValue() 
    {
        return redEye.getValue();
    }
    
}
