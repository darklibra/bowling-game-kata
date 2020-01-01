plugins {
    java
    kotlin("jvm") version "1.3.61"
}

group = "com.kry.kata.bowling"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.google.guava:guava:28.0-jre")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.1")
    testImplementation("org.mockito:mockito-junit-jupiter:3.2.4")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.1")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.11"
    }
}