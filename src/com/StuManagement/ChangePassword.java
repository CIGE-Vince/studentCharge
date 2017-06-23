package com.StuManagement;
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
public class ChangePassword extends JFrame implements ActionListener
{ 
   
    //���ݿ����Ӳ�ѯ����
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	public static String host="127.0.0.1:3306";
	
	private JPanel jp1,jp2;
	private JButton jb1,jb2;
	private JLabel jl1,jl2,jl3,jl4;
	private JTextField jtf1,jtf2,jtf3,jtf4;
	
	public ChangePassword(){
		
		
		jp1=new JPanel();
		jb1=new JButton("ȷ��");
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		this.add(jp1,BorderLayout.SOUTH);
		
		jp2=new JPanel();
		jp2.setLayout(null);
		jl1=new JLabel("�˺ţ�");
		jl1.setBounds(30,20,110,25);
		jl2=new JLabel("ԭʼ���룺");
		jl2.setBounds(30,60,110,25);
		jl3=new JLabel("�����룺");
		jl3.setBounds(30,100,110,25);
		jl4=new JLabel("ȷ�������룺");
		jl4.setBounds(30,140,110,25);
		
		jtf1=new JTextField(10);
		jtf1.setBounds(120,20,160,25);
		jtf2=new JTextField(10);
		jtf2.setBounds(120,60,160,25);
		jtf3=new JTextField(10);
		jtf3.setBounds(120,100,160,25);
		jtf4=new JTextField(10);
		jtf4.setBounds(120,140,160,25);
		
		jp2.add(jl1);
		jp2.add(jtf1);
		jp2.add(jl2);
		jp2.add(jtf2);
		jp2.add(jl3);
		jp2.add(jtf3);
		jp2.add(jl4);
		jp2.add(jtf4);
		this.add(jp2);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=310;//��������
		int h=280;//������߶�
		this.setBounds(centerX-w/2,centerY-h/2-100,w,h);//���ô����������Ļ����
		
	}
	
	public static void main(String[] args){
		ChangePassword changePassword=new ChangePassword();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1)//����ȷ�ϰ�ť�Ĵ������
		{
		    //�����ж������ʽ������ʽ�ַ���
			String patternStr="[0-9a-zA-Z]{6,12}";
			String user_name=jtf2.getText().trim();
			if(user_name.equals(""))
			{
				JOptionPane.showMessageDialog(this,"�������û���",
				                "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String oldPwd=jtf2.getText().trim();
			if(oldPwd.equals(""))
			{
				JOptionPane.showMessageDialog(this,"������ԭʼ����",
				                  "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String newPwd=jtf3.getText().trim();
			if(newPwd.equals(""))
			{
				JOptionPane.showMessageDialog(this,"������������",
				                "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!newPwd.matches(patternStr))
			{
				JOptionPane.showMessageDialog(this,
				                  "����ֻ����6��12λ����ĸ������",
				                  "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String newPwd1=jtf4.getText().trim();
			if(!newPwd.equals(newPwd1)){
			
				JOptionPane.showMessageDialog(this,"ȷ�������������벻��",
				                       "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
			try
			{
				this.initialConnection();
				String sql="update login set L_password='"+newPwd+"'"+
				            " where L_user='"+user_name+"'"+
				           " and L_password='"+oldPwd+"'";
				int i=stmt.executeUpdate(sql);
				System.out.print(i);
				if(i==0)
				{//����ʧ��
					JOptionPane.showMessageDialog(this,
					      "�޸�ʧ�ܣ����������û����������Ƿ���ȷ",
					      "����",JOptionPane.ERROR_MESSAGE);
				}
				else if(i==1)
				{//���ĳɹ�
					JOptionPane.showMessageDialog(this,"�����޸ĳɹ�",
					           "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
				this.closeConn();
			}
			catch(Exception ea){
				ea.printStackTrace();
			}
		}
		//this.dispose();
	}
	

	//�������ݿ�
	public void  initialConnection()
	{
		try
		{
			Class.forName("org.gjt.mm.mysql.Driver");
			conn=DriverManager.getConnection(
				 "jdbc:mysql://"+host+"/test1","root","2535663");
			stmt=conn.createStatement();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	//�ر����ݿ�
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
	

