
package distsys.smarteducationalenviroment;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/* @author Carolina*/

/*
    This class is the graphical window of my program.
It will show fields, buttons, and outputs.

*/
public class StudentClientGUI {
    public static void main(String[] args) {
        
        //main window (JFrame)
        JFrame jframe = new JFrame("Eco-classroom client");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(500, 400);
        jframe.setLayout(new BorderLayout());
        
        // **************TOP PANEL FOR INPUT****************
        JPanel inPanel = new JPanel(new GridLayout(5,2,10,10));
        
        JTextField fName = new JTextField();
        JTextField fAge = new JTextField();
        JTextField fGender = new JTextField();
        
        String[] tasks = {"Washing Dishes", "Sweeping", "Mooping", "Laundry", "Cooking", "Ironing", "Make the bed"};
        JComboBox<String> taskDropdown = new JComboBox<>(tasks);
        
        inPanel.add(new JLabel("Student Name: "));
        inPanel.add(fName);
        
        inPanel.add(new JLabel("Student Age: "));
        inPanel.add(fAge);
        
        inPanel.add(new JLabel("Student Gender: "));
        inPanel.add(fGender);
        
        inPanel.add(new JLabel("Select Task to do: "));
        inPanel.add(taskDropdown);
        
        JButton subButton = new JButton("Submit Domestic Task");
        
        inPanel.add(new JLabel()); //empty cell
        inPanel.add(subButton);
        
        //************** CENTER PANEL FOR OUTPUT *****************
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane pScroll = new JScrollPane(outputArea);
        
        //***************** ADD PANELS TO FRAME ******************
        jframe.add(inPanel, BorderLayout.NORTH);
        jframe.add(pScroll, BorderLayout.CENTER);
        
        //************** BUTTON LOGIC "PLACEHOLDER" **************
        subButton.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                String name = fName.getText();
                String age = fAge.getText();
                String gender = fGender.getText();
                String task = (String) taskDropdown.getSelectedItem();
                
                outputArea.append("Submitting task...\n");
                outputArea.append("Student name: " + name + " , age: " + age + " , gender: " + gender + " , and task to do: " + task + "\n");
                outputArea.append("this is a placeholder - gRPC goes here\n\n\n");
            }
        });
        
        //************** SHOW THE WINDOW **************************
        jframe.setVisible(true);
    }
    
} 