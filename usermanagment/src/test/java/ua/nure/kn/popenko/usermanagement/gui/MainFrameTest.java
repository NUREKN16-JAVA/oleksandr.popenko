package ua.nure.kn.popenko.usermanagement.gui;


import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

//import org.junit.After;
import org.junit.Before;
//import org.junit.Test;

import com.mockobjects.dynamic.Mock;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.JTableMouseEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.kn.popenko.usermanagement.User;
import ua.nure.kn.popenko.usermanagement.db.DaoFactory;
import ua.nure.kn.popenko.usermanagement.db.MockDaoFactory;

public class MainFrameTest extends JFCTestCase {

	private MainFrame mainFrame;

	
	private Mock mockUserDao;

	private java.util.List users;
	
	//@Before
	protected void setUp() throws Exception {
		super.setUp();
		
		try {
			Properties properties = new Properties();
			properties.setProperty("dao.factory", MockDaoFactory.class
					.getName());
			DaoFactory.init(properties);
			mockUserDao = ((MockDaoFactory) DaoFactory.getInstance())
					.getMockUserDao();
			
			User expectedUser = new User(new Long(1000), "Bill", "Gates", new Date());
            users = Collections.singletonList(expectedUser);
            
			mockUserDao.expectAndReturn("findAll", new ArrayList<Object>());
			setHelper(new JFCTestHelper());
			
			mainFrame = new MainFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mainFrame.setVisible(true);
	}

	//@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		try {
			mockUserDao.verify();
			mainFrame.setVisible(false);
			getHelper();
			TestHelper.cleanUp(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private Component find(Class<?> componentClass, String name ) {
		NamedComponentFinder finder;
		finder = new NamedComponentFinder(componentClass, name);
		finder.setWait(0);
		Component component = finder.find(mainFrame, 0);
		assertNotNull("Could not find component '" + name + "'", component);
		return component;
		
	}
	
	public void testBrowsePanel() {
		try {
		find(JPanel.class, "browsePanel");
		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(3, table.getColumnCount());
		assertEquals("ID", table.getColumnName(0));
		assertEquals("First Name", table.getColumnName(1));
		assertEquals("Last Name", table.getColumnName(2));
		
        find(JButton.class, "addButton");
        find(JButton.class, "editButton");
        find(JButton.class, "deleteButton");
        find(JButton.class, "detailsButton");
	} catch (Exception e) {
		fail(e.toString());
	}
	}
	
	public void testAddUser() {
		try {
		String firstName = "Bill";
		String lastName = "Gates";
		Date now = new Date();
		
		User user = new User(firstName, lastName, now);
		User expectedUser = new User(new Long(1), firstName, lastName, now);
		
		mockUserDao.expectAndReturn("create", user, expectedUser);
		
		ArrayList<User> users = new ArrayList<User>();
		users.add(expectedUser);
		mockUserDao.expectAndReturn("findAll", users);
		
		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(0, table.getRowCount());
		
		JButton addButton = (JButton) find(JButton.class, "addButton");
		getHelper().enterClickAndLeave (new MouseEventData(this, addButton));
		
		find(JPanel.class, "addPanel");
		
		JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
		JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
		JTextField dateofBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");
		JButton okButton = (JButton) find(JButton.class, "okButton");
		find(JButton.class, "cancelButton");
		
		
		getHelper().sendString(new StringEventData(this, firstNameField, firstName));
		getHelper().sendString(new StringEventData(this, lastNameField, lastName));
		DateFormat formatter = DateFormat.getDateInstance();
        
		String date = formatter.format(now);
		getHelper().sendString(new StringEventData(this, dateofBirthField, date));
		
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		
		find(JPanel.class, "browsePanel");
		table = (JTable) find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());
	    mockUserDao.verify();
    } catch (Exception e) {
        fail(e.toString());
    }
	}

    public void testEditUser() { 
    	try {
        User expectedUser = new User(new Long(1000), "Bill", "Gates", new Date());
        System.out.println(expectedUser);
        
        mockUserDao.expectAndReturn("update", expectedUser, expectedUser);

        Collection<?> users = new ArrayList<Object>(this.users);
        mockUserDao.expectAndReturn("findAll", users);
            
        JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(1, table.getRowCount());
        JButton editButton = (JButton) find(JButton.class, "editButton");
        getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
        getHelper().enterClickAndLeave(new MouseEventData(this, editButton));
            
        find(JPanel.class, "editPanel");

        JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
        JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
            
        getHelper().sendString(new StringEventData(this, firstNameField, "1"));
        getHelper().sendString(new StringEventData(this, lastNameField, "1"));

        JButton okButton = (JButton) find(JButton.class, "okButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

        find(JPanel.class, "browsePanel");
        table = (JTable) find(JTable.class, "userTable");
        assertEquals(1, table.getRowCount());
        mockUserDao.verify();
    } catch (Exception e) {
        fail(e.toString());
    }
    }

    public void testDeleteUser() {
    	try {
            User expectedUser = new User(new Long(1000), "Bill", "Gates", new Date());
            mockUserDao.expect("delete", expectedUser);

            Collection<User> users = new ArrayList<>();
            mockUserDao.expectAndReturn("findAll", users);
            
            JTable table = (JTable) find(JTable.class, "userTable");
            assertEquals(1, table.getRowCount());
            JButton deleteButton = (JButton) find(JButton.class, "deleteButton");
            getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
            getHelper().enterClickAndLeave(new MouseEventData(this, deleteButton));
            
            find(JPanel.class, "browsePanel");
            table = (JTable) find(JTable.class, "userTable");
            assertEquals(0, table.getRowCount());
            mockUserDao.verify();
            
        } catch (Exception e) {
            fail(e.toString());
        }

    }
    public void testDetailsUser() {
		try {
			JTable table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());
			
			JButton detailsButton = (JButton) find(JButton.class, "detailsButton");
			getHelper().enterClickAndLeave(new MouseEventData(this, detailsButton));
			getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
			getHelper().enterClickAndLeave(new MouseEventData(this, detailsButton));
			
			find(JPanel.class, "detailsPanel");
			JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
			JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
			
			assertEquals("Bill", firstNameField.getText());
			assertEquals("Gates", lastNameField.getText());
			JButton backButton = (JButton) find(JButton.class, "backButton");
			getHelper().enterClickAndLeave(new MouseEventData(this, backButton));
			
			find(JPanel.class, "browsePanel");
			table = (JTable) find(JTable.class, "userTable");
			assertEquals(1, table.getRowCount());
			mockUserDao.verify();
		} catch (Exception e) {
			fail(e.toString());
		}
    }
    }
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
