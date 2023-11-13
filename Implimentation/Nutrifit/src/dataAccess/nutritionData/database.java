package dataAccess.nutritionData;

import java.sql.*;

public class database {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:Nutrifit/src/dataAccess/nutritionData/nutrition.db";
        createDatabase(url);
        initializeDatabase(url);
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
                statement.addBatch("CREATE TABLE IF NOT EXISTS refuseName(RefuseID INT,RefuseDescription TEXT,RefuseDescriptionF TEXT, PRIMARY KEY(RefuseID));");
                statement.addBatch("CREATE TABLE IF NOT EXISTS refuseAmount(FoodID INT,RefuseID INT,RefuseAmount INT, PRIMARY KEY(FoodID,RefuseID), FOREIGN KEY(FoodID) REFERENCES foodName(FoodID), FOREIGN KEY(RefuseID) REFERENCES refuseName(RefuseID));");
                statement.addBatch("CREATE TABLE IF NOT EXISTS yieldName(YieldID INT,YieldDescription TEXT,YieldDescriptionF TEXT, PRIMARY KEY(YieldID));");
                statement.addBatch("CREATE TABLE IF NOT EXISTS yieldAmount(FoodID INT,YieldID INT,YieldAmount INT,YieldDateofEntry TEXT, PRIMARY KEY(FoodID,YieldID), FOREIGN KEY(FoodID) REFERENCES foodName(FoodID), FOREIGN KEY(YieldID) REFERENCES yieldName(YieldID));");
                statement.executeBatch();
                System.out.println("Nutrition database initialized. Driver:" + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
