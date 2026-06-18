package it.unibo.todolist;

import javafx.collections.*;
import javafx.scene.control.*;

public class TodoController {

    private enum Filter {
        ALL, TODO, COMPLETED
    }

    private final ObservableList<TodoItem> allItems = FXCollections.observableArrayList();
    private final ObservableList<TodoItem> visibleItems = FXCollections.observableArrayList();

    private final TodoStorage storage = new TodoStorage();

    private Filter currentFilter = Filter.ALL;

    private final TextField inputField = new TextField();
    private final Button addButton = new Button("Aggiungi");
    private final Button deleteButton = new Button("Elimina");
    private final Button toggleButton = new Button("Completa / Riapri");

    private final Button allFilterButton = new Button("Tutte");
    private final Button todoFilterButton = new Button("Da fare");
    private final Button completedFilterButton = new Button("Completate");

    private final Label messageLabel = new Label();
    private final ListView<TodoItem> listView = new ListView<>(visibleItems);

    public TodoController() {
        allItems.addAll(storage.load());
        updateVisibleItems();
        configureComponents();
    }

    private void configureComponents() {
        inputField.setId("inputField");
        inputField.setPromptText("Inserisci una nuova attività");

        addButton.setId("addButton");
        deleteButton.setId("deleteButton");
        toggleButton.setId("toggleButton");

        allFilterButton.setId("allFilterButton");
        todoFilterButton.setId("todoFilterButton");
        completedFilterButton.setId("completedFilterButton");

        messageLabel.setId("messageLabel");
        listView.setId("todoList");

        addButton.setOnAction(e -> addItem());
        deleteButton.setOnAction(e -> deleteSelectedItem());
        toggleButton.setOnAction(e -> toggleSelectedItem());

        allFilterButton.setOnAction(e -> setFilter(Filter.ALL));
        todoFilterButton.setOnAction(e -> setFilter(Filter.TODO));
        completedFilterButton.setOnAction(e -> setFilter(Filter.COMPLETED));
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

    public Button getToggleButton() {
        return toggleButton;
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

    public Label getMessageLabel() {
        return messageLabel;
    }

    public ListView<TodoItem> getListView() {
        return listView;
    }

    private void addItem() {
        String text = inputField.getText().trim();

        if (text.isEmpty()) {
            messageLabel.setText("Errore: inserire una attività.");
            return;
        }

        allItems.add(new TodoItem(text));
        inputField.clear();

        saveAndRefresh();
        messageLabel.setText("Attività aggiunta.");
    }

    private void deleteSelectedItem() {
        TodoItem selected = listView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Errore: selezionare una attività da eliminare.");
            return;
        }

        allItems.remove(selected);

        saveAndRefresh();
        messageLabel.setText("Attività eliminata.");
    }

    private void toggleSelectedItem() {
        TodoItem selected = listView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Errore: selezionare una attività.");
            return;
        }

        selected.toggleCompleted();

        saveAndRefresh();
        messageLabel.setText("Stato attività aggiornato.");
    }

    private void setFilter(Filter filter) {
        currentFilter = filter;
        updateVisibleItems();

        switch (filter) {
            case ALL -> messageLabel.setText("Filtro: tutte le attività.");
            case TODO -> messageLabel.setText("Filtro: attività da fare.");
            case COMPLETED -> messageLabel.setText("Filtro: attività completate.");
        }
    }

    private void saveAndRefresh() {
        storage.save(allItems);
        updateVisibleItems();
        listView.refresh();
    }

    private void updateVisibleItems() {
        visibleItems.clear();

        for (TodoItem item : allItems) {
            if (currentFilter == Filter.ALL) {
                visibleItems.add(item);
            } else if (currentFilter == Filter.TODO && !item.isCompleted()) {
                visibleItems.add(item);
            } else if (currentFilter == Filter.COMPLETED && item.isCompleted()) {
                visibleItems.add(item);
            }
        }
    }
}