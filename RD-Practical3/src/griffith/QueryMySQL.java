package griffith;

import java.sql.*;

public class QueryMySQL {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Graded_Practical";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
    	String QUERY = "SELECT p.name AS part_name, p.colour, GROUP_CONCAT(su.name) AS supplier_names " +
                "FROM parts p " +
                "JOIN supplies s ON p.partNum = s.partNum " +
                "JOIN supplier su ON s.supplierNum = su.supplierNum " +
                "GROUP BY p.partNum";

    	
    try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY)) {

            System.out.println("Connected to MySQL server...");

            
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

           
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s", metaData.getColumnName(i));
            }
            System.out.println();

           
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("%-20s", resultSet.getObject(i));
                }
                System.out.println();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
