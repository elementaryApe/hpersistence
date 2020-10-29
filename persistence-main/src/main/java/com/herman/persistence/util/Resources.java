package com.herman.persistence.util;

import java.io.InputStream;

/**
 * 文件加载工具
 * @author herman
 * @create 2020-10-28 19:14
 **/
public class Resources {

    /**
     * 通过当前类加载配置文件
     * @param path
     * @return
     */
    public static InputStream getResourceAsSteam(String path){
        return Resources.class.getResourceAsStream(path);
    }
}
