package com.example.demo.batch2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "batch3EntityManagerFactory",
        basePackages = { "com.example.demo.batch2.repo2" },
        transactionManagerRef = "batch3TransactionManager"
)
public class Batch2DbConfig {

    //@Primary
    @Bean(name = "batch3DataSource")
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource batch3DataSource() {
        return DataSourceBuilder.create().build();
    }

    //@Primary
    @Bean(name = "batch3EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    batch3EntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("batch3DataSource") DataSource dataSource
    ) {
        return
                builder
                        .dataSource(dataSource)
                        .packages("com.example.demo.batch2.domain2")
                        .persistenceUnit("batch2")
                        .build();
    }

    //@Primary
    @Bean(name = "batch3TransactionManager")
    public PlatformTransactionManager batch3TransactionManager(
            @Qualifier("batch3EntityManagerFactory") EntityManagerFactory
                    entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }


}
