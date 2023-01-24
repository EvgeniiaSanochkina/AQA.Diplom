package data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
    private static QueryRunner runner;
    private static Connection conn;

    @BeforeEach
    @SneakyThrows
    void setup() {
        runner = new QueryRunner();
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }
    @SneakyThrows
        public static PaymentEntity getDebitCardStatus() {
            var statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
             return runner.query(conn, statusSQL, new BeanHandler<>(PaymentEntity.class));
    }

    @SneakyThrows
    public static CreditRequestEntity getCreditCardStatus() {
        var statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, statusSQL, new BeanHandler<>(CreditRequestEntity.class));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentEntity {
        private String status;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreditRequestEntity {
        private String status;
    }
}