package web.utils;
import com.mchange.v1.db.sql.ConnectionUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public final class JDBCUtils {
    private static final ComboPooledDataSource datasource = new ComboPooledDataSource();

    static {
        Properties props = new Properties();
        InputStream in = ConnectionUtils.class.getClassLoader().getResourceAsStream("ConnectionUtils.c3p0.properties");
        System.out.println("c3p0 connects successfully step1");
        try {
            props.load(in);
            datasource.setDriverClass(props.getProperty("driverClass"));
            datasource.setJdbcUrl(props.getProperty("jdbcUrl"));
            datasource.setUser(props.getProperty("user"));
            datasource.setPassword(props.getProperty("password"));
            System.out.println("数据库连接成功2");
        } catch (IOException e) {
            System.out.println("test for connection failed");
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = datasource.getConnection();
        System.out.println("c3p0 connects successfully step2");
        return conn;
    }

    public static void release(Connection conn,PreparedStatement pstmt,ResultSet resultSet) {
        releaseCP(conn, pstmt);
        if(resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void releaseCP(Connection conn,PreparedStatement pstmt) {
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(pstmt!=null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

