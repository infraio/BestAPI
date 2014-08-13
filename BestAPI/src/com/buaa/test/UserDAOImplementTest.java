package com.buaa.test;

import static org.junit.Assert.*;
import com.buaa.dao.*;
import com.buaa.model.*;

import org.junit.Test;

public class UserDAOImplementTest {

	private UserDAOImplement userDAO;
	
	public UserDAOImplementTest() {
		userDAO = new UserDAOImplement(new DatabaseConnector().getConnection());
	}
	
	@Test
	public void testUserDAO() {
		User user = new User("test@test.com", "test", "test");
		assertTrue("should be true", userDAO.addUser(user));
		assertTrue("should be true", userDAO.findUser(user));
		assertTrue("should be true", userDAO.removeUser(user));
	}
}
