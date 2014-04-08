package search;

// import required items
import java.awt.EventQueue;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
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
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/** A search program used to find words inside text documents
 * @author Adam Kenny
 * @author Dang Yang
 * @author Lloyd Pintac
 */
@SuppressWarnings("serial")
public class GUI extends JFrame {
	private JPanel contentPane;
	private JTextField searchTextBox;
	private JTable indexTable = new JTable();
        
        //Create file chooser
	final JFileChooser fileChooser = new JFileChooser();
	private Object list;
	private boolean searchResult;
	private JScrollPane resultScrollPane;
	private List<String> myList;
	private String filePath;
	
	/**
	 * main method to launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            @Override
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
		// constants for easy access
		final String TEAM_NAME = "PKnY"; // like the fashion company DKNY, ha!
		final double VERSION_NUM = 1.2;
		final String AUTHORS = "Adam Kenny, Dang Yang, Lloyd Pintac";
		final String INDEX_HEADER = "SearchProject";
		final int FILES_INDEXED = 0;
		final String INDEX_FILE = "src/search/SearchProjectIndex.txt";
                
		//Creates custom color 
		Color customColor = new Color(255, 228, 225);
		
		setBackground(Color.WHITE);
		setTitle(TEAM_NAME + " Search Engine v" + VERSION_NUM);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 606);
		contentPane = new JPanel();
		contentPane.setBackground(customColor);
                
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // center the frame
                
                //Sets image icon for frame
                java.net.URL imageURL = GUI.class.getResource("images/index.jpg");
                ImageIcon icon = new ImageIcon(imageURL);
                setIconImage(icon.getImage());

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
                    @Override
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
                    @Override
                        public void actionPerformed(ActionEvent e) {
                                        JOptionPane.showMessageDialog(null,
                                                        TEAM_NAME + " Search Engine v " + VERSION_NUM + "\n" +
                                                        "Authors: " + AUTHORS, TEAM_NAME + " Search Engine",
                                            JOptionPane.INFORMATION_MESSAGE);
                        }
                });

                // tabbed pane to hold search and maintenance panels
                JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
                tabbedPane.setBackground(customColor);
                tabbedPane.setBounds(6, 28, 788, 544);
                contentPane.add(tabbedPane);

                // search panel
                JPanel searchPanel = new JPanel();
                searchPanel.setBackground(customColor);
                tabbedPane.addTab("Search", null, searchPanel, null);
                searchPanel.setLayout(null);

                // simple search engine label
                JLabel searchEngineLabel = new JLabel(TEAM_NAME + " Search Engine");
                searchEngineLabel.setBounds(6, 6, 755, 31);
                searchPanel.add(searchEngineLabel);
                searchEngineLabel.setHorizontalAlignment(SwingConstants.CENTER);
                searchEngineLabel.setFont(new Font("Arial", Font.BOLD, 24));

                // text box to enter search terms
                searchTextBox = new JTextField();
                searchTextBox.setText("Enter search term here");
                searchTextBox.setBounds(6, 49, 475, 24);
                searchPanel.add(searchTextBox);

                //Clears the text field when clicked
                searchTextBox.addMouseListener(new MouseAdapter(){
                    @Override 
                    public void mouseClicked(MouseEvent e){
                        // only clear textbox if default text is entered
                        if (searchTextBox.getText().equals("Enter search term here")) {
                                searchTextBox.setText("");            		
                        }
                    }
                 });

                // combo box to select search type
                final JComboBox searchTypeComboBox = new JComboBox();
                searchTypeComboBox.setBounds(493, 46, 160, 32);
                searchPanel.add(searchTypeComboBox);
                searchTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"All Terms", "Any Term", "Exact Phrase"}));

                // button to activate search
                JButton findButton = new JButton("Find it!");
                findButton.setMnemonic('i');
                findButton.setBounds(665, 46, 96, 32);
                searchPanel.add(findButton);
                findButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	 clickButtonActionPerformed(e);
                        if (searchTypeComboBox.getSelectedIndex() == 0) {
                                
                         
                        } else if (searchTypeComboBox.getSelectedIndex() == 1) {
                                // perform any term search
                                JOptionPane.showMessageDialog(null,
                                                "'any term' search placeholder",
                                                "title", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                                // perform exact phrase search
                                JOptionPane.showMessageDialog(null,
                                                "'exact phrase' search placeholder",
                                                "title", JOptionPane.INFORMATION_MESSAGE);
                        }
                        }
                });

                // scroll pane to hold result listing
                resultScrollPane = new JScrollPane();
                resultScrollPane.setBounds(6, 80, 755, 412);
                searchPanel.add(resultScrollPane);

                // maintenance panel
                JPanel maintenancePanel = new JPanel();
                maintenancePanel.setBackground(customColor);
                tabbedPane.addTab("Maintenance", null, maintenancePanel, null);
                maintenancePanel.setLayout(null);

                // label describing number of indexed files
                //Will need to find a way for the number of file(s) indexed to update
                
                JLabel indexLabel = new JLabel("Number of files currently indexed: " + indexTable.getRowCount());
                indexLabel.setHorizontalAlignment(SwingConstants.CENTER);
                indexLabel.setBounds(6, 6, 755, 16);
                maintenancePanel.add(indexLabel);

                // scroll pane to hold table of indexed files
                JScrollPane indexScrollPane = new JScrollPane();
                indexScrollPane.setBounds(6, 34, 755, 417);
                maintenancePanel.add(indexScrollPane);     
                
                // table listing all indexed files and their status
                indexTable.setModel(new DefaultTableModel(
                    new Object [][] {},
                    new String [] {
                        "File Name", "Status", "Time"
                    }
                ) {
                    //Data will not be edited
                    boolean[] canEdit = new boolean [] {
                        false, false
                    };

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
           
                indexTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                indexTable.setFillsViewportHeight(true);
                indexScrollPane.setViewportView(indexTable);
                indexTable.setBorder(null);
                indexTable.setBackground(Color.WHITE);        
                
                // button to add files to index
                JButton addFileButton = new JButton("Add File to Index...");
                addFileButton.setMnemonic('a');
                addFileButton.setBounds(6, 463, 192, 29);
                maintenancePanel.add(addFileButton);
                addFileButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e){
                                //Perform the add file method
                                addFileButtonActionPerformed(e);
                            }
                });
                
                // button to re-build index if files change
                JButton rebuildIndexButton = new JButton("Rebuild Index");
                rebuildIndexButton.setMnemonic('b');
                rebuildIndexButton.setBounds(569, 463, 192, 29);
                maintenancePanel.add(rebuildIndexButton);
                rebuildIndexButton.addActionListener(new ActionListener() {
                    @Override
                        public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(null, "rebuild index here",
                                                "title", JOptionPane.INFORMATION_MESSAGE);
                        }
                });

                // button to remove selected files from index
                JButton removeFileButton = new JButton("Remove Selected Files");
                removeFileButton.setMnemonic('m');
                removeFileButton.setBounds(292, 463, 192, 29);
                maintenancePanel.add(removeFileButton);
                removeFileButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            //Perform the remove file method
                            removeFileButtonActionPerformed(e);  
                        }
                });
                
                checkIndex(tabbedPane, INDEX_HEADER, VERSION_NUM, INDEX_FILE); // check index for changes
        }//End of public GUI
	
            //Method to search text
	private void clickButtonActionPerformed(ActionEvent e) {
	 		
		if(myList.toString().toLowerCase().contains(searchTextBox.getText().toLowerCase()))
		{					
			String[] listData = {filePath};
            @SuppressWarnings("unchecked")
			JList resultList = new JList(listData);
            resultScrollPane.setViewportView(resultList);
            resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            resultList.setBorder(null);
            resultList.setBackground(Color.WHITE);
		}
		else
		{
			String[] listData = {};
            @SuppressWarnings("unchecked")
			JList resultList = new JList(listData);
            resultScrollPane.setViewportView(resultList);
            resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            resultList.setBorder(null);
            resultList.setBackground(Color.WHITE);
		}		
 			
 	}
        //Method to add file to table
	private void addFileButtonActionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File openFile = fileChooser.getSelectedFile();
            
            try {
				Scanner input = new Scanner(openFile);
				filePath = openFile.getAbsolutePath();
				
				myList = new ArrayList<>();
				while(input.hasNextLine())
				{
					myList.add(input.nextLine());
				}
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
            
            DefaultTableModel model = (DefaultTableModel) indexTable.getModel(); 
            java.util.Date date = new java.util.Date();
            Timestamp Timestamp = (new Timestamp(date.getTime()));
			model.addRow(new Object[]{openFile.getAbsolutePath(), "Indexed", Timestamp });
        }
    } 
        //Method to remove file from table
        private void removeFileButtonActionPerformed(ActionEvent e) {                                          
            //Create table model to manage table data
            DefaultTableModel model = (DefaultTableModel) indexTable.getModel();
            int response;
            if(indexTable.getSelectedRow() == -1){
                if(indexTable.getRowCount() == 0){
                     JOptionPane.showMessageDialog(null, "Table is empty.",
                                                    "Remove File", JOptionPane.INFORMATION_MESSAGE);
                }else{
                     JOptionPane.showMessageDialog(null, "Please select row(s) to remove file.",
                                                    "Remove File", JOptionPane.INFORMATION_MESSAGE);
                }           
            }else{
                //Collect selected row(s) to remove
                String removeTxt = "\n";
                int[] rows = indexTable.getSelectedRows();
                for(int count = 0; count < rows.length; count++){
                    Object removeFile = indexTable.getModel().getValueAt(rows[count],0);
                    removeTxt += removeFile + "\n";
                }
                response = JOptionPane.showConfirmDialog(null, "Do you want to remove file(s): " + removeTxt,
                                        "Remove File", JOptionPane.YES_NO_OPTION);
                //Remove the selected row(s)
                if(response == JOptionPane.YES_OPTION){
                    for(int count = 0; count < rows.length; count++){
                        model.removeRow(rows[count]-count);
                    }
                }
            }        
        } 
        
        // method that checks the index file for changes
        private void checkIndex(JTabbedPane p, String hdr, double vers, String file) {
            boolean outOfDate = false; // assume index is up to date at first
            int filesFound = 0;
            int filesExpected = 0;
            
            try {
            	// load and check index file
                String line;
                int lineNum = 1;
                int blanksEncountered = 0;
                BufferedReader in = new BufferedReader(new FileReader(file));
                while ((line = in.readLine()) != null) {

                	// first line is header section
                	if (lineNum == 1) {
                		String[] header = line.split(" ");
                		if (!(header[0].equals(hdr) && header[1].equals(String.valueOf(vers)))) {
                			// header line is wrong, show maintenance panel
                			outOfDate = true;
                			break;
                		}
                	}
                	
                	// second line is number of indexed files
                	if (lineNum == 2) {
                		filesExpected = Integer.parseInt(line);
                	}
                	
                	// line 3 is a separator line
                	if (lineNum == 3) {
                		if (line.equals("")) {
                			// we are now entering the file list after this line
                			++blanksEncountered;
                		} else {
                			// line 3 was not blank, show maintenance panel
                			outOfDate = true;
                			break;
                		}
                	}
                	
                	// lines 4+ begin the listing of indexed files
                	if (lineNum >= 4) {
                		if (line.equals("")) {
                			// following lines will be word list
                			++blanksEncountered;
                		}
                		
                		
                		if (blanksEncountered == 1) {
                			// try to parse the file lines
                			try {
                				// lines after first blank are file listing
                				String[] fileListing = line.split(" ");
                				File f = new File(fileListing[1]);
                				long timeStamp = Long.parseLong(fileListing[2]);
                				++filesFound;
                				
                				if (!(f.exists())) {
                					// file could not be found, show maintenance panel
                					outOfDate = true;
                					break;
                				}
                				
                				if (!( f.lastModified() == timeStamp)) {
                					// last mod time does not match, show maintenance panel
                					outOfDate = true;
                					break;
                				}
                				
                			} catch (ArrayIndexOutOfBoundsException e) {
                				// file line not formatted correctly, show maint window
                				outOfDate = true;
                				break;
                			}
                		} else if (blanksEncountered == 2) {
                			// reached end of files listing, verify number of
                			// files found matches expected files found
                			if (filesFound != filesExpected) {
                				// numbers do not match, show maintenance window
                				outOfDate = true;
                				break;
                			}
                		}
                	}                	
                	++lineNum;
                }
                in.close();
            } catch (IOException e) {
                // error reading index file, create a new one
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(file));
                    out.write(hdr + " " + vers + "\n0\n");
                    out.close();
                    JOptionPane.showMessageDialog(null,
                            "Index file corrupt or not found.\nA new one has been created.",
                            "Maintenance", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    System.err.println("error");
                }
            }

            // show maintenance panel if index is out of date
            if (outOfDate) {
                JOptionPane.showMessageDialog(null,
                        "Index needs to be re-built!\nMaintenance panel will be shown.",
                        "Maintenance", JOptionPane.INFORMATION_MESSAGE);
                p.setSelectedIndex(1);      
            }
        }   
}//End of class GUI