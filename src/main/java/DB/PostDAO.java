package DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.Post;

@Repository("PostDAO")
public class PostDAO extends DAO {
	private static class LazyHolder {
		public static final PostDAO INSTANCE = new PostDAO();
	}
	private PostDAO() {
		super();
	}
	public static PostDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private final String tableName = "post";
	
	private List<PostDTO> convert(HashMap<String, ArrayList<Post>> map) {
		List<PostDTO> list = new ArrayList<PostDTO>();
		Iterator<String> keys = map.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			List<Post> models = map.get(key);
			for (Post model : models) {
				PostDTO dto = new PostDTO(
						model.getId(),
						model.getType(),
						model.getTitle(),
						model.getWriter(),
						model.getContent(),
						model.getWriteTime());
				list.add(dto);
			}
		}
		return list;
	}
	
	public HashMap<String, ArrayList<Post>> load() {
		HashMap<String, ArrayList<Post>> map = null;
		String sql = "SELECT * FROM " + tableName;
		try {
			rs = execute(sql);
			map = new HashMap<String, ArrayList<Post>>();
			while (rs.next()) {
				Post model = new Post(
						rs.getString("id"),
						rs.getString("type"),
						rs.getString("title"),
						rs.getString("writer"),
						rs.getString("content"),
						rs.getString("writeTime"));
				String type = rs.getString("type");
				ArrayList<Post> list = map.get(type);
				if (list == null) {
					list = new ArrayList<Post>();
					map.put(type, list);
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
	
	public byte save(HashMap<String, ArrayList<Post>> map) {
		byte errorCode = 0;
		List<PostDTO> dtoList = convert(map);
		List<String> sqls = new ArrayList<String>();
		sqls.add("DELETE FROM " + tableName);
		for (PostDTO dto : dtoList) {
			sqls.add("INSERT INTO " + tableName + " VALUES ("
					+ dto.getId() + "','"
					+ dto.getType() + "','"
					+ dto.getTitle() + "','"
					+ dto.getWriter() + "','"
					+ dto.getContent() + "','"
					+ dto.getWriteTime() + "')");
		}
		try {
			execute(sqls);
			errorCode = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return errorCode;
	}
}
