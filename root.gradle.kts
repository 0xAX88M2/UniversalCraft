import gg.essential.gradle.util.*

plugins {
    kotlin("jvm") version "1.6.10" apply false
    id("gg.essential.multi-version.root")
    id("gg.essential.multi-version.api-validation")
}

version = versionFromBuildIdAndBranch()

preprocess {
    val fabric11901 = createNode("1.19.1-fabric", 11901, "yarn")
    val forge11900 = createNode("1.19-forge", 11900, "srg")
    val fabric11900 = createNode("1.19-fabric", 11900, "yarn")
    val forge11801 = createNode("1.18.1-forge", 11801, "srg")
    val fabric11801 = createNode("1.18.1-fabric", 11801, "yarn")
    val forge11701 = createNode("1.17.1-forge", 11701, "srg")
    val fabric11701 = createNode("1.17.1-fabric", 11701, "yarn")
    val fabric11602 = createNode("1.16.2-fabric", 11602, "yarn")
    val forge11602 = createNode("1.16.2-forge", 11602, "srg")
    val forge11502 = createNode("1.15.2-forge", 11502, "srg")
    val forge11202 = createNode("1.12.2-forge", 11202, "srg")
    val forge10809 = createNode("1.8.9-forge", 10809, "srg")

    fabric11901.link(fabric11900)
    forge11900.link(fabric11900)
    fabric11900.link(fabric11801)
    forge11801.link(fabric11801)
    fabric11801.link(fabric11701)
    forge11701.link(fabric11701)
    fabric11701.link(fabric11602, file("versions/1.17.1-1.16.2.txt"))
    fabric11602.link(forge11602)
    forge11602.link(forge11502, file("versions/1.16.2-1.15.2.txt"))
    forge11502.link(forge11202, file("versions/1.15.2-1.12.2.txt"))
    forge11202.link(forge10809, file("versions/1.12.2-1.8.9.txt"))
}
