package com.study.config;

import com.study.security.handle.CustomExpiredSessionStrategy;
import com.study.security.handle.CustomerAuthenticationFailureHandler;
import com.study.security.handle.CustomerAuthenticationSuccessHandler;
import com.study.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 该类是 Spring Security 的配置类，该类的三个注解分别是标识该类是配置类、开启 Security 服务、开启全局 Securtiy 注解。
 * <p>
 * 首先将我们自定义的 userDetailsService 注入进来，在 configure() 方法中使用 auth.userDetailsService() 方法替换掉默认的 userDetailsService。
 * <p>
 * 这里我们还指定了密码的加密方式（5.0 版本强制要求设置），因为我们数据库是明文存储的，所以明文返回即可，如下所示：
 * <p>
 * 原文链接：https://blog.csdn.net/yuanlaijike/article/details/80249235
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig<CustomAuthenticationSuccessHandler> extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Autowired
    private CustomerAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomerAuthenticationFailureHandler customAuthenticationFailureHandler;

    /**
     * 四、踢出用户
     * 下面来看下如何主动踢出一个用户。
     *
     * 首先需要在容器中注入名为 SessionRegistry 的 Bean，这里我就简单的写在 WebSecurityConfig 中：
     * @return
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }

    /**
     * maximumSessions(int)：指定最大登录数
     * maxSessionsPreventsLogin(boolean)：是否保留已经登录的用户；为true，新用户无法登录；为 false，旧用户被踢出
     * expiredSessionStrategy(SessionInformationExpiredStrategy)：旧用户被踢出后处理方法
     * maxSessionsPreventsLogin()可能不太好理解，这里我们先设为 false，效果和 QQ 登录是一样的，登陆后之前登录的账户被踢出。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers("/login/invalid").permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登陆页
                .formLogin().loginPage("/login").permitAll()
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                // 设置登陆成功页
                //.defaultSuccessUrl("/").permitAll()
                // 自定义登陆用户名和密码参数，默认为username和password
                //  .usernameParameter("username")
                //   .passwordParameter("password")
                .and()
                .logout().permitAll().and()
                //session 过期时调用，另一种是一个是 invalidSessionStrategy()
                .sessionManagement().invalidSessionUrl("/login/invalid")
                .maximumSessions(1)
                // 当达到最大值时，是否保留已经登录的用户
                .maxSessionsPreventsLogin(false)
                // 当达到最大值时，旧用户被踢出后的操作
                .expiredSessionStrategy(new CustomExpiredSessionStrategy())
                .sessionRegistry(sessionRegistry());
        // 关闭CSRF跨域
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**");
    }


}
