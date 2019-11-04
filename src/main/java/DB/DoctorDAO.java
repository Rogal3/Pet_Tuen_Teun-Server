package DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.Doctor;

@Repository("doctorDAO")
public class DoctorDAO extends DAO {
	private final String tableName = "doctor";
	
	public DoctorDAO() {
		super();
	}
	
	private List<DoctorDTO> convert(HashMap<String, HashMap<String, Doctor>> map) {
		List<DoctorDTO> list = new ArrayList<DoctorDTO>();
		Iterator<String> hospitalNameIter = map.keySet().iterator();
		while (hospitalNameIter.hasNext()) {
			String hospitalName = hospitalNameIter.next();
			HashMap<String, Doctor> doctorByName = map.get(hospitalName);
			Iterator<String> doctorNameIter = doctorByName.keySet().iterator();
			while (doctorNameIter.hasNext()) {
				String doctorName = doctorNameIter.next();
				Doctor model = doctorByName.get(doctorName);
				DoctorDTO dto = new DoctorDTO(
						model.getName(),
						hospitalName,
						model.getPosition(),
						model.getMajor());
				list.add(dto);
			}
		}
		return list;
	}
	
	public HashMap<String, HashMap<String, Doctor>> load() {
		HashMap<String, HashMap<String, Doctor>> map = null;
		String sql = "SELECT * FROM " + tableName;
		try {
			rs = execute(sql);
			map = new HashMap<String, HashMap<String, Doctor>>();
			while (rs.next()) {
				String hospitalName = rs.getString("hospital");
				HashMap<String, Doctor> doctorByName = map.get(hospitalName);
				if (doctorByName == null) {
					doctorByName = new HashMap<String, Doctor>();
					map.put(hospitalName, doctorByName);
				}
				Doctor model = new Doctor(
						rs.getString("name"),
						rs.getString("position"),
						rs.getString("major"));
				doctorByName.put(model.getName(), model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return map;
	}
	
	public byte save(HashMap<String, HashMap<String, Doctor>> map) {
		byte errorCode = 0;
		List<DoctorDTO> list = convert(map);
		List<String> sqls = new ArrayList<String>();
		sqls.add("DELETE FROM " + tableName);
		for (DoctorDTO dto : list) {
			sqls.add("INSERT INTO " + tableName + " VALUES ("
					+ dto.getName() + "','"
					+ dto.getHospital() + "','"
					+ dto.getPosition() + "','"
					+ dto.getMajor() + ")");
		}
		errorCode = execute(sqls);
		close();
		return errorCode;
	}
}
