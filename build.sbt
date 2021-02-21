import sbt.Keys.libraryDependencies

name := "sisp"

version := "0.6"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "io.github.marchliu" % "jaskell-core_2.13" % "0.6.4",
  "org.scalactic" %% "scalactic" % "3.1.1",
  "org.scalatest" %% "scalatest" % "3.1.1" % "test"
)
