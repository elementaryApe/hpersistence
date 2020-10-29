package com.herman.persistence.util;

import com.herman.persistence.config.Configuration;
import com.herman.persistence.config.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * mapper解析处理器
 * @author herman
 * @create 2020-10-28 19:29
 **/
public class XMLMapperBuilder {

    //需要将mapper中信息写入到configuration 中
    private Configuration configuration;


    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 加载mapper中信息
     * @param inputStream
     * @throws DocumentException
     * @throws ClassNotFoundException
     */
    public void parse(InputStream inputStream) throws DocumentException,
            ClassNotFoundException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> select = rootElement.selectNodes("select");
        for (Element element : select) {
            String id = element.attributeValue("id");
            String parameterType = element.attributeValue("paramterType");
            String resultType = element.attributeValue("resultType");
            Class<?> parameterTypeClass = getClassType(parameterType);
            Class<?> resultTypeClass = getClassType(resultType);
            String key = namespace +"."+id;
            String textTrim = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParameterType(parameterTypeClass);
            mappedStatement.setResultType(resultTypeClass);
            mappedStatement.setSql(textTrim);
            configuration.getMappedStatementMap().put(key,mappedStatement);
        }
    }


    private Class<?> getClassType(String parameterType) throws
            ClassNotFoundException {
        Class<?> aClass = Class.forName(parameterType);
        return aClass;
    }

}
