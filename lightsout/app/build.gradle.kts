plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.api)
    testImplementation(libs.junit.engine)
    testImplementation(libs.junit.launcher)
    testImplementation(libs.junit4)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.test {
    useJUnit()
}

tasks.named("run", JavaExec::class) {
    standardInput = System.`in`
    standardOutput = System.`out`
}

application {
    mainClass = "Main"
}
