package spring.mvc.session15.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DateTimeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String now() {
		// 透過任意 sql 指令，例如：select datetime('now', '+8 hour') 來建立 mydb.db 資料庫
		// +8 hour 表示將格林威治時區轉換成東八區
		return jdbcTemplate.queryForObject("select datetime('now', '+8 hour')", String.class);
	}
	
}
