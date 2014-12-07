package com.buaa.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.buaa.dao.UserDAOFactory;
import com.buaa.dao.UserDAOProxy;
import com.buaa.model.User;

public class UserDAOProxyTest {

	@Test
	public void testUserDAO() throws Exception {
		User user = new User("test@test.c", "test", "test");
		assertTrue(UserDAOFactory.getUserDAOInstance().addUser(user));
		assertTrue(UserDAOFactory.getUserDAOInstance().isExistUser(user));
		assertTrue(UserDAOFactory.getUserDAOInstance().deleteUser(user));
	}

}
