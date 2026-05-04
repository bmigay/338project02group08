import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * This is the scene factory class for the Scattered Categories application
 *
 * @version 0.1.0
 * @author: Braeden Migay
 * @since: 4/20/26
 */
public class SceneFactory {
    private static int lastScore = 0;


    public static Scene create(SceneType type, Stage stage) {
        return switch (type) {
            case LOGIN -> buildLoginScene(stage);
            case NEWUSER -> buildNewuserScene(stage);
            case DASHBOARD -> buildDashboardScene(stage);
            case LEADERBOARD -> buildLeaderboardScene(stage);
            case GAME -> buildGameScene(stage);
            case CATEGORIES -> buildCategories(stage);
        };
    }

    private static Scene buildLoginScene(Stage stage) {//estrella
        Label title = new Label("Welcome to Scattered Categories!");
        TextField username = new TextField();
        username.setPromptText("username");
        PasswordField password = new PasswordField();
        password.setPromptText("password");

        Label message = new Label();
        Button login = new Button("Login");
        Button newuser = new Button("New User?");

        VBox layout = new VBox();
        layout.getChildren().addAll(title, username, password, message, login, newuser);
        login.setOnAction(event -> {

            if (username.getText().equals("") || password.getText().equals("")) {
                message.setText("enter username and password");
            } else {
                stage.setScene(SceneFactory.buildDashboardScene(stage));
            }
        });
        newuser.setOnAction(event -> {
            stage.setScene(SceneFactory.buildNewuserScene(stage));
        });
        return new Scene(layout, 600, 400);
    }

    private static Scene buildNewuserScene(Stage stage) { //braeden
        Label title = new Label("Create your account!");
        TextField username = new TextField("username");
        TextField password = new TextField("password");
        TextField repeat = new TextField("repeat password");
        Button login = new Button("Login");

        login.setOnAction(event -> {
            Scene dashboard = SceneFactory.buildDashboardScene(stage);
            stage.setScene(dashboard);
        });

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
            Scene gameScene = SceneFactory.buildGameScene(stage);
            stage.setScene(gameScene);
        });

        leaderboard.setOnAction(e -> {
            Scene leaderboardScene = SceneFactory.buildLeaderboardScene(stage);
            stage.setScene(leaderboardScene);
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(title, newGame, leaderboard, pastScores);
        return new Scene(layout, 600, 400);
    }

    static Scene buildGameScene(Stage stage) { //braeden
        Validator validator = new Validator();
        String currentLetter = validator.getRandomLetter();
        ArrayList<String> catagories = new ArrayList<>();
        catagories.add("animals");
        catagories.add("colors");
        catagories.add("foods");
        catagories.add("badHabits");
        catagories.add("politicians");
        catagories.add("countries");
        catagories.add("sports");
        catagories.add("movies");
        catagories.add("celebrities");
        catagories.add("cars");

        Label title = new Label("Your Letter is " + currentLetter);
        title.setAlignment(Pos.TOP_CENTER);
        Label cat1 = new Label("Cat1");
        TextField ans1 = new TextField("");
        HBox one = new HBox(cat1, ans1);
        one.setAlignment(Pos.CENTER);
        Label cat2 = new Label("Cat2");
        TextField ans2 = new TextField("");
        HBox two = new HBox(cat2, ans2);
        two.setAlignment(Pos.CENTER);
        Label cat3 = new Label("Cat3");
        TextField ans3 = new TextField("");
        HBox three = new HBox(cat3, ans3);
        three.setAlignment(Pos.CENTER);
        Label cat4 = new Label("Cat4");
        TextField ans4 = new TextField("");
        HBox four = new HBox(cat4, ans4);
        four.setAlignment(Pos.CENTER);
        Label cat5 = new Label("Cat5");
        TextField ans5 = new TextField("");
        HBox five = new HBox(cat5, ans5);
        five.setAlignment(Pos.CENTER);
        Label cat6 = new Label("Cat6");
        TextField ans6 = new TextField("");
        HBox six = new HBox(cat6, ans6);
        six.setAlignment(Pos.CENTER);
        Label cat7 = new Label("Cat7");
        TextField ans7 = new TextField("");
        HBox seven = new HBox(cat7, ans7);
        seven.setAlignment(Pos.CENTER);
        Label cat8 = new Label("Cat8");
        TextField ans8 = new TextField("");
        HBox eight = new HBox(cat8, ans8);
        eight.setAlignment(Pos.CENTER);
        Label cat9 = new Label("Cat9");
        TextField ans9 = new TextField("");
        HBox nine = new HBox(cat9, ans9);
        nine.setAlignment(Pos.CENTER);
        Label cat10 = new Label("Cat10");
        TextField ans10 = new TextField("");
        HBox ten = new HBox(cat10, ans10);
        ten.setAlignment(Pos.CENTER);

        Label scoreLabel = new Label();

        Button finish = new Button("Finish");
        finish.setOnAction(event -> {
            int score = 0;
            TextField[] userAnswer = {ans1, ans2, ans3, ans4, ans5, ans6, ans7, ans8, ans9, ans10};
            for (int i = 0; i < userAnswer.length; i++) {
                if (validator.isValid(catagories.get(i), userAnswer[i].getText(), currentLetter)) {
                    score += 10;
                }
            }

            lastScore = score;
            stage.setScene(SceneFactory.buildDashboardScene(stage));
        });

        VBox layout = new VBox(title, one, two, three, four, five, six, seven, eight, nine, ten, scoreLabel, finish);
        layout.setAlignment(Pos.CENTER);
        return new Scene(layout, 600, 400);
    }

    private static Scene buildLeaderboardScene(Stage stage) {
        //kaissy
        Label title = new Label("Leaderboard");

        Label rank1 = new Label("1. ");
        Label rank2 = new Label("2. ");
        Label rank3 = new Label("3. ");
        Label rank4 = new Label("4. ");
        Label rank5 = new Label("5. ");

        Button backButton = new Button("Return to Dashboard");
        Button refreshButton = new Button("Refresh");

        refreshButton.setOnAction(e -> {
            System.out.println("In first place goes to...");
            System.out.println("In second place...");
            System.out.println("In third...");
        });

        backButton.setOnAction(e -> {
            stage.setScene(SceneFactory.buildDashboardScene(stage));
        });


        VBox layout = new VBox();
        layout.getChildren().addAll(title, rank1, rank2, rank3, rank4, rank5, backButton, refreshButton);

        return new Scene(layout, 600, 400);
    }

    private static Scene buildCategories(Stage stage) { // estrella
        Label title = new Label("Categories");
        TextField newCategory = new TextField();
        newCategory.setPromptText("Enter category");

        Button add = new Button("Add");
        Button back = new Button("Back");

        // databinding
        ObservableList<String> categories = FXCollections.observableArrayList();
        ListView<String> categoryList = new ListView<>();
        categoryList.setItems(categories);
        add.setOnAction(e -> {
            String text = newCategory.getText();

            if (!text.equals("")) {
                categories.add(text);
                newCategory.clear();
            }
        });
        back.setOnAction(e -> {
            stage.setScene(SceneFactory.buildDashboardScene(stage));
        });
        VBox layout = new VBox();
        layout.setSpacing(10);

        layout.getChildren().addAll(title, newCategory, add, categoryList, back);
        return new Scene(layout, 600, 400);
    }
}