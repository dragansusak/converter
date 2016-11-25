package de.zooplus.converter.service.internal;

import de.zooplus.converter.dao.repository.UserRepository;
import de.zooplus.converter.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Created by Dragan Susak on 25-Nov-16.
 */
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserServiceImpl();
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
    }

    @Test
    public void testAllUsers() throws Exception {

    }
}
