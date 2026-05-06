package com.daclink;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDatabase {
    public static int getTotalGames() {
        DatabaseSQLite Ad = new DatabaseSQLite();
        String sql = "SELECT COUNT(*) as count FROM games";
        try (Statement stmt = Ad.connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Error getting game count: " + e);
        }
        return 0;
    }

    public static double getAverageScore() {
        DatabaseSQLite Ad = new DatabaseSQLite();
        String sql = "SELECT AVG(total_score) as avg FROM games";
        try (Statement stmt = Ad.connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getDouble("avg");
            }
        } catch (SQLException e) {
            System.out.println("Error getting average score: " + e);
        }
        return 0.0;
    }

    public static int getTotalUsers() {
        UserDataBase Ad = new UserDataBase();
        String sql = "SELECT COUNT(*) as count FROM users";
        try (Statement stmt = Ad.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Error getting user count: " + e);
        }
        return 0;
    }
    public static void refreshUserList(ListView<String> usersList) {
        UserDataBase Ad = new UserDataBase();
        String users = Ad.getUsers();
        ObservableList<String> items = FXCollections.observableArrayList();
        String[] lines = users.split("\n");
        for (String line : lines) {
            if (!line.equals("Error") && !line.isEmpty()) {
                items.add(line);
            }
        }
        usersList.setItems(items);
    }

    public static void resetGameData() {
        DatabaseSQLite Ad = new DatabaseSQLite();
        try {
            Statement stmt = Ad.connection.createStatement();
            stmt.execute("DELETE FROM answers");
            stmt.execute("DELETE FROM games");
            stmt.execute("UPDATE users SET score = 0");
            System.out.println("Game data reset successfully");
        } catch (SQLException e) {
            System.out.println("Error resetting game data: " + e);
        }
    }
}
