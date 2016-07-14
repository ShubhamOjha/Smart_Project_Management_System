package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShowStatus extends JFrame
{
	private String[] colnames={"Project ID","Project Name","Status"};
	

	private JTable jt=null;
	private JScrollPane jsp=null;
	private Object[][] data=null;
	private Connection con=null;
	private PreparedStatement pscount,psdata=null;
	private ResultSet rsdata,rscount=null;
	public ShowStatus()
	{
		setTitle("Project Status");
		con=CrudOperation.createConnection();
		
		setSize(400,400);
		setLayout(null);
		setLocationRelativeTo(null);
		createTable();
		setVisible(true);
	}
	
	public void createTable()
	{
		int row=0;
		String strcount="select count(*) from project";
		try
		{
			pscount=con.prepareStatement(strcount);
			rscount=pscount.executeQuery();
			rscount.next();
			int rowcnt=rscount.getInt(1);
			System.out.println(rowcnt);
			data=new Object[rowcnt][3];
			String strdata="select * from project";
			psdata=con.prepareStatement(strdata);
			rsdata=psdata.executeQuery();
			while(rsdata.next())
			{
				data[row][0]=rsdata.getString("projectid");
				data[row][1]=rsdata.getString("projectname");
				data[row][2]=rsdata.getString("status");
				row++;
				
			}
		}
		catch(SQLException se)

		{
			System.out.println(se);
		}
		finally
		{
			try
			{
			if(psdata!=null)
					psdata.close();
			
					if(rsdata!=null)
					rsdata.close();
					if(psdata!=null)
					psdata.close();
					if(pscount!=null)
					pscount.close();
					if(rscount!=null)
						rscount.close();
						}
			catch(SQLException se)
			{
			System.out.println(se);
		     }
		}
		jsp=new JScrollPane(new JTable(new DefaultTableModel(data,colnames)));
		jsp.setBounds(20, 20, 200, 100);
		add(jsp);
	}
	
			
	public static void main(String[] args)
	{
		new ShowStatus();
		
	}
		
	
	

	
}
