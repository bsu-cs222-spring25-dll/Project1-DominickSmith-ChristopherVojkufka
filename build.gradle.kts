plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.jayway.jsonpath:json-path:2.8.0")
    testImplementation("net.minidev:json-smart:2.4.10")
    testImplementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("com.jayway.jsonpath:json-path:2.8.0")
    implementation("net.minidev:json-smart:2.4.10")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation(platform("org.junit:junit-bom:5.9.1"))
    implementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}