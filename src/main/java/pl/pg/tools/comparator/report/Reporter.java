package pl.pg.tools.comparator.report;

import java.util.Set;

public interface Reporter {

    void reportAdditionalKeys(String key, String columnName, Object expectedValue, Object actualValue);

    void reportAdditionalKeys(Set<String> keys, String message);

    void reportCount(int expected, int actual);
}
