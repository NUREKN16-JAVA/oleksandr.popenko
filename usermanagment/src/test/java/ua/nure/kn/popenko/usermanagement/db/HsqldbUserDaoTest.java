package ua.nure.kn.popenko.usermanagement.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.kn.popenko.usermanagement.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	
	private UserDao dao;

	private ConnectionFactoryImpl connectionFactory;

	private static final String LAST_NAME_GAUGE = "Popenko";
	private static final String FIRST_NAME_GAUGE = "Oleksandr";
	private static final long ID_GAUGE = 6489L;

	public HsqldbUserDaoTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(new ConnectionFactoryImpl());
	}
	
	public void testCreateUser() throws DatabaseException{
		try {
			User user = new User();
			user.setFirstName(FIRST_NAME_GAUGE);
			user.setLastName(LAST_NAME_GAUGE);
			user.setDateOfBirth(new Date());
			assertNull(user.getId());
			User userExpected = dao.create(user);
			assertNotNull(userExpected);
			assertNotNull(userExpected.getId());
			assertEquals(userExpected.getFirstName(), user.getFirstName());
			assertEquals(userExpected.getLastName(), user.getLastName());
			assertEquals(userExpected.getDateOfBirth(), user.getDateOfBirth());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
		
	}
	
	public void testFindAll() throws DatabaseException{
		try {
			Collection<User> collection = dao.findAll();
			assertNotNull("Collection is null",collection);
			assertEquals("Collection size.",2, collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testFindUser() throws DatabaseException{
		try {
			User user = dao.find(ID_GAUGE);
			assertNotNull(user);
            long userId = user.getId();
            assertEquals(ID_GAUGE, userId);
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
		
	}

	public void testUpdateUser() throws DatabaseException {
		
        try {
        	User user = new User();
        	user.setId(ID_GAUGE);
        	user.setFirstName(FIRST_NAME_GAUGE);
			user.setLastName(LAST_NAME_GAUGE);
			user.setDateOfBirth(new Date());
            dao.update(user);
            User updatedUser = dao.find(ID_GAUGE);
            assertEquals(FIRST_NAME_GAUGE, updatedUser.getFirstName());
            assertEquals(LAST_NAME_GAUGE, updatedUser.getLastName());
        } catch (DatabaseException e) {
        	e.printStackTrace();
			fail(e.toString());
        }
		
	}
	
	public void testDeleteUser() throws DatabaseException {
		try {
        	int expectedBefore = 2;
            int expectedAfter = 1;
        	User user = new User();
        	user.setId(ID_GAUGE);
        	user.setFirstName(FIRST_NAME_GAUGE);
			user.setLastName(LAST_NAME_GAUGE);
			user.setDateOfBirth(new Date());
            int actualBefore = dao.findAll().size();
            dao.delete(user);
            int actualAfter = dao.findAll().size();

            assertEquals(expectedBefore, actualBefore);
            assertEquals(expectedAfter, actualAfter);
        } catch (DatabaseException e) {
        	e.printStackTrace();
			fail(e.toString());
        }
	}
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl();
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(
				getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}