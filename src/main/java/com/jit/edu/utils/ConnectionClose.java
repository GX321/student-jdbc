package com.jit.edu.utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author:guxiang
 * @version:2019/3/4
 */
public class ConnectionClose {
    private static final Logger logger = Logger.getLogger(ConnectionClose.class);

    public static void close(Connection conn, Statement psmt, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (Exception e) {
            logger.error("关闭连接失败！" + e.getMessage());
        }

        try {
            if (psmt != null)
                psmt.close();
        } catch (Exception e) {
            logger.error("关闭连接失败！" + e.getMessage());
        }

        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            logger.error("关闭连接失败！" + e.getMessage());
        }
    }


    public static void close(Connection conn, Statement psmt) {
        try {
            if (psmt != null)
                psmt.close();
        } catch (Exception e) {
            logger.error("关闭连接失败！" + e.getMessage());
        }

        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            logger.error("关闭连接失败！" + e.getMessage());
        }
    }

}
