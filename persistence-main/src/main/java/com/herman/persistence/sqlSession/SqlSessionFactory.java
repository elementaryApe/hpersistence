package com.herman.persistence.sqlSession;

import com.herman.persistence.config.Configuration;

/**
 *  工厂类，用于返回SqlSession的实现类对象
 * @author herman
 * @create 2020-10-28 19:19
 **/
public class SqlSessionFactory {

    private Configuration configuration;


    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
