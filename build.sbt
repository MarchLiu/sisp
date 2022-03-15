import sbt.Keys.libraryDependencies

name := "sisp"

version := "0.6.1"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "io.github.marchliu" % "jaskell-core_2.13" % "0.7.1",
  "org.scalactic" %% "scalactic" % "3.1.1",
  "org.scalatest" %% "scalatest" % "3.1.1" % "test"
)
