// COP2805 Team Search Project

package search;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** A search app GUI
 * @author Adam Kenny
 */
@SuppressWarnings("serial")
public class SearchGUI extends JFrame {
    public SearchGUI() {
        // create temporary items
    	JLabel titleLabel = new JLabel("TeamName Search Engine");
    	JLabel instructLabel = new JLabel("Enter Search Term:");
    	final JTextField textBox = new JTextField("hello world", 20);
        JButton okButton = new JButton("OK");
        final JLabel tempLabel = new JLabel("");

        // Create a panel to hold items
        JPanel panel = new JPanel(new GridLayout());
        panel.add(titleLabel);
        panel.add(instructLabel);
        panel.add(textBox);
        panel.add(okButton);
        panel.add(tempLabel);
        add(panel);

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