package com.StuManagement;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;
import javax.swing.tree.*;
import java.sql.*;
import javax.sql.*;
import javax.swing.table.*;
public class StuCourseManage extends JFrame implements ActionListener
{   
	private String host="127.0.0.1:3306";
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private String coll_id="01";
	
	//������ſγ̱������ѡ�γ̱���ı�ͷ�����ݵ�Vector
	private Vector<String> columnNames1=new Vector<String>();
	private Vector<String> columnNames2=new Vector<String>();
	private Vector<Vector> rowData1=new Vector<Vector>();
	private Vector<Vector> rowData2=new Vector<Vector>();
	
	private JLabel[] jlArray={new JLabel("���пγ��б�:"),new JLabel("�Ѱ��ſγ��б�:"),
	                     new JLabel("��������Ҫ���ŵĿγ̵Ŀγ̺�:"),new JLabel("��ʦ"),
	                     new JLabel("�Ͽ�ʱ��:"),new JLabel("����"),new JLabel("����"),
	                     new JLabel("��������Ҫ�Ƴ��Ŀγ̵Ŀγ̺�:"),new JLabel("����"),
	                     new JLabel("����")
                        };
	
    private static final String[] xingqi={"1","2","3","4","5","6","7"};
    private static final String[] jiangci={"1","2","3","4","5"};
    
    private JButton[] jbArray={new JButton("�ύ�ÿγ�"),new JButton("�Ƴ��ÿγ�"),new JButton("����ѡ��"),new JButton("ֹͣѡ��")};
    private JTextField[] jtfArray={new JTextField(),new JTextField(),new JTextField(),new JTextField()};
    private JComboBox[] jcbArray={new JComboBox(xingqi),new JComboBox(jiangci),
                                  new JComboBox(xingqi),new JComboBox(jiangci)};
  
    private JTable jt1=null;
    private JTable jt2=null;
    private JScrollPane jsp1=null;
    private JScrollPane jsp2=null;
	public StuCourseManage()
	{
		
		//��ʼ������
		this.initialData();
		this.setLayout(null);
		//��������
		jt1=new JTable(new DefaultTableModel(rowData1,columnNames1));
		jt2=new JTable(new DefaultTableModel(rowData2,columnNames2));
		jsp1=new JScrollPane(jt1);
		jsp2=new JScrollPane(jt2);

		jlArray[0].setBounds(30,10,150,30);
		this.add(jlArray[0]);
		jsp1.setBounds(30,50,600,150);
		this.add(jsp1);
		jlArray[1].setBounds(30,210,150,30);
		this.add(jlArray[1]);
		jsp2.setBounds(30,250,600,150);
		this.add(jsp2);
		jlArray[2].setBounds(30,410,200,25);
		this.add(jlArray[2]);
		jtfArray[0].setBounds(230,410,100,25);
		this.add(jtfArray[0]);
		jlArray[3].setBounds(350,410,40,25);
		this.add(jlArray[3]);
		jtfArray[1].setBounds(390,410,150,25);
		this.add(jtfArray[1]);
		jlArray[4].setBounds(30,445,80,25);
		this.add(jlArray[4]);
		jlArray[5].setBounds(110,445,40,25);
		this.add(jlArray[5]);
		jcbArray[0].setBounds(150,445,40,25);
		this.add(jcbArray[0]);
		jlArray[6].setBounds(195,445,30,25);
		this.add(jlArray[6]);
		jcbArray[1].setBounds(225,445,40,25);
		this.add(jcbArray[1]);
		jbArray[0].setBounds(300,445,130,25);
		this.add(jbArray[0]);
		jlArray[7].setBounds(30,500,200,25);
		this.add(jlArray[7]);
		jtfArray[2].setBounds(230,500,100,25);
		this.add(jtfArray[2]);
		jlArray[8].setBounds(30,535,40,25);
		this.add(jlArray[8]);
		jcbArray[2].setBounds(70,535,40,25);
		this.add(jcbArray[2]);
		jlArray[9].setBounds(120,535,60,25);
		this.add(jlArray[9]);
		jcbArray[3].setBounds(147,535,40,25);
		this.add(jcbArray[3]);
		jbArray[1].setBounds(200,535,150,25);
		this.add(jbArray[1]);
		jbArray[2].setBounds(100,570,100,30);
		this.add(jbArray[2]);
		jbArray[3].setBounds(300,570,100,30);
		this.add(jbArray[3]);
		jbArray[0].addActionListener(this);
		jbArray[1].addActionListener(this);
		jbArray[2].addActionListener(this);
		jbArray[3].addActionListener(this);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=700;
		int h=650;
		this.setBounds(centerX-w,centerY-h/3-100,w,h);//���ô����������Ļ����
		this.setBounds(centerX-350, centerY-350, 700, 650);
		this.setVisible(true);
	}


	
	//��ʼ�����ݵķ��� 
	public void initialData()
	{
		this.initialHead();//��ʼ����ͷ
		this.initialData1();//��ʼ���γ̱��������
		this.initialData2();//��ʼ����ѡ�γ̱��������
	}
	

	
	public void initialData1()
	{//��ʼ���γ̱������ݵķ���
		try
		{//��ѯ���ݿ⣬��ÿ�������пγ�
			String sql="select cou_id,cou_name,xuefen,dept.dept_name from "+
			       "course,dept where dept.dept_id=course.dept_id and"+
			       " course.coll_id='"+coll_id+"'";
			this.initialConnection();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				Vector v=new Vector();
				String cou_id=rs.getString(1);
				String cou_name=new String(rs.getString(2).getBytes("gb2312"));
				String xuefen=rs.getDouble(3)+"";
				String dept_name=new String(rs.getString(4).getBytes("gb2312"));
				v.add(cou_id);
				v.add(cou_name);
				v.add(xuefen);
				v.add(dept_name);
				this.rowData1.add(v);
			}
			rs.close();
		}
		catch(SQLException e)
		{e.printStackTrace();}
		catch(UnsupportedEncodingException e)
		{e.printStackTrace();}
	}
	
	public void initialData2()
	{//��ʼ����ѡ�γ̱���ķ���
		try
		{//��ȡ��ѡ�γ�
			String sql="select courseinfo.cou_id,course.cou_name,cou_day,"+
			            "cou_time,teacher from courseinfo,course where "+
			       "course.coll_id='"+coll_id+"' and courseinfo.cou_id=course.cou_id";
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				Vector v=new Vector();
				String cou_id=rs.getString(1);
				String cou_name=new String(rs.getString(2).getBytes("gb2312"));
				String cou_day=rs.getString(3);
				String cou_time=rs.getString(4);
				String teacher=new String(rs.getString(5).getBytes("gb2312"));
				v.add(cou_id);v.add(cou_name);v.add(cou_day);
				v.add(cou_time);v.add(teacher);
				this.rowData2.add(v);
			} 	           
		}
		catch(SQLException e)
		{e.printStackTrace();}
		catch(UnsupportedEncodingException e)
		{e.printStackTrace();}
		finally
		{this.closeConn();}
	}
	
	public void initialHead()
	{
		this.columnNames1.add("�γ̺�");
		this.columnNames1.add("�γ���");
		this.columnNames1.add("ѧ��");
		this.columnNames1.add("����רҵ");
		
		this.columnNames2.add("�γ̺�");
		this.columnNames2.add("�γ���");
		this.columnNames2.add("����");
		this.columnNames2.add("����");
		this.columnNames2.add("�ο���ʦ");
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jbArray[0]){
		     this.submitCourse();
		}
		else if(e.getSource()==jbArray[1])
		{
			this.removeCourse();
		}
		else if(e.getSource()==jbArray[2]){
		   //����ѡ�б��е����пγ̶���Ϊѧ���ɼ���
		   this.permitChose();
		}
		else if(e.getSource()==jbArray[3]){
		    //����ѡ�б��еĿγ���Ϊѧ�����ɼ�
		    this.stopChose();
		}
	}
	
	public void submitCourse()
	{
		
		try{
			this.initialConnection();
			String cou_id=jtfArray[0].getText().trim();
			
			if(cou_id.equals("")){
				JOptionPane.showMessageDialog(this,"������γ̺�",
				                   "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Vector v=new Vector();
			String sql1="select dept_name,cou_name from dept,course "+
			           "where dept.dept_id=course.dept_id and "+
			           "course.cou_id='"+cou_id+"' and course.coll_id='"+coll_id+"'";
			 rs=stmt.executeQuery(sql1);
			 if(rs.next()){
			 	String dept_name=new String(rs.getString(1).getBytes("gbk"));
				String cou_name=new String(rs.getString(2).getBytes("gbk"));
				v.add(cou_id);
				v.add(cou_name);
			 }
			 
			 else{
			 	JOptionPane.showMessageDialog(this,"����ʧ��,��ѧԺû�иÿγ�",
			 	                            "����",JOptionPane.ERROR_MESSAGE);
				return;
			 }
			 rs.close();
			 
			//��ÿ���ʱ������ʦ
			String cou_day=(String)jcbArray[0].getSelectedItem();
			String cou_time=(String)jcbArray[1].getSelectedItem();
			String teacher=jtfArray[1].getText().trim();
			if(teacher.equals("")){
				JOptionPane.showMessageDialog(this,"�������ʦ","����",
				                             JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//����sql��䣬����������Ϣд�����ݿ�
			String sql="insert into courseinfo values"+
			            "('"+cou_id+"','"+cou_day+"','"+cou_time+"',"+
			            "'"+new String(teacher.getBytes(),"gbk")+"','0')";
			int i=stmt.executeUpdate(sql);
			if(i!=1){//����ʧ��
				JOptionPane.showMessageDialog(this,"����ʧ�ܣ������Ƿ����Ժ�γ��ظ�",
				                                    "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else{//���ӳɹ������±���ģ�ͼ���ͼ
					v.add(cou_day);v.add(cou_time);v.add(teacher);
					DefaultTableModel temp=(DefaultTableModel)jt2.getModel();
					temp.getDataVector().add(v);
					((DefaultTableModel)jt2.getModel()).fireTableStructureChanged();
			}
		}
		catch(SQLException ea)
		{ea.printStackTrace();}
		catch(UnsupportedEncodingException ea)
		{ea.printStackTrace();}
		finally
		{this.closeConn();}
	}
	
	public void removeCourse()
	{
		String cou_id=jtfArray[2].getText().trim();
		if(cou_id.equals("")){
			JOptionPane.showMessageDialog(this,"������γ̺�","����",
			                             JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//���Ҫ�Ƴ��γ̵��Ͽ�ʱ��
		String cou_day=(String)jcbArray[2].getSelectedItem();
		String cou_time=(String)jcbArray[3].getSelectedItem();
		
		try{//�鿴�Ƿ�ÿ��ڿ�ѡ�б��������Ƴ�
			this.initialConnection();
			String sql1="select cou_name,teacher from courseinfo,course where "+
			      "course.cou_id=courseinfo.cou_id and course.cou_id='"+cou_id+"' and "+
			       "cou_day='"+cou_day+"' and cou_time='"+cou_time+"'";
			rs=stmt.executeQuery(sql1);
			if(rs.next()){
				String cou_name=new String(rs.getString(1).getBytes("gbk"));
				String teacher=new String(rs.getString(2).getBytes("gbk"));
				String sql="delete from courseinfo where cou_id='"+cou_id+"' and"+
				           " cou_day='"+cou_day+"' and cou_time='"+cou_time+"'";
				stmt.executeUpdate(sql);
				Vector v=new Vector();
				v.add(cou_id);v.add(cou_name);v.add(cou_day);
				v.add(cou_time);v.add(teacher);
				
				//���±�����ͼ��ʾ
				DefaultTableModel temp=(DefaultTableModel)jt2.getModel();
				temp.getDataVector().remove(v);
				((DefaultTableModel)jt2.getModel()).fireTableStructureChanged();
			}
		}
		catch(SQLException ea)
		{ea.printStackTrace();}
		catch(UnsupportedEncodingException ea)
		{ea.printStackTrace();}
		finally
		{this.closeConn();}
	}
	
	public void permitChose()
	{
		String sql="update courseinfo,course set onchosing='1' where "+
		            "courseinfo.cou_id=course.cou_id "+
		            "and course.coll_id='"+coll_id+"'";
		try{
			this.initialConnection();
			stmt.executeUpdate(sql);
			this.closeConn();
			JOptionPane.showMessageDialog(this,"�����ɹ�",
                    "������ʾ",JOptionPane.INFORMATION_MESSAGE);
		}
		
		catch(Exception ea)
		{ea.printStackTrace();
		JOptionPane.showMessageDialog(this,"����ʧ��",
                "������ʾ",JOptionPane.ERROR_MESSAGE);}
	}
	
	public void stopChose()
	{
		String sql="update courseinfo,course set onchosing='0' where"+
		             " courseinfo.cou_id=course.cou_id "+
		             "and course.coll_id='"+coll_id+"'";
		try{
			this.initialConnection();
			stmt.executeUpdate(sql);
			this.closeConn();
			JOptionPane.showMessageDialog(this,"�����ɹ�",
                    "������ʾ",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception ea)
		{ea.printStackTrace();
		JOptionPane.showMessageDialog(this,"����ʧ��",
                "������ʾ",JOptionPane.ERROR_MESSAGE);}
	}
	
	//���±�������
	public void updateTable()
	{
		this.initialConnection();
		rowData1.removeAllElements();
		this.initialData1();
		((DefaultTableModel)jt1.getModel()).setDataVector(rowData1,columnNames1);
		((DefaultTableModel)jt1.getModel()).fireTableStructureChanged();
		this.closeConn();
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
	
	public static void main(String args[])
	{
		StuCourseManage cm=new StuCourseManage();
	}
}