package com.daclink;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Admin {

    private String username;
    private UserDataBase userDb;
    private com.daclink.DatabaseSQLite gameDb;

    public Admin(String username) {
        this.username = username;
        this.userDb = new UserDataBase();
        this.gameDb = new com.daclink.DatabaseSQLite();
    }

    public Scene buildAdminDashboard(Stage stage) {
        Label title = new Label("Admin Dashboard");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        Label welcomeLabel = new Label("Welcome, " + username + "!");
        welcomeLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

        Button manageCategories = new Button("Manage Categories");
        Button viewAllUsers = new Button("View All Users");
        Button viewLeaderboard = new Button("View Leaderboard");
        Button logout = new Button("Logout");

        manageCategories.setMaxWidth(200);
        viewAllUsers.setMaxWidth(200);
        viewLeaderboard.setMaxWidth(200);
        logout.setMaxWidth(200);

        manageCategories.setStyle("-fx-font-size: 16px;");
        viewAllUsers.setStyle("-fx-font-size: 16px;");
        viewLeaderboard.setStyle("-fx-font-size: 16px;");
        logout.setStyle("-fx-font-size: 16px;");

        manageCategories.setOnAction(e -> {
            Scene categoriesScene = SceneFactory.buildCategories(stage);
            stage.setScene(categoriesScene);
        });

        viewAllUsers.setOnAction(e -> {
            String users = userDb.getUsers();

            TextArea usersArea = new TextArea(users);
            usersArea.setEditable(false);
            usersArea.setPrefHeight(300);

            Button backButton = new Button("Back");
            backButton.setOnAction(back -> {
                stage.setScene(buildAdminDashboard(stage));
            });

            VBox layout = new VBox(10);
            layout.setAlignment(Pos.CENTER);
            layout.setStyle("-fx-background-color: mediumpurple;");
            layout.getChildren().addAll(new Label("All Users"), usersArea, backButton);

            stage.setScene(new Scene(layout, 600, 400));
        });

        viewLeaderboard.setOnAction(e -> {
            String leaderboard = gameDb.getLeaderboard();

            TextArea leaderboardArea = new TextArea(leaderboard);
            leaderboardArea.setEditable(false);
            leaderboardArea.setPrefHeight(300);

            Button backButton = new Button("Back");
            backButton.setOnAction(back -> {
                stage.setScene(buildAdminDashboard(stage));
            });

            VBox layout = new VBox(10);
            layout.setAlignment(Pos.CENTER);
            layout.setStyle("-fx-background-color: mediumpurple;");
            layout.getChildren().addAll(new Label("Leaderboard"), leaderboardArea, backButton);

            stage.setScene(new Scene(layout, 600, 400));
        });

        logout.setOnAction(e -> {
            stage.setScene(SceneFactory.buildLoginScene(stage));
        });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: mediumpurple;");
        layout.getChildren().addAll(title, welcomeLabel, manageCategories, viewAllUsers, viewLeaderboard, logout);

        return new Scene(layout, 600, 400);
    }
}