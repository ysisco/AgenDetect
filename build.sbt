name := """AgenDetect"""
organization := "com.despegar"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

val sparkVersion = "2.2.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.8.10"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.10"
dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.8.10"



libraryDependencies ++= Seq(
  "org.apache.spark"          %%  "spark-core"       % sparkVersion,
  "org.apache.spark"          %%  "spark-streaming"  % sparkVersion,
  "org.apache.spark"          %%  "spark-mllib"      % sparkVersion,
  "org.apache.kafka"           %  "kafka-clients"    % "0.11.0.0",
  "org.postgresql"             %  "postgresql"       % "42.1.1",
  "org.slf4j"                  %  "slf4j-nop"        % "1.6.4",
  "com.twitter"               %%  "chill"             % "0.9.0",
  "com.typesafe.play"         %%  "play-slick"       % "3.0.0",
  "com.softwaremill.common"   %%  "tagging"           % "2.1.0",
  "com.softwaremill.macwire"  %%  "macros"            % "2.3.0" % Provided,
  "org.apache.hadoop"          %  "hadoop-client"     % "2.8.2",
  "org.scalaj"                %%  "scalaj-http"       % "2.3.0",
  "org.json4s"                %%  "json4s-native"     % "3.4.2",
  "com.despegar.ds2"          %%  "kairos-commons"    % "0.1.9",
  "org.mapdb"                  %  "mapdb"             % "3.0.5"

)


resolvers ++= Seq(
  Resolver.mavenLocal,
  "Nexus AWS Repository" at "http://sem.nexus.despeaws.net/repository/maven-public/",
  "Nexus AWS SnapshotRepo" at "http://sem.nexus.despeaws.net/repository/maven-snapshots/",
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
)

