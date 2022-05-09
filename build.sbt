organization := "dev.code-n-roll.gatling"
name := "jdbc-gatling"
scalaVersion := "2.13.8"
libraryDependencies ++= Seq(
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.7.6",
  "io.gatling"            % "gatling-test-framework"    % "3.7.6",
  "org.scalikejdbc"       %% "scalikejdbc"              % "4.0.0",
  "com.h2database"        % "h2"                        % "2.1.212",
  "ch.qos.logback"        % "logback-classic"           % "1.2.11",
  "mysql"                 % "mysql-connector-java"      % "8.0.29"  % "test",
  "org.postgresql"        % "postgresql"                % "42.3.4"  % "test",
  "org.scalatest"         %% "scalatest"                % "3.2.12"   % "test",
  "org.testcontainers"    % "postgresql"                % "1.17.1"  % "test",
  "org.testcontainers"    % "mysql"                     % "1.17.1"  % "test"
)
enablePlugins(GatlingPlugin)

parallelExecution in Test := false

//everything below this line is related to the project release
homepage := Some(url("https://github.com/rbraeunlich/gatling-jdbc"))
scmInfo := Some(ScmInfo(url("https://github.com/rbraeunlich/gatling-jdbc"), "git@github.com:rbraeunlich/gatling-jdbc.git"))
developers := List(Developer("rbraeunlich",
  "Ronny Bräunlich",
  "r.braeunlich@gmail.com",
  url("https://github.com/rbraeunlich")))
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

// Add sonatype repository settings
publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)

sonatypeProfileName := "r.braeunlich"
pomIncludeRepository := { _ => false }
publishArtifact in Test := false
publishMavenStyle := true
publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)
releasePublishArtifactsAction := PgpKeys.publishSigned.value
releaseProcess += releaseStepCommand("sonatypeReleaseAll")
