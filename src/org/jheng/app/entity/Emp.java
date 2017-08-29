package org.jheng.app.entity;

import java.sql.Date;

public class Emp {
	private int empNo;
	private String eName;
	private String job;
	private Date date;
	private int sal;
	private double comm;
	private String departName;
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public double getComm() {
		return comm;
	}
	public void setComm(double comm) {
		this.comm = comm;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	@Override
	public String toString() {
		return "Emp [empNo=" + empNo + ", eName=" + eName + ", job=" + job + ", date=" + date + ", sal=" + sal
				+ ", comm=" + comm + "]";
	}
	
	
	
}
