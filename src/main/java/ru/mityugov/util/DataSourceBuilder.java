package ru.mityugov.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.mityugov.dto.DataSourceOption;

import javax.sql.DataSource;

public class DataSourceBuilder {

    private static final Logger log = LoggerFactory.getLogger(DataSourceBuilder.class);

    public static DataSource newDataSource(DataSourceOption dataSourceOption) throws Exception {
        log.debug("Start creating new data source.");

        validateUsername(dataSourceOption.username());
        log.debug("Database username is valid.");
        validatePassword(dataSourceOption.password());
        log.debug("Database password is valid.");
        validateUrl(dataSourceOption.url());
        log.debug("Database url is valid.");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(dataSourceOption.username());
        dataSource.setPassword(dataSourceOption.password());
        dataSource.setUrl(dataSourceOption.url());

        log.debug("Creating new data source was finished successfully.");

        return dataSource;
    }

    private static void validateUrl(String url) throws Exception {
        if (url == null) {
            String message = "Database url can't be null.";
            log.error(message);
            throw new Exception(message);
        }

        if (url.isBlank()) {
            String message = "Database url can't be blank.";
            log.error(message);
            throw new Exception(message);
        }
    }

    private static void validatePassword(String password) throws Exception {
        if (password == null) {
            String message = "Database password can't be null.";
            log.error(message);
            throw new Exception(message);
        }

        if (password.isBlank()) {
            String message = "Database password can't be blank.";
            log.error(message);
            throw new Exception(message);
        }
    }

    private static void validateUsername(String username) throws Exception {
        if (username == null) {
            String message = "Database username can't be null.";
            log.error(message);
            throw new Exception(message);
        }

        if (username.isBlank()) {
            String message = "Database username can't be blank.";
            log.error(message);
            throw new Exception(message);
        }
    }
}
