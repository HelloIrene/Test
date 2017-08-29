package org.jheng.app.test;

import java.sql.Date;

import org.jheng.app.dao.UserDAO;
import org.jheng.app.entity.Emp;

public class UserTest {
	public static void main(String[] args) {
		UserDAO  ud = new UserDAO();
//		Emp emp = ud.findEmpById(7369);
//		System.out.println(emp);
		Emp emp = new Emp();
		emp.setEmpNo(1100);
		emp.setComm(0.2);
		emp.seteName("TOM");
		emp.setJob("SALE");
		emp.setDate(new Date(0));
		ud.update(emp);
		
		
//		System.out.println(new UserDAO().insertInfoIntoList());
	}
}
