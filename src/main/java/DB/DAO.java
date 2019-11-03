package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DAO {
	protected Connection con;
	protected PreparedStatement pstmt;
	protected Statement stmt;
	protected ResultSet rs;
	static {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected DAO() {
		super();
		con = null;
		pstmt = null;
		stmt = null;
		rs = null;
	}
	protected byte connect() throws SQLException {
		byte errorCode = 0;
		try {
			con = DriverManager.getConnection("jdbc:mariadb://kjasmx.myds.me:3306/petteunteun", "petteunteun", "YXy4cTT2Pn4m3ZPM");
			errorCode = 1;
		} catch (Exception e) {
			System.out.println("db 연결 실패 : " + e.getMessage());
		}
		return errorCode;
	}
	
	protected byte close() {
		byte errorCode = 0;
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (stmt != null && !stmt.isClosed())
				stmt.close();
			if (pstmt != null && !pstmt.isClosed())
				pstmt.close();
			if (con != null && !con.isClosed())
				con.close();
			errorCode = 1;
		} catch (Exception e) {
			System.out.println("db 해제 실패 : " + e.getMessage());
		}
		return errorCode;
	}
	
	protected ResultSet execute(String sql) {
		try {
			connect();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			close();
			e.printStackTrace();
		}
		return rs;
	}
	
	protected byte execute(List<String> sqls) {
		byte errorCode = 0;
		try {
			connect();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			con.setAutoCommit(false);
			for (String sql : sqls) {
				stmt.addBatch(sql);
			}
			stmt.executeBatch();
			con.commit();
			errorCode = 1;
		} catch (SQLException e) {
			close();
			e.printStackTrace();
		}
		return errorCode;
	}
}
