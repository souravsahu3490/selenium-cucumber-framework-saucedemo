package com.sourav.automation.utils;

import java.sql.*;
import java.util.*;

public class DBUtils {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=AutomationDB;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "Pass@123";

    public static List<Map<String, String>> executeQuery(String query) {

        List<Map<String, String>> data = new ArrayList<>();

        try (
                Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {

                Map<String, String> row = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getString(i));
                }

                data.add(row);
            }

        } catch (Exception e) {
            throw new RuntimeException("Database execution failed", e);
        }

        return data;
    }
}
