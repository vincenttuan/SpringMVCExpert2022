package spring.mvc.session15.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.mvc.session15.entity.Employee;

@Repository
public class EmployeeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean createTable() {
		String sql = "create table if not exists employee ( " + "	eid integer primary key, " + "	ename text, "
				+ "	salary integer, " + "	createtime datetime default current_timestamp " + ")";
		jdbcTemplate.execute(sql);
		return true;
	}

	// 使用 BeanPropertyRowMapper
	public List<Employee> queryAll() {
		String sql = "select e.eid, e.ename, e.salary, e.createtime from employee e";
		List<Employee> employees = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
		return employees;
	}
	
}
