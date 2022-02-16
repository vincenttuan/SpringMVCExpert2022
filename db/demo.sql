-- 建立 employee 資料表
-- eid 主鍵(自行產生序號：1、2、3、4 ...)
-- ename 員工姓名
-- salary 員工薪資
-- createtime 建檔時間 default current_timestamp 預設會注入現在時間
create table if not exists employee (
	eid integer primary key,
	ename text,
	salary integer,
	createtime datetime default current_timestamp
);

