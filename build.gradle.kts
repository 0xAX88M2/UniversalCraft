import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    kotlin("jvm")
    id("fabric-loom")
    `maven-publish`
    id("java")
}

group = "gg.essential"
archivesName = "universalcraft-1.20.4-fabric-yarn"
version = "323"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.20.4")
    mappings("net.fabricmc:yarn:1.20.4+build.3:v2")
    modImplementation("net.fabricmc:fabric-loader:0.15.6")
}

tasks {
    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                artifact(remapJar) {
                    builtBy(remapJar)
                }
                artifact(kotlinSourcesJar) {
                    builtBy(remapSourcesJar)
                }
            }
        }

        // select the repositories you want to publish to
        repositories {
            // uncomment to publish to the local maven
            mavenLocal()
        }
    }
}