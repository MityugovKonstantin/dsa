package ru.mityugov.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mityugov.util.table.TableHelper;

import java.util.ArrayList;
import java.util.List;

public class SequenceInfoHelper {

    private static final Logger log = LoggerFactory.getLogger(SequenceInfoHelper.class);
    private final List<SequenceInfo> sequenceInfoList = new ArrayList<>();

    public void addSequenceInfo(String tableName, long maxId, String sequenceName, long lastValue) {
        sequenceInfoList.add(new SequenceInfo(tableName, maxId, sequenceName, lastValue));
    }

    public void printFullSequenceInfo() throws Exception {
        TableHelper tableHelper = new TableHelper();

        tableHelper.addHeader("table name", "max id", "sequence name", "last value");
        sequenceInfoList
                .forEach(info -> tableHelper.addRow(info.tableName, info.maxId, info.sequenceName, info.lastValue()));
        tableHelper.competeTable();

        String table = tableHelper.generateTable();

        log.info('\n' + table);
    }

    private record SequenceInfo(String tableName, Long maxId, String sequenceName, Long lastValue) {
    }
}
