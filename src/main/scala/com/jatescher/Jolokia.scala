package com.jatescher

import com.typesafe.sbt.SbtNativePackager._
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging.autoImport.bashScriptExtraDefines
import sbt._
import sbt.Keys._

object Jolokia extends AutoPlugin {

  object autoImport {
    val jolokiaAgent = taskKey[File]("Jolokia agent jar location")
    val jolokiaVersion = settingKey[String]("Jolokia version")
    val jolokiaPort = settingKey[String]("Jolokia port")
    val jolokiaHost = settingKey[String]("Jolokia host")
  }

  import autoImport._

  override def requires = JavaAppPackaging

  val jolokiaConfig = config("jolokia-jvm").hide

  override lazy val projectSettings = Seq(
    ivyConfigurations += jolokiaConfig,
    jolokiaAgent := findJolokiaAgent(update.value),
    jolokiaVersion := "1.3.2",
    jolokiaPort := "8778",
    jolokiaHost := "0.0.0.0",
    libraryDependencies += "org.jolokia" % "jolokia-jvm" % jolokiaVersion.value % jolokiaConfig,
    mappings in Universal ++= Seq(
      jolokiaAgent.value -> "jolokia/jolokia.jar"
    ),
    bashScriptExtraDefines += """addJava "-javaagent:${app_home}/../jolokia/jolokia.jar=port=${jolokiaPort},host=${jolokiaHost}""""
  )

  private[this] val jolokiaFilter: DependencyFilter = configurationFilter("jolokia-jvm") && artifactFilter(`type` = "jar")

  def findJolokiaAgent(report: UpdateReport) = report.matching(jolokiaFilter).head

}
