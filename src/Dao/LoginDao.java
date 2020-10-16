package Dao;
import java.sql.*;
import java.util.Scanner;

import utility.ClassNotException;
import utility.Connectionmanager;

public class LoginDao 
{
	public static void ViewRecords (int uid) throws SQLException, ClassNotFoundException, ClassNotException
	{
	
		Statement stmt=null;
		Connection con=Connectionmanager.getConnection();
		stmt=con.createStatement();
		String sq="SELECT * FROM data_table WHERE user_id="+uid+"";
		//System.out.println(sq);
		ResultSet rs = stmt.executeQuery(sq);
		System.out.println("Data_ID\t\t\tDATA_NAME\t\t\tENCRYPT VALUE");
		//System.out.println(uid);
	      while(rs.next())
	      {
	         int D_id=rs.getInt("D_id");
	         String data_name=rs.getString("data_name");
	         String encrypt_value=rs.getString("encrypt_value");
	         System.out.println("  "+D_id+"\t\t\t"+data_name+"\t\t\t"+ encrypt_value );
	      }
	      rs.close();

	}

	public static void DeleteRecords(int DID) throws SQLException, ClassNotFoundException, ClassNotException
	{
		Statement stmt=null;
		Connection con=Connectionmanager.getConnection();
		stmt=con.createStatement();
		String sq="DELETE FROM data_table WHERE D_id="+DID;
		//System.out.println(sq);
		int result=stmt.executeUpdate(sq);
		if(result > 0)
		{
			System.out.println("[success] Record Deleted Successfully!!!!");
		}
		else 
		{
			System.out.println("[Failed] Record Deletion Failed!!!!");
		}
		
	}
	
	public static void UpdateRecords(String Data_name,int D_id) throws SQLException, ClassNotFoundException, ClassNotException
	{
		Statement stmt=null;
		Connection con=Connectionmanager.getConnection();
		stmt=con.createStatement();
		String sq="UPDATE data_table SET data_name = '"+Data_name+ "'  WHERE D_id = "+D_id;
		//System.out.println(sq);
		int result=stmt.executeUpdate(sq);
		if(result > 0)
		{
			System.out.println("[success] Record Updated Successfully!!!!");
		}
		else 
		{
			System.out.println("[Failed] Record Updated Failed!!!!");
		}
		
	}


}

