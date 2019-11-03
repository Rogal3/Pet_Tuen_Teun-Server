package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.Member;

@Repository("MemberDAO")
public class MemberDAO extends DAO {	
	private static class LazyHolder {
		public static final MemberDAO INSTANCE = new MemberDAO();
	}
	
	private MemberDAO() {
		super();
	}

	public static MemberDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private List<MemberDTO> convert(HashMap<String, Member> map) {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			Member model = map.get(key);
			MemberDTO dto = new MemberDTO(
					model.getId(),
					model.getPassword(),
					model.getName(),
					model.getAddress(),
					model.getPhoneNum());;
			list.add(dto);
		}
		return list;
	}
	
	public HashMap<String, Member> load() {
		HashMap<String, Member> map = null;
		String sql = "SELECT * FROM member";
		try {
			connect();
			stmt = con.createStatement();
			if (stmt != null) {
				rs = stmt.executeQuery(sql);
				map = new HashMap<String, Member>();
				while (rs.next()) {
					Member member = new Member(
							rs.getString("id"),
							rs.getString("password"),
							rs.getString("name"),
							rs.getString("address"),
							rs.getString("phoneNum"));
					map.put(rs.getString("id"), member);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return map;
	}

	public byte save(HashMap<String, Member> map) {
		List<String> sqls = new ArrayList<String>();
		List<MemberDTO> dtoList = convert(map);
		sqls.add("DELETE FROM member");
		for (MemberDTO dto : dtoList) {
			sqls.add("INSERT INTO member VALUES ('" + dto.getId() + "','" + dto.getPassword() + "','" + dto.getName() + "','" + dto.getAddress() + "','" + dto.getPhoneNum() + "')");
		}
		try {
			connect();
			for (String sql : sqls) {
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 1;
	}
	
	public byte alterSave(HashMap<String, Member> map) {
		List<String> sqls = new ArrayList<String>();
		List<MemberDTO> dtoList = convert(map);
		sqls.add("DELETE FROM member");
		for (MemberDTO dto : dtoList) {
			sqls.add("INSERT INTO member VALUES ('" + dto.getId() + "','" + dto.getPassword() + "','" + dto.getName() + "','" + dto.getAddress() + "','" + dto.getPhoneNum() + "')");
		}
		try {
			connect();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			con.setAutoCommit(false);
			for (String sql : sqls) {
				stmt.addBatch(sql);
			}
			stmt.executeBatch();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 1;
	}
}
