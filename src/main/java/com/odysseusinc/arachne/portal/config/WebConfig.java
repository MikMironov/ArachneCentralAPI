/**
 *
 * Copyright 2017 Observational Health Data Sciences and Informatics
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Company: Odysseus Data Services, Inc.
 * Product Owner/Architecture: Gregory Klebanov
 * Authors: Pavel Grafkin, Alexandr Ryabokon, Vitaly Koulakov, Anton Gackovka, Maria Pozhidaeva, Mikhail Mironov
 * Created: November 14, 2016
 *
 */

package com.odysseusinc.arachne.portal.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@SuppressWarnings("unused")
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("public/**").addResourceLocations("classpath:/public/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/analysis-execution/analyses/**").setViewName("index");
        registry.addViewController("/analysis-execution/submissions/**").setViewName("index");
        registry.addViewController("/auth/login**").setViewName("index");
        registry.addViewController("/auth/register**").setViewName("index");
        registry.addViewController("/auth/remind-password**").setViewName("index");
        registry.addViewController("/auth/reset-password**").setViewName("index");
        registry.addViewController("/data-catalog**").setViewName("index");
        registry.addViewController("/data-catalog/data-sources/**").setViewName("index");
        registry.addViewController("/dashboard**").setViewName("index");
        registry.addViewController("/expert-finder**").setViewName("index");
        registry.addViewController("/expert-finder/list**").setViewName("index");
        registry.addViewController("/expert-finder/profile/**").setViewName("index");
        registry.addViewController("/study-manager**").setViewName("index");
        registry.addViewController("/study-manager/studies/**").setViewName("index");
        registry.addViewController("/insights-library/**").setViewName("index");
        registry.addViewController("/admin-settings/**").setViewName("index");
        registry.addViewController("/insights-library/insights/**").setViewName("index");
    }

    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(3600); //refresh cache once per hour
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {

        return new CookieLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {

        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public Validator getValidator() {

        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Bean
    public BeanPostProcessor commonAnnotationBeanPostProcessor() {

        return new CommonAnnotationBeanPostProcessor();
    }

}
