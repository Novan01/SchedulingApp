

import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.awt.ActiveEvent;

public class calanderView {
	
//USE JDBC TO CONNECT TO EVENT DATABASE TO GET THE NAME, DATE, PRIORITY, AND DESCRIPTION
private static Connection connection = null;
private JFrame frame;
private JPanel panel;
private JLabel monthLabel;
private JButton prevButton;
private JButton nextButton;
private JButton addEventButton;
private JTable table;
private DefaultTableModel model;

	public calanderView() {
		frame = new JFrame("Calendar View");
		panel = new JPanel(new BorderLayout());
		monthLabel = new JLabel("", SwingConstants.CENTER);
		prevButton = new JButton("<");
		nextButton = new JButton(">");
		addEventButton = new JButton("Add Event");
		table = new JTable();
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		monthLabel.setFont(new Font("Arial", Font.BOLD, 20));
		prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Move to the previous month
                // ...
            }
        });
		addEventButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Open the Add Event dialog
                // ...
            }
        });
	}
	
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
