package com.StuManagement;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class StuInforUpdating extends JFrame implements ActionListener {
	
	JPanel jp1;
	JLabel jl1;
	JButton jb1,jb2;
	JTable jt;
	JScrollPane jsp;
	StuInforAdminModel sm;
	
	private Connection ct;
	private PreparedStatement ps;
	private ResultSet rs;
	public String host="127.0.0.1:3306";
	
	public static void main(String[] args){
		StuInforUpdating test3=new StuInforUpdating();
	}
	
	public StuInforUpdating()
	{
		
		jp1=new JPanel();
		jb1=new JButton("修改");
		jb1.addActionListener(this);
		jb2=new JButton("取消");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		
		StuInforAdminModel sm=new StuInforAdminModel();
		jt=new JTable(sm);
		jsp=new JScrollPane(jt);
		this.add(jsp);
		this.add(jp1,"South");
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==jb1)
			{
				sm=new StuInforAdminModel();
				int rowNum=this.jt.getSelectedRow();
				if(rowNum==-1)
				{
					
					JOptionPane.showMessageDialog(this, "请选择一行");
					return ;
				}

				new StuInforUpDialog(this,"修改学生信息",true,sm,rowNum);
				sm=new StuInforAdminModel();
				jt.setModel(sm);
			}else if(e.getSource()==jb2){
				this.setVisible(false);
			}
	}
}