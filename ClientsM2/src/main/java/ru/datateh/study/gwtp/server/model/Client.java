package ru.datateh.study.gwtp.server.model;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.io.Serializable;
import java.util.Date;

public class Client implements IsSerializable, Serializable {

	private Long id;
	private String fullname;
	private Date birthdate;
	private Long docNumber;
	private Boolean redEye;

	public Client() {
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
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
