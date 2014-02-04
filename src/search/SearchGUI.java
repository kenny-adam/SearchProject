// COP2805 Team Search Project

package search;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** A search app GUI
 * @author Adam Kenny, Dang Yang, x
 * @author LloydJayson
 */
@SuppressWarnings("serial")
public class SearchGUI extends JFrame {
    private static String images;
    public SearchGUI() {
       	 // Create a panel to hold items 
    	 JPanel panel = new JPanel(new GridBagLayout());
    	 JPanel panel1 = new JPanel();
    	 
    	 // Set color background to panel
    	 panel.setBackground(Color.LIGHT_GRAY);
    	 panel1.setBackground(Color.GRAY); 
    	 
    	// Add panel to the frame
    	 add(panel,BorderLayout.SOUTH);      
         add(panel1);
         
    	 // Use constraints for arranging objects
         GridBagConstraints c = new GridBagConstraints();
         
         // Create the menubar 
         JMenuBar menubar = new JMenuBar();
         setJMenuBar(menubar);
         
         // Build menus to menubar
         JMenu menu = new JMenu ("Author");
         menubar.add(menu);
         menu.setMnemonic('u');
         JMenu menu1 = new JMenu ("Project");
         menubar.add(menu1);
         menu1.setMnemonic('r');
         
         // Create a group of menu items
         JMenuItem item = new JMenuItem("Lloyd Jayson Pintac");
         menu.add(item);
         JMenuItem item1 = new JMenuItem("Adam Kenny");
         menu.add(item1);
         JMenuItem item2 = new JMenuItem("Dang Yang");
         menu.add(item2);
         JMenuItem item3 = new JMenuItem("About");
         item3.setMnemonic('b');
         menu1.add(item3);
         
         // Add action listener to menu item
         item3.addActionListener(new ActionListener(){
         @Override    
         public void actionPerformed(ActionEvent e){
        	 JOptionPane.showMessageDialog(null, "Java II Advance Project\n by: **TeamNameHere**","Search Engine",// We need a group name
        		    JOptionPane.INFORMATION_MESSAGE);
         	}
         });
         
         // Customize and arrange objects, texts and fonts 
         Font myFont = new Font("Algerian", Font.BOLD + Font.ITALIC, 26);
         Font font1 = new Font("Eras Light ITC", Font.BOLD,18);
         Font font = new Font("Arial Rounded", Font.BOLD,12);
         final JLabel tempLabel = new JLabel("");  
         c.gridx = 2;
         c.gridy = 60;
         panel.add(tempLabel, c); 
         
         JLabel searhEngineLabel = new JLabel("***SEARCH ENGINE***");
         searhEngineLabel.setFont(myFont);
         c.gridx = 2;
         c.gridy = 0;
         panel.add(searhEngineLabel, c); 
         
         JLabel Label = new JLabel("Search Terms:");
         Label.setFont(font1);
         c.gridx = 1;
         c.gridy = 2;
         panel.add(Label, c);     
         
         final JTextField textBox = new JTextField("Enter text here...", 30);
         //Clears the text box when clicked
         textBox.addMouseListener(new MouseAdapter(){
            @Override 
            public void mouseClicked(MouseEvent e){
                textBox.setText("");
            }
         });
         textBox.setToolTipText("Type word(s) to search files");
         c.gridx = 2;
         c.gridy = 2;
         c.insets = new Insets(10,10,10,10);
         panel.add(textBox, c);
         
         JButton searchButton = new JButton("Search");
         searchButton.setToolTipText("Click to search indexed file(s)");
         c.gridx = 2;
         c.gridy = 8;
         c.insets = new Insets(10,10,10,10);
         panel.add(searchButton, c);
         searchButton.setMnemonic('S');
         
         // Add radio buttons
     	JRadioButton radioButton = new JRadioButton("All of the Search Terms");
     	radioButton.setFont(font);
     	 c.gridx = 1;
         c.gridy = 20;	
         c.insets = new Insets(1,1,1,1);
     	panel.add(radioButton,c);
        radioButton.setMnemonic('l');
     	
     	JRadioButton radioButton_1 = new JRadioButton("Any of the Search Terms");
     	radioButton_1.setFont(font);
     	 c.gridx = 2;
         c.gridy = 20;
     	panel.add(radioButton_1, c);
     	radioButton_1.setMnemonic('n');
        
     	JRadioButton radioButton_2 = new JRadioButton("Exact Phrase");
     	radioButton_2.setFont(font);
     	 c.gridx = 3;
         c.gridy = 20;	
     	panel.add(radioButton_2,c);
     	radioButton_2.setMnemonic('x');
        
     	// Create group of radio button
     	ButtonGroup group = new ButtonGroup();
     	group.add(radioButton);
     	group.add(radioButton_1);
     	group.add(radioButton_2);
     	
        // create and register button listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	tempLabel.setText(textBox.getText());
                textBox.requestFocusInWindow();
            }
        });
    }

    public static void main (String[] args) {
        // create and display window frame
        JFrame frame = new SearchGUI();
        
        //Sets image icon for frame, please check if it's correct.
        java.net.URL imageURL = SearchGUI.class.getResource("images/index.jpg");
        ImageIcon icon = new ImageIcon(imageURL);
        frame.setIconImage(icon.getImage());
        
        frame.setTitle("Search Engine");
        frame.setSize(850, 550);
        frame.setLocationRelativeTo(null); // center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}