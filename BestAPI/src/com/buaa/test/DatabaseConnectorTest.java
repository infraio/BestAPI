package com.buaa.test;

import static org.junit.Assert.*;

import org.junit.Test;
import com.buaa.dao.*;

public class DatabaseConnectorTest {

	public DatabaseConnector dbc;
	
	public DatabaseConnectorTest() {
		try {
			dbc = new DatabaseConnector();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDatabaseConnector() {
		assertNotNull("should not be null", dbc);
	}

	@Test
	public void testGetConnection() {
		assertNotNull("should not be null", dbc.getConnection());
	}

	@Test
	public void testClose() {
		boolean result = false;
		try {
			dbc.close();
			result = dbc.getConnection().isClosed();
		} catch(Exception e) {
		} finally {
			assertTrue("should be true", result);
		}
	}

}
