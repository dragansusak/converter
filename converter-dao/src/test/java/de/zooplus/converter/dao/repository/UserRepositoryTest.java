package de.zooplus.converter.dao.repository;

import de.zooplus.converter.dao.config.TestDatabaseEmbededConfig;
import de.zooplus.converter.model.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dragan Susak on 25-Nov-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
@ContextConfiguration(classes = {TestDatabaseEmbededConfig.class}, loader = AnnotationConfigContextLoader.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Transactional
    @Test
    public void testFindUser() throws Exception {
        User user = userRepository.findOne(1);
        Assert.assertEquals("1", user.getId().toString());
        Assert.assertEquals(19, user.getConversions().size());
    }
}
