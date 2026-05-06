package com.daclink;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This is the scene factory class for the Scattered Categories application
 *
 * @version 0.1.0
 * @author: Braeden Migay
 * @since: 4/20/26
 */
public class SceneFactory {

    private static int lastScore = 0;
    private static String currentUsername = "";
    // private static UserDataBase userDb = new UserDataBase();
    private static com.daclink.DatabaseSQLite Sdb = new com.daclink.DatabaseSQLite();

    public static Scene create(SceneType type, Stage stage) {

        return switch (type) {
            case LOGIN -> buildLoginScene(stage);
            case NEWUSER -> buildNewuserScene(stage);
            case DASHBOARD -> buildDashboardScene(stage);
            case LEADERBOARD -> buildLeaderboardScene(stage);
            case GAME -> buildGameScene(stage);
            case CATEGORIES -> buildCategories(stage);
            case PASTSCORES -> buildPastScoresScene(stage);
        };
    }

    private static Scene buildLoginScene(Stage stage) { //estrella
        UserDataBase db = new UserDataBase();
        Label title = new Label("Welcome to Scattered Categories!");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        TextField username = new TextField();
        username.setId("usernameField");
        username.setPromptText("username");
        username.setMaxWidth(200);

        PasswordField password = new PasswordField();
        password.setId("passwordField");
        password.setPromptText("password");
        password.setMaxWidth(200);
        Label message = new Label();
        Button login = new Button("Login");
        Button newuser = new Button("New User?");

        login.setOnAction(event -> {
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                message.setText("Enter username and password");
            } else if (db.validateLogin(username.getText(), password.getText())) {
                currentUsername = username.getText();
                stage.setScene(SceneFactory.buildDashboardScene(stage));
            } else {
                message.setText("Invalid username or password");
            }
        });
        newuser.setOnAction(event -> {
            stage.setScene(SceneFactory.buildNewuserScene(stage));
        });

        VBox layout = new VBox(10, title, username, password, message, login, newuser);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: mediumpurple;");
        return new Scene(layout, 600, 400);
    }

    private static Scene buildNewuserScene(Stage stage) { //braeden
        UserDataBase db = new UserDataBase();
        Label message = new Label();
        Label title = new Label("Create your account!");
        title.setStyle("-fx-font-size: 24px;");
        TextField username = new TextField();
        username.setPromptText("username");
        username.setMaxWidth(200);
        username.setStyle("-fx-font-size: 16px;");

        PasswordField password = new PasswordField();
        password.setPromptText("password");
        password.setMaxWidth(200);
        password.setStyle("-fx-font-size: 16px;");
        PasswordField repeat = new PasswordField();
        repeat.setPromptText("repeat password");
        repeat.setStyle("-fx-font-size: 16px;");
        repeat.setMaxWidth(200);

        Button login = new Button("Create Account");
        login.setOnAction(event -> {
            if (username.getText().equals("") || password.getText().equals("") || repeat.getText().equals("")) {
                message.setText("Fill all fields");
            } else if (!password.getText().equals(repeat.getText())) {
                message.setText("Passwords do not match");
            } else {
                db.insertItem(username.getText(), password.getText(), 0);
                message.setText("account created successfully!");
                stage.setScene(SceneFactory.buildLoginScene(stage));
            }
        });
        VBox layout = new VBox();

        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: mediumpurple;");
        message.setStyle("-fx-text-fill: white;");
        layout.getChildren().addAll(title, username, password, repeat, message, login);
        return new Scene(layout, 600, 400);
    }

    private static Scene buildDashboardScene(Stage stage) {
        Label title = new Label("Welcome, " + currentUsername + "!");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");
        Label scoreDisplay = new Label();
        if (lastScore > 0) {
            scoreDisplay.setText("Last Score: " + lastScore + " points!");
            scoreDisplay.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
        } else {
            scoreDisplay.setText("No games played yet. Start a new game!");
            scoreDisplay.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
        }

        Button newGame = new Button("New Game");
        Button leaderboard = new Button("View Leaderboard");
        Button pastScores = new Button("View Past Scores");
        Button categories = new Button("Categories");

        newGame.setMaxWidth(200);
        leaderboard.setMaxWidth(200);
        pastScores.setMaxWidth(200);
        categories.setMaxWidth(200);

        newGame.setStyle("-fx-font-size: 16px;");
        leaderboard.setStyle("-fx-font-size: 16px;");
        pastScores.setStyle("-fx-font-size: 16px;");
        categories.setStyle("-fx-font-size: 16px;");

        newGame.setOnAction(e -> {
            System.out.println("New Game!");
            Scene gameScene = SceneFactory.buildGameScene(stage);
            stage.setScene(gameScene);
        });

        leaderboard.setOnAction(e -> {
            Scene leaderboardScene = SceneFactory.buildLeaderboardScene(stage);
            stage.setScene(leaderboardScene);

        });

        categories.setOnAction(e -> {
            stage.setScene(SceneFactory.buildCategories(stage));
        });
        pastScores.setOnAction((e -> {
            stage.setScene(SceneFactory.buildPastScoresScene(stage));
        }));

        VBox layout = new VBox();
        layout.setSpacing(15);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: mediumpurple;");
        layout.getChildren().addAll(title, newGame, leaderboard, pastScores, categories, scoreDisplay);
        return new Scene(layout, 600, 400);
    }

    static Scene buildGameScene(Stage stage) {
        Validator validator = new Validator();

        String currentLetter = validator.getRandomLetter();

        ArrayList<String> categories = new ArrayList<>();

        categories.add("animals");
        categories.add("colors");
        categories.add("foods");
        categories.add("badBabits");
        categories.add("politicians");
        categories.add("countries");
        categories.add("sports");
        categories.add("movies");
        categories.add("celebrities");
        categories.add("cars");
        categories.add("fruits");
        categories.add("vegetables");
        categories.add("holidays");
        categories.add("book titles");
        categories.add("song titles");
        categories.add("occupations");
        categories.add("brand names");
        categories.add("things in classroom");
        categories.add("things at beach");
        categories.add("things that are cold");
        categories.add("things that are hot");
        categories.add("tv shows");
        categories.add("girl names");
        categories.add("boy names");
        categories.add("cities");
        categories.add("rivers");
        categories.add("mountains");
        categories.add("insects");
        categories.add("birds");
        categories.add("fish");
        categories.add("flowers");
        categories.add("furniture");
        categories.add("kitchen items");
        categories.add("bathroom items");
        categories.add("tools");
        categories.add("sports teams");
        categories.add("school subjects");
        categories.add("body parts");
        categories.add("emotions");
        categories.add("weather words");
        categories.add("drinks");
        categories.add("desserts");

        Collections.shuffle(categories);

        ArrayList<String> chosenCategories = new ArrayList<>(categories.subList(0, 10));

        Label title = new Label("Your Letter is " + currentLetter);
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        title.setStyle("-fx-text-fill: white;");

        title.setAlignment(Pos.TOP_CENTER);

        Label cat1 = new Label(chosenCategories.get(0));
        TextField ans1 = new TextField("");
        HBox one = new HBox(20, cat1, ans1);
        cat1.setStyle("-fx-text-fill: white;");
        one.setAlignment(Pos.CENTER);

        Label cat2 = new Label(chosenCategories.get(1));
        TextField ans2 = new TextField("");
        HBox two = new HBox(20, cat2, ans2);
        cat2.setStyle("-fx-text-fill: white;");
        two.setAlignment(Pos.CENTER);

        Label cat3 = new Label(chosenCategories.get(2));
        TextField ans3 = new TextField("");
        cat3.setStyle("-fx-text-fill: white;");
        HBox three = new HBox(20, cat3, ans3);
        three.setAlignment(Pos.CENTER);

        Label cat4 = new Label(chosenCategories.get(3));
        TextField ans4 = new TextField("");
        HBox four = new HBox(20, cat4, ans4);
        cat4.setStyle("-fx-text-fill: white;");
        four.setAlignment(Pos.CENTER);

        Label cat5 = new Label(chosenCategories.get(4));
        TextField ans5 = new TextField("");
        HBox five = new HBox(20, cat5, ans5);
        cat5.setStyle("-fx-text-fill: white;");
        five.setAlignment(Pos.CENTER);

        Label cat6 = new Label(chosenCategories.get(5));
        TextField ans6 = new TextField("");
        HBox six = new HBox(20, cat6, ans6);
        cat6.setStyle("-fx-text-fill: white;");
        six.setAlignment(Pos.CENTER);

        Label cat7 = new Label(chosenCategories.get(6));
        TextField ans7 = new TextField("");
        HBox seven = new HBox(20, cat7, ans7);
        cat7.setStyle("-fx-text-fill: white;");
        seven.setAlignment(Pos.CENTER);

        Label cat8 = new Label(chosenCategories.get(7));
        TextField ans8 = new TextField("");
        HBox eight = new HBox(20, cat8, ans8);
        cat8.setStyle("-fx-text-fill: white;");
        eight.setAlignment(Pos.CENTER);

        Label cat9 = new Label(chosenCategories.get(8));
        TextField ans9 = new TextField("");
        HBox nine = new HBox(20, cat9, ans9);
        cat9.setStyle("-fx-text-fill: white;");
        nine.setAlignment(Pos.CENTER);

        Label cat10 = new Label(chosenCategories.get(9));
        TextField ans10 = new TextField("");
        HBox ten = new HBox(20, cat10, ans10);
        cat10.setStyle("-fx-text-fill: white;");
        ten.setAlignment(Pos.CENTER);

        Button finish = new Button("Finish");

        finish.setOnAction(event -> {

            int score = 0;

            TextField[] userAnswer = {ans1, ans2, ans3, ans4, ans5, ans6, ans7, ans8, ans9, ans10};
            for (int i = 0; i < userAnswer.length; i++) {
                if (validator.isValid(chosenCategories.get(i), userAnswer[i].getText(), currentLetter)) {
                    score += 10;
                }
            }
            lastScore = score;
            Sdb.createGame(currentUsername, currentLetter, lastScore);
            stage.setScene(SceneFactory.buildDashboardScene(stage));
        });

        VBox layout = new VBox(10, title, one, two, three, four, five, six, seven, eight, nine, ten, finish);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: mediumpurple; -fx-padding: 25px;");

        return new Scene(layout, 600, 400);
    }


    private static Scene buildCategories(Stage stage) { // estrella
        Label title = new Label("Categories");
        TextField newCategory = new TextField();
        newCategory.setPromptText("Enter category");

        Button add = new Button("Add");
        Button back = new Button("Back");
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

    private static Scene buildLeaderboardScene(Stage stage) {
        Label title = new Label("LEADERBOARD");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextArea leaderboardText = new TextArea();
        leaderboardText.setEditable(false);
        leaderboardText.setPrefHeight(300);
        leaderboardText.setStyle("-fx-font-size: 16px; -fx-font-family: monospace;");


        String leaderboardData = Sdb.getLeaderboard();
        leaderboardText.setText(leaderboardData);

        Button backButton = new Button("Return to Dashboard");
        backButton.setOnAction(e -> {
            stage.setScene(SceneFactory.buildDashboardScene(stage));
        });

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            String refreshedData = Sdb.getLeaderboard();
            leaderboardText.setText(refreshedData);
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: mediumpurple; -fx-padding: 25px;");
        layout.getChildren().addAll(title, leaderboardText, refreshButton, backButton);
        return new Scene(layout, 600, 400);
    }

    private static Scene buildPastScoresScene(Stage stage) {
        Label title = new Label("PAST SCORES - " + currentUsername);
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextArea pastScoresText = new TextArea();
        pastScoresText.setEditable(false);
        pastScoresText.setPrefHeight(350);
        pastScoresText.setStyle("-fx-font-size: 16px; -fx-font-family: monospace;");

        String pastScoresData = Sdb.getPastScores(currentUsername);
        pastScoresText.setText(pastScoresData);

        Button backButton = new Button("Back to Dashboard");
        backButton.setOnAction(e -> {
            stage.setScene(SceneFactory.buildDashboardScene(stage));
        });

        Button refreshButton = new Button("Refresh");
        refreshButton.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");
        refreshButton.setOnAction(e -> {
            String refreshedData = Sdb.getPastScores(currentUsername);
            pastScoresText.setText(refreshedData);
        });


        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: mediumpurple; -fx-padding: 25px;");
        layout.getChildren().addAll(title, pastScoresText, refreshButton, backButton);
        return new Scene(layout, 600, 500);
    }
}
