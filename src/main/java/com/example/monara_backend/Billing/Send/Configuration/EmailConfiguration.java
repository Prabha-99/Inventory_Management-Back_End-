package com.example.monara_backend.Billing.Send.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.nio.charset.StandardCharsets;

@Configuration
public class EmailConfiguration {

    @Bean
    public SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }

    @Bean
    public ITemplateResolver htmlTemplateResolver() {
        SpringResourceTemplateResolver pdfTemplateResolver = new SpringResourceTemplateResolver();
        pdfTemplateResolver.setPrefix("classpath:/templates/");
        pdfTemplateResolver.setSuffix(".html");
        pdfTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return pdfTemplateResolver;
    }


}
