package it.unibo.todolist

import it.unibo.todolist.controller.TodoController
import it.unibo.todolist.view.TodoView

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class TodoApp extends Application {

  override def start(stage: Stage): Unit = {
    val view = TodoView()
    TodoController(view)

    val scene = Scene(view.root, 650, 550)

    val css =
      getClass.getResource("/style.css")

    if css != null then
      scene.getStylesheets.add(css.toExternalForm)

    stage.setTitle("Todo List")
    stage.setScene(scene)
    stage.show()
  }
}

object App {
  def main(args: Array[String]): Unit =
    Application.launch(classOf[TodoApp], args*)
}