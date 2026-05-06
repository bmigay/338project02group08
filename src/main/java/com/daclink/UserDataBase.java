package com.daclink;
import java.sql.*;

public class UserDataBase {
    private Connection connection;

    public Connection getConnection(){
        return connection;
    }

    public UserDataBase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:app.db");
            System.out.println("Database connected. ");
            createTables();
        } catch (Exception e) {
            System.out.println("Connection failed " + e);
        }
    }

    public void createTables() {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                password TEXT NOT NULL,
                score INTEGER NOT NULL DEFAULT 0,
                isAdmin INTEGER NOT NULL DEFAULT 0
                )
                """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            try {
                stmt.execute("ALTER TABLE users ADD COLUMN isAdmin INTEGER DEFAULT 0");
                System.out.println("Added isAdmin column to existing users table");
            } catch (SQLException e) {
                System.out.println("already exist :)");
            }

            createDefaultAdmin();
        } catch (SQLException e) {
            System.err.println("createTables failed: " + e);
        }
    }
    private void createDefaultAdmin() {
        String checkSql = "SELECT * FROM users WHERE name = 'admin'";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(checkSql);
            if (!rs.next()) {
                String insertSql = "INSERT INTO users (name, password, score, isAdmin) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(insertSql)) {
                    pstmt.setString(1, "admin");
                    pstmt.setString(2, "admin123");
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 1);
                    pstmt.executeUpdate();
                    System.out.println("Default admin account created: admin/admin123");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating default admin: " + e);
        }
    }


    public void insertItem(String name, String password, int score) {
        String sql = "INSERT INTO users (name, password, score, isAdmin) VALUES (?, ?, ?, 0)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setInt(3, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(" insertItem failed : " + e.getMessage());
        }
    }

    public boolean validateLogin(String name, String password) {
        String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("validateLogin failed: " + e);
            return false;
        }
    }

    public boolean isAdmin(String name) {
        String sql = "SELECT isAdmin FROM users WHERE name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("isAdmin") == 1;
            }
        } catch (SQLException e) {
            System.out.println("isAdmin check failed: " + e);
        }
        return false;
    }

    public UserEntity getUser(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                UserEntity user = new UserEntity(rs.getString("name"), rs.getString("password"), rs.getInt("isAdmin") == 1);
                user.setId(rs.getInt("id"));
                user.setTotalScore(rs.getInt("score"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("getUser failed: " + e);
        }
        return null;
    }

    public String getUsers() {
        String sql = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            String result = "";
            while (rs.next()) {
                result = result + rs.getString("name") + " - Score: " + rs.getInt("score") + (rs.getInt("isAdmin") == 1 ? " (ADMIN)" : "") + "\n";
            }
            return result;
        } catch (Exception e) {
            return "Error";
        }
    }

    public void updateScore(int newScore, String name) {
        String sql = " UPDATE users SET score = ? WHERE name = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, newScore);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(" update failed : " + e);
        }
    }

    public void deleteUser(String name) {
        String sql = " DELETE FROM users WHERE name = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deleteUser failed : " + e);
        }
    }
    public void makeAdmin(String name) {
        String sql = " UPDATE users SET isAdmin = 1 WHERE name = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("makeAdmin failed : " + e);
        }
    }

    public void removeAdmin(String name) {
        String sql = " UPDATE users SET isAdmin = 0 WHERE name = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("removeAdmin failed : " + e);
        }
    }
}
