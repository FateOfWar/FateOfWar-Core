plugins {
    kotlin("jvm") version "1.9.21"
    idea
}

group = "me.alex_s168"
version = "c1f1"

repositories {
    mavenCentral()
    maven {
        name = "alex's repo"
        url = uri("http://207.180.202.42:8080/libs")
        isAllowInsecureProtocol = true
    }
    maven {
        name = "Valkyrien Skies Internal"
        url = uri("https://maven.valkyrienskies.org")
    }
    maven {
        name = "JitPack"
        url = uri("https://jitpack.io")
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    implementation("me.alex_s168:mathlib:0.5")
    implementation("me.alex_s168:meshlib:0.6")
    implementation("me.alex_s168:ezconf:0.2")

    implementation("com.github.alex-s168:ktlib:4a380bf749")

    // vs-core
    val vsCoreVersion = project.properties["vs_core_version"]
    implementation("org.valkyrienskies.core:api:$vsCoreVersion") {
        exclude(module = "fastutil")
    }
    implementation("org.valkyrienskies.core:api-game:$vsCoreVersion") {
        exclude(module = "netty-buffer")
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}