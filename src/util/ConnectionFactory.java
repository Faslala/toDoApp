package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/toDoApp";
    public static final String USER = "root";
    public static final String PASS = "Fa300375??";

    public static Connection getConnection() {

        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão com o banco de dados!", e);
        }
    }

    public static void closeConnection(Connection connection) {

        try {
//            if (connection != null);
            assert connection != null;
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement statement) {

        try {

            assert connection != null;
            connection.close();

            assert statement != null;
            statement.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {

        try {

            assert connection != null;
            connection.close();

            assert statement != null;
            statement.close();

            assert resultSet != null;
            resultSet.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e);
        }
    }

}
