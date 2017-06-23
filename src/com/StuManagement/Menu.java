package com.StuManagement;
import javax.swing.*;

import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class Menu extends JFrame implements ActionListener{
	
	JPanel jp1,jp2;
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9;
	JLabel jl;
	JScrollPane jsp;
	
	private JSplitPane jsp1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jsp,jp2);
	private CardLayout cl;
	private StuInforAdmin stuInforAdmin;
	private StuAdding stuAdding;
	private StuInforUpdating stuInforUpdating;
	private StuDeleting stuDeleting;
	private StuGrate stuGrate;
	private ChangePassword changePassword;
	private StuAddCourse stuAddCourse;
	private StuCourseManage stuCourseManage;
	
	public Menu(){
		
		
		
		//���
		jp1=new JPanel();
		jb1=new JButton("ѧ��������Ϣ");
		jb1.addActionListener(this);
		jb2=new JButton("�������");
		jb2.addActionListener(this);
		jb3=new JButton("�޸�ѧ����Ϣ");
		jb3.addActionListener(this);
		jb4=new JButton("ɾ��ѧ��");
		jb4.addActionListener(this);
		jb5=new JButton("ѧ���ɼ�����");
		jb5.addActionListener(this);
		jb6=new JButton("��ӿγ�");
		jb6.addActionListener(this);
		jb7=new JButton("����ѡ������");
		jb7.addActionListener(this);
		jb8=new JButton("����Ա�޸�����");
		jb8.addActionListener(this);
		jb9=new JButton("�˳�ϵͳ");
		jb9.addActionListener(this);
		/*
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		*/
		JToolBar bar=new JToolBar();
		bar.add(jb1);
		bar.add(jb2);
		bar.add(jb3);
		bar.add(jb4);
		bar.add(jb5);
		bar.add(jb6);
		bar.add(jb7);
		bar.add(jb8);
		bar.add(jb9);
		jp1.add(bar);
		bar.setLayout(new GridLayout(20,1));
		this.add(jp1,BorderLayout.WEST);
		
		//��ҳ���
		jp2=new JPanel(cl);
		jl=new JLabel(new ImageIcon("test01.jpg"));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=400;
		int h=600;
		this.setBounds(centerX-w,centerY-h/3-100,w,h);//���ô����������Ļ����
		jl.setBounds(centerX-665, centerY-400, 700, 600);
		jsp=new JScrollPane(jl);
		this.add(jsp);
		jp2.add(jl);
		this.add(jp2,BorderLayout.CENTER);
		
		this.setSize(900,600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true); 
		
	
	}
	
	
	
	public static void main(String[] args){
		Menu test=new Menu();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			stuInforAdmin=new StuInforAdmin();
		}else if(e.getSource()==jb2){
			stuAdding=new StuAdding();
		}else if(e.getSource()==jb3){
			stuInforUpdating=new StuInforUpdating();
		}else if(e.getSource()==jb4){
			stuDeleting=new StuDeleting();
		}else if(e.getSource()==jb5){
			stuGrate=new StuGrate();
		}else if(e.getSource()==jb8){
			changePassword=new ChangePassword();
		}else if(e.getSource()==jb6){
			stuAddCourse=new StuAddCourse();
		}else if(e.getSource()==jb7){
			stuCourseManage=new StuCourseManage();
		}else if(e.getSource()==jb9){
			System.exit(0);
		}
	}
	
}
