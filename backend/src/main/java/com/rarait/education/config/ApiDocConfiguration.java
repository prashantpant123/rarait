package com.rarait.education.config;

import com.rarait.framework.config.BaseSwaggerConfig;
import com.rarait.framework.config.SwaggerResource;
import com.rarait.framework.shared.ProfileNames;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@EnableSwagger2
@Configuration
@Profile({ProfileNames.UAT,ProfileNames.DEV})
public class ApiDocConfiguration extends BaseSwaggerConfig {

    @Bean
    @Lazy
    public Docket documentAPI() {
        return documentAPI(SwaggerResource.builder()
                .docPath("/")
                .title("Education")
                .description("Restful API Document")
                .version("1.0")
                .build());
    }
}