package spring.mvc.session15.repository;

import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.mvc.session15.entity.Employee;
import spring.mvc.session15.entity.Job;

@Repository
public class JobDao {
	public static final int LIMIT = 5;
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
	
	public Job get(Integer jid) {
		String sql = "select jid, jname, eid from job where jid=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {jid}, new BeanPropertyRowMapper<Job>(Job.class));
	}
	
	public int add(Job job) {
		String sql = "insert into job (jname, eid) values(?, ?)";
		return jdbcTemplate.update(sql, job.getJname(), job.getEid());
	}
	
	public int update(Job job) {
		String sql = "update job set jname=?, eid=? where jid=?";
		return jdbcTemplate.update(sql, job.getJname(), job.getEid(), job.getJid());
	}
	
	// 查詢所有筆數
	public int getCount() {
		String sql = "select count(*) from job";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	// 不分頁全部查詢
	public List<Job> query() {
		return queryPage(-1);
	}
	
	// 判斷 httpSession 值決定是否要分頁
	public List<Job> query(Object value) {
		if(value == null) {
			return query();
		}
		return queryPage(Integer.parseInt(value + ""));
	}
	
	// 分頁查詢
	public List<Job> queryPage(int offset) {
		
		ResultSetExtractor<List<Job>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("jid")  // job 表的主鍵
				.newResultSetExtractor(Job.class);
		
		String sql = "select "
					+ "	j.jid, j.jname, j.eid, "
					+ "	e.eid as employee_eid, e.ename as employee_ename, e.salary as employee_salary, "
					+ "	e.createtime as employee_createtime "
					+ "from "
					+ "	job j, employee e "
					+ "where j.eid = e.eid "
					+ "order by j.jid ";
		// 分頁查詢
		if(offset >= 0) {
			sql += String.format(" limit %d offset %d ", LIMIT, offset);
		}
		List<Job> jobs = jdbcTemplate.query(sql, resultSetExtractor);
		return jobs;
	}

	//-------------------------------------------------------------------------------
	
	// 使用 BeanPropertyRowMapper
	public List<Job> queryAll() {
		String sql = "select j.jid, j.jname, j.eid from job j";
		List<Job> jobs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Job.class));
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
	
	// 使用 SimpleFlatMapper 
	// 調用：org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory
	public List<Job> queryAll3() {
		
		ResultSetExtractor<List<Job>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("jid")  // job 表的主鍵
				.newResultSetExtractor(Job.class);
		
		String sql = "select "
					+ "	j.jid, j.jname, j.eid, "
					+ "	e.eid as employee_eid, e.ename as employee_ename, e.salary as employee_salary, "
					+ "	e.createtime as employee_createtime "
					+ "from "
					+ "	job j, employee e "
					+ "where j.eid = e.eid";
		
		List<Job> jobs = jdbcTemplate.query(sql, resultSetExtractor);
		return jobs;
	}
	
	
}








