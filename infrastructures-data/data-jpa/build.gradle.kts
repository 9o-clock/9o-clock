plugins {
    kotlin("plugin.jpa") version "1.7.20"
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}
