package ru.datateh.GWTACT.server.helpers;

import ru.datateh.GWTACT.server.internal.*;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ru.datateh.GWTACT.server.ClientDao;
import ru.datateh.GWTACT.server.domain.Client;

public interface ClientMapper extends ClientDao 
{

    @Override
    @Select("select * from tb_client where id = #{id}")
    public Client findById(@Param("id") Long id );

    @Override
    @Insert("into tb_client (fullmane, dockNumber, birthDate, redEye) values (#{fullmane}, #{dockNumber}, #{birthDate}, #{redEye})")
    public void addNew(@Param("fullName") String fullName,@Param("dockNumber") Long dockNumber,@Param("birthDate") Date birthDate,@Param("redEye") Boolean redEye);
    
    @Override
    @Select ("select * from tb_client")
    public List<Client> getClients();

    @Override
    @Delete ("delete from tb_client where id = #{id}")
    public void delete(@Param("id") Long id);

    @Override
    @Update("update tb_client set fullName = #{fullName}, dockNumber=#{dockNumber}, birthDate = #{birthDate}, redEye = #{redEye} where id = #{id}")
    public void update(Client client);
}
