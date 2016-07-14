package com.project;

import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class DeleteAccount extends JFrame implements ActionListener
{

	private JComboBox<String> cmbuserid=null;
	private JButton jb=null;
	private JLabel lblcmb=null;
	private Connection con=null;
	private PreparedStatement ps,psdelete=null;
	private ResultSet rs=null;
	
	
	public DeleteAccount()
	{
		setTitle("Delete Account");
		setSize(400,400);
		setLayout(null);
		setLocationRelativeTo(null);
		
		createGUI();
		setVisible(true);
	}
	
	public void createGUI()
	{
		lblcmb=new JLabel("userid");
		lblcmb.setBounds(40,20,50,30);
		add(lblcmb);
		
	cmbuserid=new JComboBox<String>();
	cmbuserid.setBounds(80, 20, 100,30);
	cmbuserid.addItem("select userid");
	fillCombo();
	add(cmbuserid);
	jb=new JButton("Delete");
	jb.setBounds(80, 100,100,30);
	jb.addActionListener(this);
	add(jb);
	
	}
	
	public void fillCombo()
	{
		String strsql="select userid from account where usertype!='admin'";
		con=CrudOperation.createConnection();
		try
		{
		ps=con.prepareStatement(strsql);
		rs=ps.executeQuery();
		while(rs.next())
		{
			cmbuserid.addItem(rs.getString("userid"));
		}
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}
	
	
	
	
	
public static void main(String[] args) 
{
	new DeleteAccount();
}

@Override
public void actionPerformed(ActionEvent e) 
{
 int conf=JOptionPane.showConfirmDialog(this, "are u sure");
		 if(conf==0)
		 {
	String ui=(String)cmbuserid.getSelectedItem();
	String strdelete="delete from account where userid=?";
	con=CrudOperation.createConnection();
	try
	{
		psdelete=con.prepareStatement(strdelete);
	psdelete.setString(1, ui);
	int rw=psdelete.executeUpdate();
	if(rw>0)
	{
	JOptionPane.showMessageDialog(this, "record deleted");	
	}
	else
	{
	JOptionPane.showMessageDialog(this, "select userid");	
	}
	
	}
	
	catch(SQLException se)

	{
		System.out.println(se);
	}
	finally
	{
		if(psdelete!=null)
		try
		{
			psdelete.close();
		}
		catch(SQLException se)
		{
		System.out.println(se);
	     }
	}
}
	
}
}




