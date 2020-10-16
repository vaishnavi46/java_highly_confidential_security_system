package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Admin;
import utility.ClassNotException;
import utility.Connectionmanager;
public class validations implements Interfaces {

	public boolean LoginAdmin(Admin admin) throws ClassNotException 
	{
		
		boolean result = false;
		try 
		{
		Connection con=Connectionmanager.getConnection();
		String sql="select * from user_login";
		Statement st=con.createStatement();
		ResultSet rs =st.executeQuery(sql);
		
		while(rs.next())
		{
			if(admin.getUsername().equalsIgnoreCase(rs.getString("user_name")) &&
				admin.getPassword().equalsIgnoreCase(rs.getString("user_password")))
			{
			result=true;
			}
		  }
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	

}
