import java.sql.*;

public class UserDataBase {
    private Connection connection;

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
                score INTEGER NOT NULL DEFAULT 0
                )
                """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("createTables failed: " + e);
        }
    }

    public void insertItem(String name, String password, int score) {
        String sql = " INSERT INTO users (name, password, score) VALUES (?, ?, ?) ";
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

    public String getUsers() {
        String sql = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            String result = "";
            while (rs.next()) {
                result = result + rs.getString("name") + " - " + rs.getInt("score") + "\n";
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
}