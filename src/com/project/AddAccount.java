package com.project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class AddAccount extends JFrame implements ActionListener
{
	private JLabel lbluserid,lbluserpass,lblrole=null;
	private JTextField txtuserid=null;
	private JPasswordField txtuserpass=null;
	private JComboBox<String> cmbrole=null;
	private JButton jb=null;
	private String ui,upass=null;
	String utype=null;
	private Connection con=null;
	private PreparedStatement ps=null;
	
	
	
	public AddAccount()
	{
		setTitle("Add Account");
		setLayout(null);
		setSize(400, 400);
		setLocationRelativeTo(null);
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
		lblrole=new JLabel("Role");
		lblrole.setBounds(40, 150, 70, 30);
		add(lblrole);
		
		cmbrole=new JComboBox();
		cmbrole.setBounds(120, 150, 150, 30);
		cmbrole.addItem("Select Role");
		cmbrole.addItem("HR");
		add(cmbrole);
		jb=new JButton("submit");
		jb.setBounds(130, 250, 100, 30);
		jb.addActionListener(this);
		add(jb);	
	}
	
	
	
	public boolean checkEmpty()
	{
		utype=(String)cmbrole.getSelectedItem();
		ui=txtuserid.getText().trim();
		char[] pwd=txtuserpass.getPassword();
		upass=new String(pwd).trim();
		if(ui.length()==0||upass.length()==0||utype=="Select Role")
		{
			return true;
		}
		else
			return false;
		
	}
	
	
	
	public static void main(String[] args)
	 {                                       //main method is used only to run this page separately from AdminFrame.java 
	   new AddAccount();	
        }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(checkEmpty()==true)
		{
			JOptionPane.showMessageDialog(this, "userid/password/role required");
		}
		else
		{
			String strinsert="insert into account values(?,?,?)";
			try
			{
				ps=con.prepareStatement(strinsert);
				ps.setString(1, ui);
				ps.setString(2, upass);
				ps.setString(3, utype);
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
	
   