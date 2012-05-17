package ru.datateh.GWTACT.client.activity.clientsshow;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import java.util.List;
import ru.datateh.GWTACT.client.ClientProxy;
import ru.datateh.GWTACT.client.ClientRequestFactory.ClientRequestContext;
import ru.datateh.GWTACT.client.GWTACTEntryPoint;
import ru.datateh.GWTACT.client.activity.MyAbstractActivity;
import ru.datateh.GWTACT.client.activity.PresenterNavigator;
import ru.datateh.GWTACT.client.helpers.ParameterBuilder;
import ru.datateh.GWTACT.client.place.ClientAddPlace;
import ru.datateh.GWTACT.client.place.ClientsShowPlace;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

@Singleton
public class ClientsShowActivity extends MyAbstractActivity implements PresenterNavigator
{
    
    private final ClientsShowView clientsShow;
    private final PlaceController placeController;
    
    ListDataProvider<ClientProxy> provider;
        
    List<ClientProxy> clients;
    
    @Inject
    public ClientsShowActivity(ClientsShowPlace clientShowPlace, PlaceController placeController)
    {
        clientsShow = new ClientsShowView(this);
        clientsShow.setName("clientsShow");
        clientsShow.setNavigator(this);
        
        this.placeController = placeController;
        
        createTableStructure(clientsShow.getTable());        
    }
    
    /*
        * Создаем колонки, настраиваем для них хандлеры и заталкиваем в таблицу
        */
    private void createTableStructure(CellTable<ClientProxy> table)
    {

        //Создаем обработчик кликов для редактирования клиента
        FieldUpdater<ClientProxy, String> editFilter = new FieldUpdater<ClientProxy, String>() 
        {

            public void update(int index, ClientProxy object, String value) 
            {
                ParameterBuilder builder = new ParameterBuilder(object);
                String str = builder.getParameters();
                placeController.goTo(new ClientAddPlace(str));
            }
        };
        
        
        //СОЗДАЕМ КОЛОНКИ

        // idColumn
        Column<ClientProxy, String> idColumn = new Column<ClientProxy, String>(
            new ClickableTextCell()) 
        {
            @Override
            public String getValue(ClientProxy object) 
            {
                return object.getId().toString();
            }
        };
        idColumn.setSortable(true);   
        table.setColumnWidth(idColumn, 20, Style.Unit.PCT);
        idColumn.setFieldUpdater(editFilter);
        
        // fullNameColumn
        Column<ClientProxy, String> fullNameColumn = new Column<ClientProxy, String>(
            new ClickableTextCell()) 
        {
            @Override
            public String getValue(ClientProxy object) 
            {
                return object.getFullName();
            }
        };
        fullNameColumn.setSortable(false);            
        table.setColumnWidth(fullNameColumn, 20, Style.Unit.PCT);
        fullNameColumn.setFieldUpdater(editFilter);

        // docNumberColunm
        Column<ClientProxy, String> docNumberColunm = new Column<ClientProxy, String>(
            new ClickableTextCell()) 
        {
            @Override
            public String getValue(ClientProxy object) 
            {
                return object.getDockNumber().toString();
            }
        };
        docNumberColunm.setSortable(false);            
        table.setColumnWidth(docNumberColunm, 20, Style.Unit.PCT);
        docNumberColunm.setFieldUpdater(editFilter);

        // birthDateColumn
        Column<ClientProxy, String> birthDateColumn = new Column<ClientProxy, String>(
            new ClickableTextCell()) 
        {
            @Override
            public String getValue(ClientProxy object) 
            {
                DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
                return dateTimeFormat.format(object.getBirthDate());
            }
        };
        birthDateColumn.setSortable(false);            
        table.setColumnWidth(birthDateColumn, 20, Style.Unit.PCT);
        birthDateColumn.setFieldUpdater(editFilter);
        
        // redEyeColumn
        Column<ClientProxy, String> redEyeColumn = new Column<ClientProxy, String>(
            new ClickableTextCell()) 
        {
            @Override
            public String getValue(ClientProxy object) 
            {
                return object.getRedEye().toString();
            }
        };
        redEyeColumn.setSortable(false);
        table.setColumnWidth(redEyeColumn, 20, Style.Unit.PCT);
        redEyeColumn.setFieldUpdater(editFilter);
        
        // removeBtn
        Column<ClientProxy, String> removeBtn = new Column<ClientProxy, String>(
            new ButtonCell()) 
        {                
            @Override
            public String getValue(ClientProxy object) 
            {
                return "Удалить";
            }
        };
        removeBtn.setSortable(false);
        table.setColumnWidth(removeBtn, 20, Style.Unit.PCT);
        
        
        //назначаем событие на кнопку удаления
        FieldUpdater<ClientProxy, String> filter = new FieldUpdater<ClientProxy, String>() 
        {
            @Override
            public void update(int index, ClientProxy object, String value) 
            {
                ClientRequestContext context = GWTACTEntryPoint.createContext();
                Request<Void> request = context.delete(object.getId());
                request.fire(new Receiver<Void>()
                {
                    @Override
                    public void onSuccess(Void response) 
                    {
                        Window.alert("Клиент успешно удален!");
                        updateDataInTable();
                    }

                    @Override
                    public void onFailure(ServerFailure error) 
                    {
                        String str = null;
                        Window.alert(error.getMessage());
                    }
                });
            } 
        };
        removeBtn.setFieldUpdater(filter);         

        ///Добавляем колонки в таблицу
        table.addColumn(idColumn, "id");
        table.addColumn(fullNameColumn, "Имя");
        table.addColumn(docNumberColunm, "№ документа");
        table.addColumn(birthDateColumn, "День рождения");
        table.addColumn(redEyeColumn, "Красноглазость"); 
        table.addColumn(removeBtn, "Удалить");
    }
    
    /*
     * выполняется при КАЖДОМ вызове активити
     */
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) 
    {
        updateDataInTable();
        panel.setWidget(clientsShow.asWidget());
    }

    @Override
    public void goTo(Place place) 
    {
        placeController.goTo(place);
    }
    
    /*
     * Добавляем данные из базы в табличку
     */
    private void updateDataInTable()
    {
        ClientRequestContext context = GWTACTEntryPoint.createContext();
        Request<List<ClientProxy>> request = context.getClients();
        request.fire(new Receiver<List<ClientProxy>>()
        {
            @Override
            public void onSuccess(List<ClientProxy> response) 
            {
                if ((response!=null) && (response.size()>0))
                {
                    clients = response;
                    Comparator<ClientProxy> comparator = new Comparator<ClientProxy>() 
                    {
                        @Override
                        public int compare(ClientProxy o1, ClientProxy o2) 
                        {
                            if (o1.getId()>o2.getId())
                                return 1;
                            if (o1.getId()<o2.getId())
                                return -1;
                            else return 0;
                        }
                    };
                    Collections.sort(clients, comparator);
                    setDatabase();
                }
            }
            @Override
            public void onFailure(ServerFailure error) 
            {
                Window.alert(error.getMessage());
            }
        });
    }

    
    private void setDatabase()
    {    
        provider = new ListDataProvider<ClientProxy>(clients);
        provider.addDataDisplay(clientsShow.getTable());
    }
    
    /*
     * вызывается перед каждым переходом на форму
     */
    @Override
    public void beforeShow(Place place)
    {
//        updateDataInTable();
    }
    
}