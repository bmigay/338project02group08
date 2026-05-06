package com.daclink;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Driver class for the applicaiton
 *
 * @version 0.1.0
 * @author: Braeden Migay
 * @since: 4/20/26
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene login = SceneFactory.create(SceneType.LOGIN, stage);
        stage.setScene(login);
        stage.show();
    }

}
