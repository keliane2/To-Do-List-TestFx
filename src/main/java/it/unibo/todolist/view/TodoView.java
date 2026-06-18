package it.unibo.todolist.view;

import it.unibo.todolist.model.TodoItem;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class TodoView {

    private final StackPane root;

    private final Label titleLabel;
    private final Label subtitleLabel;
    private final Label statsLabel;
    private final Label messageLabel;

    private final TextField inputField;

    private final Button addButton;
    private final Button deleteButton;

    private final Button allFilterButton;
    private final Button todoFilterButton;
    private final Button completedFilterButton;

    private final ListView<TodoItem> listView;

    public TodoView() {

        titleLabel = new Label("Todo List");
        titleLabel.setId("titleLabel");

        subtitleLabel = new Label("Organizza le tue attività quotidiane");
        subtitleLabel.setId("subtitleLabel");

        statsLabel = new Label("Totale: 0 | Da fare: 0 | Completate: 0");
        statsLabel.setId("statsLabel");

        messageLabel = new Label();
        messageLabel.setId("messageLabel");

        inputField = new TextField();
        inputField.setPromptText("Inserisci una nuova attività");
        inputField.setId("inputField");

        addButton = new Button("Aggiungi");
        addButton.setId("addButton");

        deleteButton = new Button("Elimina");
        deleteButton.setId("deleteButton");

        allFilterButton = new Button("Tutte");
        allFilterButton.setId("allFilterButton");

        todoFilterButton = new Button("Da fare");
        todoFilterButton.setId("todoFilterButton");

        completedFilterButton = new Button("Completate");
        completedFilterButton.setId("completedFilterButton");

        listView = new ListView<>();
        listView.setId("todoList");

        HBox inputBox = new HBox(10);
        inputBox.getChildren().addAll(
                inputField,
                addButton
        );

        HBox filterBox = new HBox(10);
        filterBox.getChildren().addAll(
                allFilterButton,
                todoFilterButton,
                completedFilterButton
        );

        HBox actionBox = new HBox(10);
        actionBox.getChildren().add(deleteButton);

        VBox card = new VBox(15);
        card.setId("mainCard");

        card.getChildren().addAll(
                titleLabel,
                subtitleLabel,
                inputBox,
                statsLabel,
                listView,
                actionBox,
                filterBox,
                messageLabel
        );

        card.setPadding(new Insets(30));

        root = new StackPane(card);
        root.setPadding(new Insets(30));
        root.setId("rootPane");
    }

    public Pane getRoot() {
        return root;
    }

    public TextField getInputField() {
        return inputField;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getAllFilterButton() {
        return allFilterButton;
    }

    public Button getTodoFilterButton() {
        return todoFilterButton;
    }

    public Button getCompletedFilterButton() {
        return completedFilterButton;
    }

    public ListView<TodoItem> getListView() {
        return listView;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    public Label getStatsLabel() {
        return statsLabel;
    }
}