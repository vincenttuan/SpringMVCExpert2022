-- 建立 employee 資料表
-- eid 主鍵，具有唯一性(自行產生序號：1、2、3、4 ...)
-- ename 員工姓名
-- salary 員工薪資
-- createtime 建檔時間 default current_timestamp 預設會注入現在時間
create table IF NOT EXISTS employee (
    eid integer not null auto_increment, -- 主鍵，員工 id (自行產生序號: 1, 2, 3, ...)
    ename text, -- 員工姓名 (65,535 characters)
    salary integer, -- 員工薪資
    createtime datetime default current_timestamp, -- 建檔時間
    primary key(eid)
);

-- 建立 job 資料表
-- jid 主鍵
-- jname 工作名稱
-- eid 員工編號
-- 外鍵關聯 job.eid 對應到 employee.eid
create table IF NOT EXISTS job(
    jid integer not null auto_increment, -- 主鍵，工作 id
    jname text, -- 工作名稱
    eid integer not null, -- 員工 id
    foreign key(eid) references employee(eid), -- 外鍵關聯
    primary key(jid)
);

-- 建立 Employee 範例資料
insert into employee(ename, salary) values('John', 40000);
insert into employee(ename, salary) values('Mary', 50000);
insert into employee(ename, salary) values('Bobo', 60000);
insert into employee(ename, salary) values('Mark', 70000);

-- 建立 Job 範例資料
insert into job(jname, eid) values('Job A', 1);
insert into job(jname, eid) values('Job B', 1);
insert into job(jname, eid) values('Job C', 2);
insert into job(jname, eid) values('Job D', 2);
insert into job(jname, eid) values('Job E', 4);
insert into job(jname, eid) values('Job F', 4);
insert into job(jname, eid) values('Job G', 4);

-- 雙表交集查詢 
select 
	j.jid, j.jname, j.eid, 
	e.eid as employee_eid, e.ename as employee_ename, e.salary as employee_salary, 
	e.createtime as employee_createtime
from 
	job j, employee e
where j.eid = e.eid

-- Employee(左) 左關聯 Job(右) 查詢
-- inner join 查詢 employee 與 job 的交集 (若該 employee 沒有 job 則不會被查到)
-- left join 查詢 employee 的所有狀況 (若該 employee 沒有 job 也會被查到)
select 
	e.eid, e.ename, e.salary, e.createtime,
	j.jid as job_jid, j.jname as job_jname, j.eid as job_eid
from 
	employee e left join job j on j.eid = e.eid

-- 分頁查詢
select count(*) from job -- 總筆數
select * from job limit 5 offset 0  -- 每頁5筆：第1頁
select * from job limit 5 offset 5  -- 每頁5筆：第2頁
select * from job limit 5 offset 10 -- 每頁5筆：第3頁
	
