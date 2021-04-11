package org.username4db.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class AppConfig {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String dbUsername;

	@Value("${spring.datasource.password}")
	private String dbPassword;

	@Bean
	@Profile("!local")
	public DataSource dataSource() throws SQLException {

		LOGGER.info("dbUrl = ", dbUrl);

		if (dbUrl == null || dbUrl.isEmpty()) {
			return new HikariDataSource();
		} else {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl);
			config.setUsername(dbUsername);
			config.setPassword(dbPassword);
			return new HikariDataSource(config);
		}
	}

	@Bean
	@Profile("local")
	public DataSource localDataSource() throws SQLException {

		LOGGER.info("dbUrl = ", dbUrl);

		return DataSourceBuilder //
				.create() //
				.driverClassName(driverClassName) //
				.url(dbUrl) //
				.username(dbUsername) //
				.password(dbPassword) //
				.build();
	}
}
