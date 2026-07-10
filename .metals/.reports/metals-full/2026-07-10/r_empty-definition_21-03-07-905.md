error id: file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/scala/it/unibo/todolist/TodoListGUITest.scala:clickOn.
file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/scala/it/unibo/todolist/TodoListGUITest.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -org/junit/jupiter/api/Assertions.clickOn.
	 -org/junit/jupiter/api/Assertions.clickOn#
	 -org/junit/jupiter/api/Assertions.clickOn().
	 -clickOn.
	 -clickOn#
	 -clickOn().
	 -scala/Predef.clickOn.
	 -scala/Predef.clickOn#
	 -scala/Predef.clickOn().
offset: 3890
uri: file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/scala/it/unibo/todolist/TodoListGUITest.scala
text:
```scala
package it.unibo.todolist

import it.unibo.todolist.controller.TodoController
import it.unibo.todolist.model.{TodoItem, TodoStorage}
import it.unibo.todolist.view.TodoView

import javafx.scene.Scene
import javafx.scene.control.{Label, ListView}
import javafx.stage.Stage

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.testfx.framework.junit5.ApplicationTest
import org.testfx.assertions.api.Assertions.assertThat

class TodoListGUITest extends ApplicationTest {

  override def start(stage: Stage): Unit = {
    new TodoStorage().clear()

    val view = TodoView()
    new TodoController(view)

    val scene = Scene(view.root, 650, 550)

    stage.setScene(scene)
    stage.show()
    stage.toFront()
  }

  @Test
  def shouldAddTask(): Unit = {
    clickOn("#inputField")
    write("Studiare Paradigmi")
    clickOn("#addButton")

    val listView = lookup("#todoList").queryAs(classOf[ListView[TodoItem]])

    assertEquals(1, listView.getItems.size)
    assertEquals("Studiare Paradigmi", listView.getItems.get(0).text)
  }

  @Test
  def shouldShowErrorWhenTaskIsEmpty(): Unit = {
    clickOn("#addButton")

    val messageLabel = lookup("#messageLabel").queryAs(classOf[Label])

    assertThat(messageLabel)
      .hasText("Errore: inserire una attività.")
  }

  @Test
  def shouldUpdateStatsAfterAddingTask(): Unit = {
    clickOn("#inputField")
    write("Task 1")
    clickOn("#addButton")

    val statsLabel = lookup("#statsLabel").queryAs(classOf[Label])

    assertThat(statsLabel)
      .hasText("Totale: 1 | Da fare: 1 | Completate: 0")
  }

  @Test
  def shouldCompleteTaskUsingCheckbox(): Unit = {
    clickOn("#inputField")
    write("Task completabile")
    clickOn("#addButton")

    clickOn(".check-box")

    val statsLabel = lookup("#statsLabel").queryAs(classOf[Label])

    assertThat(statsLabel)
      .hasText("Totale: 1 | Da fare: 0 | Completate: 1")
  }

  @Test
  def shouldFilterCompletedTasks(): Unit = {
    clickOn("#inputField")
    write("Task 1")
    clickOn("#addButton")

    clickOn("#inputField")
    write("Task 2")
    clickOn("#addButton")

    clickOn(".check-box")
    clickOn("#completedFilterButton")

    val listView = lookup("#todoList").queryAs(classOf[ListView[TodoItem]])

    assertEquals(1, listView.getItems.size)
    assertEquals("Task 2", listView.getItems.get(0).text)
  }

  @Test
  def shouldFilterUncompletedTasks(): Unit = {
    clickOn("#inputField")
    write("Task 1")
    clickOn("#addButton")

    clickOn("#inputField")
    write("Task 2")
    clickOn("#addButton")

    clickOn(".check-box")
    clickOn("#todoFilterButton")

    val listView = lookup("#todoList").queryAs(classOf[ListView[TodoItem]])

    assertEquals(1, listView.getItems.size)
    assertEquals("Task 1", listView.getItems.get(0).text)
  }

  @Test
  def shouldDeleteTaskAfterConfirmation(): Unit = {
    clickOn("#inputField")
    write("Task da eliminare")
    clickOn("#addButton")

    val listView = lookup("#todoList").queryAs(classOf[ListView[TodoItem]])

    interact(new Runnable {
      override def run(): Unit =
        listView.getSelectionModel.select(0)
    })

    clickOn("#deleteButton")
    clickOn("OK")

    val statsLabel = lookup("#statsLabel").queryAs(classOf[Label])

    assertThat(statsLabel)
      .hasText("Totale: 0 | Da fare: 0 | Completate: 0")
  }

  @Test
  def shouldNotDeleteTaskWithoutConfirmation(): Unit = {
    clickOn("#inputField")
    write("Task da eliminare")
    clickOn("#addButton")

    val listView = lookup("#todoList").queryAs(classOf[ListView[TodoItem]])

    interact(new Runnable {
      override def run(): Unit =
        listView.getSelectionModel.select(0)
    })

    clickOn("#deleteButton")
    @@press(KeyCode.ESCAPE)
release(KeyCode.ESCAPE)

    assertEquals(1, listView.getItems.size)
    assertEquals("Task da eliminare", listView.getItems.get(0).text)
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 