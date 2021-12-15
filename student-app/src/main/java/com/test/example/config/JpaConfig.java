/*
 * (C) 2021 Dagangnet Technologies Sdn Bhd.
 */
package com.test.example.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration()
@EnableJpaRepositories(
        basePackages = "com.test.example.repository",
        entityManagerFactoryRef = "exampleEntityManagerFactory",
        transactionManagerRef = "exampleTransactionManager"
)
//@EnableJpaAuditing
public class JpaConfig {

    @Bean
    @ConfigurationProperties("example.datasource")
    public DataSourceProperties exampleDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("example.jpa")
    public JpaProperties exampleJpaProperties() {
        return new JpaProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("example.datasource.hikari")
    public HikariDataSource exampleDataSource(@Qualifier("exampleDataSourceProperties") DataSourceProperties props) {
        return props.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean exampleEntityManagerFactory(
            @Qualifier("exampleDataSource") HikariDataSource ds,
            @Qualifier("exampleJpaProperties") JpaProperties jpaProps) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(jpaProps.isGenerateDdl());
        vendorAdapter.setShowSql(jpaProps.isShowSql());

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.test.example.entity");
        factory.setDataSource(ds);
        factory.setJpaPropertyMap(jpaProps.getProperties());
        factory.setPersistenceUnitName("examplePU");

        return factory;
    }

    @Bean
    @Primary
    public PlatformTransactionManager exampleTransactionManager(
            @Qualifier("exampleEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
