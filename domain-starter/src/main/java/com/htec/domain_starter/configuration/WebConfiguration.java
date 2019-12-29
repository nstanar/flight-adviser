package com.htec.domain_starter.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * @author Nikola Stanar
 * <p>
 * Web configuration.
 */
@Configuration
@AllArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Locale change interceptor.
     */
    private final LocaleChangeInterceptor localeChangeInterceptor;

    /**
     * Registers interceptors.
     *
     * @param registry interceptor registry
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
    }
}
