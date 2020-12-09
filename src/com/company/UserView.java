package com.company;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserView {
    private BorderPane borderPane = new BorderPane();

    private UserView(User user, Group rootGroup) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Label userName = new Label("User View For: " + user.getId());
        Label timeCreated = new Label("Created at: " + user.getCreationTime());
        TextField userIDField = new TextField();
        userIDField.setPromptText("User ID");
        Button follow = new Button("Follow User");
        follow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String userToFollow = userIDField.getText();
                User toFollow = rootGroup.getUser(userToFollow);
                if (toFollow == null) {
                    alert.setContentText("This user does not exist.");
                    alert.showAndWait();
                } else if (toFollow == user) {
                    alert.setContentText("You can't follow yourself.");
                    alert.showAndWait();
                } else if (user.getFollowingList().contains(toFollow)) {
                    alert.setContentText(user + " already follows this user.");
                    alert.showAndWait();
                } else {
                    toFollow.attach(user);
                    user.addFollowingList(toFollow);
                }
                userIDField.clear();
            }
        });
        ListView currentFollowing = new ListView(user.getFollowingList());
        currentFollowing.setPrefSize(500, 200);
        TextArea tweetField = new TextArea();
        tweetField.setWrapText(true);
        tweetField.setPrefSize(200, 100);
        tweetField.setPromptText("Tweet Message");
        Button post = new Button("Post Tweet");
        post.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String tweet = tweetField.getText();
                user.postTweet(tweet);
                tweetField.clear();
            }
        });
        ListView newsFeed = new ListView(user.getFeedList());
        newsFeed.setPrefSize(500, 200);
        HBox hbox1 = new HBox(userIDField, follow);
        hbox1.setSpacing(10);
        HBox hbox2 = new HBox(tweetField, post);
        hbox2.setSpacing(10);
        hbox2.setAlignment(Pos.BOTTOM_LEFT);
        VBox vBox = new VBox(userName, timeCreated,hbox1, currentFollowing, hbox2, newsFeed);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setSpacing(10);
        borderPane.setCenter(vBox);
    }
    public BorderPane getBorderPane()
    {
        return borderPane;
    }
    public static void openUserView(User user, Group rootGroup){
        UserView thisUserView = new UserView(user, rootGroup);
        BorderPane borderPane = thisUserView.getBorderPane();
        Scene secondScene = new Scene(borderPane, 500, 500);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        secondStage.setTitle(user.getId());
        secondStage.show();
    }


}
