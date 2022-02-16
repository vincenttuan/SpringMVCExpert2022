package spring.mvc.session15.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.mvc.session15.entity.Employee;
import spring.mvc.session15.entity.Job;

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
	
	// 使用 BeanPropertyRowMapper
	public List<Job> queryAll() {
		String sql = "select j.jid, j.jname, j.eid from job j";
		List<Job> jobs = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Job.class));
		return jobs;
	}
	
	// 實作 RowMapper
	/* public interface RowMapper<T> {
	 *     T mapRow(ResultSet rs, int rowNum) throws SQLException;
	 * }
	 * */
	public List<Job> queryAll2() {
		String sql = "select j.jid, j.jname, j.eid from job j";
		RowMapper<Job> rm = (ResultSet rs, int rowNum) -> {
			Job job = new Job();
			job.setJid(rs.getInt("jid"));
			job.setJname(rs.getString("jname"));
			job.setEid(rs.getInt("eid"));
			// 再根據 eid 查詢 employee
			String sql2 = "select e.eid, e.ename, e.salary, e.createtime from employee e where e.eid=?";
			Employee employee = jdbcTemplate.queryForObject(
					sql2, 
					new Object[] {job.getEid()}, 
					new BeanPropertyRowMapper<Employee>(Employee.class));
			job.setEmployee(employee);
			return job;
		};
		List<Job> jobs = jdbcTemplate.query(sql, rm);
		return jobs;
	}
}
