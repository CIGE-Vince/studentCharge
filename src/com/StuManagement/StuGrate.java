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
		jp2=new JPanel();
		jb2=new JButton("添加");
		jb2.addActionListener(this);
		jb3=new JButton("修改");
		jb3.addActionListener(this);
		jb4=new JButton("删除");
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
			System.out.println("用户希望查询"); 
			//因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
			String stuID=this.jtf.getText().trim();
			String sql="select* from grate where stuId='"+stuID+"'";
			//构建新的数据模型类，并更新
			sm=new StuGrateModel(sql);
			//更新JTable
			jt.setModel(sm);
		}else if(e.getSource()==jb2)
		{
			System.out.println("用户希望查询"); 
			StuGrateAddDialog sa=new StuGrateAddDialog(this,"添加学生",true);
			//构建新的数据模型类，并更新
			sm=new StuGrateModel();
			//更新JTable
			jt.setModel(sm);
		}else if(e.getSource()==jb4)
		{
			sm=new StuGrateModel();
			//getSelectedRow会返回用户点中的行，如果该用户一行都没选，就返回-1
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1)
			{
				JOptionPane.showMessageDialog(this, "请选择一行");
				return ;
			}
			//得到学生编号
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
				JOptionPane.showMessageDialog(null,"连接失败，请检查主机地址是否正确","错误",JOptionPane.ERROR_MESSAGE);
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
				
				JOptionPane.showMessageDialog(this, "请选择一行");
				return ;
			}
			
			StuGrateUpDialog stuGrateUpDialog = new StuGrateUpDialog(this,"修改学生信息",true,sm,rowNum);
			sm=new StuGrateModel();
			jt.setModel(sm);
		}
	}
}
