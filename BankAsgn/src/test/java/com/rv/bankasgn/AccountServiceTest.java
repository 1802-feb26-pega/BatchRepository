package com.rv.bankasgn;

import com.rv.bankasgn.access.AccountAccess;
import com.rv.bankasgn.pojos.Account;
import com.rv.bankasgn.service.AccountService;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    @Mock
    private AccountAccess aa;

    @InjectMocks
    private AccountService asv;

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
    public void testSuccessfulGetOneAccountWithObject() throws SQLException {
        Account a = new Account();
        a.setAccountId(666);
        a.setUserId(6017);

        when(aa.getAccountById(666)).thenReturn(a);
        assertEquals(asv.getOneAccountById(666), a);
    }

    @Test
    public void testUnsuccessfulGetOneAccountWithNull(){
        Account a = new Account();
        a.setAccountId(666);
        a.setUserId(6017);

        when(aa.getAccountById(667)).thenReturn(null);
        assertNull(asv.getOneAccountById(667));
    }

    @Test(expected = SQLException.class)
    public void testBotchedGetOneAccount(){
        Account a = new Account();
        a.setAccountId(666);
        a.setUserId(6017);

        when(aa.getAccountById(667)).thenThrow(SQLException.class);
        asv.getOneAccountById(667);
    }
}
