package com.ast.maven_sample.pojo;

import java.sql.Date;

public class CompanyPojo {
	
	private int id; private String company,location, note;
	private Date delivaryDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getDelivaryDate() {
		return delivaryDate;
	}
	public void setDelivaryDate(Date delivaryDate) {
		this.delivaryDate = delivaryDate;
	}
	@Override
	public String toString() {
		return "CompanyPojo [id=" + id + ", company=" + company + ", location=" + location + ", note=" + note
				+ ", delivaryDate=" + delivaryDate + "]";
	}
	
}
