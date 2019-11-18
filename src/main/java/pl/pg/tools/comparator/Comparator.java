package pl.pg.tools.comparator;

import pl.pg.tools.comparator.exception.ColumnNotFound;
import pl.pg.tools.comparator.report.Reporter;

import java.util.*;

public class Comparator {

    private final Map<String, Map<String, Object>> expectedRecords;
    private final Map<String, Map<String, Object>> actualRecords;
    private List<Reporter> reporters = new ArrayList<>();

    public Comparator(Map<String, Map<String, Object>> expectedRecords, Map<String, Map<String, Object>> actualRecords) {
        this.expectedRecords = expectedRecords;
        this.actualRecords = actualRecords;
    }

    public Comparator addReporter(Reporter reporter) {
        this.reporters.add(reporter);
        return this;
    }

    public Comparator compareData(List<String> columnsToBeChecked) {
        expectedRecords.entrySet().forEach(expectedEntry -> {
            String expectedKey = expectedEntry.getKey();
            if(actualRecords.containsKey(expectedKey)) {
                compareRecord(expectedEntry.getValue(), actualRecords.get(expectedKey), columnsToBeChecked, expectedKey);
            }
        });
        return this;
    }

    public Comparator compareKeys() {
        reporters.forEach(reporter -> {
            reporter.reportAdditionalKeys(getMissingKeys(), "Missing keys");
            reporter.reportAdditionalKeys(getUnexpectedKeys(), "Unexpected keys");
        });
        return this;
    }

    public Comparator compareQuantity() {
        reporters.forEach(reporter -> reporter.reportCount(expectedRecords.keySet().size(), actualRecords.keySet().size()));
        return this;
    }

    private void compareRecord(Map<String, Object> expectedRecord, Map<String, Object> actualRecord, List<String> columnsToBeChecked, String key) {
        for(String columnName: columnsToBeChecked) {
            Object expectedValue = getColumnValue(expectedRecord, columnName);
            Object actualValue = getColumnValue(actualRecord, columnName);
            if(!expectedValue.equals(actualValue)) {
                report(key, columnName, expectedValue, actualValue);
            }
        }
    }

    private void report(String key, String columnName, Object expectedValue, Object actualValue) {
        reporters.forEach(reporter -> reporter.reportAdditionalKeys(key, columnName, expectedValue, actualValue));
    }

    private Object getColumnValue(Map<String, Object> record, String columnName) {
        if(record.containsKey(columnName)) {
            return record.get(columnName);
        }
        throw new ColumnNotFound(columnName);
    }

    private Set<String> getMissingKeys() {
        Set<String> expectedKeys = expectedRecords.keySet();
        Set<String> actualKeys = actualRecords.keySet();
        Set<String> resultKeys = new HashSet<>(expectedKeys);
        resultKeys.removeAll(actualKeys);
        return resultKeys;
    }

    private Set<String> getUnexpectedKeys() {
        Set<String> expectedKeys = expectedRecords.keySet();
        Set<String> actualKeys = actualRecords.keySet();
        Set<String> resultKeys = new HashSet<>(actualKeys);
        resultKeys.removeAll(expectedKeys);
        return resultKeys;
    }
}
