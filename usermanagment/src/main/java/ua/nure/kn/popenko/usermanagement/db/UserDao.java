package ua.nure.kn.popenko.usermanagement.db;



import java.util.Collection;

import ua.nure.kn.popenko.usermanagement.User;

public interface UserDao {

		 /**
		  * Add new record to DB with user
		  * @param user with null id
		  * @return modified user with auto generated id from DB
		  * in case of any error with DB may throw DatabaseException
		  */
		User create(User user) throws DatabaseException;
		
		/**
		 * 
		 */
		User find(Long id) throws DatabaseException;
		
		Collection<User> findAll() throws DatabaseException;
		
		/**
		*
		*@param user
		*@throws DatabaseException
		*/
		void update(User user) throws DatabaseException;
		
		void delete(User user) throws DatabaseException;
		
		void setConnectionFactory(ConnectionFactory connectionFactory);

}