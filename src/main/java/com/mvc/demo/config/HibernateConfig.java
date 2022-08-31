package com.mvc.demo.config;

import java.util.Properties;
import static com.mvc.demo.constant.PackageNameConstants.COM_MVC_DEMO_ENTITY;
import static com.mvc.demo.constant.PackageNameConstants.COM_MVC_DEMO_SERVICE;
import static com.mvc.demo.constant.PackageNameConstants.COM_MVC_DEMO_DAO;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@Import(ConfigProperties.class)
@ComponentScan({COM_MVC_DEMO_ENTITY, COM_MVC_DEMO_SERVICE, COM_MVC_DEMO_DAO})
@EnableTransactionManagement
public class HibernateConfig {
	
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Value("${hibernate.dialect}")
	private String dialect;
	
	@Value("${hibernate.show_sql}")
	private String showSQL;
	
	@Value("${hibernate.format_sql}")
	private String formateSQL;
	
	@Value("${hibernate.autocommit}")
	private String autoCommit;
	
	@Value("${hibernate.cache_provider_config}")
	private String cacheProviderConfig;
	
	@Value("${hibernate.pool_size}")
	private String poolSize;
	
	@Value("${hibernate.current_session_context_class}")
	private String currentSessionContextClass;
	
	@Value("${hibernate.hbm2ddl_auto_update}")
	private String hbm2ddlAutoUpdate;
	
	@Value("${hibernate.hbm2ddl_auto_create_drop}")
	private String hbm2ddlAutoCreateDrop;
	
	
	@Primary
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(getDriverClassName());
		dataSource.setUrl(getUrl());
		dataSource.setUsername(getUsername());
		dataSource.setPassword(getPassword());
		return dataSource;
	}
	
	@Autowired
	@Primary
	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder localSessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource);
		localSessionFactoryBuilder.scanPackages(new String[] { COM_MVC_DEMO_ENTITY }); 
		localSessionFactoryBuilder.addProperties(getHibernateProperties());
		return localSessionFactoryBuilder.buildSessionFactory();
	}
		
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		
		properties.put(Environment.DIALECT, getDialect());
		properties.put(Environment.SHOW_SQL, getShowSQL());
		properties.put(Environment.FORMAT_SQL, getFormateSQL());
		properties.put(Environment.AUTOCOMMIT, getAutoCommit());
		properties.put(Environment.CACHE_PROVIDER_CONFIG, getCacheProviderConfig());
		properties.put(Environment.POOL_SIZE, getPoolSize());
		properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, getCurrentSessionContextClass());
		properties.put(Environment.HBM2DDL_AUTO, getHbm2ddlAutoUpdate());
//		properties.put(Environment.HBM2DDL_AUTO, getHbm2ddlAutoCreateDrop());
		
		return properties;
	}
}
