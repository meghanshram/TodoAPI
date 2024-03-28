package todo;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

public class Todo {
	private int id;
	private String name;
	
	@Size(min=10)
	private String desc;
	private LocalDate targetDate;
	private boolean done;
	
	
	
	public Todo(int id, String name, String desc, LocalDate targetDate, boolean done) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.targetDate = targetDate;
		this.done = done;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public LocalDate getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", name=" + name + ", desc=" + desc + ", targetDate=" + targetDate + ", done=" + done
				+ "]";
	}
	
	
}
