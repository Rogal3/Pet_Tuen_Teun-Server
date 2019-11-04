package DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.Hospital;

@Repository("hospitalDAO")
public class HospitalDAO extends DAO {
	private final String tableName = "hospital";
	
	public HospitalDAO() {
		super();
	}
	
	private List<HospitalDTO> convert(HashMap<String, Hospital> map) {
		List<HospitalDTO> list = new ArrayList<HospitalDTO>();
		Iterator<String> keys = map.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			Hospital model = map.get(key);
			HospitalDTO dto = new HospitalDTO(
					model.getName(),
					model.getAddress(),
					model.getPhoneNum(),
					model.getOpenTime(),
					model.getCloseTime());
			list.add(dto);
		}
		return list;
	}
	
	public HashMap<String, Hospital> load() {
		HashMap<String, Hospital> map = null;
		String sql = "SELECT * FROM " + tableName;
		try {
			rs = execute(sql);
			map = new HashMap<String, Hospital>();
			while (rs.next()) {
				Hospital hospital = new Hospital(
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("phoneNum"),
						rs.getString("openTime"),
						rs.getString("closeTime"));
				map.put(hospital.getName(), hospital);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return map;
	}
	
	public byte save(HashMap<String, Hospital> map) {
		byte errorCode = 0;
		List<HospitalDTO> list = convert(map);
		List<String> sqls = new ArrayList<String>();
		sqls.add("DELETE FROM " + tableName);
		for (HospitalDTO dto : list) {
			sqls.add("INSERT INTO " + tableName + " VALUES ("
					+ dto.getName() + "','"
					+ dto.getAddress() + "','"
					+ dto.getPhoneNum() + "','"
					+ dto.getOpenTime() + "','"
					+ dto.getCloseTime() + ")");
		}
		errorCode = execute(sqls);
		close();
		return errorCode;
	}
}
