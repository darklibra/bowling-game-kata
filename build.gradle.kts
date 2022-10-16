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

    implementation("com.google.guava:guava:31.1-jre")

    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")
    testImplementation("org.mockito:mockito-junit-jupiter:4.8.0")
    testImplementation("org.junit.platform:junit-platform-launcher:1.9.1")
}

configure<JavaPluginExtension> {
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
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}
