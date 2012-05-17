package ru.datateh.study.gwtp.server;

import com.gwtplatform.dispatch.server.spring.HandlerModule;
import com.gwtplatform.dispatch.server.spring.actionvalidator.DefaultActionValidator;
import com.gwtplatform.dispatch.server.spring.configuration.DefaultModule;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.datateh.study.gwtp.server.dao.ClientsDAO;
import ru.datateh.study.gwtp.server.handlers.AddClientHandler;
import ru.datateh.study.gwtp.server.handlers.DeleteClientHandler;
import ru.datateh.study.gwtp.server.handlers.EditClientHander;
import ru.datateh.study.gwtp.server.handlers.GetClientsHandler;
import ru.datateh.study.gwtp.shared.command.AddClientAction;
import ru.datateh.study.gwtp.shared.command.DeleteClientAction;
import ru.datateh.study.gwtp.shared.command.EditClientAction;
import ru.datateh.study.gwtp.shared.command.GetClientsAction;

@Configuration
@Import(DefaultModule.class)
public class ServerModule extends HandlerModule {
	
	private ClientsDAO clientsDAO;
	private DozerBeanMapper mapper;
	
	public ServerModule() {
		final List mappingFiles = new ArrayList();
		mappingFiles.add("dozerBeanMapping.xml");
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(mappingFiles);
		this.clientsDAO = new ClientsDAO();
	}
	
	@Bean
	public DefaultActionValidator getDefaultActionValidator() {
		return new DefaultActionValidator();
	}
	
	@Bean
	public AddClientHandler getAddClientHandler() {
		return new AddClientHandler();
	}
	
	@Bean
	public EditClientHander getEditClientHander() {
		return new EditClientHander();
	}
	
	@Bean
	public DeleteClientHandler getDeleteClientHandler() {
		return new DeleteClientHandler();
	}
	
	@Bean
	public GetClientsHandler getClientsHandler() {
		return new GetClientsHandler();
	}
	
	@Bean
	public ClientsDAO getClientsDAO() {
		return clientsDAO;
	}
	
	@Bean
	public Mapper getMapper() {
		return mapper;
	}

	@Override
	protected void configureHandlers() {
		bindHandler(AddClientAction.class,		AddClientHandler.class);
		bindHandler(EditClientAction.class,		EditClientHander.class);
		bindHandler(DeleteClientAction.class,	DeleteClientHandler.class);
		bindHandler(GetClientsAction.class,		GetClientsHandler.class);
	}
}
