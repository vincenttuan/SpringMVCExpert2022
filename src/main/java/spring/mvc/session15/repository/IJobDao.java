package spring.mvc.session15.repository;

import java.util.List;

import spring.mvc.session15.entity.Job;

public interface IJobDao {
	// 每頁筆數
	int LIMIT = 5;
	
	// 建立 job 資料表
	public boolean createTable();

	// 取得單筆 job 資料
	public Job get(Integer eid);

	// 新增 job 資料
	public int add(Job job);

	// 修改 job 資料
	public int update(Job job);	
	
	// 查詢所有筆數
	public int getCount();
	
	// 不分頁全部查詢
	public List<Job> query();
	
	// 判斷 httpSession 值決定是否要分頁
	public List<Job> query(Object httpSessionValue);
	
	// 分頁查詢
	public List<Job> queryPage(int offset);
		
	// 查詢全部資料-使用 BeanPropertyRowMapper
	public List<Job> queryAll();

	// 查詢全部資料-實作 RowMapper
	public List<Job> queryAll2();

	// 查詢全部資料-使用 SimpleFlatMapper
	public List<Job> queryAll3();
}
