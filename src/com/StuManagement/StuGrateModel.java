package com.StuManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class StuGrateModel extends AbstractTableModel {

	Vector rowData,columnNames;
	private Connection ct;
	private PreparedStatement ps;
	private ResultSet rs;
	public String host="127.0.0.1:3306";
	
	public static void main(String[] args){
		StuGrateModel test4=new StuGrateModel();
	}
	
	public void initial(String sql)
	{
		if(sql.equals(""))
		{
			sql="select *from grate";
		}
		
		columnNames=new Vector();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("专业班级");
		columnNames.add("分数");
		
		rowData=new Vector();
		
		
		try
		{
			Class.forName("org.gjt.mm.mysql.Driver");
			ct=DriverManager.getConnection("jdbc:mysql://"+host+"/test1","root","2535663");
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				Vector hang=new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				rowData.add(hang);
			
			}
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
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(ct!=null)ct.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return (String)((Vector)this.rowData.get(row)).get(column);
	}
	
	//通过传递的sql语句来获得数据模型
	public StuGrateModel(String sql)
	{
		this.initial(sql);
	}
	
	//一个构造函数，用于初始化我们的数据模型
	public StuGrateModel()
	{
		this.initial("");
	}
}