error id: file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/java/it/unibo/todolist/TodoListTest.java
file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/java/it/unibo/todolist/TodoListTest.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[155,5]

error in qdox parser
file content:
```java
offset: 4003
uri: file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/java/it/unibo/todolist/TodoListTest.java
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
	scala.meta.internal.metals.JavadocIndexer$.foreach(JavadocIndexer.scala:185)
	scala.meta.internal.metals.Docstrings.indexSymbolDefinition(Docstrings.scala:174)
	scala.meta.internal.metals.Docstrings.indexSymbol(Docstrings.scala:141)
	scala.meta.internal.metals.Docstrings.documentation(Docstrings.scala:49)
	scala.meta.internal.metals.MetalsSymbolSearch.documentation(MetalsSymbolSearch.scala:47)
	scala.meta.internal.pc.JavaMetalsGlobal.documentation(JavaMetalsGlobal.scala:187)
	scala.meta.internal.pc.JavaHoverProvider.$anonfun$hoverOffset$1(JavaHoverProvider.scala:67)
	scala.Option.map(Option.scala:242)
	scala.meta.internal.pc.JavaHoverProvider.hoverOffset(JavaHoverProvider.scala:57)
	scala.meta.internal.pc.JavaHoverProvider.hover(JavaHoverProvider.scala:37)
	scala.meta.internal.pc.JavaPresentationCompiler.hover(JavaPresentationCompiler.scala:80)
	scala.meta.internal.metals.Compilers.$anonfun$hover$1(Compilers.scala:1134)
	scala.meta.internal.metals.Compilers.$anonfun$withPCAndAdjustLsp$12(Compilers.scala:1628)
	scala.Option.map(Option.scala:242)
	scala.meta.internal.metals.Compilers.$anonfun$withPCAndAdjustLsp$10(Compilers.scala:1628)
	scala.Option.flatMap(Option.scala:283)
	scala.meta.internal.metals.Compilers.withPCAndAdjustLsp(Compilers.scala:1610)
	scala.meta.internal.metals.Compilers.hover(Compilers.scala:1129)
	scala.meta.internal.metals.MetalsLspService.$anonfun$hover$1(MetalsLspService.scala:985)
	scala.meta.internal.metals.CancelTokens$.future(CancelTokens.scala:38)
	scala.meta.internal.metals.MetalsLspService.hover(MetalsLspService.scala:983)
	scala.meta.internal.metals.WorkspaceLspService.hover(WorkspaceLspService.scala:524)
	scala.meta.metals.lsp.DelegatingScalaService.hover(DelegatingScalaService.scala:78)
	jdk.internal.reflect.GeneratedMethodAccessor4.invoke(Unknown Source)
	java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	java.base/java.lang.reflect.Method.invoke(Method.java:568)
	org.eclipse.lsp4j.jsonrpc.services.GenericEndpoint.lambda$recursiveFindRpcMethods$0(GenericEndpoint.java:65)
	org.eclipse.lsp4j.jsonrpc.services.GenericEndpoint.request(GenericEndpoint.java:128)
	org.eclipse.lsp4j.jsonrpc.RemoteEndpoint.handleRequest(RemoteEndpoint.java:265)
	org.eclipse.lsp4j.jsonrpc.RemoteEndpoint.consume(RemoteEndpoint.java:195)
	org.eclipse.lsp4j.jsonrpc.json.StreamMessageProducer.handleMessage(StreamMessageProducer.java:189)
	org.eclipse.lsp4j.jsonrpc.json.StreamMessageProducer.listen(StreamMessageProducer.java:97)
	org.eclipse.lsp4j.jsonrpc.json.ConcurrentMessageProcessor.run(ConcurrentMessageProcessor.java:97)
	java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
	java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	java.base/java.lang.Thread.run(Thread.java:833)
```
#### Short summary: 

QDox parse error in file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/test/java/it/unibo/todolist/TodoListTest.java