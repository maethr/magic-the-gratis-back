package com.alolorsus.mtgdb;

import java.util.HashMap;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.alolorsus.mtgdb", entityManagerFactoryRef = "mtgdbEntityManager", transactionManagerRef = "mtgdbTransactionManager")
public class MtgDBConfig {

	private final String datasource = "spring.mtgdb.datasource";
	
	@Autowired
	private Environment env;
	
	@Bean
	@ConfigurationProperties(prefix = datasource)
	public DataSource mtgdbDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty(datasource + ".driver-class-name"));
		dataSource.setUrl(env.getProperty(datasource + ".url"));
		dataSource.setUsername(env.getProperty(datasource + ".username"));
		dataSource.setPassword(env.getProperty(datasource + ".password"));

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean mtgdbEntityManager () {
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(mtgdbDataSource());
		em.setPackagesToScan(new String[] { "com.alolorsus.mtgdb" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect", env.getProperty("spring.jpa.database-platform"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	public PlatformTransactionManager mtgdbTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(mtgdbEntityManager().getObject());
		return transactionManager;
	}
}
