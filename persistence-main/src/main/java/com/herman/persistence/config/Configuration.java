package com.herman.persistence.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置信息存储
 *
 * @author herman
 * @create 2020-10-28 19:06
 **/
public class Configuration {

    /**
     * 存储数据信息
     */
    private DataSource dataSource;
    /**
     * key:statementId(各个配置文件中namespace+id) value:MappedStatement
     */
    private Map<String,MappedStatement> mappedStatementMap=new HashMap<String, MappedStatement>();


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
