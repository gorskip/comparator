package pl.pg.tools.comparator;
import pl.pg.tools.comparator.report.SoutReporter;

import java.util.*;

public class Application {

    public static void main(String[] args) {

//        sql = select * from table;
//        unique key is built of declared keyColumns
//
//        List<Map<String, Object>> records = jdbcTemplate.queryToMap(sql)

        Application app = new Application();
        List<Map<String, Object>> expected = app.buildMapsList();
        List<Map<String, Object>> actual = app.buildMapsList();

//        expected.add(app.additionalMap());

        List<String> keyColumns = Arrays.asList(new String[]{"RECORD_ID", "STATIC_ID"});
        List<String> columnsToBeChecked = Arrays.asList(new String[]{"RECORD_ID", "STATIC_ID", "STATIC_NAME", "NAME"});

        Map<String, Map<String, Object>> expectedRecords = QueryResultUtil.toMap(expected, keyColumns);
        Map<String, Map<String, Object>> actualRecords = QueryResultUtil.toMap(actual, keyColumns);

        new Comparator(expectedRecords, actualRecords)
                .addReporter(new SoutReporter())
                .compareQuantity()
                .compareData(columnsToBeChecked)
                .compareKeys();
    }

    private Map<String, Object> buildMap(int i) {
        Map<String, Object> map = new HashMap<>();
        map.put("RECORD_ID", i);
        map.put("RANDOM_NAME", UUID.randomUUID().toString());
        map.put("STATIC_NAME", "dummy name");
        map.put("STATIC_ID", 8061989);
        return map;
    }

    private Map<String, Object> additionalMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("RECORD_ID", 9999);
        map.put("NAME", UUID.randomUUID().toString());
        map.put("STATIC_NAME", "qwer");
        map.put("STATIC_ID", 8061989);
        return map;
    }

    public List<Map<String, Object>> buildMapsList() {
        List<Map<String, Object>> list = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            list.add(buildMap(i));
        }
        return list;
    }


}
