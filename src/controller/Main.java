package controller;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
import Dao.LoginDao;
import cryptogrphy.Security;
import model.Admin;
import service.validations;
import utility.ClassNotException;
import utility.Connectionmanager;


public class Main
{
	public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException, SQLException, ClassNotException
	{
		System.out.println("***********************************************************************************");
		System.out.println("                         Highly Confidential Security System                      ");
		System.out.println("***********************************************************************************");
		
		Connection Connect=null;
		Statement stmt = null;
		int choice=0;
		int userid=0;
		Connect = Connectionmanager.getConnection();
		while(choice!=3)
		{
			System.out.println(" 1.Login                          ");
			System.out.println(" 2.Sign-up                        ");
			System.out.println(" 3.Exit                           ");
			System.out.println("");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("Enter Your Choice: ");
			choice=Integer.parseInt(br.readLine());
			
			String username,password,Email,mobile_num;
			
			Admin login=new Admin();
			validations validate= new validations();
			switch(choice)
			{
			case 1:
				stmt = Connect.createStatement();
				System.out.println("[******************** Enter Login Details********************]");
				System.out.println("");
				System.out.print("Enter your Username : ");
				username=br.readLine();
				System.out.print("Enter your Password : ");
				password=br.readLine();
				login.setUsername(username);
				login.setPassword(password);
				boolean validateAdmin=validate.LoginAdmin(login);
				if(validateAdmin)
				{
					System.out.println("\n[**********Login Successfull**********]\n");
					System.out.println("[*********** Welcome "+username+"***********]");
					LoginDao logindao=new LoginDao();
					stmt=Connect.createStatement();
					String id="SELECT user_id FROM user_table WHERE user_name='"+username+"'";
					ResultSet rs = stmt.executeQuery(id);
					rs.next();
				    int user_Id=rs.getInt("user_id");
					int choice2;
					do
					{
						choice2 = 0;
						System.out.println("");
						System.out.println("1.View your records");
						System.out.println("2.Create your records");
						System.out.println("3.Delete the records");
						System.out.println("4.Update the records");
						System.out.println("5.Exit\n");
						System.out.print("Enter the choice : ");
						choice2 = Integer.parseInt(br.readLine());
						switch(choice2)
						{
							case 1:	
									LoginDao.ViewRecords(user_Id);
									String flag;
									System.out.print("\nDo you want to Decrypt any records (Y/N) :");
									flag=br.readLine();
									if(flag.equals("Y"))
									{
										System.out.print("\nEnter Data ID : ");
										int DataId=Integer.parseInt(br.readLine());
										String Query="select data_name,encrypt_value from data_table where d_id="+DataId;
										ResultSet rs1 = stmt.executeQuery(Query);
										rs1.next();
									    String Cipher_Data=rs1.getString("encrypt_value");
									    String Data_name=rs1.getString("data_name");
									    //System.out.println(Cipher_Data);
									    System.out.print("\nEnter Decrypt Key : ");
										String Decrypt_Key=br.readLine();
										String TextValue=Security.decryption(Cipher_Data,Decrypt_Key);
										System.out.println("\n**************[SUCCESS]Your Decrypted "+Data_name+" is "+TextValue+"**************");
									}
									break;
							case 2:
									stmt = Connect.createStatement();
									String Data;
									String Data_value;
									String key;
									System.out.println("");
									System.out.println("[******************** Enter Login Details********************]");
									System.out.println("");
									System.out.print("Enter your Data name     : ");
									Data=br.readLine();
									System.out.print("Enter the Data_value     : ");
									Data_value=br.readLine();
									System.out.print("Enter the key to Encrypt : ");
									key=br.readLine();
									String value=Security.encryption(Data_value,key);
									String encrypt="INSERT INTO data_table(D_id,user_id,data_name,encrypt_value) VALUES(NULL,"+user_Id+",'"+Data+"','"+value+"')";
									//value=encryption(Data_value,key);
									stmt.executeUpdate(encrypt);
									System.out.println(" \n############### [INFO] Your DATA has been Inserted Succesfully ###############");
									//System.out.println(encrypt);
									//System.out.println(value);
									break;
							case 3:
								LoginDao.ViewRecords(user_Id);
								System.out.print("\nEnter Data ID : ");
								int DataId=Integer.parseInt(br.readLine());
								LoginDao.DeleteRecords(DataId);
								break;
							case 4:
								LoginDao.ViewRecords(user_Id);
								System.out.print("\nEnter Data ID : ");
								int DataId1=Integer.parseInt(br.readLine());
								System.out.print("\nEnter the update Data Name : ");
								String Data_name=br.readLine();
								LoginDao.UpdateRecords(Data_name,DataId1);
								break;
							
							default:System.out.println("Enter the valid choice");
						}
					}
					while(choice2<5);
				}
				else
				{
					System.out.println("[Error] Please Check the Username and Password");
				}
				break;
			case 2:
				stmt = Connect.createStatement();
				System.out.println("*********************************Signup****************************      ");
				System.out.println("Enter your username");
				username=br.readLine();
				System.out.println("Enter password");
				password=br.readLine();
				System.out.println("Enter Email ID");
				Email=br.readLine();
				System.out.println("Enter Mobile Number");
				mobile_num=br.readLine();
				System.out.println("   -----------Your details has been created Succecfully------------      ");
				String User="INSERT INTO user_login(user_name,user_password) VALUES('"+username+"','"+password+"')";
				stmt.executeUpdate(User);
				String details="INSERT INTO user_table(user_id,user_name,Email,mobile_num) VALUES(NULL,'"+username+"','"+Email+"','"+mobile_num+"')";
				stmt.executeUpdate(details);
				//System.out.println(details);
				break;
			case 3:
				System.out.println("\n************************ Thank You *************************      ");
				break;
			default:
				System.out.print("Enter valid choice     ");
				break;
			}
		}
	}
}