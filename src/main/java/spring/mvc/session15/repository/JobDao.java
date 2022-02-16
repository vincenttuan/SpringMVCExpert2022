package spring.mvc.session15.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JobDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean createTable() {
		String sql = "create table if not exists job ( "
					+ "	jid integer primary key, "
					+ "	jname text, "
					+ "	eid integer not null, "
					+ "	foreign key(eid) references employee(eid) "
					+ ")";
		jdbcTemplate.execute(sql);
		return true;
	}
}
