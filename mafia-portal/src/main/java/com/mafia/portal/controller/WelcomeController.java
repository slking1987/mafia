package com.mafia.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shaolin on 2017/3/29.
 */
@Controller
public class WelcomeController {

    @RequestMapping("/")
    public String index() {
        return "welcome.html";
    }
}
