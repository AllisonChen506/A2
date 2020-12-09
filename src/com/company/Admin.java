package com.company;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class Admin {
    private static Admin pointer;
    private BorderPane borderpane = new BorderPane();
    public static Admin getInstance()
    {
        if (pointer == null) {
            synchronized (Admin.class) {
                if (pointer == null) {
                    pointer = new Admin();
                }
            }
        }
        return pointer;
    }
    private Admin() {
        Group rootGroup = new Group("Root");
        TreeItem<SysEntry> root = new TreeItem<SysEntry> (rootGroup);
        root.setExpanded(true);
        TreeView<SysEntry> treeView = new TreeView<SysEntry>(root);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        Label label = new Label("");
        TextField userField = new TextField();
        userField.setPromptText("User ID");
        Button addUser = new Button("Add User");
        addUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TreeItem<SysEntry> selectedItem = treeView.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    alert.setContentText("Please add to a group.");
                    alert.showAndWait();
                    userField.clear();
                }
                else if (selectedItem.getValue() instanceof User) {
                    alert.setContentText("Please add to a group.");
                    alert.showAndWait();
                }
                else if (selectedItem.getValue() instanceof Group){
                    String userIDInput = userField.getText();
                    if (rootGroup.containsUserId(userIDInput)){
                        alert.setContentText("This user is already in a group.");
                        alert.showAndWait();
                    }
                    else {
                        User temp = new User(userIDInput);
                        ((Group) selectedItem.getValue()).addGroupMember(temp);
                        selectedItem.getChildren().add(new TreeItem<SysEntry>(temp));
                    }
                }
                userField.clear();
            }
        });
        TextField groupField = new TextField();
        groupField.setPromptText("Group ID");
        Button addGroup = new Button("Add Group");
        addGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TreeItem<SysEntry> selectedItem = treeView.getSelectionModel().getSelectedItem();
                if (selectedItem.getValue() instanceof User){
                    alert.setContentText("Please add to a group.");
                    alert.showAndWait();
                }
                else if (selectedItem.getValue() instanceof Group){
                    String groupIDInput = groupField.getText();
                    if (rootGroup.containsGroupId(groupIDInput)){
                        alert.setContentText("This group already exists.");
                        alert.showAndWait();
                    }
                    else {
                        Group temp = new Group(groupIDInput);
                        ((Group) selectedItem.getValue()).addGroupMember(temp);
                        selectedItem.getChildren().add(new TreeItem<SysEntry>(temp));
                    }
                }
                groupField.clear();
            }
        });
        Button openUser = new Button("Open User View");
        openUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TreeItem<SysEntry> selectedItem = treeView.getSelectionModel().getSelectedItem();
                if (selectedItem.getValue() instanceof Group){
                    alert.setContentText("Please select a user to open user view");
                    alert.showAndWait();
                }
                else if (selectedItem.getValue() instanceof User){
                    User userViewUser = (User) selectedItem.getValue();
                    UserView.openUserView(userViewUser, rootGroup);
                }
            }
        });
        Button userTotal = new Button("Show User Total");
        userTotal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UserTotal userTotalVisitor = new UserTotal();
                rootGroup.accept(userTotalVisitor);
                label.setText("There are " + userTotalVisitor.getUserTotal() + " users in total.");
            }
        });
        Button groupTotal = new Button("Show Group Total");
        groupTotal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GroupTotal groupTotalVisitor = new GroupTotal();
                rootGroup.accept(groupTotalVisitor);
                label.setText("There are " + groupTotalVisitor.getGroupTotal() + " groups in total including Root.");
            }
        });
        Button messageTotalButton = new Button("Show Message Total");
        messageTotalButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MessageTotal messageTotalVisitor = new MessageTotal();
                rootGroup.accept(messageTotalVisitor);
                label.setText("There are " + messageTotalVisitor.getMessageTotal() + " messages.");
            }
        });
        Button positivePercentage = new Button("Show Positive Percentage Total");
        positivePercentage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PositiveMessage positivePercentageVisitor = new PositiveMessage();
                rootGroup.accept(positivePercentageVisitor);
                label.setText(String.format("The percentage of positive messages is %.2f%%",  positivePercentageVisitor.getPositiveMessage()));
            }
        });
        Button valid = new Button("Validate IDs");
        valid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UserVerification userVerification = new UserVerification();
                rootGroup.accept(userVerification);
                if(userVerification.getValid())
                    info.setContentText("All IDs are valid");
                else
                    info.setContentText("Not all IDs are valid or an ID has white spaces");
                info.showAndWait();
            }
        });
        Button update = new Button("Recently Updated User");
        update.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UserUpdate userUpdate= new UserUpdate();
                rootGroup.accept(userUpdate);
                info.setContentText("Recently Updated User: " + userUpdate.getLastUpdateUser());
                info.showAndWait();
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(addUser, 1,0);
        gridPane.add(userField, 0,0);
        gridPane.add(addGroup,1,1);
        gridPane.add(groupField,0,1);
        GridPane bottomPart = new GridPane();
        bottomPart.setVgap(10);
        bottomPart.setHgap(10);
        bottomPart.add(userTotal, 0,0);
        bottomPart.add(messageTotalButton, 0, 1);
        bottomPart.add(groupTotal, 1, 0);
        bottomPart.add(positivePercentage, 1, 1);
        bottomPart.add(valid, 0,2);
        bottomPart.add(update, 1, 2);
        VBox vbox = new VBox(gridPane, openUser, label, bottomPart);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);
        borderpane.setLeft(treeView);
        borderpane.setCenter(vbox);
    }

    public BorderPane getBorderpane()
    {
        return borderpane;
    }


}
