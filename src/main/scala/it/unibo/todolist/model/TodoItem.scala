package it.unibo.todolist.model

case class TodoItem(
    text: String,
    completed: Boolean = false
) {

  def getText: String =
    text

  def isCompleted: Boolean =
    completed

  def toggleCompleted: TodoItem =
    copy(completed = !completed)

}