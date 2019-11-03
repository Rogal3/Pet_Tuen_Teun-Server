package DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import model.Animal;

public class AnimalDAO extends DAO {
	private final String tableName = "animal";
	private static class LazyHolder {
		public static final AnimalDAO INSTANCE = new AnimalDAO();
	}
	private AnimalDAO() {
		super();
	}
	public AnimalDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public byte insert(AnimalDTO dto) {
		byte errorCode = 0;
		String sql = "INSERT INTO " + tableName + " VALUES (?,?,?,?,?,?,?)";
		try {
			connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getOwner());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getSpecies());
			pstmt.setInt(5, dto.getAge());
			pstmt.setString(6, dto.getAdopt());
			pstmt.setString(7, dto.getBirth());
			
			int count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DB insert error : " + e);
		} finally {
			close();
		}
		errorCode = 1;
		return errorCode;
	}
	
	public byte delete() {
		byte errorCode = 0;
		String sql = "DELETE FROM " + tableName;
		try {
			connect();
			stmt = con.createStatement();
			if (stmt != null) {
				int count = stmt.executeUpdate(sql);
			}
		} catch (SQLException e) {
			System.out.println("DB delete error : " + e);
		} finally {
			close();
		}
		errorCode = 1;
		return errorCode;
	}
	
	private String getId(String owner, String name) {
		return owner + '#' + name;
	}
	
	private List<String> tokenize(String id) {
		List<String> list = new ArrayList<String>();
		int sharpPos = 0;
		for (int pos = id.length() - 1; 0 <= pos; --pos) {
			if (id.charAt(pos) == '#') {
				sharpPos = pos;
				break;
			}
		}
		list.add(id.substring(0, sharpPos));
		list.add(id.substring(sharpPos + 1));
		return list;
	}
	
	public AnimalDTO model2DTO(String id, String owner, Animal model) {
		return new AnimalDTO(
				id,
				owner,
				model.getName(),
				model.getSpecies(),
				model.getAge(),
				model.getAdopt(),
				model.getBirth());
	}
	
	public List<AnimalDTO> convert(HashMap<String, HashMap<String, Animal>> map) {
		List<AnimalDTO> list = new ArrayList<AnimalDTO>();
		Iterator<String> key1 = map.keySet().iterator();
		while (key1.hasNext()) {
			String owner = key1.next();
			Iterator<String> key2 = map.get(owner).keySet().iterator();
			while (key2.hasNext()) {
				Animal model = map.get(owner).get(key2.next());
				String id = getId(owner, model.getName());
				AnimalDTO dto = model2DTO(id, owner, model);
				list.add(dto);
			}
		}
		return list;
	}
	
	public byte save(HashMap<String, HashMap<String, Animal>> map) {
		List<AnimalDTO> list = convert(map);
		delete();
		for (AnimalDTO dto : list) {
			insert(dto);
		}
		return 1;
	}
	
	public HashMap<String, HashMap<String, Animal>> load() {
		HashMap<String, HashMap<String, Animal>> map = null;
		String sql = "SELECT * FROM " + tableName;
		try {
			connect();
			stmt = con.createStatement();
			if (stmt != null) {
				rs = stmt.executeQuery(sql);
				map = new HashMap<String, HashMap<String, Animal>>();
				while (rs.next()) {
					String owner = rs.getString("owner");
					Animal model = new Animal(
							rs.getString("name"),
							rs.getString("species"),
							rs.getInt("age"),
							rs.getString("adopt"),
							rs.getString("birth"));
					map.put(owner, new HashMap<String, Animal>());
					map.get(owner).put(model.getName(), model);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return map;
	}
}
