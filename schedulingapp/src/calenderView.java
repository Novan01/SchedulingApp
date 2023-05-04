import java.awt.BorderLayout;
import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Locale;

public class calenderView {
	
//USE JDBC TO CONNECT TO EVENT DATABASE TO GET THE NAME, DATE, PRIORITY, AND DESCRIPTION
private JFrame frame;
private JPanel panel;
private JLabel monthLabel;
private JButton prevButton;
private JButton nextButton;
private JButton addEventButton;
private JTable table;
private DefaultTableModel model;
private int currentMonth;
private int currentYear;


	public calenderView() {
		Calendar calendar = Calendar.getInstance();
		currentMonth = calendar.get(Calendar.MONTH);
		currentYear = calendar.get(Calendar.YEAR);
		updateCalendar(currentMonth, currentYear);

		//create panel and frame and month label
		frame = new JFrame("Calendar View");
		panel = new JPanel(new BorderLayout());
		monthLabel = new JLabel("", SwingConstants.CENTER);
		
		//create the button's
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
		
		//button to move to previous month
		prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if(currentMonth == Calendar.JANUARY) {
					currentMonth = Calendar.DECEMBER;
					currentYear--;
				} 
				else {
					currentMonth--;
				}
				updateCalendar(currentMonth, currentYear);
            }
        });
		
		//button to move to next month
		nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if(currentMonth == Calendar.DECEMBER) {
					currentMonth = Calendar.JANUARY;
					currentYear++;
				} 
				else {
					currentMonth++;
				}
				updateCalendar(currentMonth, currentYear);
            }
        });
		
		//button to add new event
		addEventButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
				CreateEventView createEvent = new CreateEventView();
				Event newEvent = new Event(createEvent.getName(), createEvent.getEventDescription(), createEvent.getEventPriority(), createEvent.getEventDate());
				try {
                    Connection conn = DriverManager.getConnection("jbdc:mysql://192.168.21.14:3306/schedulingapp", "root", "**********");
                    EventController eventController = new EventController(conn);
                    eventController.create(newEvent);
                    conn.close();
                }
                catch(SQLException e) {
                    e.printStackTrace();
                }
				
			}
        });

		//method and mouse listener to populate the dateField in createEventView
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.rowAtPoint(e.getPoint());
				if(row >= 0 && col >= 0) {
					Object value = table.getValueAt(row, col);
					if(value instanceof Date) {
						Date selectedDate = (Date) value; //the selected date value
						
					}
				}
			}
			
		});

		//adding components to the panel
		panel.add(monthLabel, BorderLayout.NORTH);
		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
		buttonPanel.add(prevButton);
		buttonPanel.add(addEventButton);
		buttonPanel.add(nextButton);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		panel.add(new JScrollPane(table), BorderLayout.CENTER);

		String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Satruday"};
		model.setColumnIdentifiers(days);
		table.setModel(model);
		table.setCellSelectionEnabled(true);
		table.setRowHeight(50);

		//setting up the frame
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		//Initialize the calendar to the current month
		updateCalendar(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));

	}

	private void updateCalendar(int month, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);

		if(monthLabel == null) {
			return;
		}

		//update the month label
		monthLabel.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + year);

		//Clear the table model
		model.setRowCount(0);

		//Get number of days in month
		int numDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		//Get the day of the week for the first day of the month
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int firstDay = calendar.get(Calendar.DAY_OF_WEEK);


		int row = 0;
		int column = firstDay - 1;
		for(int day = 1; day <= numDays; day++) {
			if(column == 7) {
				row++;
				column = 0;
			}
			if(model.getRowCount() <= row) {
				model.addRow(new Object[] {"Monday",null,null,null,null,null,null}); //this populated the calendar with objects
			}
			model.setValueAt(day, row, column);
			column++;
		}

		//Highlight the current day
		LocalDate today = LocalDate.now();
		calendar.set(Calendar.YEAR, today.getYear());
		calendar.set(Calendar.MONTH, today.getMonthValue() -1);
		calendar.set(Calendar.DAY_OF_MONTH, today.getDayOfMonth());
		int currentMonth = calendar.get(Calendar.MONTH);
		int currentYear = calendar.get(Calendar.YEAR);
		if(month == currentMonth && year == currentYear) {
			int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
			int r = (currentDay + firstDay - 2) / 7;
			int c = (currentDay + firstDay - 2) / 7;
			table.setRowSelectionInterval(r, c);
			table.setSelectionBackground(Color.YELLOW);
		}

		//Resize the columns of the table
		for(int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
	}

	//might wanna move this somewhere else
	public static void main(String[] args) {
		//create login page first - establish sql connection
		new calenderView();
		new Login();
	}	
	
}
