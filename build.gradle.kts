plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}
group = "edu.bsu.cs"
version = "1.0-SNAPSHOT"
repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.jayway.jsonpath:json-path:2.9.0")
    testImplementation("net.minidev:json-smart:2.4.10")
    testImplementation("ch.qos.logback:logback-classic:1.4.14")
    testImplementation("org.mockito:mockito-core:5.5.0")
    implementation(platform("org.junit:junit-bom:5.9.1"))
    implementation("org.junit.jupiter:junit-jupiter")
    implementation("com.jayway.jsonpath:json-path:2.9.0")
    implementation("net.minidev:json-smart:2.4.10")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("org.openjfx:javafx-controls:22")
    implementation("org.openjfx:javafx-fxml:22")
    implementation("org.slf4j:slf4j-nop:2.0.11")
}

    tasks.test {
        useJUnitPlatform()
    }

    javafx {
        version = "22"
        modules("javafx.controls", "javafx.fxml")
    }

    application {
        mainClass.set("edu.bsu.cs.GUI")
    }

    tasks.withType<JavaExec> {
        jvmArgs = listOf(
            "--module-path",
            System.getProperty("java.home") + "/lib",
            "--add-modules",
            "javafx.controls,javafx.fxml"
        )
}
