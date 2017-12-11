# SBT Jolokia

[![Build Status](https://travis-ci.org/jtescher/sbt-jolokia.svg?branch=master)](https://travis-ci.org/jtescher/sbt-jolokia)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/ffbf70f3e2304f2e8dc89a338a4929cc)](https://www.codacy.com/app/jatescher/sbt-jolokia)
[![Download](https://api.bintray.com/packages/jtescher/sbt-plugin-releases/sbt-jolokia/images/download.svg)](https://bintray.com/jtescher/sbt-plugin-releases/sbt-jolokia/_latestVersion)

Jolokia support for any artifacts built with sbt-native-packager.

## Useful Links

The following is a list of useful links related to Jolokia

* [Jolokia website](https://jolokia.org)
* [Jolokia Github repository](https://github.com/rhuss/jolokia)
* [Jolokia JVM Maven repository](http://mvnrepository.com/artifact/org.jolokia/jolokia-jvm)


## Prerequisites

The plugin assumes that sbt-native-packager has been included in your SBT build configuration. This can be done by
adding the plugin following instructions at
[scala-sbt.org/sbt-native-packager](http://www.scala-sbt.org/sbt-native-packager) or by adding another plugin that
includes and initializes it (e.g. the SBT plugin for Play Framework 2.4.x).


## Installation for sbt-native-packager > 1.0.0 and Play Framework > 2.4.0

Add the following to your `project/plugins.sbt` file:

```scala
addSbtPlugin("com.jatescher" % "sbt-jolokia" % "1.1.1")
```

To use the Jolokia settings in your project, add the `Jolokia` auto-plugin to your project.

```scala
enablePlugins(Jolokia)
```

## Configuration

To use a specific `jolokia-jvm` version, add the following to your `build.sbt` file:

```scala
jolokiaVersion := "1.3.7"
```

## Testing

This project uses `sbt-scripted` to run its tests. To test this project run:

```shell
$ sbt scripted
```
