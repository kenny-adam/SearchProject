package search;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.SystemColor;

import javax.swing.JSplitPane;
import javax.swing.JTable;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField searchTextBox;
	private JTable indexTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setBackground(Color.WHITE);
		setTitle("TeamName Search Engine v0.1");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // center the frame
		
		// create and add menu items
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setBounds(0, 0, 800, 22);
		contentPane.add(menuBar);
		
        JMenu fileMenu = new JMenu ("File");
        fileMenu.setBackground(Color.LIGHT_GRAY);
        menuBar.add(fileMenu);
        fileMenu.setMnemonic('f');
        JMenu helpMenu = new JMenu ("Help");
        helpMenu.setBackground(Color.LIGHT_GRAY);
        menuBar.add(helpMenu);
        helpMenu.setMnemonic('h');
        
        // exit menu item
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setBackground(Color.WHITE);
        exitMenuItem.setMnemonic('x');
        fileMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0); // quit program
        	}
        });
        
        // about menu item
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setBackground(Color.WHITE);
        aboutMenuItem.setMnemonic('a');
        helpMenu.add(aboutMenuItem);
        aboutMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "TeamName Search Engine v0.1\n" +
						"Authors: x, y, z","TeamName Search Engine",
	        		    JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        
        // tabbed pane to hold search and maintenance panels
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setBounds(6, 28, 788, 544);
        contentPane.add(tabbedPane);
        
        // search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.WHITE);
        tabbedPane.addTab("Search", null, searchPanel, null);
        searchPanel.setLayout(null);
        
        // simple search engine label
        JLabel searchEngineLabel = new JLabel("TeamName Search Engine");
        searchEngineLabel.setBounds(6, 6, 755, 31);
        searchPanel.add(searchEngineLabel);
        searchEngineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        searchEngineLabel.setFont(new Font("Nanum Brush Script", Font.BOLD, 32));
        
        // text box to enter search terms
        searchTextBox = new JTextField();
        searchTextBox.setText("Enter search term here");
        searchTextBox.setBounds(6, 49, 475, 24);
        searchPanel.add(searchTextBox);
        
        // combo box to select search type
        final JComboBox searchTypeComboBox = new JComboBox();
        searchTypeComboBox.setBounds(493, 46, 160, 32);
        searchPanel.add(searchTypeComboBox);
        searchTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"All Terms", "Any Term", "Exact Phrase"}));
        
        // button to activate search
        JButton findButton = new JButton("Find it!");
        findButton.setBounds(665, 46, 96, 32);
        searchPanel.add(findButton);
        findButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null,
        				(String)searchTypeComboBox.getSelectedItem(),
        				"title",
	        		    JOptionPane.INFORMATION_MESSAGE);
        	}
        });

        // scroll pane to hold result listing
        JScrollPane resultScrollPane = new JScrollPane();
        resultScrollPane.setBounds(6, 80, 755, 412);
        searchPanel.add(resultScrollPane);
        
        // actual listing of search results
        String[] listData = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        JList resultList = new JList(listData);
        resultScrollPane.setViewportView(resultList);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultList.setBorder(null);
        resultList.setBackground(Color.WHITE);
        
        // maintenance panel
        JPanel maintenancePanel = new JPanel();
        maintenancePanel.setBackground(Color.WHITE);
        tabbedPane.addTab("Maintenance", null, maintenancePanel, null);
        maintenancePanel.setLayout(null);
        
        // label describing number of indexed files
        JLabel indexLabel = new JLabel("Number of files currently indexed: x");
        indexLabel.setHorizontalAlignment(SwingConstants.CENTER);
        indexLabel.setBounds(6, 6, 755, 16);
        maintenancePanel.add(indexLabel);
        
        // scroll pane to hold table of indexed files
        JScrollPane indexScrollPane = new JScrollPane();
        indexScrollPane.setBounds(6, 34, 755, 417);
        maintenancePanel.add(indexScrollPane);
        
        // table listing all indexed files and their status
        String[] tableColumnNames = {"File Name", "Status"};
        Object[][] tableData = {
                {"0a", "0b"},
                {"1a", "1b"},
                {"2a", "2b"},
                {"3a", "3b"},
                {"4a", "4b"},
                {"5a", "5b"},
                {"6a", "6b"},
                {"7a", "7b"},
                {"8a", "8b"},
                {"9a", "9b"},
                };
        indexTable = new JTable(tableData, tableColumnNames);
        indexTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        indexTable.setFillsViewportHeight(true);
        indexScrollPane.setViewportView(indexTable);
        indexTable.setBorder(null);
        indexTable.setBackground(Color.WHITE);        
        
        // button to add files to index
        JButton addFileButton = new JButton("Add File to Index...");
        addFileButton.setBounds(6, 463, 192, 29);
        maintenancePanel.add(addFileButton);
        addFileButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "add files here",
        				"title", JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        
        // button to re-build index if files change
        JButton rebuildIndexButton = new JButton("Rebuild Index");
        rebuildIndexButton.setBounds(569, 463, 192, 29);
        maintenancePanel.add(rebuildIndexButton);
        rebuildIndexButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "rebuild index here",
        				"title", JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        
        // button to remove selected files from index
        JButton removeFileButton = new JButton("Remove Selected Files");
        removeFileButton.setBounds(292, 463, 192, 29);
        maintenancePanel.add(removeFileButton);
        removeFileButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "remove files here",
        				"title", JOptionPane.INFORMATION_MESSAGE);
        	}
        });
	}
}
