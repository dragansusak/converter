package de.zooplus.converter.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dragan on 19-Nov-16.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @RequestMapping
    public String getRegistration(){
        return "registration";
    }
}
