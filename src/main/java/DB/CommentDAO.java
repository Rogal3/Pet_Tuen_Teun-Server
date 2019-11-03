package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.Comment;

@Repository("CommentDAO")
public class CommentDAO extends DAO {
	public CommentDAO() {
		super();
	}
	
	private final String tableName = "comment";
	
	public HashMap<String, ArrayList<Comment>> load() {
		HashMap<String, ArrayList<Comment>> map = null;
		String sql = "SELECT * FROM comment";
		try {
			connect();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			map = new HashMap<String, ArrayList<Comment>>();
			while (rs.next()) {
				String key = rs.getString("post");
				Comment model = new Comment(
						rs.getString("writer"),
						rs.getString("content"),
						rs.getString("writeTime"));
				ArrayList<Comment> list = map.get(key);
				if (list == null) {
					list = new ArrayList<Comment>();
					map.put(key, list);
				}
				list.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return map;
	}
	
	private List<CommentDTO> convert(HashMap<String, ArrayList<Comment>> map) {
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		Iterator<String> keys = map.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			List<Comment> comments = map.get(key);
			for (int i = 0; i < comments.size(); ++i) {
				CommentDTO dto = new CommentDTO(
						key + "#" + i,
						comments.get(i).getWriter(),
						key,
						comments.get(i).getContent(),
						comments.get(i).getWriteTime());
				list.add(dto);
			}
		}
		return list;
	}
	
	public byte save(HashMap<String, ArrayList<Comment>> map) {
		byte errorCode = 0;
		List<CommentDTO> list = convert(map);
		List<String> sqls = new ArrayList<String>();
		sqls.add("DELETE FROM " + tableName);
		for (CommentDTO dto : list) {
			String sql = "INSERT INTO " + tableName + " VALUES ('"
					+ dto.getId() + "','"
					+ dto.getWriter() + "','"
					+ dto.getPost() + "','"
					+ dto.getContent() + "','"
					+ dto.getWriteTime() + ")";
			sqls.add(sql);
		}
		try {
			connect();
			for (String sql : sqls) {
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
			}
			errorCode = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return errorCode;
	}
}
