plugins {
    kotlin("jvm") version "2.0.20"
}

group = "nl.rwslinkman"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.20")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}