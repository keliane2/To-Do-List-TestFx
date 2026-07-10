import sbtassembly.AssemblyPlugin.autoImport.*
import sbtassembly.MergeStrategy

name := "todo-list-testfx"
version := "1.0"
scalaVersion := "3.3.7"

libraryDependencies ++= Seq(
  "org.openjfx" % "javafx-controls" % "21",
  "org.openjfx" % "javafx-fxml" % "21",

  "org.junit.jupiter" % "junit-jupiter-api" % "5.10.2" % Test,
  "org.junit.jupiter" % "junit-jupiter-engine" % "5.10.2" % Test,
  "com.github.sbt.junit" % "jupiter-interface" % "0.19.0" % Test,

  "org.testfx" % "testfx-junit5" % "4.0.18" % Test,
  "org.testfx" % "openjfx-monocle" % "21.0.2" % Test
)

Test / fork := true

Compile / run / fork := true

assembly / mainClass := Some("it.unibo.todolist.App")

assembly / assemblyJarName :=
  "todo-list-testfx-assembly-1.0.jar"

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "MANIFEST.MF") =>
    MergeStrategy.discard

  case PathList("META-INF", "INDEX.LIST") =>
    MergeStrategy.discard

  case PathList("META-INF", "versions", _*) =>
    MergeStrategy.first

  case "module-info.class" =>
    MergeStrategy.discard

  case path if path.startsWith("META-INF/substrate/config/") =>
    MergeStrategy.first

  case path if path.startsWith("META-INF/services/") =>
    MergeStrategy.concat

  case path if path.endsWith(".SF") ||
               path.endsWith(".DSA") ||
               path.endsWith(".RSA") =>
    MergeStrategy.discard

  case other =>
    val oldStrategy =
      (assembly / assemblyMergeStrategy).value

    oldStrategy(other)
}