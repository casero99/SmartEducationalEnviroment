package distsys.smarteducationalenviroment;

import generated.grpc.analyzer.CustomFeedbackReply;
import generated.grpc.analyzer.CustomFeedbackRequest;
import generated.grpc.domestic.DomesticActSimulatorGrpc;
import generated.grpc.domestic.RegisterStudentsRequest;
import generated.grpc.domestic.ResisterStudentsReply;
import generated.grpc.domestic.Student;
import generated.grpc.analyzer.ParticipationAnalizerGrpc;
import generated.grpc.analyzer.ParticipationRequest;
import generated.grpc.analyzer.ParticipationStatistics;
import generated.grpc.feedback.TaskFeedbackSummary;
import generated.grpc.feedback.StudentTask;
import generated.grpc.feedback.TaskFeedback;
import generated.grpc.feedback.GenderAFeedbackGrpc;
import generated.grpc.feedback.StudentTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.logging.Level;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.awt.HeadlessException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import javax.swing.JOptionPane;

/**
 *
 * @author Carolina
 */
public class StudentClientERROR {
    





    private static final Logger logger = Logger.getLogger(StudentClientERROR.class.getName());

    //*********************************************************
    // Channels
    //*********************************************************
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        
        //discovering the services via jmDNS
        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
        
        ServiceInfo info1 = jmdns.getServiceInfo("grpc.tcp.local.", "DomesticService");
        ServiceInfo info2 = jmdns.getServiceInfo("grpc.tcp.local.", "ParticipationAnalizer");
        ServiceInfo info3 = jmdns.getServiceInfo("grpc.tcp.local.", "GenderFeedback");
        
        if (info1 == null || info2 == null || info3 == null){
            System.out.println("***********One or more services could not be found via jmDNS");
            return;
        }
        
        String host1 = info1.getInetAddresses()[0].getHostAddress();
        int port1 = info1.getPort();
        
        String host2 = info2.getInetAddresses()[0].getHostAddress();
        int port2 = info2.getPort();
        
        String host3 = info3.getInetAddresses()[0].getHostAddress();
        int port3 = info3.getPort();
        
        //**********************************************
        //Channel 1. Port 50051 - Unary Server. Domestic Activity Simulator
        //port1 = 50051;
       // ManagedChannel channel1 = ManagedChannelBuilder
         //       .forAddress(host1, port1)
         //       .usePlaintext()
         //       .build();

        //runUnaryDomesticTask(channel1);

        //channel1.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);

        //**********************************************
        //Channel 2. Port 50052 - Client & Server Streaming. Participation Analizer
        //port2 = 50052;
        ManagedChannel channel2 = ManagedChannelBuilder
                .forAddress(host2, port2)
                .usePlaintext()
                .build();
        runServerStreamingParticipationAnalizer(channel2);
        runClientStreamingCustomFeedback(channel2);
        channel2.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);

        //**********************************************
        //Channel 3. Port 50053 - Client Streaming & Bi-directional Streaming server. Gender A. Feedback
        //port3 = 50053;
        ManagedChannel channel3 = ManagedChannelBuilder
                .forAddress(host3, port3)
                .usePlaintext()
                .build();
        runClientStreamingTaskPerformance(channel3);
        runBidirectionalFeedback(channel3);
        channel3.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }

    //*********************************************************
    //SERVICE 1. UNARY - Domestic Activity Simulator
    //*********************************************************
    private static void runUnaryDomesticTask(String name, int age, String gender, String taskName) {
        //connect to the unary RPC server
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        DomesticActSimulatorGrpc.DomesticActSimulatorBlockingStub blockingStub = DomesticActSimulatorGrpc.newBlockingStub(channel);
        try {
            Student student = Student.newBuilder()
            .setStudentName(name)
                    .setStudentAge(age)
                    .setGender(gender)
                    .setTaskName(taskName)
                    .build();
            
            
            // Building the request of the messege for student
            RegisterStudentsRequest request = RegisterStudentsRequest.newBuilder()
                    .addStudents(student)
                    .build();

            //send request to server
            ResisterStudentsReply response = blockingStub.registerStudents(request);
            
            //Show response
            JOptionPane.showMessageDialog(null, "Server message:\n"
                    + response.getMessage() + "\nRegistered in: ");

            // logger.info("Greeting: " + response.getMessage());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ERROR: Input invalid number.");
        } finally {
            try{
            //shutdown channel
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    //*********************************************************
    //SERVER 2. Server Streaming RPC - Participation Analizer
    //*********************************************************
    private static void runServerStreamingParticipationAnalizer(ManagedChannel channel) {
        ParticipationAnalizerGrpc.ParticipationAnalizerBlockingStub blockingStub = ParticipationAnalizerGrpc.newBlockingStub(channel);

        //collecting students for analisis
        List<generated.grpc.analyzer.Student> students = new ArrayList<>();
        int number = Integer.parseInt(JOptionPane.showInputDialog("How many students are to analize?"));

        for (int i = 0; i < number; i++) {
            String name = JOptionPane.showInputDialog("Student name: ");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Student age: "));
            String gender = JOptionPane.showInputDialog("Gender (Male/Female/Other): ");
            String[] tasks = {"Washing Dishes", "Sweeping", "Mooping", "Laundry", "Cooking", "Ironing", "Make the bed"};
            String taskName = (String) JOptionPane.showInputDialog(null,
                    "Select Task to-do: ",
                    "Household Task",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tasks,
                    tasks[0]);

            generated.grpc.analyzer.Student student = generated.grpc.analyzer.Student.newBuilder()
                    .setStudentName(name)
                    .setStudentAge(age)
                    .setGender(gender)
                    .setTaskName(taskName)
                    .build();

            students.add(student);
        }

        //building the request
        ParticipationRequest request = ParticipationRequest.newBuilder()
                .addAllStudents(students)
                .build();

        //receiving data for ParticipationStatistics
        Iterator<ParticipationStatistics> pStats = blockingStub.analyzerParticipation(request);
        StringBuilder feedback = new StringBuilder("Statistics received: \n");

        pStats.forEachRemaining(percentage -> {
            feedback.append(" MALE %: ").append(percentage.getMalePercentage())
                    .append(", FEMALE %: ").append(percentage.getFemalePercentage())
                    .append("\n").append(percentage.getSummary()).append("\n");
        });

        JOptionPane.showMessageDialog(null, feedback.toString());
    }

    //*********************************************************
    //SERVER 2. Client Streaming RPC - Participation Analizer     
    //*********************************************************
    private static void runClientStreamingCustomFeedback(ManagedChannel channel) throws InterruptedException {
        ParticipationAnalizerGrpc.ParticipationAnalizerStub asyncStub = ParticipationAnalizerGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<CustomFeedbackRequest> requestObserver = asyncStub.submitCustomFeedback(new StreamObserver<CustomFeedbackReply>() {

            @Override
            public void onNext(CustomFeedbackReply value) {
                JOptionPane.showMessageDialog(null, "Server response: " + value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                JOptionPane.showMessageDialog(null, "Error sending feedback: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                JOptionPane.showMessageDialog(null, "Participation analysis completed and submitted");
                latch.countDown();
            }
        });
        try {
            int entryCount = Integer.parseInt(JOptionPane.showInputDialog("How many feedbacks are you submitting?"));

            for (int i = 0; i < entryCount; i++) {
                String name = JOptionPane.showInputDialog("Student name: \n");
                String feedback = JOptionPane.showInputDialog("Feedback: \n");

                CustomFeedbackRequest feedbackMsg = CustomFeedbackRequest.newBuilder()
                        .setStudentName(name)
                        .setFeedback(feedback)
                        .build();
                requestObserver.onNext(feedbackMsg); //send feedback
}
                requestObserver.onCompleted();    //closes the stream after sending all the messages
                latch.await(3, TimeUnit.SECONDS); //waits for server to respond

            
        // catch (HeadlessException | NumberFormatException e) {
           // requestObserver.onError(e);
           // return;// prevents further onNext() calls after onError 
        }catch (Exception e){
    requestObserver.onError(e);
        }
    }
    //*********************************************************
    //SERVER 3. Client Streaming RPC -Gender Analizer Feedback
    //*********************************************************

    private static void runClientStreamingTaskPerformance(ManagedChannel channel) {
        GenderAFeedbackGrpc.GenderAFeedbackStub asyncStub = GenderAFeedbackGrpc.newStub(channel);

        StreamObserver<StudentTask> requestObserver = asyncStub.taskPerformance(new StreamObserver<TaskFeedbackSummary>() {
            @Override
            public void onNext(TaskFeedbackSummary summary) {
                JOptionPane.showMessageDialog(null,
                        "Summary of the task done by the class: \n"
                        + "Total tasks: " + summary.getTotalTask()
                        + "\nAverage Time: " + summary.getTotalTime()
                        + "\nFeedback Summary: \n" + summary.getSummary());
            }

            @Override
            public void onError(Throwable t) {
                JOptionPane.showMessageDialog(null, "Error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                JOptionPane.showMessageDialog(null, "Task performance analysis completed");
            }
        });

        try {
            int numStudents = Integer.parseInt(JOptionPane.showInputDialog("How many students are to analyze?"));

            for (int i = 0; i < numStudents; i++) {
                String name = JOptionPane.showInputDialog("Enter name of student: ");
                String[] tasks = {"Washing Dishes", "Sweeping", "Mooping", "Laundry", "Cooking", "Ironing", "Make the bed"};
                String taskName = (String) JOptionPane.showInputDialog(null,
                        "Select Task to-do: ",
                        "Household Task",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        tasks,
                        tasks[0]);
                int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter duration (minutes): "));

                StudentTask task = StudentTask.newBuilder()
                        .setStudentName(name)
                        .setStudentTask(taskName)
                        .setTaskDuration(duration)
                        .build();
                requestObserver.onNext(task);
            }
            requestObserver.onCompleted();
        } catch (Exception e) {
            requestObserver.onError(e);
        }
    }

    //*********************************************************
    //SERVER 3. Bi-Directional Streaming RPC -Gender Analizer Feedback
    //*********************************************************
    private static void runBidirectionalFeedback(ManagedChannel channel) throws InterruptedException {
        GenderAFeedbackGrpc.GenderAFeedbackStub asyncStub = GenderAFeedbackGrpc.newStub(channel);
        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<StudentTask> requestObserver = asyncStub.liveTaskFeedback(new StreamObserver<TaskFeedback>() {
            @Override
            public void onNext(TaskFeedback value) {
                JOptionPane.showMessageDialog(null, "Observations: " + value.getFeedback()+ "\n");
            }

            @Override
            public void onError(Throwable t) {
                JOptionPane.showMessageDialog(null, "Error: " + t.getMessage());
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                JOptionPane.showMessageDialog(null, "Observations IRL session ended.");
                latch.countDown();
            }
        });

        int eventCount = Integer.parseInt(JOptionPane.showInputDialog("How many events to simulate?"));
        for (int i = 0; i < eventCount; i++) {
            String name = JOptionPane.showInputDialog("Student name: ");

            String[] tasks = {"Washing Dishes", "Sweeping", "Mooping", "Laundry", "Cooking", "Ironing", "Make the bed"};
            String taskName = (String) JOptionPane.showInputDialog(null,
                    "Name of task done: ",
                    "Household Task",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tasks,
                    tasks[0]);
            
            //if user input a string instead of an integer
            int duration = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    String durationInput = JOptionPane.showInputDialog("Task duration (in minutes): ");
                    duration = Integer.parseInt(durationInput.trim());
                    validInput = true;
                    
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number (ex. 5)");
                }
            }

                StudentTask event = StudentTask.newBuilder()
                        .setStudentName(name)
                        .setStudentTask(taskName)
                        .setTaskDuration(duration)
                        .build();

                requestObserver.onNext(event);

            }

            requestObserver.onCompleted();
            latch.await(3, TimeUnit.SECONDS);
        }
    
}


