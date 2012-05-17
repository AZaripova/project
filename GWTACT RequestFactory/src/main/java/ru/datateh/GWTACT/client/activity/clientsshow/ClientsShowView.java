package ru.datateh.GWTACT.client.activity.clientsshow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import ru.datateh.GWTACT.client.ClientProxy;
import ru.datateh.GWTACT.client.activity.PresenterNavigator;

public class ClientsShowView extends Composite
{
	private static ClientsShowView.ClientsShowUiBinder uiBinder = GWT.create(ClientsShowView.ClientsShowUiBinder.class);
        
	interface ClientsShowUiBinder extends UiBinder<Widget, ClientsShowView> {}
        
        private String name;
        private PresenterNavigator navigator;
        private static ClientsShowActivity clientsShowActivity;
        
        @UiField
	Button button;
        
        @UiField(provided=true) 
        CellTable<ClientProxy> table = new CellTable<ClientProxy>();
        
        
        
	ClientsShowView(ClientsShowActivity clientsShowActivityToSet) 
        {
            initWidget(uiBinder.createAndBindUi(this));
            button.setVisible(false);
            
            if (clientsShowActivity == null)
                clientsShowActivity = clientsShowActivityToSet;
	}        
        
        CellTable<ClientProxy> getTable()
        {
            return table;
        }
        
        void setTable(CellTable<ClientProxy> table)
        {
            this.table = table;
        }
        
        void setName(String name)
        {
            this.name = name;
        }

        void setNavigator(PresenterNavigator navigator)
        {
            this.navigator = navigator;
        }
}
