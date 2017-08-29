package org.jheng.app.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jheng.app.dao.UserDAO;
import org.jheng.app.entity.Emp;

import javafx.scene.control.ComboBox;

public class MyFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textId;
	private JTextField textName;
	private JTextField textJob;
	private JTextField textDate;
	private JTextField textSalary;
	private JTextField textComm;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyFrame() {
		
		comboBox = new JComboBox();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("员工信息");
		setBounds(100, 100, 491, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox.setBounds(320, 157, 80, 21);
		contentPane.add(comboBox);
		
		JLabel labelId = new JLabel("工号");
		labelId.setBounds(10, 20, 54, 15);
		contentPane.add(labelId);
		
		textId = new JTextField();
		textId.setBounds(51, 17, 249, 21);
		contentPane.add(textId);
		textId.setColumns(10);
		
		JButton buttonSearch = new JButton("查找");
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textId.getText());
				UserDAO ud = new UserDAO();
				Emp emp = ud.findEmpById(id);
				textId.setText(emp.getEmpNo()+"");;
				textName.setText(emp.geteName());;
				textJob.setText(emp.getJob());
				textDate.setText(emp.getDate().toString());;
				textSalary.setText(emp.getSal()+"");;
				textComm.setText(emp.getComm()+"");
				
				ArrayList<String> arr = ud.insertInfoIntoList();
				//System.out.println(arr);
				for(int i = 0;i<arr.size();i++){
					comboBox.addItem(arr.get(i));
				}
				comboBox.setSelectedItem(emp.getDepartName());
			}
		});
		buttonSearch.setBounds(310, 16, 93, 23);
		contentPane.add(buttonSearch);
		
		JLabel labelName = new JLabel("姓名");
		labelName.setBounds(10, 64, 54, 15);
		contentPane.add(labelName);
		
		JLabel labelJob = new JLabel("职位");
		labelJob.setBounds(246, 64, 54, 15);
		contentPane.add(labelJob);
		
		JLabel labelDate = new JLabel("入职日期");
		labelDate.setBounds(10, 111, 71, 15);
		contentPane.add(labelDate);
		
		JLabel labelSalary = new JLabel("薪资");
		labelSalary.setBounds(246, 111, 71, 15);
		contentPane.add(labelSalary);
		
		JLabel labelComm = new JLabel("提成");
		labelComm.setBounds(10, 160, 54, 15);
		contentPane.add(labelComm);
		
		JLabel labelDepart = new JLabel("部门");
		labelDepart.setBounds(246, 160, 54, 15);
		contentPane.add(labelDepart);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(91, 61, 130, 21);
		contentPane.add(textName);
		
		textJob = new JTextField();
		textJob.setColumns(10);
		textJob.setBounds(315, 61, 130, 21);
		contentPane.add(textJob);
		
		textDate = new JTextField();
		textDate.setColumns(10);
		textDate.setBounds(91, 108, 130, 21);
		contentPane.add(textDate);
		
		textSalary = new JTextField();
		textSalary.setColumns(10);
		textSalary.setBounds(315, 108, 130, 21);
		contentPane.add(textSalary);
		
		textComm = new JTextField();
		textComm.setColumns(10);
		textComm.setBounds(91, 157, 130, 21);
		contentPane.add(textComm);
		
		JButton buttonUpdate = new JButton("更新");
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateFormat fm =new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date  date = null;
				try {
					 date = new java.sql.Date( fm.parse(textDate.getText()).getTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Emp emp = new Emp();
				emp.setEmpNo(Integer.parseInt(textId.getText()));
				emp.seteName(textName.getText());
				emp.setJob(textJob.getText());
				emp.setDate(date);
				emp.setSal(Integer.parseInt(textSalary.getText()));
				emp.setComm(Double.parseDouble(textComm.getText()));
				emp.setDepartName(comboBox.getSelectedItem().toString());
				//System.out.println(comboBox.getSelectedItem().toString());
				UserDAO ud  = new UserDAO();
				ud.update(emp);
			}
		});
		buttonUpdate.setBounds(229, 227, 71, 23);
		contentPane.add(buttonUpdate);
		
		JButton buttonDelete = new JButton("删除");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textId.getText());
				UserDAO ud = new UserDAO();
				ud.delete(id);
				JOptionPane.showMessageDialog(MyFrame.this, "删除成功");
			}
		});
		buttonDelete.setBounds(306, 227, 71, 23);
		contentPane.add(buttonDelete);
		
		JButton buttonExit = new JButton("退出");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rst = JOptionPane.showConfirmDialog(MyFrame.this, "确认关闭？","确认",JOptionPane.OK_CANCEL_OPTION);
				if(rst == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		buttonExit.setBounds(387, 227, 78, 23);
		contentPane.add(buttonExit);
		
		
		
	}
}
