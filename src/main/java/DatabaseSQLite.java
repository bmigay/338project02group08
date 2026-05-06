package com.daclink;
import java.sql.*;

public class DatabaseSQLite {

    Connection connection;

    public DatabaseSQLite() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:app.db");
            System.out.println("Database connected.");
            createTables();
        } catch (Exception e) {
            System.out.println("Connection failed: " + e);
        }
    }

    public void createTables() {
        try {
            Statement stmt = connection.createStatement();
            String usersTable = "CREATE TABLE IF NOT EXISTS users (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, " + "score INTEGER)";
            String gamesTable = "CREATE TABLE IF NOT EXISTS games (" + "game_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "user_name TEXT, " + "game_date TEXT, " + "total_score INTEGER, " + "letter_used TEXT)";
            String answersTable = "CREATE TABLE IF NOT EXISTS answers (" + "answer_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "game_id INTEGER, " + "round_number INTEGER, " + "category TEXT, " + "player_answer TEXT, " + "points_earned INTEGER)";

            stmt.execute(usersTable);
            stmt.execute(gamesTable);
            stmt.execute(answersTable);

            System.out.println("Tables created.");
        } catch (Exception e) {
            System.out.println("Error creating tables: " + e);
        }
    }

    public void createGame(String username, String letter, int score) {
        try {
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO games (user_name, game_date, total_score, letter_used) " + "VALUES ('" + username + "', datetime('now'), 0, '" + score + ", " + letter + "')";
            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println("createGame error: " + e);
        }
    }

    public void saveAnswer(int gameId, int round, String category, String answer, int points) {
        try {
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO answers (game_id, round_number, category, player_answer, points_earned) " + "VALUES (" + gameId + ", " + round + ", '" + category + "', '" + answer + "', " + points + ")";
            stmt.execute(sql);

        } catch (Exception e) {
            System.out.println("saveAnswer error: " + e);
        }
    }
    public void updateGameScore(int gameId, int total) {
        try {
            Statement stmt = connection.createStatement();
            String sql = "UPDATE games SET total_score = " + total + " WHERE game_id = " + gameId;
            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println("updateGameScore error: " + e);
        }
    }
    public String getPastScores(String username) {
        String result = "";
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM games WHERE user_name = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);

            int count = 1;
            while (rs.next()) {
                result = result + "Game " + count + ": " + rs.getInt("total_score") + " points (" + rs.getString("letter_used") + ") on " + rs.getString("game_date") + "\n";
                count++;
            }
        } catch (Exception e) {
            System.out.println("getPastScores error: " + e);
        }
        if (result.equals("")) {
            return "No games played yet.";
        }
        return result;
    }

    public String getLeaderboard() {
        String result = "";
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT user_name, total_score FROM games ORDER BY total_score DESC LIMIT 10";
            ResultSet rs = stmt.executeQuery(sql);
            int rank = 1;
            while (rs.next()) {
                result = result + rank + ". " + rs.getString("user_name") + " - " + rs.getInt("total_score") + " points\n";
                rank++;
            }
        } catch (Exception e) {
            System.out.println("getLeaderboard error: " + e);
        }
        if (result.equals("")) {
            return "No scores yet.";
        }
        return result;
    }
}