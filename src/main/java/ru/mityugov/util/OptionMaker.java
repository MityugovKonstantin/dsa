package ru.mityugov.util;

import org.apache.commons.cli.*;
import ru.mityugov.dto.DataSourceOption;

public class OptionMaker {

    private final CommandLine current_command_line;

    public OptionMaker(String[] args) throws Exception {
        this.current_command_line = createCommandLine(args);
    }

    public DataSourceOption getDataSourceOption() {
        String databaseUsername = current_command_line.getOptionValue("database-username");
        String databasePassword = current_command_line.getOptionValue("database-password");
        String databaseUrl = current_command_line.getOptionValue("database-url");
        return new DataSourceOption(databaseUsername, databasePassword, databaseUrl);
    }

    private CommandLine createCommandLine(String[] args) {
        Options options = createOptions();
        try {
            return new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            return null;
        }
    }

    private Options createOptions() {
        Options options = new Options();
        options.addOption("n", "database-username", true, "Database username.");
        options.addOption("u", "database-url", true, "Database jdbc url.");
        options.addOption("p", "database-password", true, "Database password.");
        return options;
    }
}
