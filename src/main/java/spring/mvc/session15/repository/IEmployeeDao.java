package spring.mvc.session15.repository;

import java.util.List;

import spring.mvc.session15.entity.Employee;

public interface IEmployeeDao {
	// 建立 employee 資料表
	public boolean createTable();

	// 取得單筆 employee 資料
	public Employee get(Integer eid);

	// 新增 employee 資料
	public int add(Employee emp);

	// 修改 employee 資料
	public int update(Employee emp);

	// 查詢全部資料-使用 BeanPropertyRowMapper
	public List<Employee> queryAll();

	// 查詢全部資料-實作 RowMapper
	public List<Employee> queryAll2();

	// 查詢全部資料-使用 SimpleFlatMapper
	public List<Employee> queryAll3();
}
