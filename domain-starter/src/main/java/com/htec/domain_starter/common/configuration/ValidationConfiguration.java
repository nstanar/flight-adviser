package com.htec.domain_starter.common.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

/**
 * @author Nikola Stanar
 * <p>
 * Validation configuration.
 */
@Configuration
public class ValidationConfiguration {

    /**
     * Instantiates method validation post processor.
     *
     * @param localValidatorFactoryBean Local validator factory bean.
     * @return Method validation post processor.
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(final LocalValidatorFactoryBean localValidatorFactoryBean) {
        final MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidatorFactory(localValidatorFactoryBean);
        return methodValidationPostProcessor;
    }

    /**
     * Instantiates local validator factory bean.
     *
     * @param applicationContext Application context.
     * @param messageSource      Message source.
     * @return Local validator factory bean.
     */
    @Bean
    @Primary
    public LocalValidatorFactoryBean localValidatorFactoryBean(final ApplicationContext applicationContext, final MessageSource messageSource) {
        final LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        localValidatorFactoryBean.setApplicationContext(applicationContext);
        localValidatorFactoryBean.setConstraintValidatorFactory(new SpringConstraintValidatorFactory(applicationContext.getAutowireCapableBeanFactory()));
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }
}
