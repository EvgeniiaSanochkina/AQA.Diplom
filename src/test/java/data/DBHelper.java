package data;

import lombok.*;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import static java.sql.DriverManager.getConnection;

public class DBHelper {

    public static String urlName = "jdbc:mysql://localhost:3306/app";
    public static String userName = "app";
    public static String passwordName = "pass";

    @SneakyThrows
    public static String getPayStatus() {
        var runner = new QueryRunner();
        var statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
     //var conn = getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        var conn = getConnection(urlName, userName, passwordName);
        return runner.query(conn, statusSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getPayCreditStatus() {
        var runner = new QueryRunner();
        var statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        //var conn = getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        var conn = getConnection(urlName, userName, passwordName);
        return runner.query(conn, statusSQL, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void cleanDataBase() {
        var conn = getConnection(urlName, userName, passwordName);
        //var conn = getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        var runner = new QueryRunner();
        runner.execute(conn, "DELETE FROM payment_entity");
        runner.execute(conn, "DELETE FROM credit_request_entity");
        runner.execute(conn, "DELETE FROM order_entity");
    }
}