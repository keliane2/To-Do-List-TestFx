package it.unibo.todolist.controller;

import it.unibo.todolist.model.TodoItem;
import it.unibo.todolist.model.TodoStorage;
import it.unibo.todolist.view.TodoView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class TodoController {

    private enum Filter {
        ALL,
        TODO,
        COMPLETED
    }

    private final TodoView view;
    private final TodoStorage storage;

    private final ObservableList<TodoItem> allItems;
    private final ObservableList<TodoItem> visibleItems;

    private Filter currentFilter = Filter.ALL;

    public TodoController(TodoView view) {

        this.view = view;
        this.storage = new TodoStorage();

        this.allItems = FXCollections.observableArrayList();
        this.visibleItems = FXCollections.observableArrayList();

        allItems.addAll(storage.load());

        initializeView();
        registerEvents();

        updateVisibleItems();
        updateStats();
    }

    private void initializeView() {

        view.getListView().setItems(visibleItems);

        view.getListView().setCellFactory(param -> new ListCell<>() {

            private final CheckBox checkBox = new CheckBox();
            private final HBox container = new HBox(10, checkBox);

            {
                checkBox.setOnAction(e -> {

                    TodoItem item = getItem();

                    if (item != null) {

                        item.toggleCompleted();

                        saveAndRefresh();

                        view.getMessageLabel()
                                .setText("Stato attività aggiornato.");
                    }
                });
            }

            @Override
            protected void updateItem(TodoItem item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                    return;
                }

                checkBox.setText(item.getText());
                checkBox.setSelected(item.isCompleted());

                if (item.isCompleted()) {
                    checkBox.setStyle(
                            "-fx-strikethrough: true;" +
                            "-fx-text-fill: #78909c;"
                    );
                } else {
                    checkBox.setStyle(
                            "-fx-strikethrough: false;" +
                            "-fx-text-fill: #263238;"
                    );
                }

                setGraphic(container);
            }
        });
    }

    private void registerEvents() {

        view.getAddButton()
                .setOnAction(e -> addTask());

        view.getDeleteButton()
                .setOnAction(e -> deleteTask());

        view.getAllFilterButton()
                .setOnAction(e -> setFilter(Filter.ALL));

        view.getTodoFilterButton()
                .setOnAction(e -> setFilter(Filter.TODO));

        view.getCompletedFilterButton()
                .setOnAction(e -> setFilter(Filter.COMPLETED));
    }

    private void addTask() {

        String text =
                view.getInputField()
                        .getText()
                        .trim();

        if (text.isEmpty()) {

            view.getMessageLabel()
                    .setText("Errore: inserire una attività.");

            return;
        }

        allItems.add(new TodoItem(text));

        view.getInputField().clear();

        saveAndRefresh();

        view.getMessageLabel()
                .setText("Attività aggiunta.");
    }

    private void deleteTask() {

        TodoItem selected =
                view.getListView()
                        .getSelectionModel()
                        .getSelectedItem();

        if (selected == null) {

            view.getMessageLabel()
                    .setText("Errore: selezionare una attività.");

            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Conferma eliminazione");
        alert.setHeaderText("Eliminare questa attività?");
        alert.setContentText(selected.getText());

        alert.showAndWait()
                .ifPresent(response -> {

                    if (response == ButtonType.OK) {

                        allItems.remove(selected);

                        saveAndRefresh();

                        view.getMessageLabel()
                                .setText("Attività eliminata.");
                    }
                });
    }

    private void setFilter(Filter filter) {

        currentFilter = filter;

        updateVisibleItems();

        switch (filter) {

            case ALL ->
                    view.getMessageLabel()
                            .setText("Filtro: tutte");

            case TODO ->
                    view.getMessageLabel()
                            .setText("Filtro: da fare");

            case COMPLETED ->
                    view.getMessageLabel()
                            .setText("Filtro: completate");
        }
    }

    private void updateVisibleItems() {

        visibleItems.clear();

        for (TodoItem item : allItems) {

            switch (currentFilter) {

                case ALL ->
                        visibleItems.add(item);

                case TODO -> {
                    if (!item.isCompleted()) {
                        visibleItems.add(item);
                    }
                }

                case COMPLETED -> {
                    if (item.isCompleted()) {
                        visibleItems.add(item);
                    }
                }
            }
        }
    }

    private void updateStats() {

        long total = allItems.size();

        long completed =
                allItems.stream()
                        .filter(TodoItem::isCompleted)
                        .count();

        long todo = total - completed;

        view.getStatsLabel().setText(
                "Totale: " + total +
                " | Da fare: " + todo +
                " | Completate: " + completed
        );
    }

    private void saveAndRefresh() {

        storage.save(allItems);

        updateVisibleItems();

        updateStats();

        view.getListView().refresh();
    }
}