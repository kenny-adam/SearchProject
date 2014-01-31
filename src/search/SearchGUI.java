// COP2805 Team Search Project

package search;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** A search app GUI
 * @author Adam Kenny, x, x
 * @author LloydJayson
 */
@SuppressWarnings("serial")
public class SearchGUI extends JFrame {
    public SearchGUI() {
       	 // Create a panel to hold items 
    	 JPanel panel = new JPanel(new GridBagLayout());
    	 
    	 //use constraints for arranging objects
         GridBagConstraints c = new GridBagConstraints();
         
         final JLabel tempLabel = new JLabel("");  
         c.gridx = 2;
         c.gridy = 60;
         panel.add(tempLabel, c); 
         
         JLabel searhEngineLabel = new JLabel("***SEARCH ENGINE***");
         c.gridx = 2;
         c.gridy = 0;
         panel.add(searhEngineLabel, c); 
         
         JLabel Label = new JLabel("Search Terms");
         c.gridx = 1;
         c.gridy = 2;
         panel.add(Label, c);     
         
         final JTextField textBox = new JTextField("Text Here", 30);
         c.gridx = 2;
         c.gridy = 2;
         c.insets = new Insets(10,10,10,10);
         panel.add(textBox, c);
         
         JButton okButton = new JButton("OK");
         c.gridx = 2;
         c.gridy = 8;
         c.insets = new Insets(10,10,10,10);
         panel.add(okButton, c); 
         
         
     	JRadioButton radioButton = new JRadioButton("All of the Search Terms");
         c.gridx = 1;
         c.gridy = 20;	
         c.insets = new Insets(1,1,1,1);
     	panel.add(radioButton,c);
     	
     	JRadioButton radioButton_1 = new JRadioButton("Any of the Search Terms");
     	 c.gridx = 2;
         c.gridy = 20;
     	panel.add(radioButton_1, c);
     	
     	JRadioButton radioButton_2 = new JRadioButton("Exact Phrase");
     	 c.gridx = 3;
         c.gridy = 20;	
     	panel.add(radioButton_2,c);
  
        add(panel); // Add panel to the frame

        // create and register button listener
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 tempLabel.setText(textBox.getText());
            }
        });
    }

    public static void main (String[] args) {
    	// create and display window frame
        JFrame frame = new SearchGUI();
        frame.setTitle("Window Title");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}