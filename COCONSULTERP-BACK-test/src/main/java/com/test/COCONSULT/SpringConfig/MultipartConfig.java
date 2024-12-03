package com.test.COCONSULT.SpringConfig;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.multipart.support.MultipartFilter;
@Configuration
public class MultipartConfig {

    @Bean
    public FilterRegistrationBean<MultipartFilter> multipartFilterRegistrationBean() {
        FilterRegistrationBean<MultipartFilter> registrationBean = new FilterRegistrationBean<>();
        MultipartFilter multipartFilter = new MultipartFilter();
        registrationBean.setFilter(multipartFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("multipartFilter");
        registrationBean.setOrder(Integer.MIN_VALUE); // set as highest priority
        return registrationBean;
    }
}