package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static void delete(String deleteSQL) {
        try (var conn = getConnect()) {
            runner.update(conn, deleteSQL);
        }
    }

    @SneakyThrows
    public static void clearDB() {
        try (var connection = getConnect()) {
            runner.execute(connection, "DELETE FROM card_transactions");
            runner.execute(connection, "DELETE FROM cards");
            runner.execute(connection, "DELETE FROM auth_codes");
            runner.execute(connection, "DELETE FROM users");
        }
    }

    @SneakyThrows
    public static String getAuthCode() {
        String code;
        try (var conn = getConnect()) {
            var queryUserId = "select id from users where login='vasya';";
            var userId = (String) runner.query(conn, queryUserId, new ScalarHandler<>());
            var codeSQL = String.format("select code from auth_codes where user_id='%s' order by created desc", userId);
            code = runner.query(conn, codeSQL, new ScalarHandler<>());
        }
        return code;
    }
}





