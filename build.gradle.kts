val gcloudInvokerVersion: String by project
val slf4jVersion: String by project

val invoker: Configuration by configurations.creating

repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "1.7.21"
}

dependencies {
    implementation("com.google.cloud.functions:functions-framework-api:1.0.4")
    implementation("org.slf4j:slf4j-jdk14:$slf4jVersion")

    invoker("com.google.cloud.functions.invoker:java-function-invoker:$gcloudInvokerVersion")
}

tasks.register<JavaExec>("run") {
    mainClass.set("com.google.cloud.functions.invoker.runner.Invoker")
    classpath(invoker)
    inputs.files(configurations.runtimeClasspath.get(), sourceSets.main.get().output)
    args(
        "--target",
        project.findProperty("runFunction.target") ?: "com.example.FunctionMain",
        "--port",
        project.findProperty("runFunction.port") ?: 8080
    )
    doFirst {
        args("--classpath", files(configurations.runtimeClasspath, sourceSets["main"].runtimeClasspath).asPath)
    }
}
