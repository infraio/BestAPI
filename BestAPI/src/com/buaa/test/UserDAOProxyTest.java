package com.buaa.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.buaa.dao.UserDAOProxy;
import com.buaa.model.User;

public class UserDAOProxyTest {

	@Test
	public void testUserDAO() {
		User user = new User("test@test.com", "test", "test");
		UserDAOProxy userDAO = new UserDAOProxy();
		assertTrue(userDAO.addUser(user));
		assertTrue(userDAO.findUser(user));
		assertTrue(userDAO.removeUser(user));
	}

}
