package com.vb.mafia.api.request.demo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by shaolin on 2017/1/18.
 */
public class DemoInsertReq {
    @NotEmpty(message = "name is empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
