plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.2.0"
    id("io.micronaut.aot") version "4.2.0"
    id("org.liquibase.gradle") version "2.2.1"
}

version = "0.1"
group = "diploma.thesis"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/io.micronaut.data/micronaut-data-jdbc
    runtimeOnly("io.micronaut.data:micronaut-data-jdbc:4.3.1")
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    annotationProcessor("org.projectlombok:lombok:1.16.20")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.data:micronaut-data-jdbc")
    implementation("io.micronaut.liquibase:micronaut-liquibase")
    implementation("org.slf4j:jul-to-slf4j:1.7.30")
    implementation("io.micronaut:micronaut-management")
    compileOnly("io.micronaut:micronaut-http-client")
    compileOnly("org.projectlombok:lombok:1.16.20")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("io.micronaut:micronaut-http-client")
}


application {
    mainClass.set("diploma.thesis.Application")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
    targetCompatibility = JavaVersion.toVersion("17")
}


graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("diploma.thesis.*")
    }
    aot {
    // Please review carefully the optimizations enabled below
    // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
    }
}



