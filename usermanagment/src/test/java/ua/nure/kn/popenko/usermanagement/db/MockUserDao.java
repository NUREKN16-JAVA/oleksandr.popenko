package ua.nure.kn.popenko.usermanagement.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//import ua.nure.kn.arutiunov.usermanagment.db.UserDao;
import ua.nure.kn.popenko.usermanagement.User;

public class MockUserDao implements UserDao {
	private long id = 0;
    private Map<Long, User> users = new HashMap<>();
    
    
	@Override
	public User create(User user) throws DatabaseException {
		Long currentId = new Long(++id);
        user.setId(currentId);
        users.put(currentId, user);
        return user;
    }

	@Override
	public User find(Long id) throws DatabaseException {
        
        return users.get(id);
    }
	
	@Override
	public Collection<User> findAll() throws DatabaseException {
        return users.values();
    }

	@Override
	public void update(User user) throws DatabaseException {
	    Long currentId = user.getId();
        users.remove(currentId);
        users.put(currentId, user);
        
    }


	@Override
	public void delete(User user) throws DatabaseException {
        Long currentId = user.getId();
        users.remove(currentId);
    }

	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection find(String firstName, String lastName) throws DatabaseException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<?> findall() throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}


	

}
