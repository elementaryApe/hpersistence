package com.herman.persistence.config;

/**
 * mapper中 各个子节点
 * @author herman
 * @create 2020-10-28 19:09
 **/
public class MappedStatement {

    /**
     * id
     */
    private String id;

    /**
     * sql语句
     */
    private String sql;

    /**
     * 请求参数
     */
    private Class<?> parameterType;
    /**
     * 输出参数
     */
    private Class<?> resultType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Class<?> getParameterType() {
        return parameterType;
    }

    public void setParameterType(Class<?> parameterType) {
        this.parameterType = parameterType;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }
}
