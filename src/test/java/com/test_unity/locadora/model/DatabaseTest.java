package com.test_unity.locadora.model;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {

    static Connection connection;

    @BeforeAll
    static void setUpDatabase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "sa");
        connection.createStatement().execute("CREATE Table users (id int, name VARCHAR)");
    }

    @BeforeEach
    void insertUser() throws Exception {
        connection.createStatement().execute("INSERT INTO users VALUES (1, 'Jose')");
    }

    @Test
//    @Disabled
    void testUserExists() throws Exception {
        var result = connection.createStatement().execute("SELECT * FROM users where id = 2");

        Assertions.assertTrue(result);
    }

    @AfterAll
    static void closeDatabase() throws SQLException {
        connection.close();
    }
}
