package com.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

@EnableRedisHttpSession
public class HttpSessionConfig {


    @Bean
    public RedisConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }


    @Autowired
    private FindByIndexNameSessionRepository sessionRepository;


     //SpringSessionBackedSessionRegistry 是session为Spring Security提供的
    //用于在集群环境下控制会话并发的会注册表实现类
    @Bean
    public SpringSessionBackedSessionRegistry sessionRepository() {
        return new SpringSessionBackedSessionRegistry(sessionRepository);
    }

    //httpSession的事件监听，改用session提供的会话注册表
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

}
