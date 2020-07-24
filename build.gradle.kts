import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4-M3"
    application
    id("kotlinx.benchmark") version "0.2.0-dev-8"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4-M3"
}
repositories {
    mavenCentral()
    maven ( url = "https://dl.bintray.com/kotlin/kotlinx" )
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}

group = "me.enterman"
version = "1.0-SNAPSHOT"

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}
benchmark {
    targets {
        register("main")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx","kotlinx.benchmark.runtime-jvm", "0.2.0-dev-8")
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
application {
    mainClassName = "MainKt"
}