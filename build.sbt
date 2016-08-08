sbtPlugin := true

organization := "com.jatescher"

name := """sbt-jolokia"""

version := "1.0.2"

scalaVersion := "2.10.6"

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.6")

publishMavenStyle := false

bintrayOrganization := Some("jtescher")

bintrayPackageLabels := Seq("sbt", "jolokia", "sbt-native-packager")

bintrayRepository := "sbt-plugin-releases"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

// Test scripts
ScriptedPlugin.scriptedSettings
scriptedLaunchOpts := { scriptedLaunchOpts.value ++ Seq("-Xmx1024M", "-Dplugin.version=" + version.value) }
scriptedBufferLog := false
