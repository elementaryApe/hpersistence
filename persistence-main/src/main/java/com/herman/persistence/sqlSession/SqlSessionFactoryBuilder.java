package com.herman.persistence.sqlSession;

import com.herman.persistence.config.Configuration;
import com.herman.persistence.util.Resources;
import com.herman.persistence.util.XMLMapperBuilder;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 构建工厂，并在这里读取一次xml，避免xml重复读取
 *
 * @author herman
 * @create 2020-10-28 19:16
 **/
public class SqlSessionFactoryBuilder {

    /**
     * 配置信息
     */
    private Configuration configuration;

    public SqlSessionFactoryBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 构建工厂的入口，让用户传入配置文件的文件流
     * @param inputStream
     * @return
     */
    public SqlSessionFactory build(InputStream inputStream) throws PropertyVetoException, ClassNotFoundException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();
        // 读取数据源信息xml为Configuration对象
        Configuration configuration = loadXmlConfig(inputStream);
        sqlSessionFactory.setConfiguration(configuration);
        return sqlSessionFactory;
    }


    /**
     * 读取xml信息为Configuration对象
     * @param inputStream
     * @return
     */
    private Configuration loadXmlConfig(InputStream inputStream) throws PropertyVetoException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            Properties properties = new Properties();

            //读取数据源配置信息
            List<Element> selectNodes = rootElement.selectNodes("//property");

            if(selectNodes != null && selectNodes.size() > 0) {
                for (int i = 0; i < selectNodes.size(); i++) {
                    Element element =  selectNodes.get(i);
                    String name = element.attributeValue("name");
                    String value = element.attributeValue("value");
                    properties.setProperty(name,value);
                }
            }
            //连接池
            ComboPooledDataSource comboPooledDataSource = new   ComboPooledDataSource();
            comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
            comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
            comboPooledDataSource.setUser(properties.getProperty("username"));
            comboPooledDataSource.setPassword(properties.getProperty("password"));

            configuration.setDataSource(comboPooledDataSource);

            //加载mapper 配置
            List mapperElements = rootElement.selectNodes("//mapper");
            XMLMapperBuilder xmlMapperBuilder = new   XMLMapperBuilder(configuration);
            for (Object mapperElement : mapperElements) {
                String mapperPath = ((Element)mapperElement).attributeValue("resource");
                InputStream resourceAsSteam =   Resources.getResourceAsSteam(mapperPath);
                xmlMapperBuilder.parse(resourceAsSteam);
            }
            return configuration;
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return configuration;
    }

}
