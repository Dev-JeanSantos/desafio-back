plugins {
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"

}

group = "com.academy.fourtk"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencyManagement {
	imports {
		mavenBom("io.awspring.cloud:spring-cloud-aws-dependencies:2.3.2") // Substitua pela versão do AWS SDK que você deseja usar
	}
}


dependencies {

	//SPRING GLOBAL
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.4")

	implementation("org.jetbrains.kotlin:kotlin-reflect")

	//MONGO
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.mongodb:mongodb-driver-sync")

	//FEIGN
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.2")
	implementation("io.github.openfeign:feign-httpclient:13.3")

	//SQS
	implementation ("io.awspring.cloud:spring-cloud-aws-messaging")
	implementation("com.amazonaws:aws-java-sdk-sqs")

	//LOGGABLE
	implementation("io.github.microutils:kotlin-logging:3.0.5")
	implementation("ch.qos.logback:logback-classic:1.4.5")

	//Tests
	testImplementation("io.github.openfeign:feign-micrometer:13.3")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")


}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
