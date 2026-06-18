package it.unibo.todolist;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        TodoController controller = new TodoController();

        HBox inputBox = new HBox(10);
        inputBox.getChildren().addAll(
                controller.getInputField(),
                controller.getAddButton()
        );

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(
                controller.getCompleteButton(),
                controller.getDeleteButton()
        );

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
                inputBox,
                controller.getListView(),
                buttonBox,
                controller.getMessageLabel()
        );

        Scene scene = new Scene(root, 500, 400);

        stage.setTitle("Todo List - JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}