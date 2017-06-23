package com.StuManagement;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.sql.*;

import javax.sql.*;
public class Login extends JFrame implements ActionListener {

	JTextField jtf1;
	JPasswordField jpw;
	JLabel jl1,jl2;
	JLabel image;
	JButton jb1,jb2;
	JPanel jp1,jp2,jp3;
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	public String host="127.0.0.1:3306";
	
	
	public Login(){
		jp1=new JPanel();
		image=new JLabel(new ImageIcon("zhejiang1.jpg"));
		jp1.add(image);
		
		jp2=new JPanel();
		jp2.setLayout(null);
		
		jl2=new JLabel("密码：");
		jl2.setBounds(30,60,110,25);
		
		jpw=new JPasswordField(10);
		jpw.setBounds(120,60,160,25);
		jpw.setEchoChar('*');
		
		jtf1=new JTextField(10);
		jtf1.setBounds(120,20,160,25);
		
		jl1=new JLabel("帐号：");
		jl1.setBounds(30,20,110,25);
		
		jp2.add(jl1);
		jp2.add(jtf1);
		jp2.add(jl2);
		jp2.add(jpw);
		this.add(jp2);
		
		jp3=new JPanel();
		jb1=new JButton("登陆");
		jb1.addActionListener(this);
		jb2=new JButton("退出");
		jb2.addActionListener(this);
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.NORTH);
		this.add(jp3,BorderLayout.SOUTH);
		
		this.setTitle("学生信息管理系统");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=300;//本窗体宽度
		int h=230;//本窗体高度
		this.setBounds(centerX-w/2,centerY-h/2-100,w,h);//设置窗体出现在屏幕中央
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args){
		Login login=new Login();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==this.jb1)
		{

			String name=this.jtf1.getText().trim();
			
			if(name.equals("")){
				JOptionPane.showMessageDialog(this,"请输入用户名","错误",
				                               JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String pwd=this.jpw.getText().trim();
			if(pwd.equals("")){
				JOptionPane.showMessageDialog(this,"请输入密码","错误",
				                           JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try{   
	            this.initialConnection();
	            
					String sql="select * from login where "+
					"L_user='"+name+"' and L_password='"+pwd+"'";
					rs=stmt.executeQuery(sql);
					if(rs.next()){
						new Menu();//创建学生客户短窗口
						this.dispose();//关闭登陆窗口并释放资源
					}
					else{
						JOptionPane.showMessageDialog(this,"用户名或密码错误","错误",
						                           JOptionPane.ERROR_MESSAGE);
						
					}
					this.closeConn();
				
			}
			catch(SQLException ea){ea.printStackTrace();}
		}
		else if(e.getSource()==jtf1){//当输入用户名并回车时
			this.jpw.requestFocus(true);
		}
		else if(e.getSource()==jpw){//当输入密码并回车时	
			this.jb1.requestFocus(true);
		}else if(e.getSource()==jb2){
			this.dispose();
		}
	}

	public void  initialConnection()
	{
		try
		{
			Class.forName("org.gjt.mm.mysql.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://"+host+"/test1","root","2535663");
			stmt=conn.createStatement();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(this,"连接失败，请检查主机地址是否正确","错误",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void closeConn()
	{
		try
		{
			if(rs!=null)
			{
				rs.close();
			}
			if(stmt!=null)
			{
				stmt.close();
			}
			if(conn!=null)
			{
				conn.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}
