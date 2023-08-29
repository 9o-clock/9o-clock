dependencies {
    dependencies {
        api("org.springframework.boot:spring-boot-starter-data-r2dbc")
        runtimeOnly("com.h2database:h2")
        testRuntimeOnly("com.h2database:h2")
        implementation("io.r2dbc:r2dbc-h2")
    }
}
