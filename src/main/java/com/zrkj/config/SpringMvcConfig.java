package com.zrkj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by gaowenfeng on 2017/6/16.
 */
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/prize").setViewName("/prizeManager");
        registry.addViewController("/user").setViewName("/userManager");
        registry.addViewController("/userController/userPrize").setViewName("/userPrize");
        registry.addViewController("/login").setViewName("/login");
    }

}
