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
            case NEWUSER -> buildNewuserScene(stage);
            case DASHBOARD -> buildDashboardScene(stage);
            case LEADERBOARD -> buildLeaderboardScene(stage);
            case GAME -> buildGameScene(stage);
            case CATEGORIES -> buildCategories(stage);
        };
    }

    private static Scene buildLoginScene(Stage stage) { //estrella
        Label title = new Label("Welcome to Scattered Categories!");
        TextField username = new TextField("username");
        TextField password = new TextField("password");
        Button login = new Button("Login");
        Button newuser = new Button("New User?");

        VBox layout = new VBox();
        layout.getChildren().addAll(title, username, password, login, newuser);

        return new Scene(layout, 600, 400);
    }

    private static Scene buildNewuserScene(Stage stage) { //braeden
        Label title = new Label("Create your account!");
        TextField username = new TextField("username");
        TextField password = new TextField("password");
        TextField repeat = new TextField("repeat password");
        Button login = new Button("Login");

        VBox layout = new VBox();
        layout.getChildren().addAll(title, username, password, login, repeat);

        return new Scene(layout, 600, 400);
    }

    private static Scene buildDashboardScene(Stage stage) { //kaissy
        Label title = new Label("Welcome, User!");
        Button newGame = new Button("New Game");
        Button leaderboard = new Button("View Leaderboard");
        Button pastScores = new Button("View Past Scores");

        VBox layout = new VBox();
        layout.getChildren().addAll(title, newGame, leaderboard, pastScores);

        return new Scene(layout, 600, 400);
    }

    static Scene buildGameScene(Stage stage) { //braeden
        Label title = new Label("Your Letter is <>");
        Label cat1 = new Label("Cat1");
        TextField ans1 = new TextField("ans1");
        HBox one = new HBox(cat1, ans1);
        Label cat2 = new Label("Cat2");
        TextField ans2 = new TextField("ans2");
        HBox two = new HBox(cat2, ans2);
        Label cat3 = new Label("Cat3");
        TextField ans3 = new TextField("ans3");
        HBox three = new HBox(cat3, ans3);
        Label cat4 = new Label("Cat4");
        TextField ans4 = new TextField("ans4");
        HBox four = new HBox(cat4, ans4);
        Label cat5 = new Label("Cat5");
        TextField ans5 = new TextField("ans5");
        HBox five = new HBox(cat5, ans5);
        Label cat6 = new Label("Cat6");
        TextField ans6 = new TextField("");
        HBox six = new HBox(cat6, ans6);
        Label cat7 = new Label("Cat7");
        TextField ans7 = new TextField("");
        HBox seven = new HBox(cat7, ans7);
        Label cat8 = new Label("Cat8");
        TextField ans8 = new TextField("ans8");
        HBox eight = new HBox(cat8, ans8);
        Label cat9 = new Label("Cat9");
        TextField ans9 = new TextField("ans9");
        HBox nine = new HBox(cat9, ans9);
        Label cat10 = new Label("Cat10");
        TextField ans10 = new TextField("ans10");
        HBox ten = new HBox(cat10, ans10);

        VBox layout = new VBox(one, two, three, four, five, six, seven, eight, nine, ten);


        return new Scene(layout, 600, 400);
    }

    private static Scene buildLeaderboardScene(Stage stage) {
        return null;
    } //kaissy

    private static Scene buildCategories(Stage stage) { //estrella
        return null;
    }
}
