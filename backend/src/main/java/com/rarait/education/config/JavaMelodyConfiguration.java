package com.rarait.education.config;

import com.rarait.framework.config.BaseJavaMelody;
import com.rarait.framework.shared.ProfileNames;
import net.bull.javamelody.*;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Configuration
@Profile({ProfileNames.PROD,ProfileNames.DEV})
public class JavaMelodyConfiguration extends BaseJavaMelody implements ServletContextInitializer {

    private static String NAME = "Education";

    @Bean
    @Lazy
    public DefaultAdvisorAutoProxyCreator getAdvisorAutoProxy() {
        return getDefaultAdvisorAutoProxyCreator();
    }

    @Bean
    @Lazy
    public SpringDataSourceBeanPostProcessor monitorDataSource() {
        return monitoringDataSourceBeanPostProcessor();
    }

    @Bean
    @Lazy
    public MonitoringSpringAdvisor monitorSpringAdvisor() {
       return monitoringAdvisor();
    }

    @Bean
    @Lazy
    public MonitoringSpringAdvisor monitorSpringServiceAdvisor() {
       return springServiceMonitoringAdvisor();
    }

    @Bean
    @Lazy
    public MonitoringSpringAdvisor monitorSpringRestController() {
       return springRestControllerMonitoringAdvisor();
    }

    @Bean
    @Lazy
    public FilterRegistrationBean javaMelody(ServletContext servletContext) {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MonitoringFilter());
        filterRegistrationBean.setName(NAME);
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);
        filterRegistrationBean.addInitParameter(Parameter.LOG.getCode(), Boolean.toString(true));
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> param = new HashMap<>();
        param.put("monitoring-path", "/monitor/application-health");
//        param.put("storage-directory", "/home/prajin/javamelody");
        param.put("authorized-users", "education:education");
        param.put("log", "false");
        param.put("application-name", NAME);
        filterRegistrationBean.setInitParameters(param);

        final FilterRegistration filterRegistration = servletContext.getFilterRegistration(NAME);
        if (filterRegistration != null) {
            // if webapp deployed as war in a container with MonitoringFilter already added by web-fragment.xml,
            // do not try to add it again
            filterRegistrationBean.setEnabled(false);

            Map<String, String > filter = filterRegistrationBean.getInitParameters();
            for (Map.Entry<String, String> entry : filter.entrySet()) {
                filterRegistration.setInitParameter(entry.getKey(), entry.getValue());
            }
        }
        return filterRegistrationBean;
    }

    @Bean
    @Lazy
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }

    @Bean
    @Lazy
    public FilterRegistrationBean disableErrorPageFilter(ErrorPageFilter filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        if (servletContext.getFilterRegistration(NAME) == null) {
            servletContext.addListener(new SessionListener());
        }
    }
}