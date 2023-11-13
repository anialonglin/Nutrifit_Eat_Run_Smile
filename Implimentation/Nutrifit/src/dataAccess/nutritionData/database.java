package dataAccess.nutritionData;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
    public static void main(String[] args) {
        createDatabase();
    }

    private static void createDatabase() {
        String url = "jdbc:sqlite:nutrition.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Nutrition database created." + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
