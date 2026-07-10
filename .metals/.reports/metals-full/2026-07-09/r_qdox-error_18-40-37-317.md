error id: file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/java/it/unibo/todolist/TodoListServiceTest.java
file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/java/it/unibo/todolist/TodoListServiceTest.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[3,1]

error in qdox parser
file content:
```java
offset: 29
uri: file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/java/it/unibo/todolist/TodoListServiceTest.java
text:
```scala
package it.unibo.todolist;

i@@m
import org.junit.jupiter.api.Test;

import scala.collection.immutable.List;
import scala.jdk.javaapi.CollectionConverters;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListServiceTest {

    private final TodoService service = new TodoService();

    private List<TodoItem> scalaList(TodoItem... items) {
        return CollectionConverters.asScala(Arrays.asList(items)).toList();
    }

    @Test
    void shouldAddTask() {
        var result = service.add(scalaList(), "Studiare Paradigmi");

        assertTrue(result.isRight());

        var items = result.toOption().get();

        assertEquals(1, items.size());
        assertEquals("Studiare Paradigmi", items.head().text());
        assertFalse(items.head().completed());
    }

    @Test
    void shouldRejectEmptyTask() {
        var result = service.add(scalaList(), "   ");

        assertTrue(result.isLeft());
        assertEquals(
                "Errore: inserire una attività.",
                result.left().toOption().get()
        );
    }

    @Test
    void shouldDeleteTask() {
        TodoItem task1 = new TodoItem("Task 1", false);
        TodoItem task2 = new TodoItem("Task 2", false);

        var result = service.delete(scalaList(task1, task2), task1);

        assertEquals(1, result.size());
        assertEquals(task2, result.head());
    }

    @Test
    void shouldToggleTaskCompletion() {
        TodoItem task = new TodoItem("Task", false);

        var result = service.toggle(scalaList(task), task);

        assertEquals(1, result.size());
        assertTrue(result.head().completed());
    }

    @Test
    void shouldFilterCompletedTasks() {
        TodoItem task1 = new TodoItem("Task 1", true);
        TodoItem task2 = new TodoItem("Task 2", false);

        var result = service.filter(scalaList(task1, task2), TodoFilter.Completed());

        assertEquals(1, result.size());
        assertEquals(task1, result.head());
    }

    @Test
    void shouldFilterTodoTasks() {
        TodoItem task1 = new TodoItem("Task 1", true);
        TodoItem task2 = new TodoItem("Task 2", false);

        var result = service.filter(scalaList(task1, task2), TodoFilter.Todo());

        assertEquals(1, result.size());
        assertEquals(task2, result.head());
    }

    @Test
    void shouldReturnAllTasks() {
        TodoItem task1 = new TodoItem("Task 1", true);
        TodoItem task2 = new TodoItem("Task 2", false);

        var result = service.filter(scalaList(task1, task2), TodoFilter.All());

        assertEquals(2, result.size());
        assertEquals(task1, result.head());
    }

    @Test
    void shouldComputeStats() {
        TodoItem task1 = new TodoItem("Task 1", true);
        TodoItem task2 = new TodoItem("Task 2", false);
        TodoItem task3 = new TodoItem("Task 3", false);

        TodoStats stats = service.stats(scalaList(task1, task2, task3));

        assertEquals(3, stats.total());
        assertEquals(2, stats.todo());
        assertEquals(1, stats.completed());
        assertEquals(
                "Totale: 3 | Da fare: 2 | Completate: 1",
                stats.asText()
        );
    }
}
```

```



#### Error stacktrace:

```
com.thoughtworks.qdox.parser.impl.Parser.yyerror(Parser.java:2025)
	com.thoughtworks.qdox.parser.impl.Parser.yyparse(Parser.java:2147)
	com.thoughtworks.qdox.parser.impl.Parser.parse(Parser.java:2006)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:232)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:190)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:94)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:89)
	com.thoughtworks.qdox.library.SortedClassLibraryBuilder.addSource(SortedClassLibraryBuilder.java:162)
	com.thoughtworks.qdox.JavaProjectBuilder.addSource(JavaProjectBuilder.java:174)
	scala.meta.internal.mtags.JavaMtags.indexRoot(JavaMtags.scala:49)
	scala.meta.internal.metals.SemanticdbDefinition$.foreachWithReturnMtags(SemanticdbDefinition.scala:99)
	scala.meta.internal.metals.Indexer.indexSourceFile(Indexer.scala:560)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3(Indexer.scala:691)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3$adapted(Indexer.scala:688)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:630)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:628)
	scala.collection.AbstractIterator.foreach(Iterator.scala:1313)
	scala.meta.internal.metals.Indexer.reindexWorkspaceSources(Indexer.scala:688)
	scala.meta.internal.metals.MetalsLspService.$anonfun$onChange$2(MetalsLspService.scala:940)
	scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
	scala.concurrent.Future$.$anonfun$apply$1(Future.scala:691)
	scala.concurrent.impl.Promise$Transformation.run(Promise.scala:500)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	java.base/java.lang.Thread.run(Thread.java:833)
```
#### Short summary: 

QDox parse error in file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/java/it/unibo/todolist/TodoListServiceTest.java