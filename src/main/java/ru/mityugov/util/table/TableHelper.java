package ru.mityugov.util.table;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;

public class TableHelper {

    private TableStatus status;
    private static final AsciiTable TABLE = new AsciiTable();

    public TableHelper() {
        TABLE
                .getRenderer()
                .setCWC(new CWC_LongestLine());
        this.status = TableStatus.OPENED;
    }

    public void addHeader(Object... headers) throws TableException {
        if (this.status != TableStatus.OPENED)
            throw new TableException("You cannot add a header if it has already been added or the table has been " +
                    "completed.");

        TABLE.addRule();
        TABLE.addRow(headers);
        TABLE.addRule();

        this.status = TableStatus.HEADED;
    }

    public void addRow(Object... content) throws TableException {
        if (this.status != TableStatus.HEADED)
            throw new TableException("You can't add a line until you add a header.");

        TABLE.addRow(content);
    }

    public void competeTable() throws TableException {
        if (this.status != TableStatus.HEADED)
            throw new TableException("A header must be added to generate the table.");

        TABLE.addRule();

        this.status = TableStatus.COMPLETE;
    }

    public String generateTable() throws TableException {
        if (this.status != TableStatus.COMPLETE)
            throw new TableException("To generate a table, it must be completed.");

        return TABLE.render();
    }

    private enum TableStatus {
        OPENED(),
        HEADED(),
        COMPLETE()
    }
}
