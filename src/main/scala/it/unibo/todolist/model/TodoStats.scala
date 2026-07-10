package it.unibo.todolist.model

case class TodoStats(total: Int, todo: Int, completed: Int) {
  def asText: String =
    s"Totale: $total | Da fare: $todo | Completate: $completed"
}