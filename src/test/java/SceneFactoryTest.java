import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javafx.scene.Scene;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * [Description of what the class does]
 *
 * @version 0.1.0
 * @author: Braeden Migay
 * @since: 4/26/26
 */
public class SceneFactoryTest {

    private SceneFactory sceneFactory;

    @BeforeEach
    public void setup() {
        sceneFactory = new SceneFactory();
    }

    @AfterEach
    public void tearDown() {
        sceneFactory = null;
    }

    @Test
    @DisplayName("Test exists")
    public void testExists() {
        assertNotNull(sceneFactory);
    }
}
