package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * http://localhost:9401/oauth/authorize?response_type=code&client_id=admin&redirect_uri=http://www.baidu.com&scope=all&state=normal
 *
 *
 *   http://localhost:9401/authoriza/oauth/token?client_id=admin&grant_type=AUTHORIZATION_CODE&code=6XHAVM&redirect_uri=http://localhost:9410/user/getCurrentUser&client_secret=admin123456
 *
 */
@SpringBootApplication
public class Oauth2SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2SpringApplication.class , args);
    }
}
