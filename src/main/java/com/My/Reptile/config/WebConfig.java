package com.My.Reptile.config;

import com.My.Reptile.common.PageableArgumentResolver;
import com.My.Reptile.common.RequestModelArgumentResolver;
import com.My.Reptile.common.SearchParamsArgumentResolver;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;
import java.util.List;

/**
 * 配置解析器
 * <p>
 * Created by ShenJH on 2016/9/8.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 分页参数解析
        argumentResolvers.add(pageableArgumentResolver());
        // 页面多个请求对象解析
        argumentResolvers.add(requestModelArgumentResolver());
        // 检索条件请求解析
        argumentResolvers.add(searchParamsArgumentResolver());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        /*
        QuoteFieldNames———-输出key时是否使用双引号,默认为true
        WriteMapNullValue——–是否输出值为null的字段,默认为false
        WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
        WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
        WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
        WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
        */
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(fastConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    @Bean
    public PageableArgumentResolver pageableArgumentResolver() {
        return new PageableArgumentResolver();
    }

    @Bean
    public RequestModelArgumentResolver requestModelArgumentResolver() {
        return new RequestModelArgumentResolver();
    }

    @Bean
    public SearchParamsArgumentResolver searchParamsArgumentResolver() {
        return new SearchParamsArgumentResolver();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("10MB");
        factory.setMaxRequestSize("50MB");
        return factory.createMultipartConfig();
    }
}
