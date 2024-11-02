package ru.mityugov.util;

import org.junit.Test;
import ru.mityugov.util.table.TableException;

import static org.junit.Assert.assertEquals;

public class TableExceptionTest {

    @Test
    public void whenExceptionWasThrew_thenMessageWasStartedWith() {
        final String message = "Table was created incorrect.";
        final String expectedExceptionMessage = "Error when working with a table: " + message;

        TableException exception = new TableException(message);
        String actualExceptionMessage = exception.getMessage();

        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}
