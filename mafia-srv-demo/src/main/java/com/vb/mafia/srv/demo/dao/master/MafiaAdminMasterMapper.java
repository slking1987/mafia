package com.vb.mafia.srv.demo.dao.master;

import com.vb.mafia.srv.demo.dao.entity.MafiaAdmin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

/**
 * Created by shaolin on 2017/1/17.
 */
public interface MafiaAdminMasterMapper {

    @Insert("INSERT INTO mafia_admin(name) VALUES(#{name})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(MafiaAdmin mafiaAdmin);

    @Update("UPDATE mafia_admin SET name=#{name} WHERE id=#{id}")
    int update(MafiaAdmin mafiaAdmin);

    @Delete("DELETE FROM mafia_admin WHERE id=#{id}")
    int delete(int id);
}
