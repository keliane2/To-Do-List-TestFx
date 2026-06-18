package it.unibo.todolist;

import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

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

    private final Button allFilterButton = new Button("Tutte");
    private final Button todoFilterButton = new Button("Da fare");
    private final Button completedFilterButton = new Button("Completate");

    private final Label messageLabel = new Label();
    private final Label statsLabel = new Label();

    private final ListView<TodoItem> listView = new ListView<>(visibleItems);

    public TodoController() {
        allItems.addAll(storage.load());
        configureComponents();
        updateVisibleItems();
        updateStats();
    }

    private void configureComponents() {
        inputField.setId("inputField");
        inputField.setPromptText("Inserisci una nuova attività");

        addButton.setId("addButton");
        deleteButton.setId("deleteButton");

        allFilterButton.setId("allFilterButton");
        todoFilterButton.setId("todoFilterButton");
        completedFilterButton.setId("completedFilterButton");

        messageLabel.setId("messageLabel");
        statsLabel.setId("statsLabel");
        listView.setId("todoList");

        addButton.setOnAction(e -> addItem());
        deleteButton.setOnAction(e -> deleteSelectedItem());

        allFilterButton.setOnAction(e -> setFilter(Filter.ALL));
        todoFilterButton.setOnAction(e -> setFilter(Filter.TODO));
        completedFilterButton.setOnAction(e -> setFilter(Filter.COMPLETED));

        listView.setCellFactory(param -> new ListCell<>() {
            private final CheckBox checkBox = new CheckBox();
            private final HBox container = new HBox(10, checkBox);

            {
                checkBox.setOnAction(e -> {
                    TodoItem item = getItem();

                    if (item != null) {
                        item.toggleCompleted();
                        saveAndRefresh();
                        messageLabel.setText("Stato attività aggiornato.");
                    }
                });
            }

            @Override
            protected void updateItem(TodoItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    checkBox.setText(item.getText());
                    checkBox.setSelected(item.isCompleted());

                    if (item.isCompleted()) {
                        checkBox.setStyle("-fx-text-fill: #78909c; -fx-strikethrough: true;");
                    } else {
                        checkBox.setStyle("-fx-text-fill: #263238;");
                    }

                    setGraphic(container);
                    setText(null);
                }
            }
        });
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

    public Label getMessageLabel() {
        return messageLabel;
    }

    public Label getStatsLabel() {
        return statsLabel;
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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma eliminazione");
        alert.setHeaderText("Eliminare questa attività?");
        alert.setContentText(selected.getText());

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                allItems.remove(selected);
                saveAndRefresh();
                messageLabel.setText("Attività eliminata.");
            } else {
                messageLabel.setText("Eliminazione annullata.");
            }
        });
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
        updateStats();
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

    private void updateStats() {
        long completed = allItems.stream()
                .filter(TodoItem::isCompleted)
                .count();

        long total = allItems.size();
        long todo = total - completed;

        statsLabel.setText(
                "Totale: " + total +
                " | Da fare: " + todo +
                " | Completate: " + completed
        );
    }
}