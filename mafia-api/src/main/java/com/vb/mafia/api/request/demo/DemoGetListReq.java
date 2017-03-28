package com.vb.mafia.api.request.demo;

import com.vb.mafia.core.constant.ModuleType;
import com.vb.mafia.core.validator.EnumValidator;

/**
 * Created by shaolin on 2017/1/18.
 */
public class DemoGetListReq {
    private String name;
    @EnumValidator(enumClazz = ModuleType.class, message = "test enum validator")
    private String moduleType;

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
