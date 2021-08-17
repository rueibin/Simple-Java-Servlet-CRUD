package com.rueibin.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtil {
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	static {
		try {
			InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
			Properties props = new Properties();
			props.load(in);

			String driver = props.getProperty("jdbc.driver");
			String url = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");

			in.close();

			ds.setDriverClass(driver);
			ds.setJdbcUrl(url);
			ds.setUser(username);
			ds.setPassword(password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = tl.get();
		if (conn == null) {
			conn = ds.getConnection();
			tl.set(conn);
		}
		return conn;
	}

	public static DataSource getDataSource() {
		return ds;
	}

	public static void closeResource(Statement st, ResultSet rs) {
		closeStatement(st);
		closeResult(rs);
	}

	public static void closeResource(Connection conn, Statement st, ResultSet rs) {
		closeResource(st, rs);
		closeConn(conn);
	}

	private static void closeConn(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn = null;
	}

	private static void closeStatement(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void closeResult(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
