package com.example.entity;

import javax.persistence.Entity;

@Entity
public class Task extends TransactionalEntity{

	private static final long serialVersionUID = 1L;


	private String taskShort;
	
	private String taskDetail;
	
	public String getTaskShort() {
		return taskShort;
	}
	public void setTaskShort(String taskShort) {
		this.taskShort = taskShort;
	}
	public String getTaskDetail() {
		return taskDetail;
	}
	public void setTaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskDetail == null) ? 0 : taskDetail.hashCode());
		result = prime * result + ((taskShort == null) ? 0 : taskShort.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (taskDetail == null) {
			if (other.taskDetail != null)
				return false;
		} else if (!taskDetail.equals(other.taskDetail))
			return false;
		if (taskShort == null) {
			if (other.taskShort != null)
				return false;
		} else if (!taskShort.equals(other.taskShort))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", taskShort=" + taskShort + ", taskDetail=" + taskDetail + "]";
	}
	
	
		
}
