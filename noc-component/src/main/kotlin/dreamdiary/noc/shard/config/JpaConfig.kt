package dreamdiary.noc.shard.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import javax.sql.DataSource


@EnableJpaAuditing
@Configuration
class JpaConfig {
    @Value("\${spring.datasource.driver-class-name}")
    private val driverClassName: String? = null

    @Value("\${spring.datasource.url}")
    private val url: String? = null

    @Value("\${spring.datasource.username}")
    private val userName: String? = null

    @Value("\${spring.datasource.password}")
    private val password: String? = null
    @Bean
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
            .driverClassName(driverClassName)
            .url(url)
            .username(userName)
            .password(password)
            .build()
    }
}
