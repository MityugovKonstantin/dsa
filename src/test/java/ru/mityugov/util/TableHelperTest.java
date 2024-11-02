package ru.mityugov.util;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import org.junit.Test;
import ru.mityugov.util.table.TableException;
import ru.mityugov.util.table.TableHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TableHelperTest {

    @Test
    public void whenAddRowToOpenedTable_thenThrowTableException() {
        TableHelper tableHelper = new TableHelper();
        assertThrows(TableException.class, () -> tableHelper.addRow("test content 1", "test content 2"));
    }

    @Test
    public void whenAddHeaderToHeadedTable_thenThrowTableException() {
        TableHelper tableHelper = new TableHelper();
        tableHelper.addHeader("test header 1", "test header 2");
        assertThrows(TableException.class, () -> tableHelper.addHeader("test header 3", "test header 4"));
    }

    @Test
    public void whenCompleteOpenedTable_thenThrowTableException() {
        TableHelper tableHelper = new TableHelper();
        assertThrows(TableException.class, tableHelper::competeTable);
    }

    @Test
    public void whenGenerateOpenedTable_thenThrowTableException() {
        TableHelper tableHelper = new TableHelper();
        assertThrows(TableException.class, tableHelper::generateTable);
    }

    @Test
    public void whenGenerateHeadedTable_thenThrowTableException() {
        TableHelper tableHelper = new TableHelper();
        tableHelper.addHeader("test header 1", "test header 2");
        assertThrows(TableException.class, tableHelper::generateTable);
    }

    @Test
    public void whenCompleteCompletedTable_thenThrowTableException() {
        TableHelper tableHelper = new TableHelper();
        tableHelper.addHeader("test header 1", "test header 2");
        tableHelper.competeTable();
        assertThrows(TableException.class, tableHelper::competeTable);
    }

    @Test
    public void whenAddRowToCompletedTable_thenThrowTableException() {
        TableHelper tableHelper = new TableHelper();
        tableHelper.addHeader("test header 1", "test header 2");
        tableHelper.competeTable();
        assertThrows(TableException.class, () -> tableHelper.addRow("test content 1", "test content 2"));
    }

    @Test
    public void whenAddHeaderToCompletedTable_thenThrowTableException() {
        TableHelper tableHelper = new TableHelper();
        tableHelper.addHeader("test header 1", "test header 2");
        tableHelper.competeTable();
        assertThrows(TableException.class, () -> tableHelper.addHeader("test header 3", "test header 4"));
    }

    @Test
    public void whenCreatingTableIsValid_thenGenerateTable() {
        final String expectedTable = getExpectedTable();
        TableHelper tableHelper = new TableHelper();
        tableHelper.addHeader("header 1", "header 2", "header 3");
        tableHelper.addRow("content 1", "content 2", "content 3");
        tableHelper.addRow("content 4", "content 5", "content 6");
        tableHelper.competeTable();
        String actualTable = tableHelper.generateTable();

        assertEquals(expectedTable, actualTable);
    }

    private String getExpectedTable() {
        AsciiTable asciiTable = new AsciiTable();
        asciiTable
                .getRenderer()
                .setCWC(new CWC_LongestLine());
        asciiTable.addRule();
        asciiTable.addRow("header 1", "header 2", "header 3");
        asciiTable.addRule();
        asciiTable.addRow("content 1", "content 2", "content 3");
        asciiTable.addRow("content 4", "content 5", "content 6");
        asciiTable.addRule();
        return asciiTable.render();
    }
}
