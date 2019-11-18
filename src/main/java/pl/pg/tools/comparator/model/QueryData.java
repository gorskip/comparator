package pl.pg.tools.comparator.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "queryData")
@XmlAccessorType(XmlAccessType.FIELD)
public class QueryData {

    @XmlElement(name = "name")
    private String name;
    private String query;
    private String keyColumns;
    private String columnsToBeChecked;
}
