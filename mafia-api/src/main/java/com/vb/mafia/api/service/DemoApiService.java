package com.vb.mafia.api.service;

import com.google.common.base.Preconditions;
import com.vb.mafia.api.request.demo.DemoGetListReq;
import com.vb.mafia.api.request.demo.DemoInsertReq;
import com.vb.mafia.api.util.ApiUtil;
import com.vb.mafia.core.util.JsonUtil;
import com.vb.mafia.srv.demo.dao.entity.MafiaAdmin;
import com.vb.mafia.srv.demo.service.MafiaAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shaolin on 2017/1/18.
 */
@Service
public class DemoApiService {

    @Autowired
    private MafiaAdminService mafiaAdminService;

    public List<MafiaAdmin> getList(String bodyStr) throws Exception
    {
        DemoGetListReq req = Preconditions.checkNotNull(JsonUtil.parse(bodyStr, DemoGetListReq.class), "DemoGetListReq is null");
        ApiUtil.checkParamByValidator(req);

        return mafiaAdminService.selectList();
    }

    public void insert(String bodyStr) throws Exception
    {
        DemoInsertReq req = Preconditions.checkNotNull(JsonUtil.parse(bodyStr, DemoInsertReq.class), "DemoInsertReq is null");
        ApiUtil.checkParamByValidator(req);

        MafiaAdmin admin = new MafiaAdmin();
        admin.setName(req.getName());
        mafiaAdminService.insert(admin);
    }
}
