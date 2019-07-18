package ru.iokhin.tm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.iokhin.tm.api.UserClient;
import ru.iokhin.tm.enumerated.RoleEnum;
import ru.iokhin.tm.model.UserDTO;

import java.util.List;

import static junit.framework.TestCase.*;


public class UserClientTest {

    private final static String URL = "http://localhost:8080/api/user";
    private final static String USER_ID = "678f735d-5aa5-408b-ad36-69cf28e2e123";
    private UserClient userClient;

    @Before
    public void setUp() throws Exception {
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(USER_ID);
        userDTO.setLogin("test");
        userDTO.setPassword("test");
        userDTO.setRole(RoleEnum.USER);
        userDTO.setEmail("biba@mail.ru");
        userClient = UserClient.client(URL);
        assertNotNull(userClient.merge(userDTO));
        assertNotNull(userClient.findByLogin(userDTO.getLogin()));
    }

    @Test
    public void findOne() {
        assertNotNull(userClient.findOne(USER_ID));
    }

    @Test
    public void findAll() {
        final List<UserDTO> users = userClient.findAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @After
    public void tearDown() throws Exception {
        assertNotNull(userClient.removeById(USER_ID));
    }
}
