package ru.iokhin.tm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("ru.iokhin.tm.repository")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource(@Value("${datasource.driver}") final String dataSourceDriver,
                                 @Value("${datasource.url}") final String dataSourceUrl,
                                 @Value("${datasource.user}") final String dataSourceUser,
                                 @Value("${datasource.password}") final String dataSourcePassword) {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceDriver);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUser);
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource,
                       @Value("${hibernate.show_sql}") final boolean showSql,
                       @Value("${hibernate.hbm2ddl.auto}") final String tableStrategy,
                       @Value("${hibernate.dialect}") final String dialect,
                       @Value("hibernate.cache.use_second_level_cache") final String cache,
                       @Value("hibernate.cache.use_query_cache") final String queryCache,
                       @Value("hibernate.cache.use_minimal_puts") final String minPuts,
                       @Value("hibernate.cache.hazelcast.use_lite_member") final String useLiteMember,
                       @Value("hibernate.cache.region_prefix") final String regionPrefix,
                       @Value("hibernate.cache.provider_configuration_file_resource_path") final String resourcePath,
                       @Value("hibernate.cache.region.factory_class") final String factoryClass) {
        final LocalContainerEntityManagerFactoryBean factoryBean;
        factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("ru.iokhin.tm");
        final Properties properties = new Properties();
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.hbm2ddl.auto", tableStrategy);
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.cache.use_second_level_cache", cache);
        properties.put("hibernate.cache.use_query_cache", queryCache);
        properties.put("hibernate.cache.use_minimal_puts", minPuts);
        properties.put("hibernate.cache.hazelcast.use_lite_member", useLiteMember);
        properties.put("hibernate.cache.region_prefix", regionPrefix);
        properties.put("hibernate.cache.provider_configuration_file_resource_path", resourcePath);
        properties.put("hibernate.cache.region.factory_class", factoryClass);
        factoryBean.setJpaProperties(properties);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

}
