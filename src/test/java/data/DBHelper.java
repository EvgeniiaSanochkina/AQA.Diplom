package data;

import lombok.*;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class DBHelper {
 //   private static String url = System.getProperty("url");
 // private static String user = System.getProperty("user");
    //  private static String password = System.getProperty("password");

    // private DBHelper() {
    //   }


    @SneakyThrows
    public static String getPayStatus() {
        var runner = new QueryRunner();
        var statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
       var conn = getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        // var conn = getConnection(url, user, password);
        return runner.query(conn, statusSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getPayCreditStatus() {
        var runner = new QueryRunner();
        var statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
       var conn = getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        //var conn = getConnection(url, user, password);
        return runner.query(conn, statusSQL, new ScalarHandler<>());
    }

}