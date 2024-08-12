plugins {
	java
	idea
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
	id("org.flywaydb.flyway") version "10.17.0"
	id("io.freefair.lombok") version "8.7.1"
	id("com.diffplug.spotless") version "6.25.0"
}

group = "com.octanner.demo"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

// this is needed to get the flyway libraries to work on the gradle command line
buildscript {
	dependencies {
		classpath("org.flywaydb:flyway-database-postgresql:10.17.0")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("com.apollographql.federation:federation-graphql-java-support:5.1.0")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.apache.commons:commons-lang3")
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