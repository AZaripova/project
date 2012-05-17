package ru.datateh.GWTACT.client.dto;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;


public class ClientDTO implements IsSerializable
{
    
    private Long id;
    
    private String fullName;

    private Long dockNumber;
    
    private Date birthDate;

    private Boolean redEye;
    
    private Long version;
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the dockNumber
     */
    public Long getDockNumber() {
        return dockNumber;
    }

    /**
     * @param dockNumber the dockNumber to set
     */
    public void setDockNumber(Long dockNumber) {
        this.dockNumber = dockNumber;
    }

    /**
     * @return the redEye
     */
    public Boolean getRedEye() {
        return redEye;
    }

    /**
     * @param redEye the redEye to set
     */
    public void setRedEye(Boolean redEye) {
        this.redEye = redEye;
    }
}
