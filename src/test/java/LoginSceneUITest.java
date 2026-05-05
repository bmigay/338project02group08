import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
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
}