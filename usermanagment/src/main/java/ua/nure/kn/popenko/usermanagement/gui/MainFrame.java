package ua.nure.kn.popenko.usermanagement.gui;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import ua.nure.kn.popenko.usermanagement.db.DaoFactory;
import ua.nure.kn.popenko.usermanagement.db.UserDao;
import ua.nure.kn.popenko.usermanagement.util.Messages;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8011960452561587049L;
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 800;
	private JPanel contentPanel;
	private BrowsePanel browsePanel;
	private EditPanel editPanel;
    private DetailsPanel detailsPanel;
	private AddPanel addPanel;
    private UserDao dao;
    
	public MainFrame() {
		super();
		dao = DaoFactory.getInstance().getUserDao();
		initialize();
	}
	
	public UserDao getDao() {
        return dao;
    }
	
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
		this.setContentPane(getContentPanel());
	}
		
	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
			
		}
		return contentPanel;
	}
	
	private JPanel getBrowsePanel() {
		if (browsePanel == null) {
			browsePanel = new BrowsePanel(this);
		}
		((BrowsePanel) browsePanel).initTable();
		return browsePanel;
	}

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);

	}

	public void showAddPanel() {
		showPanel(getAddPanel());
		
	}

	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
		
	}

	private AddPanel getAddPanel() {
		if (addPanel == null) {
			addPanel = new AddPanel(this);
			
		}
		return addPanel;
	}
	
	public void showBrowsePanel() {
        showPanel(getBrowsePanel());
    }

    public void showEditPanel() {
        showPanel(getEditPanel());
    }

	public void showDetailsPanel() {
        showPanel(getDetailsPanel());
    }

	private EditPanel getEditPanel() {
        JTable userTable = browsePanel.getUserTable();
        Long selectedUserId = (Long) userTable.getValueAt(userTable.getSelectedRow(), 0);
        if (editPanel == null) {
            editPanel = new EditPanel(this, selectedUserId);
        }
        editPanel.setUser(selectedUserId);
        return editPanel;
    }

    private JPanel getDetailsPanel() {
        JTable userTable = browsePanel.getUserTable();
        Long selectedUserId = (Long) userTable.getValueAt(userTable.getSelectedRow(), 0);
        if (detailsPanel == null) {
            detailsPanel = new DetailsPanel(this, selectedUserId);
        }
        detailsPanel.setUser(selectedUserId);
        return detailsPanel;
    }

}