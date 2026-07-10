error id: file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/main/java/it/unibo/todolist/view/TodoView.java
file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/main/java/it/unibo/todolist/view/TodoView.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[3,8]

error in qdox parser
file content:
```java
offset: 41
uri: file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/main/java/it/unibo/todolist/view/TodoView.java
text:
```scala
package it.unibo.todolist.view;

import .@@.it.unibo.todolist.model.TodoItem;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class TodoView {

    private final StackPane root;

    private final Label titleLabel;
    private final Label subtitleLabel;
    private final Label statsLabel;
    private final Label messageLabel;

    private final TextField inputField;

    private final Button addButton;
    private final Button deleteButton;

    private final Button allFilterButton;
    private final Button todoFilterButton;
    private final Button completedFilterButton;

    private final ListView<TodoItem> listView;

    public TodoView() {

        titleLabel = new Label("Todo List");
        titleLabel.setId("titleLabel");

        subtitleLabel = new Label("Organizza le tue attività quotidiane");
        subtitleLabel.setId("subtitleLabel");

        statsLabel = new Label("Totale: 0 | Da fare: 0 | Completate: 0");
        statsLabel.setId("statsLabel");

        messageLabel = new Label();
        messageLabel.setId("messageLabel");

        inputField = new TextField();
        inputField.setPromptText("Inserisci una nuova attività");
        inputField.setId("inputField");

        addButton = new Button("Aggiungi");
        addButton.setId("addButton");

        deleteButton = new Button("Elimina");
        deleteButton.setId("deleteButton");

        allFilterButton = new Button("Tutte");
        allFilterButton.setId("allFilterButton");

        todoFilterButton = new Button("Da fare");
        todoFilterButton.setId("todoFilterButton");

        completedFilterButton = new Button("Completate");
        completedFilterButton.setId("completedFilterButton");

        listView = new ListView<>();
        listView.setId("todoList");

        HBox inputBox = new HBox(10);
        inputBox.getChildren().addAll(
                inputField,
                addButton
        );

        HBox filterBox = new HBox(10);
        filterBox.getChildren().addAll(
                allFilterButton,
                todoFilterButton,
                completedFilterButton
        );

        HBox actionBox = new HBox(10);
        actionBox.getChildren().add(deleteButton);

        VBox card = new VBox(15);
        card.setId("mainCard");

        card.getChildren().addAll(
                titleLabel,
                subtitleLabel,
                inputBox,
                statsLabel,
                listView,
                actionBox,
                filterBox,
                messageLabel
        );

        card.setPadding(new Insets(30));

        root = new StackPane(card);
        root.setPadding(new Insets(30));
        root.setId("rootPane");
    }

    public Pane getRoot() {
        return root;
    }

    public TextField getInputField() {
        return inputField;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getAllFilterButton() {
        return allFilterButton;
    }

    public Button getTodoFilterButton() {
        return todoFilterButton;
    }

    public Button getCompletedFilterButton() {
        return completedFilterButton;
    }

    public ListView<TodoItem> getListView() {
        return listView;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    public Label getStatsLabel() {
        return statsLabel;
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

QDox parse error in file:///D:/UNIBO/LM_1/S2/Paradigmi/Progetto/Automatic_GUI_Testing/src/main/java/it/unibo/todolist/view/TodoView.java