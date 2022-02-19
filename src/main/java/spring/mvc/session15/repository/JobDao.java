package spring.mvc.session15.repository;

import java.sql.ResultSet;
import java.util.List;

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
public class JobDao implements IJobDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 建立 job 資料表
	@Override
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
	
	// 取得單筆 job 資料
	@Override
	public Job get(Integer jid) {
		String sql = "select jid, jname, eid from job where jid=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {jid}, new BeanPropertyRowMapper<Job>(Job.class));
	}
	
	// 新增 job 資料
	@Override
	public int add(Job job) {
		String sql = "insert into job (jname, eid) values(?, ?)";
		return jdbcTemplate.update(sql, job.getJname(), job.getEid());
	}
	
	// 修改 job 資料
	@Override
	public int update(Job job) {
		String sql = "update job set jname=?, eid=? where jid=?";
		return jdbcTemplate.update(sql, job.getJname(), job.getEid(), job.getJid());
	}
	
	// 刪除 employee 資料
	@Override
	public int delete(Integer jid) {
		// 官方文件：外鍵約束默認是禁用的（為了向後兼容），所以必須分別為每個數據庫連接啟用。
		// 所以使用 sqlite 刪除的時候要啟動外鍵約束，預設是關閉
		jdbcTemplate.execute("PRAGMA foreign_keys = ON");
		
		String sql = "delete from job where jid=?";
		return jdbcTemplate.update(sql, jid);
	}
	
	// 查詢所有筆數
	@Override
	public int getCount() {
		String sql = "select count(*) from job";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	// 不分頁全部查詢
	@Override
	public List<Job> query() {
		return queryPage(-1);
	}
	
	// 判斷 httpSession 值決定是否要分頁
	@Override
	public List<Job> query(Object httpSessionValue) {
		if(httpSessionValue == null) {
			return query();
		}
		return queryPage(Integer.parseInt(httpSessionValue + ""));
	}
	
	// 分頁查詢
	@Override
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
	
	// 查詢全部資料-使用 BeanPropertyRowMapper
	@Override
	public List<Job> queryAll() {
		String sql = "select j.jid, j.jname, j.eid from job j";
		List<Job> jobs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Job.class));
		return jobs;
	}
	
	// 查詢全部資料-實作 RowMapper
	/* public interface RowMapper<T> {
	 *     T mapRow(ResultSet rs, int rowNum) throws SQLException;
	 * }
	 * */
	@Override
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
	
	// 查詢全部資料-使用 SimpleFlatMapper 
	// 調用：org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory
	@Override
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








