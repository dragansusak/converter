package de.zooplus.converter.web.controller;

import de.zooplus.converter.model.Users;
import de.zooplus.converter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Dragan Susak on 17-Nov-16.
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<Users>> getHome(){
        List<Users> l = userService.getAllUsers();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }
}
