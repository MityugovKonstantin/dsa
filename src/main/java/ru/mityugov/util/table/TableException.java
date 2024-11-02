package ru.mityugov.util.table;

public class TableException extends RuntimeException {
    public TableException(String message) {
        super("Error when working with a table: " + message);
    }
}
