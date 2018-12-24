package ua.nure.kn.popenko.usermanagement.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.popenko.usermanagement.User;
import ua.nure.kn.popenko.usermanagement.db.DaoFactory;
import ua.nure.kn.popenko.usermanagement.db.DatabaseException;

public class BrowseServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6847793370404496436L;
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			 throws ServletException, IOException {
        if (req.getParameter("addButton") != null) {
            add(req, resp);
        } else if (req.getParameter("editButton") != null) {
            edit(req, resp);
        } else if (req.getParameter("deleteButton") != null) {
            delete(req, resp);
        } else if (req.getParameter("detailsButton") != null) {
            details(req, resp);
        } else {
            browse(req, resp);
        }
	}
	private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
        if (idStr == null || idStr.trim().length() == 0) {
            req.setAttribute("error","�hoose user");
            req.getRequestDispatcher("/browse.jsp").forward(req,  resp);
            return;
        }
        try {
            User user = DaoFactory.getInstance().getUserDao().find(new Long(idStr));
            req.getSession().setAttribute("user", user);
        } catch (Exception e) {
            req.setAttribute("error","ERROR:" + e.toString());
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/details").forward(req, resp);
        
    }
		
	
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idS =req.getParameter("id");
		if (idS == null || idS.trim().length()==0) {
            req.setAttribute("error", "Choose user");
            
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
            return;
        }
	
        try {
            User user = DaoFactory.getInstance().getUserDao().find(new Long(idS));
            DaoFactory.getInstance().getUserDao().delete(user);
            req.getSession().setAttribute("user", user);
        } catch (DatabaseException e) {
            req.setAttribute("error", "Database error " + e.toString());
            
        }
        browse(req, resp);
    }
		
	
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String idStr = req.getParameter("id");
	        if (idStr == null || idStr.trim().length() == 0) {
	            req.setAttribute("error", "Choose user");
	            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
	            return;
	        }
	        try {
	            User user = DaoFactory.getInstance().getUserDao().find(new Long(idStr));
	            req.getSession().setAttribute("user", user);
	        } catch (Exception e) {
	            e.printStackTrace();
	            req.setAttribute("error", "ERROR:" + e.toString());
	            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
	            return;
	        }
	        req.getRequestDispatcher("/edit").forward(req, resp);
	}
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/add").forward(req, resp);
		
	}
	private void browse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Collection<?> users;
        try {
            users = DaoFactory.getInstance().getUserDao().findAll();
            req.getSession().setAttribute("users", users);
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
        } catch (DatabaseException e) {
            throw new ServletException(e);
        }
		
	}
}
