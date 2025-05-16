plugins {
    application
    id("org.openjfx.javafxplugin") version "0.0.13"
}

repositories {
    mavenCentral()
}

javafx {
    version = "23.0.1"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("MainApp")
}

val javafxVersion = "23.0.1"
val os = org.gradle.internal.os.OperatingSystem.current()

dependencies {
    implementation("org.openjfx:javafx-controls:$javafxVersion:${platform()}")
    implementation("org.openjfx:javafx-fxml:$javafxVersion:${platform()}")

    implementation("org.json:json:20210307")
    implementation("org.glassfish.tyrus.bundles:tyrus-standalone-client:2.1.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
}

fun platform(): String = when {
    os.isWindows -> "win"
    os.isMacOsX -> "mac"
    os.isLinux -> "linux"
    else -> throw GradleException("OS no soportado")
}


tasks.register<Jar>("fatJar") {
    archiveClassifier.set("all")

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes["Main-Class"] = "MainApp"
    }

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
    })
}
