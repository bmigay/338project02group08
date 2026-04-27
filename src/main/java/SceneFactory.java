import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;


/**
 * This is the scene factory class for the Scattered Categories application
 *
 * @version 0.1.0
 * @author: Braeden Migay
 * @since: 4/20/26
 */
public class SceneFactory {

    public static Scene create(SceneType type, Stage stage) {
        return switch(type) {
            case LOGIN -> buildLoginScene(stage);
            case NEWUSER -> buildNewserScene(stage);
            case DASHBOARD -> buildDashboardScene(stage);
            case LEADERBOARD -> buildLeaderboardScene(stage);
            case GAME -> buildGameScene(stage);
            case CATEGORIES -> buildCategories(stage);
        };
    }

    private static Scene buildLoginScene(Stage stage) {
        Label title = new Label("Welcome to Scattered Categories!");
        TextField username = new TextField("username");
        TextField password = new TextField("password");
        Button login = new Button("Login");
        Button newuser = new Button("New User?");

        VBox layout = new VBox();
        layout.getChildren().addAll(title, username, password, login, newuser);

        return new Scene(layout, 600, 400);
    }

    private static Scene buildNewserScene(Stage stage) {
        Label title = new Label("Create your account!");
        TextField username = new TextField("username");
        TextField password = new TextField("password");
        TextField repeat = new TextField("repeat password");
        Button login = new Button("Login");

        VBox layout = new VBox();
        layout.getChildren().addAll(title, username, password, login, repeat);

        return new Scene(layout, 600, 400);
    }

    private static Scene buildDashboardScene(Stage stage) {
        Label title = new Label("Welcome, User!: ");
        Button newGame = new Button("New Game: ");
        Button leaderboard = new Button("View Leaderboard: ");
        Button pastScores = new Button("View Past Scores: ");

        newGame.setOnAction(e -> {
            System.out.println("New Game!");
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(title, newGame, leaderboard, pastScores);


        return new Scene(layout, 600, 400);
    }

    private static Scene buildGameScene(Stage stage) {
        Label title = new Label("Your Letter is <>");
        Label cat1 = new Label("Cat1");
        TextField ans1 = new TextField("ans1");
        HBox one = new HBox(cat1, ans1);
        Label cat2 = new Label("Cat2");

        return null;
    }

    private static Scene buildLeaderboardScene(Stage stage) {
        Label title = new Label ("Leaderboard");

        Label rank1 = new Label ("1. ");
        Label rank2 = new Label ("2. ");
        Label rank3 = new Label ("3. ");
        Label rank4 = new Label ("4. ");
        Label rank5 = new Label ("5. ");

        Button backButton = new Button ("Return to Dashboard");
        Button refreshButton = new Button("Refresh");

        refreshButton.setOnAction(e -> {
            System.out.println("In first place goes to...");;
            System.out.println("In second place...");
            System.out.println("In third...");
        });



        VBox layout = new VBox();
        layout.getChildren().addAll(title, rank1, rank2, rank3, rank4, rank5, backButton, refreshButton);

        // back button still needed

        return new Scene(layout, 600,400);
    }

    private static Scene buildCategories(Stage stage) {
        return null;
    }
}
