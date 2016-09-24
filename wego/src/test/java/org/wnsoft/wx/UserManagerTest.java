/**
 * Created by wunan on 16-9-22.
 * UserManager Test Cases
 */
package org.wnsoft.wx;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wnsoft.entity.User;

import static org.junit.Assert.*;

public class UserManagerTest {
    private TokenManager tokenManager = new TokenManager();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUserTest() throws Exception {
        UserManager userManager = new UserManager();
        User user = userManager.doGetUser("wunan", tokenManager.getToken());
        System.out.println(user.toString());
    }
}