package app.config

import app.model.RedisItem
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import java.sql.SQLException


@Configuration
class RedisConfig {

    @Bean
    fun redisConnectionFactory(
        @Value("\${spring.data.redis.host}") host: String,
        @Value("\${spring.data.redis.port}") port: Int
    ) = LettuceConnectionFactory(host, port)

    @Bean
    fun redisTemplate(
        redisConnectionFactory: RedisConnectionFactory
    ): RedisTemplate<String, RedisItem> {
        val template = RedisTemplate<String, RedisItem>()
        template.setConnectionFactory(redisConnectionFactory)
        template.setEnableTransactionSupport(true)
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = Jackson2JsonRedisSerializer(RedisItem::class.java)
        return template
    }


//    @Bean
//    fun transactionManager(): PlatformTransactionManager? {
//        // 사용하고 있는 datasource 관련 내용, 아래는 JDBC
//        return DataSourceTransactionManager(datasource())
//        // JPA 사용하고 있다면 아래처럼 사용하고 있음
//        return JpaTransactionManager(entityManagerFactory)
//    }
}
