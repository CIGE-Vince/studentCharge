package com.StuManagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class StuGrateAddDialog extends JDialog implements ActionListener{
	
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JPanel jp1,jp2,jp3;
	JButton jb1,jb2;
	
	private Connection cnon;
	private PreparedStatement psmt;
	private ResultSet rset;
	private Statement stmt;
	public String host="127.0.0.1:3306";
	
	public StuGrateAddDialog(Frame owner,String title,boolean modal)
	{
		
		super(owner,title,modal);
		
		//左上
		jp1=new JPanel();
		jl1=new JLabel("     学号         ");
		jl2=new JLabel("     姓名         ");
		jl3=new JLabel("     性别         ");
		jl4=new JLabel("     专业         ");
		jl5=new JLabel("     分数         ");
		
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		
		//右上
		jp2=new JPanel();
		jtf1=new JTextField(10);
		jtf2=new JTextField(10);
		jtf3=new JTextField(10);
		jtf4=new JTextField(10);
		jtf5=new JTextField(10);
		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		
		//下面
		jb1=new JButton("确定");
		jb1.addActionListener(this);
		jb2=new JButton("取消");
		jb2.addActionListener(this);
		jp3=new JPanel();
		jp3.add(jb1);
		jp3.add(jb2);
		
		jp1.setLayout(new GridLayout(5,1));
		jp2.setLayout(new GridLayout(5,1));
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		
		this.setSize(300,300);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX=screenSize.width/2;
		int centerY=screenSize.height/2;
		int w=300;//本窗体宽度
		int h=300;//本窗体高度
		this.setBounds(centerX-w/2,centerY-h/2-30,w,h);//设置窗体出现在屏幕中央
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
				String strsql="insert into grate values(?,?,?,?,?)";
				psmt=cnon.prepareStatement(strsql);
				System.out.println("用户希望查询"); 
				psmt.setString(1,jtf1.getText());
				psmt.setString(2,jtf2.getText());
				psmt.setString(3,jtf3.getText());
				psmt.setString(4,jtf4.getText());
				psmt.setString(5,jtf5.getText());
				
				psmt.executeUpdate();
				
				this.dispose();
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"连接失败，请检查主机地址是否正确","错误",JOptionPane.ERROR_MESSAGE);
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