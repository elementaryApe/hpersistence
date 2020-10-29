package com.herman.persistence.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class JdbcTest {

    /**
     *
     */
    @Test
    public void test() {
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            /**
             * 问题一：频繁获取/释放数据库连接，影响数据库和应用性能
             * 解决：数据库连接池技术，C3P0,DRUID（阿里巴巴荣誉出品，号称前无古人后无来者世界最强没有之一）
             */
            Connection connection = DriverManager.getConnection("jdbc:mysql://47.99.112.89:3306/mybatis?characterEncoding=utf-8", "root", "Zmq745210");
            /**
             * 问题二：sql语句硬编码，后期难以维护
             * 解决：若sql语句和java代码分离，比如sql写在配置文件中。Mybatis就是这么干的
             */

            String sql = "select * from user_info where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            /**
             * 问题三：sql语句where条件和占位符一一对应，后期难以维护
             */
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            /**
             * 问题四：结果集解析麻烦，查询列硬编码
             * 期望：如果单条记录直接返回实体对象，如果多条记录返回实体的集合
             */
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                System.out.println("id:"+id+", username："+username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
