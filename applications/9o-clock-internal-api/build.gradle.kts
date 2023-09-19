dependencies {
    implementation(project(":components:quiz:app-quiz"))
    implementation(project(":components:user:app-user"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    implementation("org.springdoc:springdoc-openapi-starter-common:2.1.0")
}
