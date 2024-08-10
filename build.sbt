lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "play-scala-cqrs",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    scalaVersion := "3.3.1",
    libraryDependencies += guice,
    scalacOptions ++= Seq(
      "-feature",
      "-Werror"
    )
  )
