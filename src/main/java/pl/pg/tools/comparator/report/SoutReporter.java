package pl.pg.tools.comparator.report;

import java.util.Set;

public class SoutReporter implements Reporter {
    @Override
    public void reportAdditionalKeys(String key, String columnName, Object expectedValue, Object actualValue) {
        System.out.println("Key: [" + key + "] " + "Column name: [" + columnName +"]  Expected: [" + String.valueOf(expectedValue) + "]  Actual: [" + String.valueOf(actualValue) + "]");
    }

    @Override
    public void reportAdditionalKeys(Set<String> keys, String message) {
        System.out.println(message + ": " + keys.size());
        keys.forEach(key -> System.out.println(key));
    }

    @Override
    public void reportCount(int expected, int actual) {
        System.out.println("Expected records: [" + expected + "]  Actual records: [" + actual +"]");
    }
}
