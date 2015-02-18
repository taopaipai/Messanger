package utgenome

import sbt._ 
import Keys._

object ProjectBuild extends Build {
  
  lazy val root = Project(
    id ="sample-project",  // Set your project name here base =
    base = file("."), 
    settings =
       Defaults.defaultSettings 
       ++ Seq(PackageTask.packageDistTask)
       ++ PackageTask.distSettings 
       ++ Seq(
         name := "Messanger",
         version := "1.0",
         scalaVersion := "2.11.5",
         javacOptions ++= Seq("-source", "1.7", "-target", "1.7"),
         javaHome := Some(file("/Library/Java/JavaVirtualMachines/jdk1.7.0_75.jdk/Contents/Home")),
         organization := "org.jp.co.hoge",
         version := "1.0-SNAPSHOT", 
         scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked"), 
         parallelExecution := true, 
         crossPaths := false, 
         libraryDependencies ++= (Seq(
	     "org.codehaus.plexus" % "plexus-classworlds" % "2.4",
         "org.xerial" % "xerial-core" % "3.2.2",
         "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
         ) ++ Seq(
           "org.scala-lang.modules" % "scala-swing_2.11" % "1.0.1"
         ) ++ Seq(
           "ch.qos.logback" % "logback-classic" % "1.0.13"
         ) ++ Seq(
           "com.typesafe.akka" % "akka-slf4j_2.11" % "2.3.9",
           "com.typesafe.akka" % "akka-actor_2.11" % "2.3.9",
           "com.typesafe.akka" % "akka-remote_2.11" % "2.3.9"
         )),
         resolvers ++= Seq(
           "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"  
         )
      )
   )
}
