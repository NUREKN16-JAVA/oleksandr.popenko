package ua.nure.kn.popenko.usermanagement.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImpl implements ConnectionFactory {
	
	private static final String DRIVER = "org.hsqldb.jdbcDriver";
    private static final String URL = "jdbc:hsqldb:file:db/usermanagement";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private String driver;
    private String url;
    private String user;
    private String password;

    public ConnectionFactoryImpl() {
        this.driver = DRIVER;
        this.url = URL;
        this.user = USER;
        this.password = PASSWORD;
    }

    public ConnectionFactoryImpl(String driver, String url, String user, String password) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }

	public ConnectionFactoryImpl(Properties properties) {
		 user = properties.getProperty("connection.user");
		 password = properties.getProperty("connection.password");
		 url = properties.getProperty("connection.url");
		 driver = properties.getProperty("connection.driver");
	}

	@Override
	public Connection createConnection() throws DatabaseException {
		
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DatabaseException(e); 
		}
		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

}