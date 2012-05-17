package ru.datateh.study.gwtp.shared;

import com.google.gwt.user.cellview.client.TextColumn;
import ru.datateh.study.gwtp.shared.dto.ClientDTO;

public class ClientColumn extends TextColumn<ClientDTO> {
	
	public static enum ClientColumnType {
		FULLNAME,
		BIRTHDATE,
		DOC_NUMBER,
		RED_EYE
	}

	private final ClientColumnType type;

	public ClientColumn(ClientColumnType type) {
		super();
		this.type = type;
	}

	public String getHeader() {
		switch(type) {
			case FULLNAME:
				return "ФИО";
				
			case BIRTHDATE:
				return "Дата рождения";
				
			case DOC_NUMBER:
				return "Номер документа";
				
			case RED_EYE:
				return "Красные глаза";
		}
		return null;
	}

	@Override
	public String getValue(ClientDTO cl) {
		switch(type) {
			case FULLNAME:
				return cl.getFullname();
				
			case BIRTHDATE:
				return cl.getBirthdate();
				
			case DOC_NUMBER:
				return cl.getDocNumber().toString();
				
			case RED_EYE:
				if (cl.getRedEye()) {
					return "Да";
				}
				return "Нет";
		}
		return null;
	}
}
