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
    val jolokiaProtocol = settingKey[String]("Jolokia protocol")
  }

  import autoImport._

  override def requires = JavaAppPackaging

  val jolokiaConfig = config("jolokia-jvm").hide

  override lazy val projectSettings = Seq(
    ivyConfigurations += jolokiaConfig,
    jolokiaAgent := findJolokiaAgent(update.value),
    jolokiaVersion := "1.3.6",
    jolokiaPort := "8778",
    jolokiaHost := "0.0.0.0",
    jolokiaProtocol := "https",
    libraryDependencies += "org.jolokia" % "jolokia-jvm" % jolokiaVersion.value % jolokiaConfig classifier "agent",
    mappings in Universal ++= Seq(
      jolokiaAgent.value -> "jolokia/jolokia.jar"
    ),
    bashScriptExtraDefines += s"JOLOKIA_PORT=${jolokiaPort.value}",
    bashScriptExtraDefines += s"JOLOKIA_HOST=${jolokiaHost.value}",
    bashScriptExtraDefines += s"JOLOKIA_PROTOCOL=${jolokiaProtocol.value}",
    bashScriptExtraDefines += """addJava "-javaagent:${app_home}/../jolokia/jolokia.jar=port=${JOLOKIA_PORT},host=${JOLOKIA_HOST},protocol=${JOLOKIA_PROTOCOL}""""
  )

  private[this] val jolokiaFilter: DependencyFilter = configurationFilter("jolokia-jvm") && artifactFilter(`type` = "jar", classifier = "agent")

  def findJolokiaAgent(report: UpdateReport) = report.matching(jolokiaFilter).head

}
