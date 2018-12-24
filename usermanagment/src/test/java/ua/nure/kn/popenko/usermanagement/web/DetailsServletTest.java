package ua.nure.kn.popenko.usermanagement.web;

import junit.framework.TestCase;
import ua.nure.kn.popenko.usermanagement.User;
import ua.nure.kn.popenko.usermanagement.gui.DetailsPanel;

import java.text.DateFormat;
import java.util.Date;

public class DetailsServletTest extends MockServletTestCase {

    private static final String BACK_BUTTON = "backButton";

    public void setUp() throws Exception {
        super.setUp();
        createServlet(DetailsServlet.class);
    }

    public void testDetails() {
        addRequestParameter(BACK_BUTTON, "Back");
        User user = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNull("User is in session", user);
    }
}