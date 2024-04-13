plugins {
	java
	idea
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.flywaydb.flyway") version "10.11.0"
	id("io.freefair.lombok") version "8.6"
	id("com.diffplug.spotless") version "6.25.0"
}

group = "com.octanner.demo"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

// this is needed to get the flyway libraries to work on the gradle command line
buildscript {
	dependencies {
		classpath("org.flywaydb:flyway-database-postgresql:10.11.0")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.flywaydb:flyway-core")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.graphql:spring-graphql-test")
	testImplementation("org.junit.jupiter:junit-jupiter")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

flyway {
	url = "jdbc:postgresql://localhost:5433/books"
	user = "guest"
	password = "guest"
	cleanDisabled = false
}

tasks.withType<Test> {
	useJUnitPlatform()
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
	java {
		googleJavaFormat("1.22.0")
	}
}

tasks.register("format") {
	dependsOn("spotlessApply")
}

idea {
	module {
		isDownloadJavadoc = true
		isDownloadSources = true
	}
}