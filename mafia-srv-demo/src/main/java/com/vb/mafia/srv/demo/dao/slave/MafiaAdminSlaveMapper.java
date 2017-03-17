package com.vb.mafia.srv.demo.dao.slave;

import com.vb.mafia.srv.demo.dao.entity.MafiaAdmin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by shaolin on 2017/1/17.
 */
public interface MafiaAdminSlaveMapper {

    @Select("SELECT * FROM mafia_admin")
    List<MafiaAdmin> selectList();
}
