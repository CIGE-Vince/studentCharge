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

public class StuGrateUpdating extends JFrame implements ActionListener {
	
	JPanel jp1;
	JLabel jl1;
	JButton jb1,jb2;
	JTable jt;
	JScrollPane jsp;
	StuGrateModel sm;
	
	private Connection ct;
	private PreparedStatement ps;
	private ResultSet rs;
	public String host="127.0.0.1:3306";
	
	public static void main(String[] args){
		StuGrateUpdating test3=new StuGrateUpdating();
	}
	
	public StuGrateUpdating()
	{
		
		jp1=new JPanel();
		jb1=new JButton("�޸�");
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		
		StuGrateModel sm=new StuGrateModel();
		jt=new JTable(sm);
		jsp=new JScrollPane(jt);
		this.add(jsp);
		this.add(jp1,"South");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=600;//���������
		int h=600;//������߶�
		this.setBounds(centerX-w/2,centerY-h/2-30,w,h);//���ô����������Ļ����
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true); 
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==jb1)
			{
				sm=new StuGrateModel();
				int rowNum=this.jt.getSelectedRow();
				if(rowNum==-1)
				{
					
					JOptionPane.showMessageDialog(this, "��ѡ��һ��");
					return ;
				}

				new StuGrateUpDialog(this,"�޸�ѧ����Ϣ",true,sm,rowNum);
				sm=new StuGrateModel();
				jt.setModel(sm);
			}else if(e.getSource()==jb2){
				this.setVisible(false);
			}
	}
}