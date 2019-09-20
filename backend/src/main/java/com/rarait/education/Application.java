package com.rarait.education;

import com.rarait.education.config.MainConfiguration;
import com.rarait.education.shared.AppProperties;
import net.bull.javamelody.JavaMelodyAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import java.awt.*;
import java.util.Properties;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Import(value = MainConfiguration.class)
@EnableAutoConfiguration(
        exclude = {RedisAutoConfiguration.class,
                FreeMarkerAutoConfiguration.class,
                JavaMelodyAutoConfiguration.class,
                H2ConsoleAutoConfiguration.class
        })
@SpringBootApplication(scanBasePackageClasses = {MainPackage.class})
public class Application extends SpringBootServletInitializer {

    static { /* works fine! ! */
        System.setProperty("java.awt.headless", "false");
        System.out.println(java.awt.GraphicsEnvironment.isHeadless());
        /* ---> prints true */
    }

    private static final Color COLOR_BACKGROUND = Color.WHITE;
    static Properties getProperties() {
        Properties props = new Properties();
//        props.put("spring.config.location", AppProperties.PROP_PATH);
        return props;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class)
                .properties(getProperties());
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .sources(Application.class)
                .properties(getProperties())
                .run(args);
    }
}



