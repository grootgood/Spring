package org.galapagos.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	static { // static 초기화
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		String url = "jdbc:mysql://localhost:3309/glory_db";
		try (Connection con = DriverManager.getConnection(url, "glory", "1234")) {
			log.info(con);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
}
