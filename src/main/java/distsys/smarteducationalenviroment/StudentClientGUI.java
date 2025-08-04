package distsys.smarteducationalenviroment;

import generated.grpc.analyzer.CustomFeedbackReply;
import generated.grpc.analyzer.CustomFeedbackRequest;
import generated.grpc.analyzer.Student;
import generated.grpc.feedback.StudentTask;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
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

       
        /*
        GUI initialization
        studentLis: stores the registered students
        
        The layout splits into:
        top -> inputs
        center -> outputs
        bottom -> buttons
        
        */
        List<Student> studentList = new ArrayList<>();
        //main window (JFrame: Main application window)
        JFrame jframe = new JFrame("Smart Educational Enviroment");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(400, 350);
        jframe.setLayout(new BorderLayout());

        // **************TOP PANEL FOR INPUT****************
        JPanel inPanel = new JPanel(new GridLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //labels and inputs
        // collects the students name, age, gender, and task
        JTextField fName = new JTextField(20);
        JTextField fAge = new JTextField(5);

        String[] fGender = {"-Select gender-", "Male", "Female", "Other"};
        JComboBox<String> genderDd = new JComboBox<>(fGender);

        String[] tasks = {"-Select task to do-", "Washing Dishes", "Sweeping", "Mooping", "Laundry", "Cooking", "Ironing", "Make the bed"};
        JComboBox<String> taskDd = new JComboBox<>(tasks);

        //************** CENTER PANEL FOR OUTPUT *****************
        JButton addButton = new JButton("Register student");

        //************** CENTER PANEL FOR OUTPUT *****************
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane pScroll = new JScrollPane(outputArea);

        //************** BOTTOM PANEL BUTTONS*****************
        JButton subButton = new JButton("Submit Domestic Task");
        JButton submitAnalyzeButton = new JButton("Submit Analized Task");
        JButton feedbackButton = new JButton("Submit feedback Task");
        JButton performanceButton = new JButton("Submit task performance");
        JButton feedbackLiveButton = new JButton("Live feedback");

        JPanel pButton = new JPanel(); //default FlowLayout
        pButton.add(subButton);
        pButton.add(submitAnalyzeButton);
        pButton.add(feedbackButton);
        pButton.add(performanceButton);
        pButton.add(feedbackLiveButton);

        //***************** ADD PANELS TO FRAME ******************
        jframe.add(inPanel, BorderLayout.NORTH);
        jframe.add(pScroll, BorderLayout.CENTER);
        jframe.add(pButton, BorderLayout.SOUTH);

        //---NAME----------------------------------
        //-----------------------------------------
        gbc.gridx = 0;
        gbc.gridy = 0;
        inPanel.add(new JLabel("Student Name: "));
        gbc.gridx = 1;
        inPanel.add(fName, gbc);

        //---AGE-----------------------------------
        //-----------------------------------------
        gbc.gridx = 0;
        gbc.gridy = 1;
        inPanel.add(new JLabel("Student Age: "));
        gbc.gridx = 1;
        inPanel.add(fAge);

        //---GENDER--------------------------------
        //-----------------------------------------
        gbc.gridx = 0;
        gbc.gridy = 2;
        inPanel.add(new JLabel("Student Gender: "));
        gbc.gridx = 1;
        inPanel.add(genderDd);

        //---TASK----------------------------------
        //-----------------------------------------
        gbc.gridx = 0;
        gbc.gridy = 3;
        inPanel.add(new JLabel("Select Task to do: "));
        gbc.gridx = 1;
        inPanel.add(taskDd);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inPanel.add(addButton, gbc);
        gbc.gridx = 1;

        inPanel.add(new JLabel()); //empty cell

        //************** BUTTON LOGIC "UNARY RPC" **************
        
        //add student to 'student list'
        //sends the single student info to the DomesticActSimulatorServer
        subButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = fName.getText().trim();
                int age = Integer.parseInt(fAge.getText().trim());
                String gender = genderDd.getSelectedItem().toString();
                String taskName = taskDd.getSelectedItem().toString();

                //calling the gRPC helper method
                String result = StudentClientHelper.runUnaryDomesticTask(name, age, gender, taskName);

                outputArea.append("Submitting task...\n");
                outputArea.append("Student Name: " + name + "\n Age: " + age + "\n Gender: " + gender + "\n Task to do: " + taskName + "\n");
                outputArea.append("!!!! " + result + "\n");
            }
        });

        //e-> extends the 'ActionListener()'
        addButton.addActionListener(e -> {
            String name = fName.getText().trim();
            String ageStr = fAge.getText().trim();
                String gender = genderDd.getSelectedItem().toString();
            String taskName = taskDd.getSelectedItem().toString();

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
            //message will be displayed if the user doesn't select a gender.
            if (gender.equals("-Select gender-")) {
                    JOptionPane.showMessageDialog(null, "Please select a valid gender");
                    return;
                }
            //message will be displayed if the user doesn't select a gender.
                if (taskName.equals("-Select task to do-")) {
                    JOptionPane.showMessageDialog(null, "Please select a valid task");
                    return;
                }
                
                //Creating and sotre student
            Student student = Student.newBuilder()
                    .setStudentName(name)
                    .setStudentAge(age)
                    .setGender(gender)
                    .setTaskName(taskName)
                    .build();
            
            studentList.add(student); //student added to the list
            
            outputArea.append(" The student " + name+ "\n , age: " + age + "\n , gender: " + gender + "\n , task to do: " + taskName + " is registered succesfully!\n\n");
                
            fName.setText("");
            fAge.setText("");
            genderDd.setSelectedIndex(0);
            taskDd.setSelectedIndex(0);
            fName.requestFocus();
        });

        
        //*********************************************************
        //SERVER 2. Server Streaming RPC - Participation Analizer
        //*********************************************************
        //Send students in the list to the server and receives the participation statistics
        submitAnalyzeButton.addActionListener(e -> {
            

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

            String task = (String) taskDd.getSelectedItem();

            //Building student message
            Student student = Student.newBuilder()
                    .setStudentName(name)
                    .setStudentAge(age)
                    .setGender(gender)
                    .setTaskName(task)
                    .build();

            //calling 'helper' to run server-streaming RPC
            String feedback = StudentClientHelper.runServerStreamingParticipationAnalizer(studentList);
            outputArea.append("!!!Server replied:\n " + feedback + "\n");

        });

        //*********************************************************
        //SERVER 2. Client Streaming RPC - Participation Analizer
        //*********************************************************        
        //each students feedback is sent as a stream to the server
        feedbackButton.addActionListener(e -> {
            List<CustomFeedbackRequest> feedbackList = new ArrayList<>();

            int total = Integer.parseInt(JOptionPane.showInputDialog("How many feedbacks will you want to write?"));

            for (int i = 0; i < total; i++) {
                String name = JOptionPane.showInputDialog("Student name: ");
                String feedback = JOptionPane.showInputDialog("Write feedback of student or overall: ", "\n");

                CustomFeedbackRequest request = CustomFeedbackRequest.newBuilder()
                        .setStudentName(name)
                        .setFeedback(feedback)
                        .build();

                feedbackList.add(request);
            }
            String result = StudentClientHelper.runClientStreamingCustomFeedback(feedbackList);
            outputArea.append("Custom feedback sent: \n" + result + "\n");
        });

        //*********************************************************
        //SERVER 3. Client Streaming RPC - Gender Feedback
        //*********************************************************
        // simulate class task performance and receive aggregted feedback
        performanceButton.addActionListener(e -> {
            List<StudentTask> taskList = new ArrayList<>();

            int total = Integer.parseInt(JOptionPane.showInputDialog("How many students to enter task duration for?"));

            for (int i = 0; i < total; i++) {
                String name = JOptionPane.showInputDialog("Student name: ", "\n");
                String performancetasks = JOptionPane.showInputDialog("Task done: ", "\n");

                String timeStr = JOptionPane.showInputDialog("How many minutes did it take? ");
                //task duration from an int to a string
                int time;
                try {
                    time = Integer.parseInt(timeStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Age must be a number");
                    return;
                }

                //Building student message
                StudentTask studentTask = StudentTask.newBuilder()
                        .setStudentName(name)
                        .setStudentTask(performancetasks)
                        .setTaskDuration(time)
                        .build();

                taskList.add(studentTask);
            }
            String result = StudentClientHelper.runClientStreamingTaskPerformance(taskList);
            outputArea.append("Task performance result: \n" + result + "\n");
        });

        //*********************************************************
        //SERVER 3. Bi-directional RPC - Gender Feedback
        //*********************************************************
        //Sends one task at a time and receives feedback in real-time
        feedbackLiveButton.addActionListener(e -> {
            List<StudentTask> taskList = new ArrayList<>();

            int total = Integer.parseInt(JOptionPane.showInputDialog("How many students to enter live feedback for?"));

            for (int i = 0; i < total; i++) {
                String name = JOptionPane.showInputDialog("Student name: ");
                String performancetasks = JOptionPane.showInputDialog("Task done: ", "\n");

                String timeStr = JOptionPane.showInputDialog("How many minutes did it take? ");
                //task duration from an int to a string
                int time;
                try {
                    time = Integer.parseInt(timeStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Time duration must be a number");
                    return;
                }

                //Building student message
                StudentTask studentTask = StudentTask.newBuilder()
                        .setStudentName(name)
                        .setStudentTask(performancetasks)
                        .setTaskDuration(time)
                        .build();

                taskList.add(studentTask);
            }
            String result = StudentClientHelper.runBidirectionalFeedback(taskList);
            outputArea.append("!!!Live task feedback sent: \n" + result + "\n");
        });
        //************** SHOW THE WINDOW **************************
        jframe.setVisible(true); //display the full GUI window after all components are initialized

    }

}
