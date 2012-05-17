package ru.datateh.study.gwtp.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.io.Serializable;

public class ClientDTO implements IsSerializable, Serializable {

	private Long id;
	private String fullname;
	private String birthdate;
	private Long docNumber;
	private Boolean redEye;

	public ClientDTO() {
	}
	
	public ClientDTO(Long id, String fullname, String birthdate, Long docNumber, boolean redEye) {
		this.id			= id;
		this.fullname	= fullname;
		this.birthdate	= birthdate;
		this.docNumber	= docNumber;
		this.redEye		= redEye;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Long getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(Long docNumber) {
		this.docNumber = docNumber;
	}

	public Boolean getRedEye() {
		return redEye;
	}

	public void setRedEye(Boolean redEye) {
		this.redEye = redEye;
	}
}
