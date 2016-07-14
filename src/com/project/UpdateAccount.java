package com.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateAccount extends JFrame implements ActionListener
{
	private JComboBox<String> cmbuserid=null;
	private JButton jb=null;
	private JLabel lblcmb=null;
	private Connection con=null;
	private JLabel lblpass=null;
	private JTextField txtpass=null;
	private PreparedStatement ps,psupdate=null;
	private ResultSet rs=null;
	
	
	public UpdateAccount()
	{
		con=CrudOperation.createConnection();
		setTitle("Update Account");
		setLayout(null);
		setSize(400, 400);
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
	cmbuserid.setBounds(100, 20, 100,30);
	cmbuserid.addItem("select userid");
	fillCombo();
	add(cmbuserid);
	cmbuserid.addActionListener(this);
	lblpass=new JLabel("Password");
	lblpass.setBounds(40, 100,60,30);
	add(lblpass);
	txtpass=new JTextField();
	txtpass.setBounds(100, 100, 100, 30);
	add(txtpass);
	
	jb=new JButton("update");
	jb.setBounds(100, 160,100,30);
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
	
	
	
	
	
public static void main(String[] args) 
{
	new UpdateAccount();
}
@Override
public void actionPerformed(ActionEvent e)
{

	String ui=(String)cmbuserid.getSelectedItem();
	if(e.getSource()==cmbuserid)
	{
		String strsql="select userpass from account where userid=?";
		con=CrudOperation.createConnection();
		try
		{
		ps=con.prepareStatement(strsql);
		ps.setString(1, ui);
		rs=ps.executeQuery();
		while(rs.next())
		{
			txtpass.setText(rs.getString("userpass"));
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
		
	if(e.getSource()==jb)
	{
		String upass=txtpass.getText();
		String strupdate="update account set userpass=? where userid=?";
		con=CrudOperation.createConnection();
		try
		{
			psupdate=con.prepareStatement(strupdate);
			psupdate.setString(1, upass);
			psupdate.setString(2, ui);
		int rw=psupdate.executeUpdate();
		if(rw>0)
		{
		JOptionPane.showMessageDialog(this, "password updated");	
		}
		}
		
		catch(SQLException se)
		{
			System.out.println(se);
		}
		finally
		{
			if(psupdate!=null)
			try
			{
				psupdate.close();
			}
			catch(SQLException se)
			{
			System.out.println(se);
		    }
		}

}
}
}