package com.project;
import java.sql.*;
import java.awt.event.*;

import javax.swing.*;
public class Login extends JFrame implements ActionListener
{
	private JLabel lbluserid,lbluserpass=null;
	private JTextField txtuserid=null;
	private JPasswordField txtuserpass=null;
	private JButton jb=null;
	private String ui,upass=null;
	private Connection con=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
		
	public Login(String title)
	{
		setTitle(title);
		setLayout(null);
		setSize(400, 400);
		con=CrudOperation.createConnection();
		createGUI();
		setVisible(true);
	}
	
	
	
	
	public void createGUI()
	{
		lbluserid=new JLabel("userid");
		lbluserid.setBounds(40, 50, 70, 30);
		add(lbluserid);
		txtuserid=new JTextField();
		txtuserid.setBounds(120, 50, 150, 30);
		add(txtuserid);
		lbluserpass=new JLabel("userpass");
		lbluserpass.setBounds(40, 100, 70, 30);
		add(lbluserpass);
		txtuserpass=new JPasswordField(30);
		txtuserpass.setBounds(120, 100, 150, 30);
		txtuserpass.setEchoChar('*');
		add(txtuserpass);
		jb=new JButton("submit");
		jb.setBounds(130, 150, 100, 30);
		jb.addActionListener(this);
		add(jb);
		
		
		
	}
	
	
	public boolean checkEmpty()
	{
		ui=txtuserid.getText().trim();
		char[] pwd=txtuserpass.getPassword();
		upass=new String(pwd).trim();
		if(ui.length()==0||upass.length()==0)
		{
			return true;
		}
		else
			return false;
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(checkEmpty()==true)
		{
			JOptionPane.showMessageDialog(this, "userid/password required");
		}
		else
		{
			
			String strsql="select * from account where userid=? and userpass=?";
			try
			{
			ps=con.prepareStatement(strsql);
			ps.setString(1, ui);
			ps.setString(2, upass);
			rs=ps.executeQuery();
			if(rs.next())
			{
				String ac=rs.getString("usertype");
				if(ac.equals("admin"))
				{
					Admin a=new Admin();
				}
				if(ac.equals("HR"))
				{
					HR hr=new HR();
				}
				
			
			}
			else
			{
				JOptionPane.showMessageDialog(this, " invalid userid/password");
				
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
	{
		new Login("login frame");
		
	}
	}
	
	
	
	


