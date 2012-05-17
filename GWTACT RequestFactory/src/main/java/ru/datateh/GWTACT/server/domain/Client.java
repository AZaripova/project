package ru.datateh.GWTACT.server.domain;

import java.util.Date;

public class Client implements Identifiable, Versionable, Comparable<Client>
{

    private Long id;

    private Long version;
    
    private String fullName;

    private Date birthDate;

    private Long dockNumber;

    private Boolean redEye;

    public Long getId() 
    {
        return id;
    }

    
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getVersion() 
    {
        return this.version;
    }

    public void setVersion(Long version) 
    {
        this.version = version;
    }    

    public String getFullName() 
    {
        return fullName;
    }

    public void setFullName(String fullName) 
    {
        this.fullName = fullName;
    }

    public Date getBirthDate() 
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) 
    {
        this.birthDate = birthDate;
    }

    public Long getDockNumber() 
    {
        return dockNumber;
    }

    public void setDockNumber(Long docNumber) 
    {
        this.dockNumber = docNumber;
    }

    public Boolean getRedEye() 
    {
            return redEye;
    }

    public void setRedEye(Boolean redEye) 
    {
        this.redEye = redEye;
    }

    @Override
    public int compareTo(Client o) 
    {
        if (o.getId()<id)
            return -1;
        if (o.getId()>id)
            return 1;
        else return 0;
    }

}
