plugins {
    kotlin("plugin.jpa") version "1.7.20"
}

dependencies {
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("com.h2database:h2")
    testRuntimeOnly("com.h2database:h2")
    api("org.springframework.boot:spring-boot-starter-data-jpa")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}
