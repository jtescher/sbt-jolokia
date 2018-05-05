import sbt._
import sbt.Keys._
import sbt.ScriptedPlugin._

lazy val publishSettings: Seq[Def.Setting[_]] = Seq(
  publishMavenStyle := false,
  bintrayOrganization := Some("jtescher"),
  bintrayPackageLabels := Seq("sbt", "jolokia", "sbt-native-packager"),
  bintrayRepository := "sbt-plugin-releases",
  licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
)

// Test scripts
lazy val scriptedSettings: Seq[Def.Setting[_]] = ScriptedPlugin.scriptedSettings ++ Seq(
  scriptedLaunchOpts := { scriptedLaunchOpts.value ++ Seq("-Xmx1024M", "-Dplugin.version=" + version.value) },
  scriptedBufferLog := false
)

lazy val `sbt-jolokia` = (project in file("."))
  .settings(
    name := """sbt-jolokia""",
    organization := "com.jatescher",
    version := "1.1.2",
    sbtPlugin := true,
    crossSbtVersions := Seq("0.13.17", "1.0.4"),
    addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.34" % "provided")
  )
  .settings(publishSettings: _*)
  .settings(scriptedSettings: _*)

//workaround for https://github.com/sbt/sbt/issues/3749
scalaVersion in ThisBuild := {
  if ((sbtBinaryVersion in pluginCrossBuild).value.startsWith("0.")) "2.10.7" else "2.12.6"
}
