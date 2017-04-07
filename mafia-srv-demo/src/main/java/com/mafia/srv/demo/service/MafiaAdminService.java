package com.mafia.srv.demo.service;

import com.google.common.base.Preconditions;
import com.mafia.core.log.LogService;
import com.mafia.srv.demo.dao.entity.MafiaAdmin;
import com.mafia.srv.demo.dao.master.MafiaAdminMasterMapper;
import com.mafia.srv.demo.dao.slave.MafiaAdminSlaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shaolin on 2017/1/17.
 */
@Service
@Transactional(rollbackFor = {Throwable.class})
public class MafiaAdminService {
    private static final Class LOG_CLASS = MafiaAdminService.class;

    @Autowired
    private MafiaAdminMasterMapper masterMapper;
    @Autowired
    private MafiaAdminSlaveMapper slaveMapper;

    @Autowired
    private LogService logService;

    public int insert(MafiaAdmin mafiaAdmin)
    {
        Preconditions.checkNotNull(mafiaAdmin);
        return masterMapper.insert(mafiaAdmin);
    }

    public int update(MafiaAdmin mafiaAdmin)
    {
        return masterMapper.update(mafiaAdmin);
    }

    public int delete(int id)
    {
        return masterMapper.delete(id);
    }

    public List<MafiaAdmin> selectList()
    {
        List<MafiaAdmin> list = slaveMapper.selectList();
        logService.info(LOG_CLASS, list);
        return list;
    }
}
