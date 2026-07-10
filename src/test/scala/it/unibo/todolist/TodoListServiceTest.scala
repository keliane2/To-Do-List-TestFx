package it.unibo.todolist

import it.unibo.todolist.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TodoListServiceTest {

  private val service = TodoService()

  @Test
  def shouldAddTask(): Unit = {
    val result = service.add(List.empty, "Studiare Paradigmi")

    assertTrue(result.isRight)

    val items = result.toOption.get

    assertEquals(1, items.size)
    assertEquals("Studiare Paradigmi", items.head.text)
    assertFalse(items.head.completed)
  }

  @Test
  def shouldRejectEmptyTask(): Unit = {
    val result = service.add(List.empty, "   ")

    assertTrue(result.isLeft)
    assertEquals("Errore: inserire una attività.", result.left.toOption.get)
  }

  @Test
  def shouldDeleteTask(): Unit = {
    val task1 = TodoItem("Task 1")
    val task2 = TodoItem("Task 2")

    val result = service.delete(List(task1, task2), task1)

    assertEquals(List(task2), result)
  }

  @Test
  def shouldToggleTaskCompletion(): Unit = {
    val task = TodoItem("Task")

    val result = service.toggle(List(task), task)

    assertEquals(1, result.size)
    assertTrue(result.head.completed)
  }

  @Test
  def shouldFilterCompletedTasks(): Unit = {
    val task1 = TodoItem("Task 1", completed = true)
    val task2 = TodoItem("Task 2")

    val result = service.filter(List(task1, task2), TodoFilter.Completed)

    assertEquals(List(task1), result)
  }

  @Test
  def shouldFilterTodoTasks(): Unit = {
    val task1 = TodoItem("Task 1", completed = true)
    val task2 = TodoItem("Task 2")

    val result = service.filter(List(task1, task2), TodoFilter.Todo)

    assertEquals(List(task2), result)
  }

  @Test
  def shouldReturnAllTasks(): Unit = {
    val task1 = TodoItem("Task 1", completed = true)
    val task2 = TodoItem("Task 2")

    val result = service.filter(List(task1, task2), TodoFilter.All)

    assertEquals(List(task1, task2), result)
  }

  @Test
  def shouldComputeStats(): Unit = {
    val task1 = TodoItem("Task 1", completed = true)
    val task2 = TodoItem("Task 2")
    val task3 = TodoItem("Task 3")

    val stats = service.stats(List(task1, task2, task3))

    assertEquals(3, stats.total)
    assertEquals(2, stats.todo)
    assertEquals(1, stats.completed)
    assertEquals("Totale: 3 | Da fare: 2 | Completate: 1", stats.asText)
  }
}