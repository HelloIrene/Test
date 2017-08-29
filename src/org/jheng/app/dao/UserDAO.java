package org.jheng.app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jheng.app.entity.Emp;
import org.jheng.app.entity.T_User;
import org.jheng.app.util.DBTool;

public class UserDAO {
	//查询部门编号和名称并插入到list中
    public ArrayList<String> insertInfoIntoList() {
    	ArrayList<String> arr = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBTool.getInstance().getConnection();
			// 3、创建Statement
			stmt = conn.createStatement();
			// 4、执行Statement,执行查询返回结果集
			rs = stmt.executeQuery("SELECT DNAME FROM DEPT");
			// 5、处理结果集
			while (rs.next()) {
				// 每查到一条数据，装配一个User对象
				arr.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.closeAll(rs, stmt, conn);
		}
		return arr;
    }
	//通过工号查找信息
	public Emp findEmpById(int id) {
		Emp emp = null;
		Connection conn = null;
		PreparedStatement ps = null; //预编译的Statement
		ResultSet rs = null;
		try {
			conn = DBTool.getInstance().getConnection();
			//stmt = conn.createStatement();
			ps = conn.prepareStatement("SELECT * FROM EMP,DEPT WHERE EMPNO=? AND DEPT.DEPTNO=EMP.DEPTNO");
			ps.setInt(1, id);
			//rs = stmt.executeQuery("SELECT * FROM T_User WHERE uname = '" + name + "' AND password = '" + pwd + "'");
			rs =  ps.executeQuery();
			while (rs.next()) {
				// 每查到一条数据，装配一个User对象
				emp = new Emp();
				emp.setEmpNo(id);
				emp.seteName(rs.getString("ENAME"));
				emp.setJob(rs.getString("JOB"));
				emp.setDate(rs.getDate("HIREDATE"));
				emp.setSal(rs.getInt("SAL"));
				emp.setComm(rs.getDouble("COMM"));
				emp.setDepartName(rs.getString("DNAME"));
				//emp.setDepartName(rs.getString("DNAME"));
				//emp.setDepartName(rs.getString("DEPARTMENTNAME"));	
				System.out.print("QuQ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBTool.closeAll(rs, ps, conn);
		}
		return emp;
	}
	//更新用户信息
	public int update(Emp emp){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		try {
			
			conn = DBTool.getInstance().getConnection();
			ps = conn.prepareStatement
			("UPDATE EMP SET ENAME=?, JOB=?, HIREDATE=?, SAL=? ,COMM=? ,DEPTNO=? WHERE EMPNO=?");
//			ps = conn.prepareStatement
//					("UPDATE EMP,DEPT SET ENAME=?, JOB=?, HIREDATE=?, SAL=? ,COMM=? WHERE EMPNO=?");
			
			ps.setString(1, emp.geteName());
			ps.setString(2, emp.getJob());
			ps.setDate(3, (Date) emp.getDate());;
			ps.setInt(4, emp.getSal());
			ps.setDouble(5, emp.getComm());
			ps.setInt(6, getIdByName(emp.getDepartName()));
			ps.setInt(7, emp.getEmpNo());
			row = ps.executeUpdate(); //插入数据库，返回SQL语句执行后受影响的行数
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBTool.closeAll(null, ps, conn);
		}
		return row;
	}
	
	public int delete(int id){
		Connection conn = null;
		PreparedStatement ps = null;
		int row = 0;
		try {
			conn = DBTool.getInstance().getConnection();
			ps = conn.prepareStatement("DELETE EMP WHERE EMPNO=?");
			ps.setInt(1, id);
			row = ps.executeUpdate(); //插入数据库，返回SQL语句执行后受影响的行数	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBTool.closeAll(null, ps, conn);
		}
		return row;
	}
	
	public static int getIdByName(String name){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		try {
			conn = DBTool.getInstance().getConnection();
			//ps = conn.prepareStatement("INSERT INTO T_User VALUES(userid_seq.nextval, ?,?,?,?)");
			//MySQL
			ps = conn.prepareStatement("SELECT DEPTNO FROM DEPT WHERE DNAME=?");
			ps.setString(1, name);
			rs = ps.executeQuery(); //插入数据库，返回SQL语句执行后受影响的行数
			while(rs.next()){
				row = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBTool.closeAll(null, ps, conn);
		}
		
		return row;
	}
	
	/****************************************************************************/
	
	public int add(T_User user){
		Connection conn = null;
		PreparedStatement ps = null;
		int row = 0;
		try {
			conn = DBTool.getInstance().getConnection();
			//ps = conn.prepareStatement("INSERT INTO T_User VALUES(userid_seq.nextval, ?,?,?,?)");
			//MySQL
			ps = conn.prepareStatement("INSERT INTO T_User (uname, password, email, regDate) VALUES (?,?,?,?)");
			ps.setString(1, user.getUname());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setDate(4, user.getRegDate());
			row = ps.executeUpdate(); //插入数据库，返回SQL语句执行后受影响的行数
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBTool.closeAll(null, ps, conn);
		}
		
		return row;
	}

	
	
}
