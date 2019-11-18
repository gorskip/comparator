package pl.pg.tools.comparator.model;

import lombok.Data;

import java.util.List;

@Data
public class TestData {

    private int resultLimit;
    private List<QueryData> queryData;
}
