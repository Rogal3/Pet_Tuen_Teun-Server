package DB;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.Doctor;

@Repository("doctorDAO")
public class DoctorDAO extends DAO {
	private final String tableName = "doctor";
	
	public DoctorDAO() {
		super();
	}
	

}
