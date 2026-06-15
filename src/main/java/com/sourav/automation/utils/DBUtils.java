package com.sourav.automation.utils;

import java.sql.*;
import java.util.*;

public class DBUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/automationdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

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
