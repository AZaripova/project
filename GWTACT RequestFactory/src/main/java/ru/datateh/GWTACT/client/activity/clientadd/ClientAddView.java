package ru.datateh.GWTACT.client.activity.clientadd;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import ru.datateh.GWTACT.client.dto.ClientDTO;

public class ClientAddView extends Composite implements Editor<ClientDTO>
{

    private static ClientAddUiBinder uiBinder = GWT.create(ClientAddUiBinder.class);
    private static ClientAddActivity clientAddActivity;
    
    @UiField Button okButton;
    @UiField ValueBoxEditorDecorator<Long> id;
    @UiField ValueBoxEditorDecorator<String> fullName;
    @UiField ValueBoxEditorDecorator<Long> dockNumber;
    @UiField DataBoxView birthDate;
    @UiField RedEyeBoxView redEye;
    @UiField Button cancelButton;

    interface ClientAddUiBinder extends UiBinder<Widget, ClientAddView>{}
    
    ClientAddView(ClientAddActivity clientAddActivityToSet) 
    {
        initWidget(uiBinder.createAndBindUi(this));
        if (clientAddActivity == null)
            clientAddActivity=clientAddActivityToSet;
    }
    
    
    
    /*
     * Событие для кнопки "оК"
     */
    @UiHandler("okButton")
    void onOkClick(ClickEvent event) 
    {
        clientAddActivity.onOkClick();
    }
    
    /*
     * Событие для кнопки "Отмена"
     */
    @UiHandler("cancelButton")
    void onCancelClick(ClickEvent event)
    {
        clientAddActivity.onCancelClick();
    }
}