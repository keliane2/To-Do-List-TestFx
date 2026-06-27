package it.unibo.todolist;

import it.unibo.todolist.controller.TodoController;
import it.unibo.todolist.model.TodoItem;
import it.unibo.todolist.model.TodoStorage;
import it.unibo.todolist.view.TodoView;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.assertj.core.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

public class TodoListTest extends ApplicationTest {


    @Override
    public void start(Stage stage) {
        new TodoStorage().clear();

        TodoView view = new TodoView();
        new TodoController(view);

        Scene scene = new Scene(view.getRoot(), 650, 550);

        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    void shouldAddTask() {
        clickOn("#inputField");
        write("Studiare Paradigmi");
        clickOn("#addButton");

        @SuppressWarnings("unchecked")
        ListView<TodoItem> listView = lookup("#todoList").queryAs(ListView.class);

        assertThat(listView.getItems())
            .hasSize(1);

        assertThat(listView.getItems().get(0).getText())
            .isEqualTo("Studiare Paradigmi");
    }

    @Test
    void shouldShowErrorWhenTaskIsEmpty() {
        clickOn("#addButton");

        Label messageLabel = lookup("#messageLabel").queryAs(Label.class);

        assertThat(messageLabel)
                .hasText("Errore: inserire una attività.");
    }

    @Test
    void shouldUpdateStatsAfterAddingTask() {
        clickOn("#inputField");
        write("Task 1");
        clickOn("#addButton");

        Label statsLabel = lookup("#statsLabel").queryAs(Label.class);

        assertThat(statsLabel)
                .hasText("Totale: 1 | Da fare: 1 | Completate: 0");
    }

    @Test
    void shouldCompleteTaskUsingCheckbox() {
        clickOn("#inputField");
        write("Task completabile");
        clickOn("#addButton");

        clickOn(".check-box");

        Label statsLabel = lookup("#statsLabel").queryAs(Label.class);

        assertThat(statsLabel)
                .hasText("Totale: 1 | Da fare: 0 | Completate: 1");
    }

    @Test
    void shouldFilterCompletedTasks() {
        clickOn("#inputField");
        write("Task 1");
        clickOn("#addButton");

        clickOn("#inputField");
        write("Task 2");
        clickOn("#addButton");

        clickOn(".check-box");

        clickOn("#completedFilterButton");

        @SuppressWarnings("unchecked")
        ListView<TodoItem> listView = lookup("#todoList").queryAs(ListView.class);

        assertThat(listView.getItems())
            .hasSize(1);

        assertThat(listView.getItems().get(0).getText())
            .isEqualTo("Task 2");
    }

    @Test
    void shouldFilterUncompletedTasks() {
        clickOn("#inputField");
        write("Task 1");
        clickOn("#addButton");

        clickOn("#inputField");
        write("Task 2");
        clickOn("#addButton");

        clickOn(".check-box");

        clickOn("#todoFilterButton");

        @SuppressWarnings("unchecked")
        ListView<TodoItem> listView = lookup("#todoList").queryAs(ListView.class);

        assertThat(listView.getItems())
            .hasSize(1);

        assertThat(listView.getItems().get(0).getText())
            .isEqualTo("Task 1");
    }

    @Test
    void shouldDeleteTaskAfterConfirmation() {
        clickOn("#inputField");
        write("Task da eliminare");
        clickOn("#addButton");

        ListView<?> listView = lookup("#todoList").queryAs(ListView.class);
        interact(() -> listView.getSelectionModel().select(0));

        clickOn("#deleteButton");

        clickOn("OK");

        Label statsLabel = lookup("#statsLabel").queryAs(Label.class);

        assertThat(statsLabel)
                .hasText("Totale: 0 | Da fare: 0 | Completate: 0");
    }

    @Test
    void shouldNotDeleteTaskWithoutConfirmation() {

        clickOn("#inputField");
        write("Task da eliminare");
        clickOn("#addButton");

        @SuppressWarnings("unchecked")
        ListView<TodoItem> listView = lookup("#todoList").queryAs(ListView.class);

        interact(() -> listView.getSelectionModel().select(0));

        clickOn("#deleteButton");

        clickOn("Annulla");

        assertThat(listView.getItems())
                .hasSize(1);

        assertThat(listView.getItems().get(0).getText())
                .isEqualTo("Task da eliminare");
    }
}