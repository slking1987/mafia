package com.vb.mafia.srv.demo.service;

import com.mafia.core.test.MafiaTest;
import com.mafia.srv.demo.dao.entity.MafiaAdmin;
import com.mafia.srv.demo.service.MafiaAdminService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shaolin on 2017/1/17.
 */
public class MafiaAdminServiceTest extends MafiaTest {

    @Autowired
    private MafiaAdminService mafiaAdminService;

    @Test
    public void insert() throws Exception {
        MafiaAdmin admin = new MafiaAdmin();
        admin.setName("test");
        mafiaAdminService.insert(admin);
    }

    @Test
    public void update() throws Exception {
        MafiaAdmin admin = new MafiaAdmin();
        admin.setId(1);
        admin.setName("test upate");
        mafiaAdminService.update(admin);
    }

    @Test
    public void delete() throws Exception {
        mafiaAdminService.delete(1);
    }

    @Test
    public void selectList() throws Exception {
        mafiaAdminService.selectList();
    }

}