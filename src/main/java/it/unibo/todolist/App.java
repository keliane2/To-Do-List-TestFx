package it.unibo.todolist;

import it.unibo.todolist.controller.TodoController;
import it.unibo.todolist.view.TodoView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        TodoView view = new TodoView();

        new TodoController(view);

        Scene scene = new Scene(
                view.getRoot(),
                650,
                550
        );

        scene.getStylesheets().add(
                getClass()
                        .getResource("/style.css")
                        .toExternalForm()
        );

        stage.setTitle("Todo List");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}