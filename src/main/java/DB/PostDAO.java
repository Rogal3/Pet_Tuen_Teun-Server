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
	public PostDAO() {
		super();
	}
	
	private final String tableName = "post";
	
	public List<Post> load() {
		List<Post> list = null;
		String sql = "SELECT * FROM " + tableName;
		try {
			rs = execute(sql);
			list = new ArrayList<Post>();
			while (rs.next()) {
				Post post = new Post(
						rs.getString("id"),
						rs.getString("type"),
						rs.getString("title"),
						rs.getString("writer"),
						rs.getString("content"),
						rs.getString("writeTime"));
				list.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public byte save(List<Post> posts) {
		byte errorCode = 0;
		List<String> sqls = new ArrayList<String>();
		sqls.add("DELETE FROM " + tableName);
		for (Post post : posts) {
			sqls.add("INSERT INTO " + tableName + " VALUES ("
					+ post.getId() + "','"
					+ post.getType() + "','"
					+ post.getTitle() + "','"
					+ post.getWriter() + "','"
					+ post.getContent() + "','"
					+ post.getWriteTime() + ")");
		}
		errorCode = execute(sqls);
		close();
		return errorCode;
	}
}
