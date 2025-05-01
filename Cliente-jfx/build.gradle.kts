plugins {
    application
    id("org.openjfx.javafxplugin") version "0.0.13"
}

repositories {
    mavenCentral()
}

javafx {
    version = "21"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("MainApp")
}

dependencies{
    implementation("org.json:json:20210307")
    implementation("org.glassfish.tyrus.bundles:tyrus-standalone-client:2.1.1")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.15.2")
}