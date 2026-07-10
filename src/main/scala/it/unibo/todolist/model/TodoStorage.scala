package it.unibo.todolist.model

import java.nio.file.{Files, Path, Paths}
import scala.jdk.CollectionConverters.*

class TodoStorage {

  private val filePath: Path =
    Paths.get("tasks.csv")

  def load(): List[TodoItem] =
    if Files.exists(filePath) then
      Files.readAllLines(filePath)
        .asScala
        .toList
        .flatMap(parseLine)
    else
      List.empty

  def save(items: List[TodoItem]): Unit = {
    val lines =
      items.map(item => s"${item.completed};${item.text}")

    Files.write(filePath, lines.asJava)
  }

  def clear(): Unit =
    save(List.empty)

  private def parseLine(line: String): Option[TodoItem] =
    line.split(";", 2).toList match {
      case completed :: text :: Nil =>
        Some(TodoItem(text, completed.toBoolean))

      case _ =>
        None
    }
}