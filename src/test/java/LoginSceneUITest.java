import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
/**
 *
 */

public class LoginSceneUITest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        Scene scene = SceneFactory.create(SceneType.LOGIN, stage);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void loginButtonExists() {
        verifyThat(".button", hasText("Login"));
    }
    @Test
    void loginTransitionsToDashboard() {

        clickOn(".text-field").write("estrella");
        clickOn(".password-field").write("1234");
        clickOn("Login");

        verifyThat("New Game", isVisible());
    }
}