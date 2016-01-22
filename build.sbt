sbtPlugin := true

organization := "com.jatescher"

name := """sbt-jolokia"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.6"

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.6")

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

// Test scripts
ScriptedPlugin.scriptedSettings
scriptedLaunchOpts := { scriptedLaunchOpts.value ++ Seq("-Xmx1024M", "-Dplugin.version=" + version.value) }
scriptedBufferLog := false
