import sbt.Keys.scalaVersion
import sbt.Keys.version

scalaVersion := "2.12.10"
scalacOptions ++= Seq(
  "-encoding",
  "utf8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlog-reflective-calls"
)

organization := "com.github.mliarakos"
name := "shocon-fallback"
version := "1.0.0-SNAPSHOT"

enablePlugins(ScalaJSPlugin, ShoconPlugin)

libraryDependencies ++= Seq(
  "org.akka-js"   %%% "shocon"    % "1.0.0",
  "org.scalatest" %%% "scalatest" % "3.1.4" % Test
)

compile in Compile := (compile in Compile).dependsOn(shoconConcat).value
