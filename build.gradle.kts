plugins {
  id("com.diffplug.spotless") version "6.25.0"
  application

  kotlin("jvm") version "2.0.20"
}

repositories {
  mavenLocal()
  mavenCentral()

  maven("https://jitpack.io")
  maven("https://packages.jetbrains.team/maven/p/kds/kotlin-ds-maven")
}

dependencies {
  api("org.jetbrains.kotlin:kotlin-stdlib:2.0.20")

  implementation("org.jetbrains.kotlinx:kandy-lets-plot:0.7.0")
  implementation("org.jetbrains.kotlinx:kotlin-statistics-jvm:0.3.0")

  implementation("com.github.nwillc.ksvg:ksvg:master-SNAPSHOT")
}

group = "com.hopskipnfall"

description = "Kaillera-Lag-Simulator"

version = "0.12.0"

kotlin { jvmToolchain(17) }

sourceSets {
  main { kotlin.srcDir("src/main/java") }

  test { kotlin.srcDir("src/test/java") }
}

tasks.withType<Test> {
  useJUnitPlatform()
  useJUnit()

  systemProperty(
    "flogger.backend_factory",
    "org.emulinker.testing.TestLoggingBackendFactory#getInstance"
  )
}

// Formatting/linting.
spotless {
  kotlin {
    target("**/*.kt", "**/*.kts")
    targetExclude("build/", ".git/", ".idea/", ".mvn", "src/main/java-templates/")
    ktfmt().googleStyle()
  }

  yaml {
    target("**/*.yml", "**/*.yaml")
    targetExclude("build/", ".git/", ".idea/", ".mvn")
    jackson()
  }
}

application { mainClass.set("com.hopskipnfall.MainKt") }
