package com.megazone.sample.config;

import com.megazone.sample.exrepository.ExternalDemoRepository;
import com.megazone.sample.exentity.ExternalDemoEntity;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Random;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
@EnableJpaRepositories(basePackages = "com.megazone.sample.exrepository", entityManagerFactoryRef = "exMysqlEntityManager", transactionManagerRef = "exMysqlTransactionManager")
public class ExternalDataSourceConfig {

    private static final Logger logger = getLogger(ExternalDataSourceConfig.class);

    @Value("${datasource.driver-class-name}")
    String mysqlDriverClassName;
    @Value("${datasource.jdbc-url}")
    String mysqlUrl;
    @Value("${datasource.username}")
    String mysqlUsername;
    @Value("${datasource.name}")
    String name;

    @Value("${datasource.password}")
    String mysqlPassword;
    String ddlAuto = "create";
    String dialect = "org.hibernate.dialect.MySQL5Dialect";

    @Bean
    public boolean loggerPrintConfig() {
        logger.info("[mysqlConfig]=====================================================================");
        logger.info("mysqlDriverClassName :: " + mysqlDriverClassName);
        logger.info("mysqlUrl :: " + mysqlUrl);
        logger.info("mysqlUsername :: " + mysqlUsername);
        logger.info("mysqlPassword :: " + mysqlPassword);
        logger.info("ddlAuto :: " + ddlAuto);
        logger.info("dialect :: " + dialect);
        logger.info("==================================================================================");
        return true;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean exMysqlEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mysqlSource());
        em.setPackagesToScan(new String[]{"com.megazone.sample.exentity"});
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", ddlAuto);// validate
        properties.put("hibernate.dialect", dialect); // hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public DataSource mysqlSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(mysqlDriverClassName);
        dataSource.setUrl(mysqlUrl);
        dataSource.setUsername(mysqlUsername);
        dataSource.setPassword(mysqlPassword);
        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager exMysqlTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(exMysqlEntityManager().getObject());
        return transactionManager;
    }


    @Bean
    public String initData(ExternalDemoRepository eduRepository) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            eduRepository.save(new ExternalDemoEntity(name + " " + i, "010-" + String.format("%04d", random.nextInt(9999)) + "-" + String.format("%04d", random.nextInt(9999)), random.nextInt(99)));
        }
        return null;
    }


}
