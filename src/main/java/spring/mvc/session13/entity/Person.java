package spring.mvc.session13.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Person {
	//@NotNull(message = "姓名不可以是空值")
	@NotNull(message = "{person.name.empty}")
	@Size(min = 2, max = 50, message = "姓名字數範圍必須介於 2~50 個字之間")
	private String name; // 姓名
	
	//@NotNull(message = "年齡不可以是空值")
	@NotNull(message = "{person.age.empty}")
	@Range(min = 18, max = 99, message = "年齡範圍必須介於 18~99 歲之間")
	private Integer age; // 年齡
	
	//@NotNull(message = "會員的設定不可以是空值")
	@NotNull(message = "{person.member.empty}")
	private Boolean member; // 是否是會員
	
	//@NotNull(message = "生日不可以是空值")
	@NotNull(message = "{person.birth.empty}")
	@Past(message = "生日不可以大於現在的日期")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8") // 返回日期類型
	@DateTimeFormat(pattern="yyyy-MM-dd") //接收日期類型
	private Date birth; // 生日
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Boolean getMember() {
		return member;
	}
	public void setMember(Boolean member) {
		this.member = member;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", member=" + member + ", birth=" + birth + "]";
	}
	
}
