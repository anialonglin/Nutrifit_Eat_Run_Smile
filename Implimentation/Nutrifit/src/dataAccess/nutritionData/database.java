package dataAccess.nutritionData;

import com.opencsv.CSVReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class database {

    private static database instance = null;

    public static database getInstance() {
        if (instance == null) {
            instance = new database();
        }
        return instance;
    }

    private static void createDatabase(String url) {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Nutrition database created. Driver:" + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void initializeDatabase(String url) {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                Statement statement = conn.createStatement();
                statement.addBatch("CREATE TABLE IF NOT EXISTS foodName(FoodID INT,FoodCode INT,FoodGroupID INT,FoodSourceID INT,FoodDescription TEXT,FoodDescriptionF TEXT,FoodDateOfEntry TEXT,FoodDateOfPublication TEXT,CountryCode INT,ScientificName TEXT, PRIMARY KEY (FoodID), FOREIGN KEY(FoodGroupID) REFERENCES foodGroup(FoodGroupID), FOREIGN KEY(FoodSourceID) REFERENCES foodSource(FoodSourceID));");
                statement.addBatch("CREATE TABLE IF NOT EXISTS foodGroup(FoodGroupID INT,FoodGroupCode INT,FoodGroupName TEXT,FoodGroupNameF TEXT, PRIMARY KEY(FoodGroupID));");
                statement.addBatch("CREATE TABLE IF NOT EXISTS foodSource(FoodSourceID INT,FoodSourceCode INT,FoodSourceDescription TEXT,FoodSourceDescriptionF TEXT, PRIMARY KEY(FoodSourceID));");
                statement.addBatch("CREATE TABLE IF NOT EXISTS measureName(MeasureID INT,MeasureDescription TEXT,MeasureDescriptionF TEXT, PRIMARY KEY(MeasureID));");
                statement.addBatch("CREATE TABLE IF NOT EXISTS conversionFactors(FoodID INT,MeasureID INT,ConversionFactorValue FLOAT,ConvFactorDateOfEntry TEXT, PRIMARY KEY (FoodID,MeasureID), FOREIGN KEY(FoodID) REFERENCES foodName(FoodID), FOREIGN KEY(MeasureID) REFERENCES measureName(MeasureID));");
                statement.addBatch("CREATE TABLE IF NOT EXISTS nutrientName(NutrientID INT,NutrientCode INT,NutrientSymbol TEXT,NutrientUnit TEXT,NutrientName TEXT,NutrientNameF TEXT,Tagname TEXT,NutrientDecimals INT, PRIMARY KEY(NutrientID));");
                statement.addBatch("CREATE TABLE IF NOT EXISTS nutrientAmount(FoodID INT,NutrientID INT,NutrientValue FLOAT,StandardError FLOAT,NumberofObservations INT,NutrientSourceID INT,NutrientDateOfEntry TEXT, PRIMARY KEY(FoodID,NutrientID), FOREIGN KEY(FoodID) REFERENCES foodName(FoodID), FOREIGN KEY(NutrientID) REFERENCES nutrientName(NutrientID), FOREIGN KEY(NutrientSourceID) REFERENCES nutrientSource(NutrientSourceID));");
                statement.addBatch("CREATE TABLE IF NOT EXISTS nutrientSource(NutrientSourceID INT,NutrientSourceCode INT,NutrientSourceDescription TEXT,NutrientSourceDescriptionF TEXT, PRIMARY KEY(NutrientSourceID));");
                statement.addBatch("CREATE TABLE IF NOT EXISTS yieldName(YieldID INT,YieldDescription TEXT,YieldDescriptionF TEXT, PRIMARY KEY(YieldID));");
                statement.addBatch("CREATE TABLE IF NOT EXISTS yieldAmount(FoodID INT,YieldID INT,YieldAmount INT,YieldDateofEntry TEXT, PRIMARY KEY(FoodID,YieldID), FOREIGN KEY(FoodID) REFERENCES foodName(FoodID), FOREIGN KEY(YieldID) REFERENCES yieldName(YieldID));");
                statement.executeBatch();
                System.out.println("Nutrition database initialized. Driver:" + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:sqlite:Nutrifit/src/dataAccess/nutritionData/nutrition.db";
        wipeDB(url);
        createDatabase(url);
        initializeDatabase(url);
        fillDatabase(url);
    }

    private static void fillDatabase(String url) {
        String foodNameCSV = "Nutrifit/src/dataAccess/nutritionData/CFG/FOOD NAME.csv";
        String foodGroupCSV = "Nutrifit/src/dataAccess/nutritionData/CFG/FOOD GROUP.csv";
        String foodSourceCSV = "Nutrifit/src/dataAccess/nutritionData/CFG/FOOD SOURCE.csv";
        String measureNameCSV = "Nutrifit/src/dataAccess/nutritionData/CFG/MEASURE NAME.csv";
        String conversionFactorsCSV = "Nutrifit/src/dataAccess/nutritionData/CFG/CONVERSION FACTOR.csv";
        String nutrientNameCSV = "Nutrifit/src/dataAccess/nutritionData/CFG/NUTRIENT NAME.csv";
        String nutrientAmountCSV = "Nutrifit/src/dataAccess/nutritionData/CFG/NUTRIENT AMOUNT.csv";
        String nutrientSourceCSV = "Nutrifit/src/dataAccess/nutritionData/CFG/NUTRIENT SOURCE.csv";
        String yieldNameCSV = "Nutrifit/src/dataAccess/nutritionData/CFG/YIELD NAME.csv";
        String yieldAmountCSV = "Nutrifit/src/dataAccess/nutritionData/CFG/YIELD AMOUNT.csv";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                try {
                    CSVReader reader = new CSVReader(new java.io.FileReader(foodNameCSV));
                    String[] line = reader.readNext();
                    line = reader.readNext(); //get header out of the way
                    reader.readNext();
                    StringBuilder sql = new StringBuilder();
                    Statement statement = conn.createStatement();
                    String scratch; //for cleaning inputs
                    while ((line = reader.readNext()) != null && !line[0].isEmpty()) {
                        sql.setLength(0);
                        sql.append("INSERT INTO foodName VALUES(").append(line[0]).append(",");
                        if (!line[1].isEmpty()) {
                            sql.append(line[1]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[2].isEmpty()) {
                            sql.append(line[2]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[3].isEmpty()) {
                            sql.append(line[3]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[4].isEmpty()) {
                            scratch = line[4].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[5].isEmpty()) {
                            scratch = line[5].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[6].isEmpty()) {
                            scratch = line[6].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[7].isEmpty()) {
                            scratch = line[7].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[8].isEmpty()) {
                            sql.append(line[8]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[9].isEmpty()) {
                            scratch = line[9].replace("'", "''");
                            sql.append("'").append(scratch).append("');");
                        } else {
                            sql.append("NULL);");
                        }
                        statement.addBatch(sql.toString());
                    }
                    statement.executeBatch();
                    System.out.println(1);
                    reader = new CSVReader(new java.io.FileReader(foodGroupCSV));
                    line = reader.readNext(); //get header out of the way
                    while ((line = reader.readNext()) != null && !line[0].isEmpty()) {
                        sql.setLength(0);
                        sql.append("INSERT INTO foodGroup VALUES(").append(line[0]).append(",");
                        if (!line[1].isEmpty()) {
                            sql.append(line[1]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[2].isEmpty()) {
                            scratch = line[2].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[3].isEmpty()) {
                            scratch = line[3].replace("'", "''");
                            sql.append("'").append(scratch).append("');");
                        } else {
                            sql.append("NULL);");
                        }
                        statement.addBatch(sql.toString());
                    }
                    statement.executeBatch();
                    System.out.println(2);
                    reader = new CSVReader(new java.io.FileReader(foodSourceCSV));
                    line = reader.readNext(); //get header out of the way
                    while ((line = reader.readNext()) != null && !line[0].isEmpty()) {
                        sql.setLength(0);
                        sql.append("INSERT INTO foodSource VALUES(").append(line[0]).append(",");
                        if (!line[1].isEmpty()) {
                            sql.append(line[1]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[2].isEmpty()) {
                            scratch = line[2].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[3].isEmpty()) {
                            scratch = line[3].replace("'", "''");
                            sql.append("'").append(scratch).append("');");
                        } else {
                            sql.append("NULL);");
                        }
                        statement.addBatch(sql.toString());
                    }
                    statement.executeBatch();
                    System.out.println(3);
                    reader = new CSVReader(new java.io.FileReader(measureNameCSV));
                    line = reader.readNext(); //get header out of the way
                    while ((line = reader.readNext()) != null && !line[0].isEmpty()) {
                        sql.setLength(0);
                        sql.append("INSERT INTO measureName VALUES(").append(line[0]).append(",");
                        if (!line[1].isEmpty()) {
                            scratch = line[1].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[2].isEmpty()) {
                            scratch = line[2].replace("'", "''");
                            sql.append("'").append(scratch).append("');");
                        } else {
                            sql.append("NULL);");
                        }
                        statement.addBatch(sql.toString());
                    }
                    statement.executeBatch();
                    System.out.println(4);
                    reader = new CSVReader(new java.io.FileReader(conversionFactorsCSV));
                    line = reader.readNext(); //get header out of the way
                    long time = System.currentTimeMillis();
                    while ((line = reader.readNext()) != null && !line[0].isEmpty()) {
                        sql.setLength(0);
                        sql.append("INSERT INTO conversionFactors VALUES(").append(line[0]).append(",");
                        if (!line[1].isEmpty()) {
                            sql.append(line[1]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[2].isEmpty()) {
                            sql.append(line[2]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[3].isEmpty()) {
                            scratch = line[3].replace("'", "''");
                            sql.append("'").append(scratch).append("');");
                        } else {
                            sql.append("NULL);");
                        }
                        statement.execute(sql.toString());
//                        if(time + 1800000 < System.currentTimeMillis()){
//                            System.out.println("breaking for time, database is functional, but not full for conversion factors, left alone for longer the database would eventually be complete");
//                            break;
//                        }
                    }
                    System.out.println(5);
                    reader = new CSVReader(new java.io.FileReader(nutrientNameCSV));
                    line = reader.readNext(); //get header out of the way
                    while ((line = reader.readNext()) != null && !line[0].isEmpty()) {
                        sql.setLength(0);
                        sql.append("INSERT INTO nutrientName VALUES(").append(line[0]).append(",");
                        if (!line[1].isEmpty()) {
                            sql.append(line[1]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[2].isEmpty()) {
                            scratch = line[2].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[3].isEmpty()) {
                            scratch = line[3].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[4].isEmpty()) {
                            scratch = line[4].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[5].isEmpty()) {
                            scratch = line[5].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[6].isEmpty()) {
                            scratch = line[6].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[7].isEmpty()) {
                            sql.append(line[7]).append(");");
                        } else {
                            sql.append("NULL);");
                        }
                        statement.addBatch(sql.toString());
                    }
                    statement.executeBatch();
                    System.out.println(6);
                    reader = new CSVReader(new java.io.FileReader(nutrientAmountCSV));
                    line = reader.readNext(); //get header out of the way
                    time = System.currentTimeMillis();
                    while ((line = reader.readNext()) != null && !line[0].isEmpty()) {
                        sql.setLength(0);
                        sql.append("INSERT INTO nutrientAmount VALUES(").append(line[0]).append(",");
                        if (!line[1].isEmpty()) {
                            sql.append(line[1]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[2].isEmpty()) {
                            sql.append(line[2]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[3].isEmpty()) {
                            sql.append(line[3]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[4].isEmpty()) {
                            sql.append(line[4]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[5].isEmpty()) {
                            sql.append(line[5]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[6].isEmpty()) {
                            scratch = line[6].replace("'", "''");
                            sql.append(scratch).append(");");
                        } else {
                            sql.append("NULL);");
                        }
                        statement.execute(sql.toString());
//                        if(time + 1800000 < System.currentTimeMillis()){
//                            System.out.println("breaking for time, database is functional, but not full for nutrient amounts, left alone for longer the database would eventually be complete");
//                           break;
//                        }
                    }
                    System.out.println(7);
                    reader = new CSVReader(new java.io.FileReader(nutrientSourceCSV));
                    line = reader.readNext(); //get header out of the way
                    while ((line = reader.readNext()) != null && !line[0].isEmpty()) {
                        sql.setLength(0);
                        sql.append("INSERT INTO nutrientSource VALUES(").append(line[0]).append(",");
                        if (!line[1].isEmpty()) {
                            sql.append(line[1]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[2].isEmpty()) {
                            scratch = line[2].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[3].isEmpty()) {
                            scratch = line[3].replace("'", "''");
                            sql.append("'").append(scratch).append("');");
                        } else {
                            sql.append("NULL);");
                        }
                        statement.addBatch(sql.toString());
                    }
                    statement.executeBatch();
                    System.out.println(8);
                    reader = new CSVReader(new java.io.FileReader(yieldNameCSV));
                    line = reader.readNext(); //get header out of the way
                    while ((line = reader.readNext()) != null && !line[0].isEmpty()) {
                        sql.setLength(0);
                        sql.append("INSERT INTO yieldName VALUES(").append(line[0]).append(",");
                        if (!line[1].isEmpty()) {
                            scratch = line[1].replace("'", "''");
                            sql.append("'").append(scratch).append("',");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[2].isEmpty()) {
                            scratch = line[2].replace("'", "''");
                            sql.append("'").append(scratch).append("');");
                        } else {
                            sql.append("NULL);");
                        }
                        statement.addBatch(sql.toString());
                    }
                    statement.executeBatch();
                    System.out.println(11);
                    reader = new CSVReader(new java.io.FileReader(yieldAmountCSV));
                    line = reader.readNext(); //get header out of the way
                    while ((line = reader.readNext()) != null && !line[0].isEmpty()) {
                        sql.setLength(0);
                        sql.append("INSERT INTO yieldAmount VALUES(").append(line[0]).append(",");
                        if (!line[1].isEmpty()) {
                            sql.append(line[1]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[2].isEmpty()) {
                            sql.append(line[2]).append(",");
                        } else {
                            sql.append("NULL,");
                        }
                        if (!line[3].isEmpty()) {
                            scratch = line[3].replace("'", "''");
                            sql.append("'").append(scratch).append("');");
                        } else {
                            sql.append("NULL);");
                        }
                        statement.addBatch(sql.toString());
                    }
                    statement.executeBatch();
                    System.out.println(12);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Nutrition database filled. Driver:" + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void wipeDB(String url) {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                Statement statement = conn.createStatement();
                statement.execute("PRAGMA writable_schema = 1;");
                statement.execute("delete from sqlite_master where type in ('table', 'index', 'trigger');");
                statement.execute("PRAGMA writable_schema = 0;");
                statement.execute("VACUUM;");
                statement.execute("PRAGMA INTEGRITY_CHECK;");
                System.out.println("Nutrition database wiped. Driver:" + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadDatabase() {
        String url = "jdbc:sqlite:Nutrifit/src/dataAccess/nutritionData/nutrition.db";
        wipeDB(url);
        createDatabase(url);
        initializeDatabase(url);
        fillDatabase(url);
    }

    public ArrayList<String> findFoodItem(String[] queryParams) {
        ArrayList<String> foodList = new ArrayList<>();
        String url = "jdbc:sqlite:Nutrifit/src/dataAccess/nutritionData/nutrition.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                Statement statement = conn.createStatement();
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT FoodDescription FROM foodName WHERE FoodDescription LIKE '%");
                for (String query : queryParams) {
                    sql.append(query).append("%");
                }
                sql.append("%' LIMIT 20;");
                ResultSet rs = statement.executeQuery(sql.toString());
                while (rs.next()) {
                    foodList.add(rs.getString("FoodDescription"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return foodList;
    }
    public int getProteinCount(HashMap<String, Integer> foodIDs) {
        String sql;
        int result = 0;
        String URL = "jdbc:sqlite:Nutrifit/src/dataAccess/nutritionData/nutrition.db";
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                for (Integer foodID : foodIDs.values()) {
                    sql = "SELECT COUNT(*) FROM foodName WHERE FoodID is " + foodID + " and (FoodGroupID is 5 or FoodGroupID is 7 or FoodGroupID is 10 or FoodGroupID is 13 or FoodGroupID is 15 or FoodGroupID is 17);";
                    Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery(sql);
                    if (rs.getInt(1) > 0) {
                        result++;
                    }
                }
                return result;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Protein count not found");
        return -1;
    }

    public int getCarbCount(HashMap<String, Integer> foodIDs) {
        String sql;
        int result = 0;
        String URL = "jdbc:sqlite:Nutrifit/src/dataAccess/nutritionData/nutrition.db";
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                for (Integer foodID : foodIDs.values()) {
                    sql = "SELECT COUNT(*) FROM foodName WHERE FoodID is " + foodID + " and (FoodGroupID is 8 or FoodGroupID is 19 or FoodGroupID is 18 or FoodGroupID is 20);";
                    Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery(sql);
                    if (rs.getInt(1) > 0) {
                        result++;
                    }
                }
                return result;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Carb count not found");
        return -1;
    }

    public int getFruitAndVegCount(HashMap<String, Integer> foodIDs) {
        String sql;
        int result = 0;
        String URL = "jdbc:sqlite:Nutrifit/src/dataAccess/nutritionData/nutrition.db";
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                for (Integer foodID : foodIDs.values()) {
                    sql = "SELECT COUNT(*) FROM foodName WHERE FoodID is " + foodID + " and (FoodGroupID is 9 or FoodID is 11 or FoodID is 16);";
                    Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery(sql);
                    if (rs.getInt(1) > 0) {
                        result++;
                    }
                }
                return result;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Fruit and Veg count not found");
        return -1;
    }

    public int getID(String foodItem) {
        String url = "jdbc:sqlite:Nutrifit/src/dataAccess/nutritionData/nutrition.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                Statement statement = conn.createStatement();
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT FoodID FROM foodName WHERE FoodDescription is '").append(foodItem).append("';");
                ResultSet rs = statement.executeQuery(sql.toString());
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("ID not found");
        return -1;
    }

    public int getCalories(int foodID){
        String url = "jdbc:sqlite:Nutrifit/src/dataAccess/nutritionData/nutrition.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                Statement statement = conn.createStatement();
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT NutrientValue FROM nutrientAmount WHERE FoodID is ").append(foodID).append(" and NutrientID is 208;");
                ResultSet rs = statement.executeQuery(sql.toString());
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Calories not found");
        return -1;
    }
}

