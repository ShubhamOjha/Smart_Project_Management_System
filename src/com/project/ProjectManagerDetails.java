package com.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ProjectManagerDetails extends JFrame implements ActionListener
{
private JLabel lbluserid,lblname,lbladdress,lblemail,lblphno=null;
private JTextField txtuserid,txtname,txtaddress,txtemail,txtphno=null;
private JButton jb=null;
private String cid,name,address,email=null;
private  String phno=null;
private Connection con=null;
private PreparedStatement ps=null;


	public ProjectManagerDetails()
	{
		setTitle("Project Manager Detail");
		setLayout(null);
		setSize(400, 400);
		setLocationRelativeTo(null);
		con=CrudOperation.createConnection();
		createGUI();
		setVisible(true);
	        
	}
	

	public void createGUI()
	{
	lbluserid=new JLabel("UserId");
	lbluserid.setBounds(10, 10, 60, 40);
	add(lbluserid);
	lblname=new JLabel("Name");
	lblname.setBounds(10, 50, 60, 40);
	add(lblname);
	lbladdress=new JLabel("Address");
	lbladdress.setBounds(10, 90, 60, 40);
	add(lbladdress);
	lblemail=new JLabel("Email");
	lblemail.setBounds(10, 130, 60, 40);
	add(lblemail);
	lblphno=new JLabel("Phone No");
	lblphno.setBounds(10, 170, 60, 40);
	add(lblphno);
	
	txtuserid=new JTextField();
	txtuserid.setBounds(80, 10, 150, 30);
	add(txtuserid);
	txtname=new JTextField();
	txtname.setBounds(80, 50, 150, 30);
	add(txtname);
	txtaddress=new JTextField();
	txtaddress.setBounds(80, 90, 150, 30);
	add(txtaddress);
	txtemail=new JTextField();
	txtemail.setBounds(80, 130, 150, 30);
	add(txtemail);
	txtphno=new JTextField();
	txtphno.setBounds(80, 170, 150, 30);
	add(txtphno);
	jb=new JButton("Submit");
	jb.setBounds(50, 220, 80, 30);
	add(jb);
	jb.addActionListener(this);
	
	
	
	
	}
	
	public boolean checkEmpty()
	{
		cid=txtuserid.getText().trim();
		name=txtname.getText().trim();
		address=txtaddress.getText().trim();
		email=txtemail.getText().trim();
	   // phno=Long.parseLong(txtphno.getText().trim());
		phno=txtphno.getText().trim();
		
		if(cid.length()==0||name.length()==0||address.length()==0||email.length()==0||phno.length()==0)
		{
			return true;
		}
		else
			return false;
		
	}
	
	
	
	public static void main(String[] args)
	 {                                       //main method is used only to run this page separately from AdminFrame.java 
	   new ProjectManagerDetails();	
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
			String strinsert="insert into projectmanager values(?,?,?,?,?)";
			try
			{
				ps=con.prepareStatement(strinsert);
				ps.setString(1, cid);
				ps.setString(2, name);
				ps.setString(3, address);
				ps.setString(4, email);
				ps.setString(5, phno);
				
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

	
}
