import java.sql.*;

public class Connect {

    private final String url = "jdbc:postgresql:http://192.168.88.187:5432/postgres";
    private final String user = "postgres";
    private final String password = "16091997";


    public Connection connection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void allZakaz() {
        String sql = "select * from zakaz";
        try {
            Statement statement = connection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getInt("id_food")
                + " " + resultSet.getInt("id_cafe"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void popularProduct() {
        String sql = "select f.name as name," +
                "f.price as price," +
                "count(zakaz.id_food)" +
                "from zakaz" +
                "inner join food f on f.id = zakaz.id_food" +
                "group by f.price, f.name";
        try {
            Statement statement = connection().createStatement();
ResultSet resultSet = statement.executeQuery(sql);
while (resultSet.next()) {
    System.out.println(resultSet.getString("name") + " " + resultSet.getInt("price")
    +  " " + resultSet.getInt("count"));
}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
