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
public class EmployeeDao implements IEmployeeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 建立 employee 資料表
	@Override
	public boolean createTable() {
		String sql = "create table if not exists employee ( " + "	eid integer primary key, " + "	ename text, "
				+ "	salary integer, " + "	createtime datetime default current_timestamp " + ")";
		jdbcTemplate.execute(sql);
		return true;
	}
	
	// 取得單筆 employee 資料
	@Override
	public Employee get(Integer eid) {
		String sql = "select eid, ename, salary, createtime from employee where eid=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {eid}, new BeanPropertyRowMapper<Employee>(Employee.class));
	}
	
	// 新增 employee 資料
	@Override
	public int add(Employee emp) {
		String sql = "insert into employee (ename, salary) values(?, ?)";
		return jdbcTemplate.update(sql, emp.getEname(), emp.getSalary());
	}
	
	// 修改 employee 資料
	@Override
	public int update(Employee emp) {
		String sql = "update employee set ename=?, salary=? where eid=?";
		return jdbcTemplate.update(sql, emp.getEname(), emp.getSalary(), emp.getEid());
	}
	
	// 刪除 employee 資料
	@Override
	public int delete(Integer eid) {
		// 官方文件：外鍵約束默認是禁用的（為了向後兼容），所以必須分別為每個數據庫連接啟用。
		// 所以使用 sqlite 刪除的時候要啟動外鍵約束，預設是關閉
		jdbcTemplate.execute("PRAGMA foreign_keys = ON");
		
		String sql = "delete from employee where eid=?";
		return jdbcTemplate.update(sql, eid);
	}

	
	// 查詢所有筆數
	@Override
	public int getCount() {
		String sql = "select count(*) from employee";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	// 不分頁全部查詢
	@Override
	public List<Employee> query() {
		return queryPage(-1);
	}
	
	// 判斷 httpSession 值決定是否要分頁
	@Override
	public List<Employee> query(Object httpSessionValue) {
		if(httpSessionValue == null) {
			return query();
		}
		return queryPage(Integer.parseInt(httpSessionValue + ""));
	}
	
	// 分頁查詢
	@Override
	public List<Employee> queryPage(int offset) {
		
		String sql = "select e.eid, e.ename, e.salary, e.createtime from employee e order by e.eid ";
		// 分頁查詢
		if(offset >= 0) {
			sql += String.format(" limit %d offset %d ", LIMIT, offset);
		}
		
		RowMapper<Employee> rm = (ResultSet rs, int rowNum) -> {
			Employee employee = new Employee();
			employee.setEid(rs.getInt("eid"));
			employee.setEname(rs.getString("ename"));
			employee.setSalary(rs.getInt("salary"));
			employee.setCreatetime(rs.getDate("createtime"));
			String sql2 = "select j.jid, j.jname, j.eid from job j where j.eid=?";
			List<Job> jobs = jdbcTemplate.query(sql2, new Object[] { employee.getEid() },
					new BeanPropertyRowMapper<Job>(Job.class));

			employee.setJobs(jobs);
			return employee;
		};

		List<Employee> employees = jdbcTemplate.query(sql, rm);
		return employees;
	}
	
	//-------------------------------------------------------------------------------
	
	// 查詢全部資料-使用 BeanPropertyRowMapper
	@Override
	public List<Employee> queryAll() {
		String sql = "select e.eid, e.ename, e.salary, e.createtime from employee e";
		List<Employee> employees = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
		return employees;
	}

	// 查詢全部資料-實作 RowMapper
	/*
	 * public interface RowMapper<T> { T mapRow(ResultSet rs, int rowNum) throws
	 * SQLException; }
	 */
	@Override
	public List<Employee> queryAll2() {
		String sql = "select e.eid, e.ename, e.salary, e.createtime from employee e";

		RowMapper<Employee> rm = (ResultSet rs, int rowNum) -> {
			Employee employee = new Employee();
			employee.setEid(rs.getInt("eid"));
			employee.setEname(rs.getString("ename"));
			employee.setSalary(rs.getInt("salary"));
			employee.setCreatetime(rs.getDate("createtime"));
			String sql2 = "select j.jid, j.jname, j.eid from job j where j.eid=?";
			List<Job> jobs = jdbcTemplate.query(sql2, new Object[] { employee.getEid() },
					new BeanPropertyRowMapper<Job>(Job.class));

			employee.setJobs(jobs);
			return employee;
		};

		List<Employee> employees = jdbcTemplate.query(sql, rm);
		return employees;
	}

	// 查詢全部資料-使用 SimpleFlatMapper
	// 調用：org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory
	@Override
	public List<Employee> queryAll3() {

		ResultSetExtractor<List<Employee>> resultSetExtractor = 
				JdbcTemplateMapperFactory.newInstance()
				.addKeys("eid") // employee 表的主鍵
				.newResultSetExtractor(Employee.class);

		String sql = "select "
					+ "	e.eid, e.ename, e.salary, e.createtime, "
					+ "	j.jid as job_jid, j.jname as job_jname, j.eid as job_eid "
					+ "from "
					+ "	employee e left join job j on j.eid = e.eid";

		List<Employee> employees = jdbcTemplate.query(sql, resultSetExtractor);
		return employees;
	}

}
