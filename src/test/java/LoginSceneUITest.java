import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class LoginSceneUITest extends ApplicationTest {

    @BeforeEach
    void setupDatabase() {
        UserDataBase db = new UserDataBase();
        db.insertItem("estrella", "1234", 0);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = SceneFactory.create(SceneType.LOGIN, stage);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void loginTransitionsToDashboard() {
        clickOn((javafx.scene.control.TextField) lookup(".text-field").query());
        write("estrella");
        clickOn((javafx.scene.control.PasswordField) lookup(".password-field").query());
        write("1234");
        clickOn("Login");
        verifyThat("New Game", isVisible());
    }
}