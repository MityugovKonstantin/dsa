package ru.mityugov.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SequenceInfoHelper {

    private static final Logger log = LoggerFactory.getLogger(SequenceInfoHelper.class);
    private final List<SequenceInfo> sequenceInfoList = new ArrayList<>();

    public void addSequenceInfo(String tableName, long maxId, String sequenceName, long lastValue) {
        sequenceInfoList.add(new SequenceInfo(tableName, maxId, sequenceName, lastValue));
    }

    public void printFullSequenceInfo() throws Exception {
        int maxTableNameSize = sequenceInfoList.stream()
                .map(si -> si.tableName)
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(() -> new Exception(""))
                .length();

        int maxMaxIdSize = sequenceInfoList.stream()
                .map(si -> si.maxId)
                .max(Comparator.comparingLong(Long::longValue))
                .orElseThrow(() -> new Exception(""))
                .toString()
                .length();

        int maxSequenceNameSize = sequenceInfoList.stream()
                .map(si -> si.sequenceName)
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(() -> new Exception(""))
                .length();

        int maxLastValueSize = sequenceInfoList.stream()
                .map(si -> si.lastValue)
                .max(Comparator.comparingLong(Long::longValue))
                .orElseThrow(() -> new Exception(""))
                .toString()
                .length();

        StringBuilder fullInfoBuilder = new StringBuilder();
        fullInfoBuilder.append("\ntable_name\t|\tmax_id\t|\tsequence_name\t|\tlast_value\n");

        for (SequenceInfo sequenceInfo : sequenceInfoList) {
            StringBuilder sequenceInfoBuilder = new StringBuilder();

            sequenceInfoBuilder.append(sequenceInfo.tableName);
            int tableNameDifference = maxTableNameSize - sequenceInfo.tableName.length();
            if (tableNameDifference > 0) {
                sequenceInfoBuilder.append(" ".repeat(tableNameDifference));
            }

            sequenceInfoBuilder.append('|');
            sequenceInfoBuilder.append('\t');

            sequenceInfoBuilder.append(sequenceInfo.maxId);
            int maxIdDifference = maxMaxIdSize - sequenceInfo.maxId.toString().length();
            if (maxIdDifference > 0) {
                sequenceInfoBuilder.append(" ".repeat(maxIdDifference));
            }

            sequenceInfoBuilder.append('|');
            sequenceInfoBuilder.append('\t');

            sequenceInfoBuilder.append(sequenceInfo.sequenceName);
            int sequenceNameDifference = maxSequenceNameSize - sequenceInfo.sequenceName.length();
            if (sequenceNameDifference > 0) {
                sequenceInfoBuilder.append(" ".repeat(sequenceNameDifference));
            }

            sequenceInfoBuilder.append('|');
            sequenceInfoBuilder.append('\t');

            sequenceInfoBuilder.append(sequenceInfo.lastValue);
            int lastValueDifference = maxLastValueSize - sequenceInfo.lastValue.toString().length();
            if (lastValueDifference > 0) {
                sequenceInfoBuilder.append(" ".repeat(lastValueDifference));
            }

            sequenceInfoBuilder.append('\n');
            fullInfoBuilder.append(sequenceInfoBuilder);
        }

        log.info(fullInfoBuilder.toString());
    }

    private record SequenceInfo(String tableName, Long maxId, String sequenceName, Long lastValue) {
    }
}
