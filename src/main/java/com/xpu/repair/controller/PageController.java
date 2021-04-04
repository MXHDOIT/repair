package com.xpu.repair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @RequestMapping(value = {"/","/login"},method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = {"user/index"},method = RequestMethod.GET)
    public String userIndex(){
        return "user/index";
    }

    @RequestMapping(value = {"admin/index"},method = RequestMethod.GET)
    public String adminIndex(){
        return "admin/index";
    }

    @RequestMapping(value = {"technician/index"},method = RequestMethod.GET)
    public String technicianIndex(){
        return "technician/index";
    }
}
