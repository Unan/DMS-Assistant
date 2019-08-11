package com.griddynamics.dms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

    @RequestMapping("/login")
    public String index(){
        return "redirect:index.html";
    }
}
