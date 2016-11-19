package de.zooplus.converter.web.controller;

import de.zooplus.converter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Dragan Susak on 17-Nov-16.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String getHome(){
       return "home";
    }
}
