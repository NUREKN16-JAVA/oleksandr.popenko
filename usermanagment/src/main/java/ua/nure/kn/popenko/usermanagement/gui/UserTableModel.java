package ua.nure.kn.popenko.usermanagement.gui;


import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import ua.nure.kn.popenko.usermanagement.User;

public class UserTableModel extends AbstractTableModel {
	
	private static final String[] COLUMN_NAMES = {"ID", "First Name", "Last Name"};
	private static final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};
	/*private List users = null;
	private int rowIndex;
	private int columnIndex;*/
	private ArrayList users=null;

	
	public UserTableModel(Collection users) {
		this.users = new ArrayList(users);
		
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return users.size();
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		}
		return null;
	}
	
	public User getUser(int index) {
        return (User) users.get(index);
    }

    
    public void addUsers(Collection<User> users) {
        this.users.addAll(users);
        
    }

    public void clearUsers() {
        this.users = new ArrayList<>();
    }

}