package com.project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.project.AddAccount;
import com.project.DeleteAccount;
import com.project.UpdateAccount;
public class Admin extends JFrame implements ActionListener
{
	
	private JMenu mnuacc,mnucreateapp,mnust=null;
	private JMenuBar mb=null;
	private JMenuItem miadd,miupdate,midelete,mimobile,miweb,midesktop,mist=null;
	
	
	
	public Admin()
	{
		setTitle("Admin Frame");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		createMenu();
		setVisible(true);
		
	}
	
	public void createMenu()
	{
	mb=new JMenuBar();
	mnuacc=new JMenu("Account");
	mnucreateapp=new JMenu("Create Application");
	miadd=new JMenuItem("Add");
	miadd.addActionListener(this);
	miupdate=new JMenuItem("Update");
	miupdate.addActionListener(this);
	
	midelete=new JMenuItem("Delete");
	midelete.addActionListener(this);
	
	mimobile=new JMenuItem("Mobile");
	miweb=new JMenuItem("Web");
	midesktop=new JMenuItem("Desktop");
	mnuacc.add(miadd);
	mnuacc.add(miupdate);
	mnuacc.add(midelete);
	mnucreateapp.add(mimobile);
	mnucreateapp.add(miweb);
	mnucreateapp.add(midesktop);
	
	
	mnust=new JMenu("Status");
	mist=new JMenuItem("Show Status");
	mist.addActionListener(this);
	mnust.add(mist);
	
	mb.add(mnuacc);
	mb.add(mnucreateapp);
	mb.add(mnust);
	setJMenuBar(mb);
	
	}
	
	
	
	
	public static void main(String[] args) 
	{
		new Admin();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		
		String cap=ae.getActionCommand();
		if(cap.equals("Add"))
		{
	       new AddAccount();
		}
		if(cap.equals("Delete"))
		{
	       new DeleteAccount();
		}
		if(cap.equals("Update"))
		{
	       new UpdateAccount();
		}
		
		if(cap.equals("Show Status"))
		{
	       new ShowStatus();
		}
		
		}
		
	}
	
	
	
	
	
	
	
	
	


