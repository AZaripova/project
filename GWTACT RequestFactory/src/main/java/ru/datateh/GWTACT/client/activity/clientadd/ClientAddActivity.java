package ru.datateh.GWTACT.client.activity.clientadd;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import ru.datateh.GWTACT.client.ClientProxy;
import ru.datateh.GWTACT.client.ClientRequestFactory.ClientRequestContext;
import ru.datateh.GWTACT.client.GWTACTEntryPoint;
import ru.datateh.GWTACT.client.activity.MyAbstractActivity;
import ru.datateh.GWTACT.client.activity.PresenterNavigator;
import ru.datateh.GWTACT.client.dto.ClientDTO;
import ru.datateh.GWTACT.client.helpers.ParameterParser;
import ru.datateh.GWTACT.client.place.ClientAddPlace;
import ru.datateh.GWTACT.client.place.ClientsShowPlace;

@Singleton
public class ClientAddActivity extends MyAbstractActivity implements PresenterNavigator
{
        
    interface Driver extends SimpleBeanEditorDriver<ClientDTO, ClientAddView>{}
        
    private Driver clientDTODriver;
    
    private final ClientAddView clientAdd;
    private final PlaceController placeController;

    
    //сохраняем параметры
    private String parameters;
    //Версия клиента (используется только при редактировании)
    private Long version;
    
    @Inject
    public ClientAddActivity(PlaceController placeController)
    {
        this.placeController = placeController;
        clientAdd = new ClientAddView(this);
        clientDTODriver = GWT.create(Driver.class);
        clientDTODriver.initialize(clientAdd);
    }
    
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) 
    {
        panel.setWidget(clientAdd.asWidget());
        clientDTODriver.edit(new ClientDTO());
    }

    @Override
    public void goTo(Place place) 
    {
        placeController.goTo(place);
    }
    
    
    @Override
    public void beforeShow(Place place) 
    {
        String token = ((ClientAddPlace)place).getToken();
        if (token.length()>0)
        {
            parameters = token;
            ParameterParser parser = new ParameterParser(parameters);
            version = Long.parseLong(parser.getVersion());
//            clientAdd.id.setText(parser.getId());
//            clientAdd.fullName.setText(parser.getFullName());
//            clientAdd.dockNumber.setText(parser.getDockNumber());
//            clientAdd.birthDate.getTextBox().setText(parser.getBirthDate());
            if (parser.getRedEye().equals("true"))
                clientAdd.redEye.setValue(true);
            else
                clientAdd.redEye.setValue(false);
        }
        else
        {
            version = null;
            parameters = null;
//            clientAdd.birthDate.getTextBox().setText("01.01.1988");
//            clientAdd.id.setText("");
//            clientAdd.fullName.setText("");
//            clientAdd.dockNumber.setText("");
//            clientAdd.redEye.setValue(false);
//            clientAdd.id.setText("");
        }
    }
    
    public void onOkClick()//(final String id, final String fullName, final String dockId, final Date birthDate, final Boolean redEye)
    {
        try
        {
            final ClientDTO clientDTO = clientDTODriver.flush();
            if (version == null)
            {
                ClientRequestContext context = GWTACTEntryPoint.createContext();
                Request<Void> request = context.addNew(clientDTO.getFullName(), clientDTO.getDockNumber(), clientDTO.getBirthDate(), clientDTO.getRedEye());

                request.fire(new Receiver<Void>()
                {
                    @Override
                    public void onSuccess(Void result) 
                    {
                    Window.alert("Клиент успешно добавлен!");
                    placeController.goTo(new ClientsShowPlace());
                    }

                    @Override
                    public void onFailure(ServerFailure error) 
                    {
                        Window.alert("Клиент не добавлен! Ошибка:\n"+error.getMessage());
                    }            
                });       
            }

            if (version != null)
            {
                ClientRequestContext context = GWTACTEntryPoint.createContext();
                Request<ClientProxy> request = context.findById(Long.parseLong(clientDTO.getId().toString()));

                request.fire(new Receiver<ClientProxy>()
                {

                    //Выдергиваем клиента по айдишнику. Если он найден - изменяем
                    @Override
                    public void onSuccess(ClientProxy response) 
                    {
                        if (response == null)
                            Window.alert("Клиент не обновлен!\n (На момент обновления клиент удален)");
                        else
                        {
                            if (response.getVersion() != version)
                            {

                                final Long lastVersion = response.getVersion();

                                final DialogBox dialog = new DialogBox(false, true);

                                //Создаем FlexTable

                                FlexTable flexTable = new FlexTable();
                                FlexTable.FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();

                                flexTable.addStyleName("cw-FlexTable");
                                flexTable.setWidth("32em");
    //                            flexTable.setCellSpacing(5);
    //                            flexTable.setCellPadding(3);

                                flexTable.setHTML(0, 0, "<center><H4><font color=\"red\">Во время редактирования произошли изменения в базе!"
                                        + "<br>Выберите вариант для внесения в базу:</font></H4></center>");
                                cellFormatter.setColSpan(0, 0, 4);

                                //заголовки таблички
                                flexTable.setText(1, 1, "До редактирования");
                                flexTable.setText(1, 2, "Ваш вариант");
                                flexTable.setText(1, 3, "Сейчас в базе");
                                flexTable.setText(2, 0, "Имя");
                                flexTable.setText(3, 0, "№ Документа");
                                flexTable.setText(4, 0, "День рождения");
                                flexTable.setText(5, 0, "Красноглазость");

                                //Запролнем данные о клиенте на начало редактирования
                                ParameterParser parser = new ParameterParser(parameters);
                                flexTable.setText(2, 1, parser.getFullName());
                                flexTable.setText(3, 1, parser.getDockNumber());
                                flexTable.setText(4, 1, parser.getBirthDate());
                                flexTable.setText(5, 1, parser.getRedEye());

                                //заполняем данные, которыу сейчас введены у пользователя
                                flexTable.setText(2, 2, clientDTO.getFullName().toString());
                                flexTable.setText(3, 2, clientDTO.getDockNumber().toString());
                                DateTimeFormat format = DateTimeFormat.getFormat("dd.MM.yyyy");
                                flexTable.setText(4, 2, format.format(clientDTO.getBirthDate()));
                                flexTable.setText(5, 2, clientDTO.getRedEye().toString());

                                //заполняем данные, которые сейчас в базе
                                flexTable.setText(2, 3, response.getFullName());
                                flexTable.setText(3, 3, response.getDockNumber().toString());
                                flexTable.setText(4, 3, format.format(response.getBirthDate()));
                                flexTable.setText(5, 3, response.getRedEye().toString());

                                //Откат параметров на "до начала редактирования"
                                flexTable.setWidget(6, 1, new Button("Выбрать", new ClickHandler() 
                                {
                                    @Override
                                    public void onClick(ClickEvent event) 
                                    {
                                        ParameterParser parser = new ParameterParser(parameters);
                                        DateTimeFormat format = DateTimeFormat.getFormat("dd.MM.yyyy");
                                        version = lastVersion;
                                        dialog.hide();
//!!!!!!!!!!!!!!!!!!!!!                 onOkClick(clientDTO.getId().toString(, parser.getFullName(), parser.getDockNumber(), format.parse(parser.getBirthDate()), Boolean.parseBoolean(parser.getRedEye()));
                                    }
                                }));

                                //устанавливаем свои параметры
                                flexTable.setWidget(6, 2, new Button("Выбрать", new ClickHandler() 
                                {
                                    @Override
                                    public void onClick(ClickEvent event) 
                                    {
                                        version = lastVersion;
                                        dialog.hide();
//                                        onOkClick(id, clientAdd.fullName.getText(), clientAdd.dockNumber.getText(), clientAdd.birthDate.getValue(), clientAdd.redEye.getValue());
                                    }
                                }));

                                flexTable.setWidget(6, 3, new Button("Выбрать", new ClickHandler() 
                                {
                                    @Override
                                    public void onClick(ClickEvent event) 
                                    {
                                        dialog.hide();
                                        placeController.goTo(new ClientsShowPlace());
                                    }
                                }));

                                //добавляем кнопку "Отмена"
                                cellFormatter.setColSpan(7, 0, 4);

                                Button cancelButton = new Button("Отмена", new ClickHandler() 
                                    {
                                        @Override
                                        public void onClick(ClickEvent event) 
                                        {
                                            dialog.hide();
                                        }
                                    });
                                cancelButton.setWidth("100%");

                                flexTable.setWidget(7, 0, cancelButton);
                                dialog.add(flexTable);


                                dialog.center();
                                dialog.show();
                            }
                            else
                            {
                                ClientRequestContext context = GWTACTEntryPoint.createContext();
                                ClientProxy canEdit = context.edit(response);
                                canEdit.setFullName(clientDTO.getFullName());
                                canEdit.setDockNumber(clientDTO.getDockNumber());
                                canEdit.setBirthDate(clientDTO.getBirthDate());
                                canEdit.setRedEye(clientDTO.getRedEye());
                                canEdit.setVersion(++version);
                                context.update(canEdit).fire(new Receiver<Void>()
                                {
                                    @Override
                                    public void onSuccess(Void response) 
                                    {
                                        Window.alert("Клиент успешно обновлен!");
                                        placeController.goTo(new ClientsShowPlace());
                                    }

                                    @Override
                                    public void onFailure(ServerFailure error) 
                                    {
                                        Window.alert("Ошибка!+\n"+error.getMessage());
                                    }
                                });
                            }
                        }
                    }

                    //Если клиент по каким-то причинам не найден (удалили с другого компа или на страничку редактирования вернулись кнопкой "взад" в браузере)
                    //выдаем ошибку
                    @Override
                    public void onFailure(ServerFailure error) 
                    {
                        Window.alert("Клиент не обновлен!\n"+error.getMessage());
                    }
                });
            }
        }
        catch (Exception ex)
        {
            Window.alert("Проверьте вводимые параметры!\n"+ex.getMessage());
        }
    }
    
    /*
     * проверка на правильность заполнения полей во вьюшке
     */
    Boolean clientValidation(String name, String dockId)
    {
        Boolean result = true;
        try
        {
            if (name.trim().length() == 0)
                result = false;
            Long tmp = Long.parseLong(dockId);
            if (tmp <= 0)
            {
                return false;
            }
        }
        catch (Throwable th)
        { 
            return false;
        }
        return result;
    }
    
    public void onCancelClick()
    {
        placeController.goTo(new ClientsShowPlace());
    }
}
