plugins {
    java
    checkstyle
    jacoco
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.flywaydb.flyway") version "10.10.0"
}

group = "com.jgarivera"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // production
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
    implementation("org.webjars:webjars-locator-core")
    implementation("org.webjars.npm:bulma:1.0.1")
    implementation("org.webjars:font-awesome:6.5.2")
    implementation("org.webjars.npm:htmx.org:2.0.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("com.github.f4b6a3:uuid-creator:6.0.0")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // development
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

buildscript {
    dependencies {
        classpath("org.flywaydb:flyway-database-postgresql:10.10.0")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

checkstyle {
    configFile = file("checkstyle.xml")
}

flyway {
    url = "jdbc:postgresql://localhost:5432/qwest"
    user = "user"
    password = "password"
    cleanDisabled = false
}

tasks.register("lintMigrations") {
    description = "Lints migration SQL files."

    doFirst {
        exec {
            commandLine("sqlfluff", "lint", "src/main/resources/db/migration")
        }
    }
}

tasks.check {
    dependsOn("lintMigrations")
}
