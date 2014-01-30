// COP2805 Team Search Project

package search;

import java.awt.event.*;

import javax.swing.*;

/** A search app GUI
 * @author Adam Kenny, x, x
 */
@SuppressWarnings("serial")
public class SearchGUI extends JFrame {
    public SearchGUI() {
        // create temporary items
    	final JTextField textBox = new JTextField("hello world", 20);
        JButton okButton = new JButton("OK");
        final JLabel tempLabel = new JLabel("");

        // Create a panel to hold items
        JPanel panel = new JPanel();
        panel.add(textBox);
        panel.add(okButton);
        panel.add(tempLabel);
        
        add(panel); // Add panel to the frame

        // create and register button listener
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tempLabel.setText(textBox.getText());	// adds text to label
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