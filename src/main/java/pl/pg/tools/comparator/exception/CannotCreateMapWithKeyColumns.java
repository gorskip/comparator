package pl.pg.tools.comparator.exception;

import java.util.List;
import java.util.stream.Collectors;

public class CannotCreateMapWithKeyColumns extends RuntimeException {

    public CannotCreateMapWithKeyColumns(List<String> keyColumns, Exception e) {
        super(keyColumns.stream().collect(Collectors.joining(",")), e);
    }
}
