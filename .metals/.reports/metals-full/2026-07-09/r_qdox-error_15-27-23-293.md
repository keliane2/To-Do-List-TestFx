error id: file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/scala/it/unibo/todolist/TodoListTest.java
file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/scala/it/unibo/todolist/TodoListTest.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[155,5]

error in qdox parser
file content:
```java
offset: 4003
uri: file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/scala/it/unibo/todolist/TodoListTest.java
text:
```scala
package it.unibo.todolist;

import it.unibo.todolist.controller.TodoController;
import it.unibo.todolist.model.TodoItem;
import it.unibo.todolist.model.TodoStorage;
import it.unibo.todolist.view.TodoView;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.assertj.core.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

public class TodoListTest extends ApplicationTest {


    @Override
    public void start(Stage stage) {
        new TodoStorage().clear();

        TodoView view = new TodoView();
        new TodoController(view);

        Scene scene = new Scene(view.getRoot(), 650, 550);

        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    void shouldAddTask() {
        clickOn("#inputField");
        write("Studiare Paradigmi");
        clickOn("#addButton");

        @SuppressWarnings("unchecked")
        ListView<TodoItem> listView = lookup("#todoList").queryAs(ListView.class);

        assertThat(listView.getItems())
            .hasSize(1);

        assertThat(listView.getItems().get(0).getText())
            .isEqualTo("Studiare Paradigmi");
    }

    @Test
    void shouldShowErrorWhenTaskIsEmpty() {
        clickOn("#addButton");

        Label messageLabel = lookup("#messageLabel").queryAs(Label.class);

        assertThat(messageLabel)
                .hasText("Errore: inserire una attività.");
    }

    @Test
    void shouldUpdateStatsAfterAddingTask() {
        clickOn("#inputField");
        write("Task 1");
        clickOn("#addButton");

        Label statsLabel = lookup("#statsLabel").queryAs(Label.class);

        assertThat(statsLabel)
                .hasText("Totale: 1 | Da fare: 1 | Completate: 0");
    }

    @Test
    void shouldCompleteTaskUsingCheckbox() {
        clickOn("#inputField");
        write("Task completabile");
        clickOn("#addButton");

        clickOn(".check-box");

        Label statsLabel = lookup("#statsLabel").queryAs(Label.class);

        assertThat(statsLabel)
                .hasText("Totale: 1 | Da fare: 0 | Completate: 1");
    }

    @Test
    void shouldFilterCompletedTasks() {
        clickOn("#inputField");
        write("Task 1");
        clickOn("#addButton");

        clickOn("#inputField");
        write("Task 2");
        clickOn("#addButton");

        clickOn(".check-box");

        clickOn("#completedFilterButton");

        @SuppressWarnings("unchecked")
        ListView<TodoItem> listView = lookup("#todoList").queryAs(ListView.class);

        assertThat(listView.getItems())
            .hasSize(1);

        assertThat(listView.getItems().get(0).getText())
            .isEqualTo("Task 2");
    }

    @Test
    void shouldFilterUncompletedTasks() {
        clickOn("#inputField");
        write("Task 1");
        clickOn("#addButton");

        clickOn("#inputField");
        write("Task 2");
        clickOn("#addButton");

        clickOn(".check-box");

        clickOn("#todoFilterButton");

        @SuppressWarnings("unchecked")
        ListView<TodoItem> listView = lookup("#todoList").queryAs(ListView.class);

        assertThat(listView.getItems())
            .hasSize(1);

        assertThat(listView.getItems().get(0).getText())
            .isEqualTo("Task 1");
    }

    @Test
    void shouldDeleteTaskAfterConfirmation() {
        clickOn("#inputField");
        write("Task da eliminare");
        clickOn("#addButton");

        ListView<?> listView = lookup("#todoList").queryAs(ListView.class);
        interact(() -> listView.getSelectionModel().select(0));

        clickOn("#deleteButton");

        clickOn("OK");

        Label statsLabel = lookup("#statsLabel").queryAs(Label.class);

        assertThat(statsLabel)
                .hasText("Totale: 0 | Da fare: 0 | Completate: 0");
    }y

    @@@Test
    void shouldNotDeleteTaskWithoutConfirmation() {

        clickOn("#inputField");
        write("Task da eliminare");
        clickOn("#addButton");

        @SuppressWarnings("unchecked")
        ListView<TodoItem> listView = lookup("#todoList").queryAs(ListView.class);

        interact(() -> listView.getSelectionModel().select(0));

        clickOn("#deleteButton");

        clickOn("Annulla");

        assertThat(listView.getItems())
                .hasSize(1);

        assertThat(listView.getItems().get(0).getText())
                .isEqualTo("Task da eliminare");
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

QDox parse error in file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/scala/it/unibo/todolist/TodoListTest.java