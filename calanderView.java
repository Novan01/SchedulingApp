package cs3354;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class calanderView {
	
//USE JDBC TO CONNECT TO EVENT DATABASE TO GET THE NAME, DATE, PRIORITY, AND DESCRIPTION
private static Connection connection = null;
	
	public static void selection() throws SQLException
	{
		int count = 0;
		//Tries to get the data from the table using the variable count
		String sql = "SELECT * FROM event where count = " + count;
		//connects the statement to SQL database
	Statement statement = 	connection.createStatement();
		//puts the data we get into result from sql
		ResultSet result  = statement.executeQuery(sql);
		//loops through the table till all the data is printed.
		while(result.next())
		{
			
			int id = result.getInt("id");
			String name  = result.getString("name");
			Date date = result.getDate("date");
			String description = result.getString("description");
			int priority  = result.getInt("prioirity");
			
			System.out.println("ID - "+id+"\n"
			+"Name - "+name +"\n"
			+"Date - "+ date +"\n"
			+"Description - "+description+"\n"
			+"Priority Level - "+priority+"\n");
			//increments the count
			count++;
		}
		
	}
	
	
	
}
