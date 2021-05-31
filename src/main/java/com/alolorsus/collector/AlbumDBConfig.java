package com.alolorsus.collector;

import java.util.HashMap;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(basePackages = "com.alolorsus.collector", entityManagerFactoryRef = "albumEntityManager", transactionManagerRef = "albumTransactionManager")
public class AlbumDBConfig {

	private final String datasource = "spring.album.datasource";
	
	@Autowired
	private Environment env;

	@Primary
	@Bean
	@ConfigurationProperties(prefix = datasource)
	public DataSource albumDataSource() {
	
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty(datasource + ".driver-class-name"));
		dataSource.setUrl(env.getProperty(datasource + ".url"));
		dataSource.setUsername(env.getProperty(datasource + ".username"));
		dataSource.setPassword(env.getProperty(datasource + ".password"));

		return dataSource;
	}

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean albumEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(albumDataSource());
		em.setPackagesToScan(new String[] { "com.alolorsus.collector" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect", env.getProperty("spring.jpa.database-platform"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Primary
	@Bean
	public PlatformTransactionManager albumTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(albumEntityManager().getObject());
		return transactionManager;
	}
}