package it.unibo.todolist.controller

import it.unibo.todolist.model.*
import it.unibo.todolist.view.TodoView

import javafx.collections.{FXCollections, ObservableList}
import javafx.scene.control.*
import javafx.scene.layout.HBox

import scala.jdk.CollectionConverters.*

class TodoController(view: TodoView) {

  private val storage = TodoStorage()
  private val service = TodoService()

  private var items: List[TodoItem] =
    storage.load()

  private var currentFilter: TodoFilter =
    TodoFilter.All

  private val visibleItems: ObservableList[TodoItem] =
    FXCollections.observableArrayList[TodoItem]()

  initializeView()
  registerEvents()
  refresh()

  private def initializeView(): Unit = {
    view.listView.setItems(visibleItems)

    view.listView.setCellFactory(_ =>
      new ListCell[TodoItem] {

        private val checkBox = CheckBox()
        private val container = HBox(10, checkBox)

        checkBox.setOnAction(_ =>
          Option(getItem).foreach { item =>
            items = service.toggle(items, item)
            saveAndRefresh()
            view.messageLabel.setText("Stato attività aggiornato.")
          }
        )

        override def updateItem(item: TodoItem, empty: Boolean): Unit = {
          super.updateItem(item, empty)

          if empty || item == null then {
            setGraphic(null)
            setText(null)
          } else {
            checkBox.setText(item.text)
            checkBox.setSelected(item.completed)

            val style =
              if item.completed then
                "-fx-strikethrough: true; -fx-text-fill: #78909c;"
              else
                "-fx-strikethrough: false; -fx-text-fill: #263238;"

            checkBox.setStyle(style)
            setGraphic(container)
            setText(null)
          }
        }
      }
    )
  }

  private def registerEvents(): Unit = {
    view.addButton.setOnAction(_ => addTask())
    view.deleteButton.setOnAction(_ => deleteTask())

    view.allFilterButton.setOnAction(_ =>
      setFilter(TodoFilter.All)
    )

    view.todoFilterButton.setOnAction(_ =>
      setFilter(TodoFilter.Todo)
    )

    view.completedFilterButton.setOnAction(_ =>
      setFilter(TodoFilter.Completed)
    )
  }

  private def addTask(): Unit = {
    val text = view.inputField.getText

    service.add(items, text) match {
      case Left(error) =>
        view.messageLabel.setText(error)

      case Right(updatedItems) =>
        items = updatedItems
        view.inputField.clear()
        saveAndRefresh()
        view.messageLabel.setText("Attività aggiunta.")
    }
  }

  private def deleteTask(): Unit =
    selectedTask match {
      case None =>
        view.messageLabel.setText("Errore: selezionare una attività.")

      case Some(task) =>
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.setTitle("Conferma eliminazione")
        alert.setHeaderText("Eliminare questa attività?")
        alert.setContentText(task.text)
        alert.getButtonTypes.setAll(ButtonType.OK, ButtonType.CANCEL)

        val response = alert.showAndWait()

        if response.isPresent && response.get() == ButtonType.OK then {
          items = service.delete(items, task)
          saveAndRefresh()
          view.messageLabel.setText("Attività eliminata.")
        } else {
          view.messageLabel.setText("Eliminazione annullata.")
        }
    }

  private def setFilter(filter: TodoFilter): Unit = {
    currentFilter = filter
    refresh()

    val message =
      filter match {
        case TodoFilter.All       => "Filtro: tutte"
        case TodoFilter.Todo      => "Filtro: da fare"
        case TodoFilter.Completed => "Filtro: completate"
      }

    view.messageLabel.setText(message)
  }

  private def selectedTask: Option[TodoItem] =
    Option(view.listView.getSelectionModel.getSelectedItem)

  private def refresh(): Unit = {
    val filteredItems =
      service.filter(items, currentFilter)

    visibleItems.setAll(filteredItems.asJava)

    view.statsLabel.setText(
      service.stats(items).asText
    )

    view.listView.refresh()
  }

  private def saveAndRefresh(): Unit = {
    storage.save(items)
    refresh()
  }
}