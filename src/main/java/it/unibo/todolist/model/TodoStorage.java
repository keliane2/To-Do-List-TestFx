package it.unibo.todolist.model;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TodoStorage {

    private static final Path FILE_PATH = Paths.get("tasks.csv");

    public List<TodoItem> load() {
        List<TodoItem> items = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return items;
        }

        try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 2);

                if (parts.length == 2) {
                    boolean completed = Boolean.parseBoolean(parts[0]);
                    String text = parts[1];
                    items.add(new TodoItem(text, completed));
                }
            }
        } catch (IOException e) {
            System.err.println("Errore nel caricamento: " + e.getMessage());
        }

        return items;
    }

    public void save(List<TodoItem> items) {
        try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH)) {
            for (TodoItem item : items) {
                writer.write(item.isCompleted() + ";" + item.getText());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Errore nel salvataggio: " + e.getMessage());
        }
    }

    public void clear() {
        save(List.of());
    }
}