package ua.nure.kn.popenko.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.kn.popenko.usermanagement.User;
import ua.nure.kn.popenko.usermanagement.db.DatabaseException;
import ua.nure.kn.popenko.usermanagement.util.Messages;

public class DetailsPanel extends JPanel implements ActionListener {

    private static final int ROWS = 3;
    private static final int COLS = 2;

    private MainFrame parent;
    private User selectedUser;
    private JTextField dateOfBirthField;
    private JPanel fieldPanel;
    private JPanel buttonPanel;
    private JButton okButton;
    private JTextField firstNameField;
    private JTextField lastNameField;

    public DetailsPanel(MainFrame parent, Long selectedUserId) {
        this.parent = parent;
        setUser(selectedUserId);
        initialize();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        parent.showBrowsePanel();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        add(getFieldPanel(), BorderLayout.NORTH);
        add(getButtonsPanel(), BorderLayout.SOUTH);
        setName("redactPanel");
    }

    private JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(ROWS, COLS));
            addLabeledField(fieldPanel, Messages.getString("AddPanel.first_name"), getFirstNameField());
            addLabeledField(fieldPanel, Messages.getString("AddPanel.last_name"), getLastNameField());
            addLabeledField(fieldPanel, Messages.getString("AddPanel.date_of_birth"), getDateOfBirthField());
        }
        return fieldPanel;
    }

    private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }

    private JPanel getButtonsPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton(), null);
        }
        return buttonPanel;
    }

    private JButton getOkButton() {
        if (okButton == null) {
            okButton = new JButton();
            okButton.setText(Messages.getString("AddPanel.ok"));
            okButton.setName("okButton");
            okButton.setActionCommand("ok");
            okButton.addActionListener(this);
        }
        return okButton;
    }

    private JTextField getFirstNameField() {
        if (firstNameField == null) {
            firstNameField = new JTextField();
            firstNameField.setText(selectedUser.getFirstName());
            firstNameField.setEditable(false);
            firstNameField.setName("firstNameField");
        }
        return firstNameField;
    }

    private JTextField getLastNameField() {
        if (lastNameField == null) {
            lastNameField = new JTextField();
            lastNameField.setText(selectedUser.getLastName());
            lastNameField.setEditable(false);
            lastNameField.setName("lastNameField");
        }
        return lastNameField;
    }

    private JTextField getDateOfBirthField() {
        if (dateOfBirthField == null) {
            dateOfBirthField = new JTextField();
            dateOfBirthField.setText(selectedUser.getDateOfBirth().toString());
            dateOfBirthField.setEditable(false);
            dateOfBirthField.setName("dateOfBirthField");
        }
        return dateOfBirthField;
    }

    public void setUser(Long id) {
        try {
            selectedUser = parent.getDao().find(id);
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}