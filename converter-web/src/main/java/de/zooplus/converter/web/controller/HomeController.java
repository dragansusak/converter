package de.zooplus.converter.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dragan Susak on 17-Nov-16.
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<String> getHome(){
        return new ResponseEntity<>("home", HttpStatus.OK); Heroku
    }
}
