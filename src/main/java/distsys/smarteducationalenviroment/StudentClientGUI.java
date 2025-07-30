package distsys.smarteducationalenviroment;


import generated.grpc.analyzer.CustomFeedbackReply;
import generated.grpc.analyzer.CustomFeedbackRequest;
import generated.grpc.analyzer.Student;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/* @author Carolina*/

 /*
    This class is the graphical window of my program.
It will show fields, buttons, and outputs!

 */
public class StudentClientGUI {

    public static void main(String[] args) {

        //*********************************************************
        //SERVICE 1. UNARY - Domestic Activity Simulator
        //*********************************************************
        //main window (JFrame)
        JFrame jframe = new JFrame("Domestic Simulator");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(500, 400);
        jframe.setLayout(new BorderLayout());

        // **************TOP PANEL FOR INPUT****************
        JPanel inPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        JTextField fName = new JTextField();
        JTextField fAge = new JTextField();

        String[] fGender = {"-Select gender-", "Male", "Female", "Other"};
        JComboBox<String> genderDd = new JComboBox<>(fGender);

        String[] tasks = {"-Select task to do-", "Washing Dishes", "Sweeping", "Mooping", "Laundry", "Cooking", "Ironing", "Make the bed"};
        JComboBox<String> taskDd = new JComboBox<>(tasks);

        inPanel.add(new JLabel("Student Name: "));
        inPanel.add(fName);

        inPanel.add(new JLabel("Student Age: "));
        inPanel.add(fAge);

        inPanel.add(new JLabel("Student Gender: "));
        inPanel.add(genderDd);

        inPanel.add(new JLabel("Select Task to do: "));
        inPanel.add(taskDd);
        
        inPanel.add(new JLabel()); //empty cell

        //************** CENTER PANEL FOR OUTPUT *****************
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane pScroll = new JScrollPane(outputArea);
        
        //************** BOTTOM PANEL BUTTONS*****************
        JButton subButton = new JButton("Submit Domestic Task");
        JButton analyzeButton = new JButton("Submit Analized Task");
        JButton feedbackButton = new JButton("Submit feedback Task");
        
        JPanel pButton = new JPanel(); //default FlowLayout
        pButton.add(subButton);
        pButton.add(analyzeButton);
        pButton.add(feedbackButton);
        
        //***************** ADD PANELS TO FRAME ******************
        jframe.add(inPanel, BorderLayout.NORTH);
        jframe.add(pScroll, BorderLayout.CENTER);
        jframe.add(pButton, BorderLayout.SOUTH);

        //************** BUTTON LOGIC "UNARY RPC" **************
        subButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = fName.getText();
                String ageStr = fAge.getText();

                //message will be displayed if the user leaves the spaces empty.
                if (name.isEmpty() || ageStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the boxes.");
                    return;
                }

                //age from an int to a string
                int age;
                try {
                    age = Integer.parseInt(ageStr);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Age must be a number");
                    return;
                }

                String gender = (String) genderDd.getSelectedItem();

                //message will be displayed if the user doesn't select a gender.
                if (gender.equals("-Select gender-")) {
                    JOptionPane.showMessageDialog(null, "Please select a valid gender");
                    return;
                }

                String task = (String) taskDd.getSelectedItem();

                //message will be displayed if the user doesn't select a gender.
                if (task.equals("-Select task to do-")) {
                    JOptionPane.showMessageDialog(null, "Please select a valid task");
                    return;
                }

                //calling the gRPC helper method
                String result = StudentClientHelper.runUnaryDomesticTask(name, age, gender, task);

                outputArea.append("Submitting task...\n");
                outputArea.append("Student Name: " + name + "\n Age: " + age + "\n Gender: " + gender + "\n Task to do: " + task + "\n");
                outputArea.append("!!!! " + result + "\n");
            }
        });


        //*********************************************************
        //SERVER 2. Server Streaming RPC - Participation Analizer
        //*********************************************************
        analyzeButton.addActionListener(e -> {

            //gather input from fields and validating inputs
            String name = fName.getText().trim();
            String ageStr = fAge.getText().trim();

            //message will be displayed if the user leaves the spaces empty.
            if (name.isEmpty() || ageStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the boxes.");
                return;
            }

            //age from an int to a string
            int age;
            try {
                age = Integer.parseInt(ageStr);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Age must be a number");
                return;
            }

            String gender = (String) genderDd.getSelectedItem();

            //message will be displayed if the user doesn't select a gender.
            if (gender.equals("-Select gender-")) {
                JOptionPane.showMessageDialog(null, "Please select a valid gender");
                return;
            }

            String task = (String) taskDd.getSelectedItem();

            //message will be displayed if the user doesn't select a gender.
            if (task.equals("-Select task to do-")) {
                JOptionPane.showMessageDialog(null, "Please select a valid task");
                return;
            }

            //Building student message
            Student student = Student.newBuilder()
                    .setStudentName(name)
                    .setStudentAge(age)
                    .setGender(gender)
                    .setTaskName(task)
                    .build();

            //calling 'helper' to run server-streaming RPC
            String feedback = StudentClientHelper.runServerStreamingParticipationAnalizer(student);
            outputArea.append("!!!Server replied:\n " + feedback + "\n");

        });
        
        //*********************************************************
        //SERVER 2. Client Streaming RPC - Participation Analizer
        //*********************************************************        
        feedbackButton.addActionListener(e -> {
            List<CustomFeedbackRequest> feedbackList = new ArrayList<>();
            
            int total = Integer.parseInt(JOptionPane.showInputDialog("How many feedbacks will you want to write?"));
            
            for(int i = 0; i<total; i++){
                String name = JOptionPane.showInputDialog("Student name: ");
                String feedback = JOptionPane.showInputDialog("Write feedback of student or overall: ");
                
                CustomFeedbackRequest request = CustomFeedbackRequest.newBuilder()
                        .setStudentName(name)
                        .setFeedback(feedback)
                        .build();
                
                feedbackList.add(request);
            }
            String result = StudentClientHelper.runClientStreamingCustomFeedback(feedbackList);
            outputArea.append("Custom feedback sent: \n" + result + "\n");
        });
        
        //************** SHOW THE WINDOW **************************
        jframe.setVisible(true);

    }

}


