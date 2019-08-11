package com.griddynamics.dms.config;

import com.griddynamics.dms.config.security.user.UserInterceptor;
import com.griddynamics.dms.config.security.admin.AdminInterceptor;
import com.griddynamics.dms.config.security.UserMethodResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final UserInterceptor userInterceptor;
    private final AdminInterceptor adminInterceptor;

    public WebConfiguration(UserInterceptor userInterceptor,
                            AdminInterceptor adminInterceptor) {
        this.userInterceptor = userInterceptor;
        this.adminInterceptor = adminInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor);
        registry.addInterceptor(adminInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserMethodResolver());
    }
}