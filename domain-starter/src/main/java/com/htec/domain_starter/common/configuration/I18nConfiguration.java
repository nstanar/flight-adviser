package com.htec.domain_starter.common.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author Nikola Stanar
 * <p>
 * Internationalization configuration.
 */
@Configuration
public class I18nConfiguration {

    /**
     * Instantiates validation message source.
     *
     * @return {@link MessageSource}
     */
    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/validation_messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Instantiates locale resolver.
     *
     * @return {@link LocaleResolver}
     */
    @Bean
    public LocaleResolver localeResolver() {
        final SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }

    /**
     * Instantiates locale change interceptor.
     *
     * @return {@link LocaleChangeInterceptor}
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
}
