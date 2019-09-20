package com.rarait.education.config;

import com.rarait.education.shared.setting.DatasourceSetting;
import com.rarait.framework.config.BaseDatasourceConfig;
import com.rarait.education.MainPackage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Configuration
@EnableSpringDataWebSupport
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = {MainPackage.class},
        entityManagerFactoryRef = "education-emf",
        transactionManagerRef = "education-tm")
@ComponentScan(basePackageClasses = {MainPackage.class})
public class DataSourceConfiguration extends BaseDatasourceConfig {

    private static final String PU_NAME = "education";
    private static final String DATASOURCE_NAME = "education-ds";
    private static final String[] PACKAGES = {MainPackage.class.getPackage().getName()};

    @Primary
    @Bean(name = DATASOURCE_NAME)
    public DataSource coreDataSource(final DatasourceSetting dbSetting) {
        return buildDataSource(dbSetting);
    }

    @Bean(name = "education-emf")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier(value = DATASOURCE_NAME) DataSource dataSource) {
        return buildEntityManagerFactory(
                dataSource,
                PU_NAME,
                BaseDatasourceConfig.DIALECT_MYSQL,
                BaseDatasourceConfig.DDL_UPDATE,
                PACKAGES);
    }

    @Bean(name = "education-tm")
    public PlatformTransactionManager transactionManager(
            @Qualifier(value = "education-emf") EntityManagerFactory emf) {
        return buildTransactionManager(emf);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}