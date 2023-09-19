dependencies {
    implementation(project(":components:user:domain-user"))
    testImplementation(project(":components:user:domain-user"))
    api("org.springframework.boot:spring-boot-starter-validation")
}
