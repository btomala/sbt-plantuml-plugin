import bintray.Keys._

name := "sbt-plantuml-plugin"

organization := "com.banno"

version := "1.2.0-SNAPSHOT"

scalaVersion := "2.10.5"

sbtPlugin := true

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")

resolvers ++= Seq(
  "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases"
)

publishTo <<= (version) { v =>
  if (v.trim.endsWith("SNAPSHOT")) {
    Some("Banno Snapshots Repo" at "http://nexus.banno.com/nexus/content/repositories/snapshots")
  } else {
    Some("Banno Releases Repo" at "http://nexus.banno.com/nexus/content/repositories/releases")
  }
}

credentials += Credentials(Path.userHome / ".ivy2" / ".banno_credentials")

// bintray
bintrayPublishSettings

bintrayOrganization in bintray := Some("banno")

repository in bintray := "oss"

licenses ++= Seq(("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html")))

// specs2 support
libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.4",
  "org.specs2" %% "specs2-core" % "3.6.1" % "test"
)

// sbt scripted
ScriptedPlugin.scriptedSettings

scriptedBufferLog := false

scriptedLaunchOpts <+= version { "-Dplugin.version=" + _ }

// Don't publish sources or scaladoc jars.
publishArtifact in (Compile, packageSrc) := false

publishArtifact in (Compile, packageDoc) := false
