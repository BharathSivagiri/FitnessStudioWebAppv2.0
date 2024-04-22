package DatabaseConnect;

import java.sql.*;

public class DBConnection
{
    Connection connection;
    public void InitiateDB()
    {
        try {
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "FITNESSSTUDIOWEBAPP";
            String password = "dude2001";

            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public int InsertDB(String sql, Object[] params) throws SQLException
    {
        PreparedStatement statement;
            String data = sql;
            statement = connection.prepareStatement(data);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        int result = statement.executeUpdate();
            return result;
    }
    public int DeleteDB(String sql, Object[] params) throws SQLException
    {
        PreparedStatement statement;
        String data = sql;
        statement = connection.prepareStatement(data);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        int result = statement.executeUpdate();
        return result;
    }
    public ResultSet ReadDB(String sql, Object[] params) throws SQLException
    {
        PreparedStatement statement;
        String data = sql;
        statement = connection.prepareStatement(data);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        ResultSet rs = statement.executeQuery();
        return rs;
    }
    public int UpdateDB(String sql, Object[] params) throws SQLException
    {
        PreparedStatement statement;
        String data = sql;
        statement = connection.prepareStatement(data);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        int result = statement.executeUpdate();
        return result;
    }
    public ResultSet PassDB(String sql, Object[] params) {
        PreparedStatement statement;
        ResultSet resultSet;
        String data = sql;
        try {
            statement = connection.prepareStatement(data);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}
