package DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.Rating;

@Repository("RatingDAO")
public class RatingDAO extends DAO {
	private static class LazyHolder {
		public static final RatingDAO INSTANCE = new RatingDAO();
	}
	private RatingDAO() {
		super();
	}
	public static RatingDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private final String tableName = "rating";
	
	/*
	 * @return writer#hospital
	 */
	private String getId(String writer, String hospital) {
		return writer + "#" + hospital;
	}
	
	/*
	 * @id Raing.id
	 * @return (writer, hospital) pair
	 */
	private List<String> tokenizeId(String id) {
		List<String> list = new ArrayList<String>();
		int sharpPos = id.lastIndexOf('#');
		list.add(id.substring(0, sharpPos));
		list.add(id.substring(sharpPos + 1));
		return list;
	}
	
	private List<RatingDTO> convert(HashMap<String, Rating> map) {
		List<RatingDTO> list = new ArrayList<RatingDTO>();
		Iterator<String> keys = map.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			Rating model = map.get(key);
			RatingDTO dto = new RatingDTO(
					model.getId(),
					model.getWriter(),
					model.getHospital(),
					model.getScale(),
					model.getContent());
			list.add(dto);
		}
		return list;
	}
	
	public HashMap<String, Rating> load() {
		HashMap<String, Rating> map = null;
		String sql = "SELECT * FROM " + tableName;
		try {
			rs = execute(sql);
			map = new HashMap<String, Rating>();
			while (rs.next()) {
				Rating model = new Rating(
						rs.getString("id"),
						rs.getString("writer"),
						rs.getString("hopspital"),
						rs.getInt("scale"),
						rs.getString("content"));
				map.put(model.getId(), model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return map;
	}
	
	public byte save(HashMap<String, Rating> map) {
		byte errorCode = 0;
		List<RatingDTO> dtoList = convert(map);
		List<String> sqls = new ArrayList<String>();
		sqls.add("DELETE FROM " + tableName);
		for (RatingDTO dto : dtoList) {
			sqls.add("INSERT INTO " + tableName + " VALUES ("
					+ dto.getId() + "','"
					+ dto.getWriter() + "','"
					+ dto.getHospital() + "',"
					+ dto.getScale() + ",'"		// int has no quotes.
					+ dto.getContent() + "')");
		}
		errorCode = execute(sqls);
		close();
		return errorCode;
	}
}
