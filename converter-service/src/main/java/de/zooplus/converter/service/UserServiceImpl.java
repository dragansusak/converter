package de.zooplus.converter.service;

import de.zooplus.converter.dao.repository.UserRepository;
import de.zooplus.converter.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dragan Susak on 18-Nov-16.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;
    @Override
    public List<Users> getAllUsers() {
        return repository.findAll();
    }
}
