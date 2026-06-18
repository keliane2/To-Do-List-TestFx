package it.unibo.todolist;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        TodoController controller = new TodoController();

        Label title = new Label("Todo List");
        title.setId("titleLabel");

        Label subtitle = new Label("Organizza le tue attività quotidiane");
        subtitle.setId("subtitleLabel");

        HBox inputBox = new HBox(10);
        inputBox.setId("inputBox");
        inputBox.getChildren().addAll(
                controller.getInputField(),
                controller.getAddButton()
        );

        HBox actionBox = new HBox(10);
        actionBox.getChildren().addAll(
                controller.getToggleButton(),
                controller.getDeleteButton()
        );

        HBox filterBox = new HBox(10);
        filterBox.getChildren().addAll(
                controller.getAllFilterButton(),
                controller.getTodoFilterButton(),
                controller.getCompletedFilterButton()
        );

        VBox card = new VBox(15);
        card.setId("mainCard");
        card.getChildren().addAll(
                title,
                subtitle,
                inputBox,
                controller.getListView(),
                actionBox,
                filterBox,
                controller.getMessageLabel()
        );

        StackPane root = new StackPane(card);
        root.setPadding(new Insets(30));
        root.setId("rootPane");

        Scene scene = new Scene(root, 650, 550);
        scene.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm()
        );

        stage.setTitle("Todo List - JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}