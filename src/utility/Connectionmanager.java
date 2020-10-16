package utility;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Connectionmanager 
{
	public static Properties loadPropertiesFile() throws IOException
	{
		Properties prop = new Properties();
		InputStream in = Connectionmanager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(in);
		in.close();
		return prop;
	}
	
	public static Connection getConnection() throws ClassNotException, SQLException, ClassNotFoundException
	{
		Properties prop=null;
		try {
			prop=loadPropertiesFile();
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		final String driver=prop.getProperty("driver");
		final String url=prop.getProperty("url");
		final String username=prop.getProperty("username");
		final String password=prop.getProperty("password");
	
		
		Class.forName(driver);
		
		Connection con=null;
		con=DriverManager.getConnection(url, username, password);
		
		
		return con;
	}

	public static Connection getConnection1() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
	
