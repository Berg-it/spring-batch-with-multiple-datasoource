package com.example.demo.batch3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "batch4EntityManagerFactory",
        basePackages = { "com.example.demo.batch3.repo3" },
        transactionManagerRef = "batch4TransactionManager"
)
public class Batch3DbConfig {

    @Bean(name = "batch4DataSource")
    @ConfigurationProperties(prefix = "spring.third-datasource")
    public DataSource batch4DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "batch4EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    batch4EntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("batch4DataSource") DataSource dataSource
    ) {
        return
                builder
                        .dataSource(dataSource)
                        .packages("com.example.demo.batch3.domain3")
                        .persistenceUnit("batch4")
                        .build();
    }


    @Bean(name = "batch4TransactionManager")
    public PlatformTransactionManager batch4TransactionManager(
            @Qualifier("batch4EntityManagerFactory") EntityManagerFactory
                    entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }


}
