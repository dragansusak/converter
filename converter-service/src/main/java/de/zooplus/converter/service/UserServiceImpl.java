package de.zooplus.converter.service;

import de.zooplus.converter.dao.repository.UserRepository;
import de.zooplus.converter.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dragan Susak on 18-Nov-16.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

//    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
