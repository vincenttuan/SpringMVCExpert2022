package spring.mvc.session15.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean createTable() {
		String sql = "create table if not exists employee ( "
					+ "	eid integer primary key, "
					+ "	ename text, "
					+ "	salary integer, "
					+ "	createtime datetime default current_timestamp "
					+ ")";
		jdbcTemplate.execute(sql);
		return true;
	}
}
