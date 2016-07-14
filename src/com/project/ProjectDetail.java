package com.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class ProjectDetail extends JFrame implements ActionListener
{
	private JLabel lblprojectid,lblname,lblstartdate,lblenddate,lblclientid,lblappid=null;
	private JTextField txtprojectid,txtname,txtclientid,txtappid=null;
	private JDateChooser jdstartdate,jdenddate=null;
	private JButton jb=null;
	private String pid,name,sdt,edt,cid,appid=null;
	private  String phno=null;
	private Connection con=null;
	private PreparedStatement ps=null;

	public ProjectDetail()
	{
		setTitle("Project Detail");
		setLayout(null);
		setSize(400, 400);
		setLocationRelativeTo(null);
		con=CrudOperation.createConnection();
		createGUI();
		setVisible(true);
	        
	}
	
	public void createGUI()
	{
	lblprojectid=new JLabel("project Id");
	lblprojectid.setBounds(10, 10, 120, 40);
	add(lblprojectid);
	lblname=new JLabel("Project Name");
	lblname.setBounds(10, 50, 120, 40);
	add(lblname);
	lblstartdate=new JLabel("StartDate");
	lblstartdate.setBounds(10, 90, 120, 40);
	add(lblstartdate);
	lblenddate=new JLabel("EndDate");
	lblenddate.setBounds(10, 130, 120, 40);
	add(lblenddate);
	lblclientid=new JLabel("Client Id");
	lblclientid.setBounds(10, 170, 120, 40);
	add(lblclientid);
	lblappid=new JLabel("Application Id");
	
	lblappid.setBounds(10, 210, 120, 40);
	add(lblappid);
	
	txtprojectid=new JTextField();
	txtprojectid.setBounds(150, 10, 150, 30);
	add(txtprojectid);
	txtname=new JTextField();
	txtname.setBounds(150, 50, 150, 30);
	add(txtname);
	jdstartdate=new JDateChooser();
	jdstartdate.setBounds(150, 90, 150, 30);
	add(jdstartdate);
	jdenddate=new JDateChooser();
	jdenddate.setBounds(150, 130, 150, 30);
	add(jdenddate);
	txtclientid=new JTextField();
	txtclientid.setBounds(150, 170, 150, 30);
	add(txtclientid);
	txtappid=new JTextField();
    txtappid.setBounds(150, 210, 150, 30);
	add(txtappid);
	
	jb=new JButton("Submit");
	jb.setBounds(150, 270, 80, 30);
	jb.addActionListener(this);
	
	add(jb);
	}
	
	
	
	public boolean checkEmpty()
	{
		pid=txtprojectid.getText().trim();
		name=txtname.getText().trim();
		
		
		cid=txtclientid.getText().trim();
		appid=txtappid.getText().trim();
		
		if(cid.length()==0||name.length()==0||pid.length()==0||appid.length()==0)
		{
			return true;
		}
		else
			return false;
		
	}
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{

		
		if(checkEmpty()==true)
		{
			JOptionPane.showMessageDialog(this, "All entries are required");
		}
		
		else
		{
			//java.util.Date sdt=new java.util.Date();
			//java.util.Date edt=new java.util.Date();
			
			java.util.Date sdt=jdstartdate.getDate();
			java.sql.Date sqdt=new java.sql.Date(sdt.getTime());
			java.util.Date edt=jdenddate.getDate();
			java.sql.Date eqdt=new java.sql.Date(edt.getTime());
			
			String strinsert="insert into project values(?,?,?,?,?,?,?)";
			try
			{
				ps=con.prepareStatement(strinsert);
				ps.setString(1, pid);
				ps.setString(2, name);
				ps.setDate(3, sqdt );
				ps.setDate(4, eqdt);
				ps.setString(5, cid);
				ps.setString(6, appid);
				ps.setString(7, "not assigned");	
				int rw=ps.executeUpdate();
				if(rw>0)
				{
					JOptionPane.showMessageDialog(this, "record added successfully");
				}
			}
				catch(SQLException se)
				{
					System.out.println(se);
				}
				finally
				{
					if(ps!=null)
					try
					{
						ps.close();
					}
					catch(SQLException se)
					{
					System.out.println(se);
				}
				}
			}
			
		
	}
	
	
	public static void main(String[] args)
	 {                                       //main method is used only to run this page separately from AdminFrame.java 
	   new ProjectDetail();	
    }
}
