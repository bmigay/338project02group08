package com.daclink;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * [Description of what the class does]
 *
 * @version 0.1.0
 * @author: Braeden Migay
 * @since: 4/26/26
 */
public class SceneFactoryTest {

    private Stage stage;

    @BeforeAll
    static void bootToolkit() throws Exception {
        FxToolkit.registerPrimaryStage(); // starts JavaFX once for the whole class
    }

    @BeforeEach
    void freshStage() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
    }

    @AfterEach
    void resetStage() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
    }

    @Test
    @DisplayName("SceneFactory instantiated")
    void testExists() {
        assertNotNull(new SceneFactory());
    }

    @Test
    @DisplayName("LOGIN scene not null and has root")
    void loginSceneHasRoot() throws Exception {
        Scene scene = FxToolkit.setupScene(
                () -> SceneFactory.create(SceneType.LOGIN, stage));

        assertNotNull(scene);
        assertNotNull(scene.getRoot());
    }

    @Test
    @DisplayName("NEWUSER scene not null")
    void newuserSceneNotNull() throws Exception {
        Scene scene = FxToolkit.setupScene(
                () -> SceneFactory.create(SceneType.NEWUSER, stage));

        assertNotNull(scene);
    }

    @Test
    @DisplayName("DASHBOARD scenenot null")
    void dashboardSceneNotNull() throws Exception {
        Scene scene = FxToolkit.setupScene(
                () -> SceneFactory.create(SceneType.DASHBOARD, stage));

        assertNotNull(scene);
    }

    @Test
    @DisplayName("LEADERBOARD scene not null")
    void leaderboardSceneNotNull() throws Exception {
        Scene scene = FxToolkit.setupScene(
                () -> SceneFactory.create(SceneType.LEADERBOARD, stage));

        assertNotNull(scene);
    }

    @Test
    @DisplayName("GAME scene is not null")
    void gameSceneNotNull() throws Exception {
        Scene scene = FxToolkit.setupScene(
                () -> SceneFactory.create(SceneType.GAME, stage));

        assertNotNull(scene);
    }

    @Test
    @DisplayName("CATEGORIES scene not null")
    void categoriesSceneNotNull() throws Exception {
        Scene scene = FxToolkit.setupScene(
                () -> SceneFactory.create(SceneType.CATEGORIES, stage));

        assertNotNull(scene);
    }
}
