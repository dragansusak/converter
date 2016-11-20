package de.zooplus.converter.service.internal;

import de.zooplus.converter.model.entity.User;

import java.util.List;

/**
 * Created by Dragan Susak on 18-Nov-16.
 */
public interface UserService {

    List<User> getAllUsers();

    boolean checkEmailUnique(String email);

    void saveUser(User user);

    User getUserByEmail(String email);

    User getById(Integer userId);
}
