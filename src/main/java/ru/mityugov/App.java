package ru.mityugov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.mityugov.dto.DataSourceOption;
import ru.mityugov.service.DsaService;
import ru.mityugov.util.DataSourceBuilder;
import ru.mityugov.util.OptionMaker;

import javax.sql.DataSource;

@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) throws Exception {
        log.info("Welcome to DSA (Database sequence normalizer).");
        log.info("DSA was started.");

        log.debug("Start validate options.");
        OptionMaker optionMaker = new OptionMaker(args);
        log.debug("Option validation success.");

        DataSourceOption dataSourceOption = optionMaker.getDataSourceOption();
        DataSource dataSource = DataSourceBuilder.newDataSource(dataSourceOption);

        DsaService dsaService = new DsaService(dataSource);
        dsaService.analyseSequences();
        log.info("DSA die...");
    }
}
