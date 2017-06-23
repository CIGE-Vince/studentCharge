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

public class StuDeleting extends JFrame implements ActionListener {
	
	JPanel jp1,jp2;
	JLabel jl;
	JButton jb1,jb2,jb3;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuInforAdminModel sm;
	
	private Connection ct;
	private PreparedStatement ps;
	private ResultSet rs;
	public String host="127.0.0.1:3306";
	
	public static void main(String[] args){
		StuDeleting test3=new StuDeleting();
	}
	
	public StuDeleting()
	{
		
		jp1=new JPanel();
		jb1=new JButton("删除");
		jb1.addActionListener(this);
		jb2=new JButton("取消");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		
		jp2=new JPanel();
		
		jtf=new JTextField(10);
		jl=new JLabel("请输入姓名");
		jb3=new JButton("查询");
		jb3.addActionListener(this);
		jp2.add(jl);
		jp2.add(jtf);
		jp2.add(jb3);
		
		
		StuInforAdminModel sm=new StuInforAdminModel();
		jt=new JTable(sm);
		jsp=new JScrollPane(jt);
		this.add(jsp);
		this.add(jp1,"South");
		this.add(jp2,"North");
		
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
			sm=new StuInforAdminModel();
			jt.setModel(sm);
			
		}else if(e.getSource()==jb2){
			this.dispose();
		}
		
	}
}