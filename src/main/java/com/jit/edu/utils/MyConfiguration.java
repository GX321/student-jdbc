package com.jit.edu.utils;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取数据库配置信息获得连接
 *
 * @author:guxiang
 * @version:2019/3/3
 */
public class MyConfiguration {
    private static final Logger logger = Logger.getLogger(MyConfiguration.class);


    /**
     * 读取xml信息返回数据库连接
     */

    public Connection getConnection(String resource) {
        try {
            String fileName = this.getClass().getClassLoader().getResource(resource).getPath();
            File file = new File(fileName);
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Element root = document.getRootElement();
            return evalDataSource(root);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("error occured while evaling " + resource);

        }


    }

    /**
     * 读取xml信息返回sql语句Map
     *
     * @param resource
     * @return
     */
    public Map<String, String> getSql(String resource) {
        try {
            String fileName = this.getClass().getClassLoader().getResource(resource).getPath();
            File file = new File(fileName);
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Element root = document.getRootElement();
            if (!root.getName().equals("sql")) {
                throw new RuntimeException("root should not be <sql>");
            }
            Map<String, String> sqlStatement = new HashMap<String, String>();
            for (Object item : root.elements()) {
                Element i = (Element) item;
                sqlStatement.put(i.getName(), getValue(i));
            }
            return sqlStatement;

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("error occured while evaling xml" + resource);

        }
    }

    /**
     * 解析数据源信息
     *
     * @param node
     * @return
     * @throws ClassNotFoundException
     */
    private static Connection evalDataSource(Element node) throws ClassNotFoundException {
        if (!node.getName().equals("database")) {
            throw new RuntimeException("root should not be <database>");
        }
        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;
        //获取属性节点
        for (Object item : node.elements("property")) {
            Element i = (Element) item;
            String value = getValue(i);
            String name = i.attributeValue("name");
            if (name == null || value == null) {
                throw new RuntimeException("[database]: <property>should contain name and value");
            }
            //赋值
            switch (name) {
                case "url":
                    url = value;
                    break;
                case "username":
                    username = value;
                    break;
                case "password":
                    password = value;
                    break;
                case "driverClassName":
                    driverClassName = value;
                    break;
                default:
                    throw new RuntimeException("[database]: <property> unknown name");
            }
        }
        Class.forName(driverClassName);
        Connection connection = null;
        try {
            //建立数据库连接
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return connection;

    }


    /**
     * 获取xml节点value
     *
     * @param node
     * @return
     */
    private static String getValue(Element node) {
        return node.hasContent() ? node.getText() : node.attributeValue("value");
    }

    public MyConfiguration() {
    }
}
