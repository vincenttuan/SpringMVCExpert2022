package spring.mvc.session12.entity;

import java.util.Arrays;

public class Exam {
	private String id; // 學員代號
	private String exam; // 考試代號 
	private String[] slot; // 可考試時段
	private Boolean pay; // 繳費狀況: 已繳(true) 未繳(false)
	private String note; // 備註
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	public String[] getSlot() {
		return slot;
	}
	public void setSlot(String[] slot) {
		this.slot = slot;
	}
	public Boolean getPay() {
		return pay;
	}
	public void setPay(Boolean pay) {
		this.pay = pay;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public String toString() {
		return "Exam [id=" + id + ", exam=" + exam + ", slot=" + Arrays.toString(slot) + ", pay=" + pay + ", note="
				+ note + "]";
	}
	
	
}
