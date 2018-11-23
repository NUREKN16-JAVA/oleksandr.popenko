package test.java.ua.nure.kn.popenko;

import main.java.ua.nure.kn.popenko.User;
import junit.framework.TestCase;

import java.text.ParseException;
import java.util.Calendar;

public class UserTest extends TestCase {

    private User user;

    private final String FULL_NAME_GAUGE = "Popenko, Aleksandr";
    private final String FIRST_NAME_GAUGE = "Aleksandr";
    private final String LAST_NAME_GAUGE = "Popenko";
    
    private static final int YEAR_OF_BIRTH = 1990;

    //Tests are running for the moment of the following date: 22.11.18
    //Birthday has already passed this year 
  	private static final int GAUGE_AGE_1 = 28;
  	private static final int DAY_OF_BIRTH_1 = 22;
  	private static final int MONTH_OF_BIRTH_1 = 2;
  		
  	//Birthday is on the same day (22.11.18)
  	private static final int GAUGE_AGE_2 = 28;
  	private static final int DAY_OF_BIRTH_2 = 22;
  	private static final int MONTH_OF_BIRTH_2 = 11;
  	
 	//Birthday is on the 31st December, 1990
 	private static final int GAUGE_AGE_3 = 27;
 	private static final int DAY_OF_BIRTH_3 = 31;
  	private static final int MONTH_OF_BIRTH_3 = 12;
  		
  	//Birthday is tomorrow 
  	private static final int GAUGE_AGE_4 = 27;
  	private static final int DAY_OF_BIRTH_4 = 23;
  	private static final int MONTH_OF_BIRTH_4 = 11;
  		
  	//Birthday is on the 1st January, 1991
  	private static final int GAUGE_AGE_5 = 26;
  	private static final int DAY_OF_BIRTH_5 = 1;
  	private static final int MONTH_OF_BIRTH_5 = 1;
  		
  	//Birthday is on the 29st February, 1992 (leap year)
  	private static final int YEAR_OF_BIRTH_2 = 1992;
  	private static final int GAUGE_AGE_6 = 26;
  	private static final int DAY_OF_BIRTH_6 = 29;
  	private static final int MONTH_OF_BIRTH_6 = 2;
  	
  	public UserTest() throws ParseException {
    }

    public void setUp() throws Exception {
        user = new User();
        super.setUp();
    }
  	
  	//For testing user's full name.
  	public void testGetFullName() {
  		user.setFirstName(FIRST_NAME_GAUGE);
  		user.setLastName(LAST_NAME_GAUGE);
  		
  		assertEquals(FULL_NAME_GAUGE, user.getFullName());
  	}
  	//For testing if Birthday has already passed this year 
  	public void testGetAge() {
  		Calendar calendar = Calendar.getInstance();
  		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_1, DAY_OF_BIRTH_1);
  		
  		user.setDateOfBirth(calendar.getTime());
  		assertEquals(GAUGE_AGE_1, user.getAge());
  	}
    //For testing if Birthday is on the same day (22.11.18)
  	public void test2() {
  		Calendar calendar = Calendar.getInstance();
  		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_2, DAY_OF_BIRTH_2);
  		
  		user.setDateOfBirth(calendar.getTime());
  		assertEquals(GAUGE_AGE_2, user.getAge());
  	}
    //For testing if Birthday is on the 31st December, 1990
  	public void test3() {
  		Calendar calendar = Calendar.getInstance();
  		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_3, DAY_OF_BIRTH_3);
  		
  		user.setDateOfBirth(calendar.getTime());
  		assertEquals(GAUGE_AGE_3, user.getAge());
  	}
    //For testing if Birthday is tomorrow
  	public void test4() {
  		Calendar calendar = Calendar.getInstance();
  		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_4, DAY_OF_BIRTH_4);
  		
  		user.setDateOfBirth(calendar.getTime());
  		assertEquals(GAUGE_AGE_4, user.getAge());
  	}
    //For testing if Birthday is on the 1st January, 1991
  	public void test5() {
  		Calendar calendar = Calendar.getInstance();
  		calendar.set(YEAR_OF_BIRTH_2, MONTH_OF_BIRTH_5, DAY_OF_BIRTH_5);
  		
  		user.setDateOfBirth(calendar.getTime());
  		assertEquals(GAUGE_AGE_5, user.getAge());
  	}
    //For testing if Birthday is on the 29st February, 1992 (leap year)
  	public void test6() {
  		Calendar calendar = Calendar.getInstance();
  		calendar.set(YEAR_OF_BIRTH_2, MONTH_OF_BIRTH_6, DAY_OF_BIRTH_6);
  		
  		user.setDateOfBirth(calendar.getTime());
  		assertEquals(GAUGE_AGE_6, user.getAge());
  	}
  	
  }