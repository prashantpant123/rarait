package com.rarait.education.config;

import com.rarait.framework.security.SecurityPackage;
import com.rarait.education.MainPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Configuration
@Import(value = {
        DataSourceConfiguration.class,
        ApiDocConfiguration.class,
        RestTemplateConfiguration.class,
        SecurityConfiguration.class,
        AsyncConfiguration.class,
        EmailConfiguration.class,
        JavaMelodyConfiguration.class
//        WebMvcConfig.class
})
@ComponentScan(basePackageClasses = {MainPackage.class, SecurityPackage.class})
public class MainConfiguration {

}
