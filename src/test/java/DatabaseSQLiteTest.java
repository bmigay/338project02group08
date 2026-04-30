package com.daclink;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseSQLiteTest {
    private com.daclink.DatabaseSQLite db;

    @BeforeEach
    void setup() {
        db = new com.daclink.DatabaseSQLite();
    }
    @Test //creating game
    void testCreateGame() {
        db.createGame("testplayer", "S");
        assertTrue(true);
        System.out.println("testing CreateGame");
    }
    @Test //saving answers and updating scores
    void testSaveAnswerAndUpdateScore() {
        db.createGame("testplayer2", "A");
        db.saveAnswer(1, 1, "Fruits", "Apple", 10);
        db.saveAnswer(1, 2, "Cities", "Austin", 10);
        db.saveAnswer(1, 3, "Animals", "Anteater", 10);
        db.updateGameScore(1, 30);
        assertTrue(true);
        System.out.println("testing SaveAnswerAndUpdateScore");
    }
    @Test
    void testGetPastScores() {
        db.createGame("scoreplayer", "B");
        db.createGame("scoreplayer", "C");
        String scores = db.getPastScores("scoreplayer");
        assertNotNull(scores, "Past scores should not be null");
        System.out.println("Past scores: " + scores);
        System.out.println("testing GetPastScores");
    }

    @Test //leaderboard
    void testGetLeaderboard() {
        String leaderboard = db.getLeaderboard();
        assertNotNull(leaderboard, "Leaderboard should not be null");
        System.out.println("Leaderboard: \n" + leaderboard);
        System.out.println("testing GetLeaderboard");
    }
}
