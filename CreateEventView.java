import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CreateEventView extends JFrame {
    final private Font mainFont = new Font("Arial", Font.BOLD, 18);
    private JTextField nameField, descriptionField, dateField;
    private JLabel nameLabel, descriptionLabel, priorityLabel, dateLabel;
    private JSpinner prioritySpinner;
    private JButton saveButton;

    public CreateEventView() {
        super("Add Event");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));
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
        dateField.setEditable(false); //the user will select from the calendar view

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

    public String getEventDate() {
        return dateField.getText();
    }

    // setter method for the event date field
    public void setEventDate(String date) {
        dateField.setText(date);
    }

    // add an ActionListener to the save button
    public void addSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }
}