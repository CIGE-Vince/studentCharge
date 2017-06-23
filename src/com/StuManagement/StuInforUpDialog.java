package com.StuManagement;

import javax.swing.*;

import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class StuInforUpDialog extends JDialog implements ActionListener{
	
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JPanel jp1,jp2,jp3;
	JButton jb1,jb2;
	
	private Connection cnon;
	private PreparedStatement psmt;
	private ResultSet rset;
	private Statement stmt;
	public String host="127.0.0.1:3306";
	
	public StuInforUpDialog(Frame owner,String title,boolean modal,StuInforAdminModel sm,int rowNums)
	{
		
		super(owner,title,modal);
		
		//����
		jp1=new JPanel();
		jl1=new JLabel("     ѧ��         ");
		jl2=new JLabel("     ����         ");
		jl3=new JLabel("     �Ա�         ");
		jl4=new JLabel("     ����         ");
		jl5=new JLabel("     ����         ");
		jl6=new JLabel("     ϵ��         ");
		
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		
		//����
		
		jp2=new JPanel();
		jtf1=new JTextField(10);
		jtf1.setText((String)sm.getValueAt(rowNums, 0));
		jtf1.setEditable(false);
		jtf2=new JTextField(10);
		jtf2.setText((String)sm.getValueAt(rowNums, 1));
		jtf3=new JTextField(10);
		jtf3.setText(sm.getValueAt(rowNums, 2).toString());
		jtf4=new JTextField(10);
		jtf4.setText((String)sm.getValueAt(rowNums, 3));
		jtf5=new JTextField(10);
		jtf5.setText((String)sm.getValueAt(rowNums, 4));
		jtf6=new JTextField(10);
		jtf6.setText((String)sm.getValueAt(rowNums, 5));
		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);
		
		//����
		jb1=new JButton("ȷ��");
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb2.addActionListener(this);
		jp3=new JPanel();
		jp3.add(jb1);
		jp3.add(jb2);
		
		jp1.setLayout(new GridLayout(6,1));
		jp2.setLayout(new GridLayout(6,1));
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		
		this.setSize(300,300);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=300;//��������
		int h=300;//������߶�
		this.setBounds(centerX-w/2,centerY-h/2-30,w,h);//���ô����������Ļ����
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true); 
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb2)
		{
			setVisible(false);
		}else if(arg0.getSource()==jb1)
		{
			try
			{
				Class.forName("org.gjt.mm.mysql.Driver");
				
				cnon=DriverManager.getConnection("jdbc:mysql://"+host+"/test1","root","2535663");
				String strsql="Update student set stuName=?,stuSex=?," +
						"stuAge=?,stuFrom=?,stuDept=? where stuId=?";
				psmt=cnon.prepareStatement(strsql);
				
				psmt.setString(1,jtf2.getText());
				psmt.setString(2,jtf3.getText());
				psmt.setString(3,jtf4.getText());
				psmt.setString(4,jtf5.getText());
				psmt.setString(5,jtf6.getText());
				psmt.setString(6,jtf1.getText());
				
				psmt.executeUpdate();
				
				this.dispose();
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"����ʧ�ܣ�����������ַ�Ƿ���ȷ","����",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			finally{
				try{
					if(rset!=null)rset.close();
					if(psmt!=null)psmt.close();
					if(cnon!=null)cnon.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}


