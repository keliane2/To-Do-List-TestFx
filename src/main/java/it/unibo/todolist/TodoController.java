package it.unibo.todolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

public class TodoController {

    private final ObservableList<TodoItem> items = FXCollections.observableArrayList();

    private final TextField inputField = new TextField();
    private final Button addButton = new Button("Aggiungi");
    private final Button deleteButton = new Button("Elimina");
    private final Button completeButton = new Button("Completa");
    private final Label messageLabel = new Label();
    private final ListView<TodoItem> listView = new ListView<>(items);

    public TextField getInputField() {
        inputField.setId("inputField");
        inputField.setPromptText("Inserisci una nuova attività");
        return inputField;
    }

    public Button getAddButton() {
        addButton.setId("addButton");
        addButton.setOnAction(e -> addItem());
        return addButton;
    }

    public Button getDeleteButton() {
        deleteButton.setId("deleteButton");
        deleteButton.setOnAction(e -> deleteSelectedItem());
        return deleteButton;
    }

    public Button getCompleteButton() {
        completeButton.setId("completeButton");
        completeButton.setOnAction(e -> completeSelectedItem());
        return completeButton;
    }

    public Label getMessageLabel() {
        messageLabel.setId("messageLabel");
        return messageLabel;
    }

    public ListView<TodoItem> getListView() {
        listView.setId("todoList");
        return listView;
    }

    private void addItem() {
        String text = inputField.getText().trim();

        if (text.isEmpty()) {
            messageLabel.setText("Errore: inserire una attività.");
            return;
        }

        items.add(new TodoItem(text));
        inputField.clear();
        messageLabel.setText("Attività aggiunta.");
    }

    private void deleteSelectedItem() {
        TodoItem selected = listView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Errore: selezionare una attività da eliminare.");
            return;
        }

        items.remove(selected);
        messageLabel.setText("Attività eliminata.");
    }

    private void completeSelectedItem() {
        TodoItem selected = listView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Errore: selezionare una attività da completare.");
            return;
        }

        selected.setCompleted(true);
        listView.refresh();
        messageLabel.setText("Attività completata.");
    }
}