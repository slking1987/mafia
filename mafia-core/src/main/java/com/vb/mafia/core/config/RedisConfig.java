package com.vb.mafia.core.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String mafHost;
    @Value("${spring.redis.port}")
    private int mafPort;
    @Value("${spring.redis.password}")
    private String mafPassword;
    @Value("${spring.redis.database}")
    private int mafDatabase;

    @Value("${redis.pool.max-idle}")
    private int maxIdle;
    @Value("${redis.pool.min-idle}")
    private int minIdle;
    @Value("${redis.pool.max-active}")
    private int maxActive;
    @Value("${redis.pool.max-wait}")
    private int maxWait;

    private RedisConnectionFactory getRedisConnectionFactory(int database, String host, String password, int port, int maxIdle, int minIdle, int maxWait) {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setDatabase(database);
        connectionFactory.setHostName(host);
        connectionFactory.setPassword(password);
        connectionFactory.setPort(port);
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnCreate(true);
        poolConfig.setTestWhileIdle(true);
        connectionFactory.setUsePool(true);
        return connectionFactory;
    }

    @Bean(name = "wiselyKeyGenerator")
    public KeyGenerator wiselyKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };

    }

    @Bean
    public CacheManager cacheManager(@Qualifier("redisTemplate") RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(21600L);
        return cacheManager;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String,?> redisTemplate(@Qualifier("mafConnectionFactory") RedisConnectionFactory factory) {
        RedisTemplate<String,?> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean(name = "mafConnectionFactory")
    @Primary
    public RedisConnectionFactory mafRedisConnectionFactory() {
        return getRedisConnectionFactory(mafDatabase, mafHost, mafPassword, mafPort, maxIdle, minIdle, maxWait);
    }
}