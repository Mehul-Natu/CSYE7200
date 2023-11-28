ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.10"

lazy val root = (project in file("."))
  .settings(
    name := "movie-rating"
  )
Compile / doc / scalacOptions ++= Seq("-Vimplicits", "-deprecation", "-Ywarn-dead-code", "-Ywarn-value-discard", "-Ywarn-unused")

val scalaTestVersion = "3.2.3"

val sparkVersion = "3.2.1"

unmanagedBase := baseDirectory.value / "spark-csv/lib"


libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  "com.github.nscala-time" %% "nscala-time" % "2.24.0",
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion
)
