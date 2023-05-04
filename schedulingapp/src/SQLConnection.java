import java.sql.*;

public class SQLConnection
{
        public static void main(String args[])
        {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql:\\192.168.21.24:3306\schedulingapp","username","password");
                    Statement stmt=con.createStatement();
                    ResultSet rs=stmt.executeQuery("SELECT * FROM events");
                    while(rs.next())
                            System.out.println();
                con.close();
                }
                catch(Exception e)
                    { 
                    System.out.println(e);
                }
        }
}
