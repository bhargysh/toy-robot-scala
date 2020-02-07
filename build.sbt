name := "toy-robot-scala"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect"  % "2.0.0",
  "org.specs2"    %% "specs2-core"  % "4.8.3" % Test,
)
