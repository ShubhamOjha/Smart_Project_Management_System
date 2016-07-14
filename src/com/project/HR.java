package com.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class HR extends JFrame implements ActionListener
{
	
	private JMenu mnuadddetail,mnucreateapp=null;
	private JMenuBar mb=null;
	private JMenuItem miprojectmanager,miclient,miprod=null;
	
	public HR()
	{
		setTitle("HR");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		createMenu();
		setVisible(true);
	
	}
	public void createMenu()
	{
	
		
		mb=new JMenuBar();
		mnuadddetail=new JMenu("Add Details");
		miprojectmanager=new JMenuItem("Project Manager");
		miprojectmanager.addActionListener(this);
		miclient=new JMenuItem("Client");
		miclient.addActionListener(this);
		miprod=new JMenuItem("project detail");
		miprod.addActionListener(this);
		
		mnuadddetail.add(miprojectmanager);
		mnuadddetail.add(miclient);
		
mnuadddetail.add(miprod);
		
		mb.add(mnuadddetail);
		setJMenuBar(mb);
		
	}
	
	public static void main(String[] args)
	 {                                       //main method is used only to run this page separately from AdminFrame.java 
	   new HR();	
       }
	@Override
	public void actionPerformed(ActionEvent e)
	{

		String cap=e.getActionCommand();
		if(cap.equals("Project Manager"))
		{
	       new ProjectManagerDetails();
		}
		if(cap.equals("Client"))
		{
	       new ClientDetails();
		}
		if(cap.equals("project detail"))
		{
	       new ProjectDetail();
		}
		
	
		
	}

	
	

}
