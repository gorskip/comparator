package pl.pg.tools.comparator;

import pl.pg.tools.comparator.exception.CannotCreateMapWithKeyColumns;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryResultUtil {

    public static Map<String, Map<String, Object>> toMap(List<Map<String, Object>> list, List<String> keyColumns) {
        try {
            return list.stream()
                    .collect(Collectors.toMap(x -> createKey(x, keyColumns), x -> x));
        } catch(Exception e) {
            throw new CannotCreateMapWithKeyColumns(keyColumns, e);
        }
    }

    public static String createKey(Map<String, Object> x, List<String> keyColumns) {
        return keyColumns.stream()
                .map(keyColumn -> String.valueOf(x.get(keyColumn)))
                .collect(Collectors.joining("-"));
    }
}
