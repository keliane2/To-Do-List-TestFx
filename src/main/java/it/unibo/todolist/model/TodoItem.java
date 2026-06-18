package it.unibo.todolist.model;

public class TodoItem {

    private final String text;
    private boolean completed;

    public TodoItem(String text) {
        this(text, false);
    }

    public TodoItem(String text, boolean completed) {
        this.text = text;
        this.completed = completed;
    }

    public String getText() {
        return text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void toggleCompleted() {
        completed = !completed;
    }

    @Override
    public String toString() {
        return completed ? "[X] " + text : "[ ] " + text;
    }
}