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
		//�����
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("��ѯ");
		jb1.addActionListener(this);
		jl1=new JLabel("������ѧ��");
		
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		//�����
		
		
		
		
		
		StuInforAdminModel sm=new StuInforAdminModel();
		
		jt=new JTable(sm);
		
		jsp=new JScrollPane(jt);
		
		this.add(jsp);
		this.add(jp1,"North");
		this.setSize(900,600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=600;//��������
		int h=600;//������߶�
		this.setBounds(centerX-w/2,centerY-h/2-30,w,h);//���ô����������Ļ����
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true); 
	}
	public void actionPerformed(ActionEvent arg0){
		//�ж��ĸ���ť�����
		if(arg0.getSource()==jb1)
		{
			System.out.println("�û�ϣ����ѯ"); 
			//��Ϊ�ѶԱ�����ݷ�װ��StuModel�У����ǾͿ��ԱȽϼ򵥵���ɲ�ѯ
			String stuID=this.jtf.getText().trim();
			String sql="select* from student where stuId='"+stuID+"'";
			//�����µ�����ģ���࣬������
			sm=new StuInforAdminModel(sql);
			//����JTable
			jt.setModel(sm);
		}
	}
}
