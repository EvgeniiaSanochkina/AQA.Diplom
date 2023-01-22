package data;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static QueryRunner runner = new QueryRunner();

    private DBHelper() {
    }


    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }


}