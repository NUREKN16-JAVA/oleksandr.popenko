package ua.nure.kn.popenko.usermanagement.web;

import ua.nure.kn.popenko.usermanagement.User;
import ua.nure.kn.popenko.usermanagement.db.DaoFactory;
import ua.nure.kn.popenko.usermanagement.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailsServlet extends HttpServlet {
    private static final String BROWSE_SERVLET = "/browse";
    private static final String DETAILS_JSP = "/details.jsp";
    private static final String BACK_BUTTON = "backButton";

    /**
     * If "Back" button was clicked, goes to "/browse" servlet page.
     * If "Back" button wasn't clicked, stays on "/details.jsp" page.
     * */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter(BACK_BUTTON) != null){
            req.getSession(true).removeAttribute("user");
            req.getRequestDispatcher(BROWSE_SERVLET).forward(req, resp);
        }
        else{
            req.getRequestDispatcher(DETAILS_JSP).forward(req, resp);
        }
    }
}