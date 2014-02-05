// COP2805 Team Search Project

package search;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

/** A search app GUI
 * @author LloydJayson
 */
@SuppressWarnings("serial")
public class SearchGUI extends JFrame {
    public SearchGUI() {
       	 // Create a panel to hold items 
    	 JPanel panel = new JPanel(new GridBagLayout());
    	 JPanel panel1 = new JPanel(new GridBagLayout());
    	 
    	 // Set color background to panel
    	 panel.setBackground(Color.LIGHT_GRAY);
    	 panel1.setBackground(Color.GRAY); 
    	 
    	// Add panel to the frame
    	 add(panel,BorderLayout.NORTH);      
         add(panel1);
        
    	 // Use constraints for arranging objects
         GridBagConstraints c = new GridBagConstraints();
         
         // Create the menubar 
         JMenuBar menubar = new JMenuBar();
         setJMenuBar(menubar);
         
         // Build menus to menubar
         JMenu menu = new JMenu ("Author");
         menubar.add(menu);
         JMenu menu1 = new JMenu ("Project");
         menubar.add(menu1);
         
         // Create a group of menu items
         JMenuItem item = new JMenuItem("Lloyd Jayson Pintac");
         menu.add(item);
         JMenuItem item1 = new JMenuItem("Adam Kenny");
         menu.add(item1);
         JMenuItem item2 = new JMenuItem("Dang Yang");
         menu.add(item2);
         JMenuItem item3 = new JMenuItem("About");
         menu1.add(item3);
         
         // Add action listener to menu item
         item3.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
        	 JOptionPane.showMessageDialog(null, "Java II Advance Project\n by: **TeamNameHere**","Search Engine",// We need a group name
        		    JOptionPane.INFORMATION_MESSAGE);
         	}
         });
         
         // Customize and arrange objects, texts and fonts 
         Font myFont = new Font("Algerian", Font.BOLD + Font.ITALIC, 26);
         Font fnt = new Font("Courier new", Font.BOLD, 32);
         Font font1 = new Font("Eras Light ITC", Font.BOLD,18);
         Font font = new Font("Arial Rounded", Font.BOLD,12);
         final JLabel tempLabel = new JLabel("");  
         c.gridx = 2;
         c.gridy = 60;
         panel.add(tempLabel, c); 
         
         JLabel labelmaint = new JLabel("***Maintenance***");
         labelmaint.setFont(fnt);
         c.gridx = 2;
         c.gridy = 0;
         panel1.add(labelmaint , c); 
         
		 JLabel labelNumOfIndex = new JLabel("***Number of Files Indexed:   ***");
		 labelNumOfIndex.setFont(font1);
         c.gridx = 2;
         c.gridy = 1;
         c.insets = new Insets(10,10,10,10);
         panel1.add(labelNumOfIndex, c); 
         
         JLabel searhEngineLabel = new JLabel("***SEARCH ENGINE***");
         searhEngineLabel.setFont(myFont);
         c.gridx = 2;
         c.gridy = 0;
         panel.add(searhEngineLabel, c); 
         
         JLabel Label = new JLabel("Search Terms");
         Label.setFont(font1);
         c.gridx = 1;
         c.gridy = 2;
         panel.add(Label, c);     
         
         final JTextField textBox = new JTextField(null, 30);
         textBox.setToolTipText("Type Here");
         c.gridx = 2;
         c.gridy = 2;
         c.insets = new Insets(10,10,10,10);
         panel.add(textBox, c);
         
         JButton okButton = new JButton("OK");
         okButton.setToolTipText("Click Here");
         c.gridx = 2;
         c.gridy = 8;
         c.insets = new Insets(10,10,10,10);
         panel.add(okButton, c); 
         
         JButton okButton_1 = new JButton("UPDATE");
         okButton.setToolTipText("Click Here");
         c.gridx = 1;
         c.gridy = 5;
         c.insets = new Insets(10,10,10,10);
         panel1.add(okButton_1, c); 
         
         JButton okButton_2 = new JButton("DELETE");
         okButton.setToolTipText("Click Here");
         c.gridx = 3;
         c.gridy = 5;
         c.insets = new Insets(10,10,10,10);
         panel1.add(okButton_2, c); 
         
         // Add radio buttons
     	JRadioButton radioButton = new JRadioButton("AND");
     	radioButton.setFont(font);
     	radioButton.setPreferredSize(new Dimension(80, 20));
     	 c.gridx = 1;
         c.gridy = 20;	
         c.insets = new Insets(1,1,1,1);
     	panel.add(radioButton,c);
     	
     	JRadioButton radioButton_1 = new JRadioButton("OR");
     	radioButton_1.setFont(font);
     	radioButton_1.setPreferredSize(new Dimension(80, 20));
     	 c.gridx = 2;
         c.gridy = 20;
     	panel.add(radioButton_1, c);
     	
     	JRadioButton radioButton_2 = new JRadioButton("PHRASE");
     	radioButton_2.setFont(font);
     	radioButton_2.setPreferredSize(new Dimension(80, 20));
     	 c.gridx = 3;
         c.gridy = 20;	
     	panel.add(radioButton_2,c);
        	
     	String fileFromList[]={"File1","File2","File3","File4","File5"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		final JComboBox<String> cb = new JComboBox(fileFromList);    
     	cb.setPreferredSize(new Dimension(100, 20));
     	c.gridx = 2;
     	c.gridy = 5;
     	c.insets = new Insets(20,20,20,20);
     	panel1.add(cb,c);
     	
     	@SuppressWarnings({ "rawtypes" })
		final DefaultListModel model = new DefaultListModel();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		
		JList jl = new JList(model);
     	jl.setPreferredSize(new Dimension(120, 160));
     	c.gridx = 2;
     	c.gridy = 24;
     	c.insets = new Insets(20,20,20,20);
     	panel.add(jl,c);
     	
     	
     	
     	// Create group of radio button
     	ButtonGroup group = new ButtonGroup();
     	group.add(radioButton);
     	group.add(radioButton_1);
     	group.add(radioButton_2);
     	

        	
        // create and register button listener
        okButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unchecked")
			public void actionPerformed (ActionEvent e) {
            	String text = textBox.getText();
            	 if ( text.length() > 0 ) {
                     model.addElement( text );
                     textBox.setText( "" );
                   }
            }
        });
    
    
    }
    public static void main (String[] args) {
	    	EventQueue.invokeLater( new Runnable() {
	    	      public void run() {
	    	// create and display window frame
	        JFrame frame = new SearchGUI();
	        frame.setTitle("Search Engine");
	        frame.setSize(1000, 700);
	        frame.setLocationRelativeTo(null); // center the frame
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	    	}
	    });
    }	
}
