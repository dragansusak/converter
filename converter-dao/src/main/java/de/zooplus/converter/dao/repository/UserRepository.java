package de.zooplus.converter.dao.repository;

import de.zooplus.converter.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dragan Susak on 18-Nov-16.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    long countByEmail(String email);

    User findByEmail(String email);

}
