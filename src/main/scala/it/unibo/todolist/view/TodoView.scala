package it.unibo.todolist.view

import it.unibo.todolist.model.TodoItem
import javafx.geometry.Insets
import javafx.scene.control.*
import javafx.scene.layout.*

class TodoView {

  val titleLabel = new Label("Todo List")
  val subtitleLabel = new Label("Organizza le tue attività quotidiane")
  val statsLabel = new Label("Totale: 0 | Da fare: 0 | Completate: 0")
  val messageLabel = new Label()

  val inputField = new TextField()
  val addButton = new Button("Aggiungi")
  val deleteButton = new Button("Elimina")

  val allFilterButton = new Button("Tutte")
  val todoFilterButton = new Button("Da fare")
  val completedFilterButton = new Button("Completate")

  val listView = new ListView[TodoItem]()

  private val inputBox = new HBox(10, inputField, addButton)
  private val actionBox = new HBox(10, deleteButton)
  private val filterBox = new HBox(10, allFilterButton, todoFilterButton, completedFilterButton)

  private val card = new VBox(15)

  val root = new StackPane(card)

  initialize()

  private def initialize(): Unit = {
    titleLabel.setId("titleLabel")
    subtitleLabel.setId("subtitleLabel")
    statsLabel.setId("statsLabel")
    messageLabel.setId("messageLabel")

    inputField.setPromptText("Inserisci una nuova attività")
    inputField.setId("inputField")

    addButton.setId("addButton")
    deleteButton.setId("deleteButton")

    allFilterButton.setId("allFilterButton")
    todoFilterButton.setId("todoFilterButton")
    completedFilterButton.setId("completedFilterButton")

    listView.setId("todoList")

    card.setId("mainCard")
    card.setPadding(new Insets(30))

    card.getChildren.addAll(
      titleLabel,
      subtitleLabel,
      inputBox,
      statsLabel,
      listView,
      actionBox,
      filterBox,
      messageLabel
    )

    root.setPadding(new Insets(30))
    root.setId("rootPane")
  }

  def getRoot = root
  def getListView = listView
  def getInputField = inputField
  def getAddButton = addButton
  def getDeleteButton = deleteButton
  def getAllFilterButton = allFilterButton
  def getTodoFilterButton = todoFilterButton
  def getCompletedFilterButton = completedFilterButton
  def getMessageLabel = messageLabel
  def getStatsLabel = statsLabel
}

object TodoView {
  def apply(): TodoView = new TodoView()
  
}