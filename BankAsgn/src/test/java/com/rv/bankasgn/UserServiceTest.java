package com.rv.bankasgn;

import com.rv.bankasgn.access.AccountAccess;
import com.rv.bankasgn.access.UserAccess;
import com.rv.bankasgn.pojos.Account;
import com.rv.bankasgn.pojos.User;
import com.rv.bankasgn.service.UserService;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserAccess ua;

    @InjectMocks
    private UserService usv;

    @BeforeClass
    public static void before() throws Exception{

    }

    @AfterClass
    public static void after() throws Exception{

    }

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterEach() {

    }

    @Test
    public void testGetOneUser() {
        User u = new User();
        u.setUserId(666);
        u.setFirstname("Plamp");
        u.setLastname("Goit");
        u.setPassword("goit6017");
        u.setEmail("goit@goit.com");

        when(ua.getUserByEmail(u.getEmail())).thenReturn(u);

        assertEquals(u, usv.getOneUser("goit@goit.com", "goit6017"));
        assertNull(usv.getOneUser("goit@goit.com", "goit"));
    }

    @Test(expected = SQLException.class)
    public void testBotchedSaveUser() throws SQLException{
        User u = new User();
        u.setUserId(666);
        u.setFirstname("Plamp");
        u.setLastname("Goit");
        u.setPassword("goit6017");
        u.setEmail("goit@goit.com");

        doThrow(SQLException.class).when(ua).saveUser(u);

        usv.saveUser(u);

        verify(ua, times(1)).saveUser(u);
    }

    @Test
    public void testSuccessfulSaveUser() throws SQLException{
        User u = new User();
        u.setUserId(666);
        u.setFirstname("Plamp");
        u.setLastname("Goit");
        u.setPassword("goit6017");
        u.setEmail("goit@goit.com");

        assertEquals(u, usv.saveUser(u));
    }

    @Test
    public void testSuccessfulUpdateUser() throws SQLException{
        User u = new User();
        u.setUserId(666);
        u.setFirstname("Plamp");
        u.setLastname("Goit");
        u.setPassword("goit6017");
        u.setEmail("goit@goit.com");

        usv.updateUser(u);
    }

    @Test(expected = SQLException.class)
    public void testBotchedUpdateUser() throws SQLException{
        User u = new User();
        u.setUserId(666);
        u.setFirstname("Plamp");
        u.setLastname("Goit");
        u.setPassword("goit6017");
        u.setEmail("goit@goit.com");

        doThrow(SQLException.class).when(ua).updateUser(u);
        usv.updateUser(u);
    }

    @Test
    public void testSuccessfulRegisterUser() throws SQLException{
        User u = new User();
        u.setUserId(666);
        u.setFirstname("Plamp");
        u.setLastname("Goit");
        u.setPassword("goit6017");
        u.setEmail("goit@goit.com");

        when(ua.getUserByEmail("goit@goit.com")).thenReturn(null);
        assertEquals(u,usv.registerUser(u));
    }

    @Test
    public void testBotchedRegisterUser() throws SQLException{
        User u = new User();
        u.setUserId(666);
        u.setFirstname("Plamp");
        u.setLastname("Goit");
        u.setPassword("goit6017");
        u.setEmail("goit@goit.com");

        when(ua.getUserByEmail("goit@goit.com")).thenReturn(u);
        assertNull(usv.registerUser(u));
    }

    @Test(expected = SQLException.class)
    public void testRegisterUserWithDBFailure() throws SQLException{
        User u = new User();
        u.setUserId(666);
        u.setFirstname("Plamp");
        u.setLastname("Goit");
        u.setPassword("goit6017");
        u.setEmail("goit@goit.com");

        when(ua.getUserByEmail("goit@goit.com")).thenThrow(SQLException.class);
        usv.registerUser(u);
    }
}
