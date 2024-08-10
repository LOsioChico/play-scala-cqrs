lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "play-scala-cqrs",
    sharedSettings,
    commands += run
  )
  .aggregate(postgresql)

lazy val postgresql = (project in file("postgresql"))
  .enablePlugins(PlayScala)
  .settings(
    name := "postgresql",
    sharedSettings,
    libraryDependencies += "org.postgresql" % "postgresql" % "42.7.3"
  )

def run = Command.single("run") {
  case (state, "postgresql") => "project postgresql" :: "run" :: state
  case (state, _) =>
    println("Usage: sbt selectRun <subproject>")
    state
 }

val sharedSettings = Seq(
  organization := "com.losiochico",
  version := "1.0",
  scalaVersion := "3.3.1",
  scalacOptions ++= Seq(
    "-feature",
    "-Werror"
  ),
  libraryDependencies += guice
)