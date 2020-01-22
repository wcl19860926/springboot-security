package com.study.config;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ProxyTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.util.Arrays;

@Configuration
public class CASConfig {

    @Value("${cas.server.prefix}")
    private String casServerPrefix;
    @Value("${cas.server.login}")
    private String casServerLogin;
    @Value("${cas.server.logout}")
    private String casServerLogout;
    @Value("${cas.client.prefix}")
    private String casClientPrefix;
    @Value("${cas.client.logout}")
    private String casClientLogout;
    @Value("${cas.client.login}")
    private String casClientLogin;
    @Value("${cas.client.logout_relative}")
    private String casClientLogoutRelative;
    @Value("${cas.user.inmemory}")
    private String casUserInmemory;

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(casClientLogin);
        serviceProperties.setSendRenew(false);
        return serviceProperties;


    }

    /**
     * CAS 验证入口
     * @param sp
     * @return
     */
    @Bean
    @Primary
    public AuthenticationEntryPoint authenticationEntryPoint(ServiceProperties sp) {

        CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
        entryPoint.setLoginUrl(casServerLogin);
        entryPoint.setServiceProperties(sp);
        return entryPoint;
    }

    /**
     * ticket验证校验，需要提供CAS Server 校验的ticket地址
     * @return
     */
    @Bean
    public TicketValidator ticketValidator() {
        //默认使用Cas20ProxyTickerValidatro，验证入口是${casServerPrefix}/proxyValidate
        return new Cas20ProxyTicketValidator(casServerPrefix);
    }

    /**
     * 使用内存里的用户并分配权限
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager memoryUserDetailsManager = new InMemoryUserDetailsManager();
        memoryUserDetailsManager.createUser(User.withUsername(casUserInmemory).password("").roles("USER").build());
        return memoryUserDetailsManager;
    }

    /**
     * 处理CAS处理验证逻辑
     * @param sp
     * @param ticketValidator
     * @param userDetailsService
     * @return
     */

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider(ServiceProperties sp, TicketValidator ticketValidator, UserDetailsService userDetailsService) {
        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setServiceProperties(sp);
        provider.setTicketValidator(ticketValidator);
        provider.setUserDetailsService(userDetailsService);
        provider.setKey("blurooo");
        return provider;
    }

    /**
     * 提供CAS验证专用过滤，过滤器的验证逻辑由CasAuthenticationProvider提供
     * @param sp
     * @param ap
     * @return
     */
    @Bean
    public CasAuthenticationFilter casAuthenticationFilter(ServiceProperties sp, AuthenticationProvider ap) {
        CasAuthenticationFilter filter = new CasAuthenticationFilter();
        filter.setServiceProperties(sp);
        filter.setAuthenticationManager(new ProviderManager(Arrays.asList(ap)));
        return filter;
    }

    /**
     * 接收CAS服服器发出的注销请求
     * @return
     */
    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(casServerPrefix);
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    /**
     * 将注销请求转发到CAS Server
     * @return
     */
    @Bean
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter(casServerLogout, new SecurityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl(casClientLogoutRelative);
        return logoutFilter;
    }

}

