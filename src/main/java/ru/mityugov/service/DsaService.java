package ru.mityugov.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.mityugov.util.SequenceInfoHelper;

import javax.sql.DataSource;
import java.util.List;

public class DsaService {

    private static final Logger log = LoggerFactory.getLogger(DsaService.class);
    private static final SequenceInfoHelper sequenceInfoHelper = new SequenceInfoHelper();
    private final JdbcTemplate jdbcTemplate;
    private final List<String> tableNames;

    public DsaService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.tableNames = findTableNames();
        log.info("{} tables was found.", tableNames.size());
        log.debug(String.join("\n", tableNames));
    }

    public void analyseSequences() throws Exception {
        log.info("Start analysing sequences.");
        for (String tableName : tableNames) {
            log.info("Start analysing table '{}'.", tableName);
            long maxId = findTableMaxId(tableName);
            log.info("Max id for table '{}' is {}.", tableName, maxId);
            String tableIdSequenceName = findTableIdSequenceName(tableName);
            log.info("Sequence name for '{}' table id is {}.", tableName, tableIdSequenceName);
            long sequenceLastValue = findSequenceLastValue(tableIdSequenceName);
            log.info("Last value of '{}' sequence is {}.", tableIdSequenceName, sequenceLastValue);
            sequenceInfoHelper.addSequenceInfo(tableName, maxId, tableIdSequenceName, sequenceLastValue);
        }
        sequenceInfoHelper.printFullSequenceInfo();
    }

    private long findSequenceLastValue(String sequenceName) throws Exception {
        if (sequenceName == null) {
            throw new Exception("Не указано название перечисления.");
        }

        String request = String.format(
                "select last_value from pg_sequences where schemaname = 'public' and sequencename = '%s';",
                sequenceName
        );
        Integer lastValue = jdbcTemplate.query(request, rs -> rs.next() ? rs.getInt(1) : null);

        if (lastValue == null) {
            throw new Exception(String.format("Последнее значение перечисления '%s' не найден.", sequenceName));
        }

        return lastValue;
    }

    private String findTableIdSequenceName(String tableName) throws Exception {
        if (tableName == null) {
            throw new Exception("Не указано название таблицы.");
        }

        String sequenceNameRequest = String.format("select pg_get_serial_sequence('%s', 'id');", tableName);
        String sequenceName = jdbcTemplate.query(sequenceNameRequest, rs -> rs.next() ? rs.getString(1) : null);

        if (sequenceName == null) {
            throw new Exception(String.format("Название перечисления для таблицы '%s' не найдено.",
                    tableName));
        }

        return sequenceName.substring(sequenceName.indexOf('.') + 1);
    }

    private long findTableMaxId(String tableName) throws Exception {
        if (tableName == null) {
            throw new Exception("Не указано название таблицы.");
        }

        String request = String.format("select max(id) from %s;", tableName);
        Integer maxId = jdbcTemplate.query(request, rs -> rs.next() ? rs.getInt(1) : null);

        if (maxId == null) {
            throw new Exception(String.format("Максимальный id для таблицы '%s' не найден.", tableName));
        }

        return maxId;
    }

    private List<String> findTableNames() {
        String request = "select table_name\n" +
                "from information_schema.tables\n" +
                "where table_schema = 'public'\n" +
                "  and table_type = 'BASE TABLE'\n" +
                "  and table_name != 'databasechangeloglock'\n" +
                "  and table_name != 'databasechangelog';";
        return jdbcTemplate.queryForList(request, String.class);
    }
}
