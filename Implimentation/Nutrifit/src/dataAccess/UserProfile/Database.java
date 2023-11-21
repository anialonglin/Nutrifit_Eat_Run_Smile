package dataAccess.UserProfile;

import dataAccess.nutritionData.database;

import java.sql.*;

public class Database {

    private static Database instance = null;
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }


    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:Nutrifit/src/dataAccess/UserProfile/User.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    private static void initializeDatabase(String url) {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                Statement statement = conn.createStatement();

                // Create UserProfile table
                statement.addBatch("CREATE TABLE IF NOT EXISTS UserProfile ("
                        + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + " name TEXT NOT NULL,"
                        + " age INTEGER,"
                        + " sex TEXT,"
                        + " height_cm REAL,"
                        + " weight_kg REAL"
                        + ");");

                // Create DietLog table
                statement.addBatch("CREATE TABLE IF NOT EXISTS DietLog ("
                        + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + " username TEXT,"
                        + " Date TEXT,"
                        + " Meal_Type TEXT,"
                        + " Food_item TEXT,"
                        + " Quantity INTEGER,"
                        + " FOREIGN KEY (username) REFERENCES UserProfile(username)"
                        + ");");

                // Create ExerciseLog table
                statement.addBatch("CREATE TABLE IF NOT EXISTS ExerciseLog ("
                        + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + " username TEXT,"
                        + " Date TEXT,"
                        + " Exercise_Type TEXT,"
                        + " Duration INTEGER,"
                        + " Intensity TEXT,"
                        + " FOREIGN KEY (username) REFERENCES UserProfile(username)"
                        + ");");

                statement.executeBatch();
                System.out.println("User database initialized. Driver:" + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertUserProfile(String name, int age, String sex, double height_cm, double weight_kg) {
        // Insert a new user profile into the UserProfile table
        String sql = "INSERT INTO UserProfile(name, age, sex, height_cm, weight_kg) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, sex);
            pstmt.setDouble(4, height_cm);
            pstmt.setDouble(5, weight_kg);
            pstmt.executeUpdate();
            System.out.println("A new user profile has been inserted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertDietLog(String username, String date, String mealType, String foodItem, int quantity) {
        // Insert a new diet log into the DietLog table
        String sql = "INSERT INTO DietLog(username, Date, Meal_Type, Food_item, Quantity) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, date);
            pstmt.setString(3, mealType);
            pstmt.setString(4, foodItem);
            pstmt.setInt(5, quantity);
            pstmt.executeUpdate();
            System.out.println("A new diet log has been inserted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertExerciseLog(String username, String date, String exerciseType, int duration, String intensity) {
        // Insert a new exercise log into the ExerciseLog table
        String sql = "INSERT INTO ExerciseLog(username, Date, Exercise_Type, Duration, Intensity) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, date);
            pstmt.setString(3, exerciseType);
            pstmt.setInt(4, duration);
            pstmt.setString(5, intensity);
            pstmt.executeUpdate();
            System.out.println("A new exercise log has been inserted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAllUserProfiles() {
        // Select all user profiles from the UserProfile table
        String sql = "SELECT id, name, age, sex, height_cm, weight_kg FROM UserProfile";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getInt("age") + "\t" +
                        rs.getString("sex") + "\t" +
                        rs.getDouble("height_cm") + "\t" +
                        rs.getDouble("weight_kg"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAllDietLogs() {
        // Select all diet logs from the DietLog table
        String sql = "SELECT ID, username, Date, Meal_Type, Food_item, Quantity FROM DietLog";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") + "\t" +
                        rs.getString("username") + "\t" +
                        rs.getString("Date") + "\t" +
                        rs.getString("Meal_Type") + "\t" +
                        rs.getString("Food_item") + "\t" +
                        rs.getInt("Quantity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAllExerciseLogs() {
        // Select all exercise logs from the ExerciseLog table
        String sql = "SELECT ID, username, Date, Exercise_Type, Duration, Intensity FROM ExerciseLog";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") + "\t" +
                        rs.getString("username") + "\t" +
                        rs.getString("Date") + "\t" +
                        rs.getString("Exercise_Type") + "\t" +
                        rs.getInt("Duration") + "\t" +
                        rs.getString("Intensity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void wipeDB(String url) {
        // Wipe the entire database
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                Statement statement = conn.createStatement();
                statement.execute("PRAGMA writable_schema = 1;");
                statement.execute("delete from sqlite_master where type in ('table', 'index', 'trigger');");
                statement.execute("PRAGMA writable_schema = 0;");
                statement.execute("VACUUM;");
                statement.execute("PRAGMA INTEGRITY_CHECK;");
                System.out.println("User database wiped. Driver:" + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createDatabase(String url) {
        // Create the user database
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("User database created. Driver:" + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}