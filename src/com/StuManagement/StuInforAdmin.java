package com.StuManagement;
import javax.swing.*;

import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class StuInforAdmin extends JFrame implements ActionListener {
	
	JPanel jp1;
	JLabel jl1;
	JButton jb1;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuInforAdminModel sm;
	
	private Connection ct;
	private PreparedStatement ps;
	private ResultSet rs;
	public String host="127.0.0.1:3306";
	
	public static void main(String[] args){
		StuInforAdmin test3=new StuInforAdmin();
	}
	
	public StuInforAdmin()
	{
		//上面的
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("查询");
		jb1.addActionListener(this);
		jl1=new JLabel("请输入学号");
		
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		//下面的
		
		
		
		
		
		StuInforAdminModel sm=new StuInforAdminModel();
		
		jt=new JTable(sm);
		
		jsp=new JScrollPane(jt);
		
		this.add(jsp);
		this.add(jp1,"North");
		this.setSize(900,600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=600;//本窗体宽度
		int h=600;//本窗体高度
		this.setBounds(centerX-w/2,centerY-h/2-30,w,h);//设置窗体出现在屏幕中央
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true); 
	}
	public void actionPerformed(ActionEvent arg0){
		//判断哪个按钮被点击
		if(arg0.getSource()==jb1)
		{
			System.out.println("用户希望查询"); 
			//因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
			String stuID=this.jtf.getText().trim();
			String sql="select* from student where stuId='"+stuID+"'";
			//构建新的数据模型类，并更新
			sm=new StuInforAdminModel(sql);
			//更新JTable
			jt.setModel(sm);
		}
	}
}
