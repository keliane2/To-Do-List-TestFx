package it.unibo.todolist.model

class TodoService {

  def add(items: List[TodoItem], text: String): Either[String, List[TodoItem]] =
    text.trim match {
      case "" => Left("Errore: inserire una attività.")
      case value => Right(items :+ TodoItem(value))
    }

  def delete(items: List[TodoItem], item: TodoItem): List[TodoItem] =
    items.filterNot(_ == item)

  def toggle(items: List[TodoItem], item: TodoItem): List[TodoItem] =
    items.map(current =>
      if current == item then current.toggleCompleted
      else current
    )

  def filter(items: List[TodoItem], filter: TodoFilter): List[TodoItem] =
    filter match {
      case TodoFilter.All =>
        items

      case TodoFilter.Todo =>
        items.filterNot(_.completed)

      case TodoFilter.Completed =>
        items.filter(_.completed)
    }

  def stats(items: List[TodoItem]): TodoStats = {
    val completed = items.count(_.completed)
    TodoStats(
      total = items.size,
      todo = items.size - completed,
      completed = completed
    )
  }
}