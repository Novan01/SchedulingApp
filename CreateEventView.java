import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.*;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateEventView extends JFrame {
    final private Font mainFont = new Font("Arial", Font.BOLD, 18);
    private JTextField nameField, descriptionField, dateField;
    private JLabel nameLabel, descriptionLabel, priorityLabel, dateLabel;
    private JSpinner prioritySpinner;
    private JButton saveButton;

    public CreateEventView() {
        super("Add Event");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 200));
        setLocationRelativeTo(null);

        nameLabel = new JLabel("Name");
        nameField = new JTextField(20);
        nameLabel.setFont(mainFont);

        descriptionLabel = new JLabel("Description");
        descriptionField = new JTextField(50);
        descriptionLabel.setFont(mainFont);

        priorityLabel = new JLabel("Priority");
        SpinnerNumberModel priorityModel = new SpinnerNumberModel(1,1,5,1);
        prioritySpinner = new JSpinner(priorityModel);

        dateLabel = new JLabel("Date");
        dateField = new JTextField(10);
        dateField.setEditable(true); //the user will select from the calendar view

        saveButton = new JButton("Save");

        //add components to the JFrame
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(5,5,5,5);
        add(nameLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        add(descriptionLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        add(descriptionField, gc);

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        add(priorityLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        add(prioritySpinner, gc);

        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.LINE_END;
        add(dateLabel, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        add(dateField, gc);

        gc.gridx = 0;
        gc.gridy++;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.CENTER;
        add(saveButton, gc);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
				//have the save button create the new object and 
                Event newEvent = waitForInput();
                try {
                    Connection conn = DriverManager.getConnection("jbdc:mysql://192.168.21.14:3306/schedulingapp", "root", "$pe11Bre@k2020.");
                    EventController eventController = new EventController(conn);
                    eventController.create(newEvent);
                    conn.close();
                }
                catch(SQLException e) {
                    e.printStackTrace();
                }
                
			}
        });

        pack();
        setVisible(true);

    }
    

    // getter methods for the input fields
    public String getEventName() {
        return nameField.getText();
    }

    public String getEventDescription() {
        return descriptionField.getText();
    }

    public int getEventPriority() {
        return (int) prioritySpinner.getValue();
    }

    public Date getEventDate() {
        return convertStringToDate(dateField.getText());
    }

    // setter method for the event date field
    public void setEventDate(Date date) {
        dateField.setText(date.toString());
    }
    

    public Event waitForInput() {
        setVisible(true);
        while(isVisible()) {
            try {
                Thread.sleep(100);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        Event newEvent = new Event();
        newEvent.setName(nameField.getText());
        newEvent.setDescription(descriptionField.getText());
        newEvent.setPriority((int) prioritySpinner.getValue());
        newEvent.setDate(convertStringToDate(dateField.getText()));

        return newEvent;
    }

    public Date convertStringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date parsed = format.parse(dateString);
            java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
            return sqlDate;
        }
        catch(ParseException e) {
            e.printStackTrace();
        }
        
        return null;
         
    }
}