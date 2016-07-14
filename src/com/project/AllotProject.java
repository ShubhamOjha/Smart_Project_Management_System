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

import com.project.CrudOperation;
import com.toedter.calendar.JDateChooser;

public class AllotProject extends JFrame implements ActionListener
{
	
	private JComboBox<String> cmbpname,cmbpmname=null;
	private JButton jb=null;
	private JLabel lblcmb,lblcmb2=null;
	private Connection con=null;
	private PreparedStatement ps,psdelete,psu=null;
	private ResultSet rs=null;
	private String pid,uid=null;
	private JLabel lbldate=null;
	private JDateChooser jddate=null; 
	
	
	public AllotProject()
	{
		setTitle("Project Detail");
		setLayout(null);
		setSize(600, 600);
		setLocationRelativeTo(null);
		con=CrudOperation.createConnection();
		createGUI();
		setVisible(true);
	        
	}
	
	public void createGUI()
	{
		lblcmb=new JLabel("Project Name");
		lblcmb.setBounds(40,20,150,30);
		add(lblcmb);
		
	cmbpname=new JComboBox<String>();
	cmbpname.setBounds(200, 20, 200,30);
	cmbpname.addItem("select Project Name");
	fillCombo();
	add(cmbpname);
	
	lblcmb2=new JLabel("Project manager Name");
	lblcmb2.setBounds(40,200,150,30);
	add(lblcmb2);
	
cmbpmname=new JComboBox<String>();
cmbpmname.setBounds(200, 200, 200,30);
cmbpmname.addItem("select Project manager Name");
fillCombo2();
add(cmbpmname);
lbldate=new JLabel("Assign Date");
lbldate.setBounds(40, 300, 150, 30);
add(lbldate);


jddate=new JDateChooser();
jddate.setBounds(200, 300, 200, 30);
add(jddate);

	jb=new JButton("Submit");
	jb.setBounds(200, 400,100,30);
	jb.addActionListener(this);
	add(jb);
	
	}
	
	
	public void fillCombo()
	{
		String strsql="select projectname from project";
		try
		{
		ps=con.prepareStatement(strsql);
		rs=ps.executeQuery();
		while(rs.next())
		{
			cmbpname.addItem(rs.getString("projectname"));
		}
		//pid=rs.getString("projectid");
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}
	
	
	
	public void fillCombo2()
	{
		String strsql="select name from projectmanager";
		try
		{
		ps=con.prepareStatement(strsql);
		rs=ps.executeQuery();
		while(rs.next())
		{
			cmbpmname.addItem(rs.getString("name"));
		}
		//uid=rs.getString("userid");
	
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}
	
	
	
	public static void main(String[] args)
	 {                                       //main method is used only to run this page separately from AdminFrame.java 
	   new AllotProject();	
   }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String pname=(String)cmbpname.getSelectedItem();
		
		
		String strsql="select projectid from project where projectname=?";
		try
		{
		ps=con.prepareStatement(strsql);
		ps.setString(1, pname);
		rs=ps.executeQuery();
		rs.next();
		
		pid=rs.getString("projectid");
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	
	
	
	
	String pmname=(String)cmbpmname.getSelectedItem();
	
	
	String strsql2="select userid from projectmanager where name=?";
	try
	{
	ps=con.prepareStatement(strsql2);
	ps.setString(1, pmname);
	rs=ps.executeQuery();
	if(rs.next())
	uid=rs.getString("userid");
	
	}
	catch(SQLException ee)
	{
		System.out.println(ee);
	}



java.util.Date sdt=jddate.getDate();
java.sql.Date sqdt=new java.sql.Date(sdt.getTime());

	
	
	
		
		
		
		String strupdate="update project set status=?";
		

		String strinsert="insert into assignproject (projectid,userid,assigndate,status)values(?,?,?,?)";
		try
		{
			psu=con.prepareStatement(strupdate);
			psu.setString(1, "assigned");
			psu.executeUpdate();
			ps=con.prepareStatement(strinsert);
			ps.setString(1, pid);
			ps.setString(2, uid);
			ps.setDate(3, sqdt );
			ps.setString(4, "assigned");
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
	

	
