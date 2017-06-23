package com.StuManagement;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class StuGrate extends JFrame implements ActionListener {
	
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuGrateModel sm;
	
	private Connection ct;
	private PreparedStatement ps;
	private ResultSet rs;
	public String host="127.0.0.1:3306";
	
	public static void main(String[] args){
		StuGrate test3=new StuGrate();
	}
	
	public StuGrate()
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
		jp2=new JPanel();
		jb2=new JButton("���");
		jb2.addActionListener(this);
		jb3=new JButton("�޸�");
		jb3.addActionListener(this);
		jb4=new JButton("ɾ��");
		jb4.addActionListener(this);
		
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		
		
		
		StuGrateModel sm=new StuGrateModel();
		
		jt=new JTable(sm);
		
		jsp=new JScrollPane(jt);
		
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1)
		{
			System.out.println("�û�ϣ����ѯ"); 
			//��Ϊ�ѶԱ�����ݷ�װ��StuModel�У����ǾͿ��ԱȽϼ򵥵���ɲ�ѯ
			String stuID=this.jtf.getText().trim();
			String sql="select* from grate where stuId='"+stuID+"'";
			//�����µ�����ģ���࣬������
			sm=new StuGrateModel(sql);
			//����JTable
			jt.setModel(sm);
		}else if(e.getSource()==jb2)
		{
			System.out.println("�û�ϣ����ѯ"); 
			StuGrateAddDialog sa=new StuGrateAddDialog(this,"���ѧ��",true);
			//�����µ�����ģ���࣬������
			sm=new StuGrateModel();
			//����JTable
			jt.setModel(sm);
		}else if(e.getSource()==jb4)
		{
			sm=new StuGrateModel();
			//getSelectedRow�᷵���û����е��У�������û�һ�ж�ûѡ���ͷ���-1
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return ;
			}
			//�õ�ѧ�����
			String stuId=(String)sm.getValueAt(rowNum, 0);
			try
			{
				Class.forName("org.gjt.mm.mysql.Driver");
				ct=DriverManager.getConnection("jdbc:mysql://"+host+"/test1","root","2535663");
				ps=ct.prepareStatement("delete from student where stuId=?");
				ps.setString(1, stuId);
				ps.executeUpdate();
				
				
			}
			catch(SQLException e1)
			{
				JOptionPane.showMessageDialog(null,"����ʧ�ܣ�����������ַ�Ƿ���ȷ","����",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			catch(ClassNotFoundException e1)
			{
				e1.printStackTrace();
			}
			finally{
				try{
					if(rs!=null)rs.close();
					if(ps!=null)ps.close();
					if(ct!=null)ct.close();
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
			sm=new StuGrateModel();
			jt.setModel(sm);
			
		}else if(e.getSource()==jb3)
		{
			sm=new StuGrateModel();
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1)
			{
				
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return ;
			}
			
			StuGrateUpDialog stuGrateUpDialog = new StuGrateUpDialog(this,"�޸�ѧ����Ϣ",true,sm,rowNum);
			sm=new StuGrateModel();
			jt.setModel(sm);
		}
	}
}
