package spring.mvc.session15.repository;

import java.util.List;

import spring.mvc.session15.entity.Employee;

public interface IEmployeeDao {
	// 每頁筆數
	int LIMIT = 5;
		
	// 建立 employee 資料表
	public boolean createTable();

	// 取得單筆 employee 資料
	public Employee get(Integer eid);

	// 新增 employee 資料
	public int add(Employee emp);

	// 修改 employee 資料
	public int update(Employee emp);
	
	// 刪除 employee 資料
	public int delete(Integer eid);
		
	// 查詢所有筆數
	public int getCount();
		
	// 不分頁全部查詢
	public List<Employee> query();
		
	// 判斷 httpSession 值決定是否要分頁
	public List<Employee> query(Object httpSessionValue);
		
	// 分頁查詢
	public List<Employee> queryPage(int offset);
		
	// 查詢全部資料-使用 BeanPropertyRowMapper
	public List<Employee> queryAll();

	// 查詢全部資料-實作 RowMapper
	public List<Employee> queryAll2();

	// 查詢全部資料-使用 SimpleFlatMapper
	public List<Employee> queryAll3();
}
