package distsys.smarteducationalenviroment;

import generated.grpc.analyzer.CustomFeedbackReply;
import generated.grpc.analyzer.CustomFeedbackRequest;
import generated.grpc.domestic.DomesticActSimulatorGrpc;
import generated.grpc.domestic.RegisterStudentsRequest;
import generated.grpc.domestic.ResisterStudentsReply;
import generated.grpc.analyzer.ParticipationAnalizerGrpc;
import generated.grpc.analyzer.ParticipationRequest;
import generated.grpc.analyzer.ParticipationStatistics;
import generated.grpc.analyzer.Student;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.swing.JOptionPane;

/*@author Carolina*/
public class StudentClient {

    private static final Logger logger = Logger.getLogger(StudentClient.class.getName());

    //Unary rpc StudentClient
    public static void main(String[] args) throws Exception {
        String host = "localhost";

        //Channel 1. Port 50051 - Unary Server. Domestic Activity Simulator
        int port1 = 50051;
        ManagedChannel channel1 = ManagedChannelBuilder
                .forAddress(host, port1)
                .usePlaintext()
                .build();

        runUnaryDomesticTask(channel1);

        channel1.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);

        //Channel 2. Port 50052 - Client Streaming. Participation Analizer
        int port2 = 50052;
        ManagedChannel channel2 = ManagedChannelBuilder
                .forAddress(host, port2)
                .usePlaintext()
                .build();
        runServerStreamingParticipationAnalizer(channel2);
        runClientStreamingCustomFeedback(channel2);
        channel2.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);

        //Channel 3. Port 50053 - Server Streaming & Bi-directional Streaming server. Gender A. Feedback
        int port3 = 50053;
        ManagedChannel channel3 = ManagedChannelBuilder
                .forAddress(host, port3)
                .usePlaintext()
                .build();
        runClientStreamingTaskPerformance(channel3);
        runBidirectionalFeedback(channel3);
        channel3.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }

    //SERVICE 1. UNARY - Domestic Activity Simulator
    private static void runUnaryDomesticTask(ManagedChannel channel) throws InterruptedException {
        DomesticActSimulatorGrpc.DomesticActSimulatorBlockingStub blockingStub = DomesticActSimulatorGrpc.newBlockingStub(channel);
        try {
            String name = JOptionPane.showInputDialog("Please enter name: ");
            String ageString = JOptionPane.showInputDialog("Enter age: ");
            int age = Integer.parseInt(ageString); //convert 'ageString' to an integer.

            String[] genders = {"Male", "Female", "Other"};
            String gender = (String) JOptionPane.showInputDialog(null,
                    "Select gender: ",
                    "Student gender",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    genders,
                    genders[0]);

            String[] tasks = {"Washing Dishes", "Sweeping", "Mooping", "Laundry", "Cooking", "Ironing", "Make the bed"};
            String taskName = (String) JOptionPane.showInputDialog(null,
                    "Select Task to-do: ",
                    "Household Task",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tasks,
                    tasks[0]);

            String durationStng = JOptionPane.showInputDialog("Enter duration of the task (minutes): ");
            double duration = Double.parseDouble(durationStng);

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
            //shutdown channel
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }

    }

//SERVER 2. Server Streaming RPC - Participation Analizer
    private static void runServerStreamingParticipationAnalizer(ManagedChannel channel) {
        ParticipationAnalizerGrpc.ParticipationAnalizerBlockingStub blockingStub = ParticipationAnalizerGrpc.newBlockingStub(channel);

        //collecting students for analisis
        List<generated.grpc.analyzer.Student> students = new ArrayList<>();
        int number = Integer.parseInt(JOptionPane.showInputDialog("How many students are to analize?"));

        for (int i = 0; i < number; i++) {
            String name = JOptionPane.showInputDialog("Student name: ");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Student age: "));
            String gender = JOptionPane.showInputDialog("Gender (Male/Female/Other): ");
            String task = JOptionPane.showInputDialog("Task to-do: ");

            generated.grpc.analyzer.Student student = generated.grpc.analyzer.Student.newBuilder()
                    .setStudentName(name)
                    .setStudentAge(age)
                    .setGender(gender)
                    .setTaskName(task)
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

//SERVER 2. Client Streaming RPC - Participation Analizer     
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
            int entryCount = Integer.parseInt(JOptionPane.showInputDialog("How many feedback submitted?"));

            for (int i = 0; i < entryCount; i++) {
                String name = JOptionPane.showInputDialog("Student name: ");
                String feedback = JOptionPane.showInputDialog("Feedback: \n");

                CustomFeedbackRequest feedbackMsg = CustomFeedbackRequest.newBuilder()
                        .setStudentName(name)
                        .setFeedback(feedback)
                        .build();
                requestObserver.onNext(feedbackMsg);

                requestObserver.onCompleted();
                latch.await(3, TimeUnit.SECONDS);

            }
            requestObserver.onCompleted();
        } catch (HeadlessException | NumberFormatException e) {
            requestObserver.onError(e);
        }
    }

    //SERVER 3. Server Streaming RPC -Gender Analizer Feedback
    private static void runClientStreamingTaskPerformance(ManagedChannel channel) {
        GenderAFeedbackGrpc.GenderAFeedbackBlockingStub blockingStub = GenderAFeedbackGrpc.newBlockingStub(channel);

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
                        .setStudentTask(name)
                        .setTaskDuration(duration)
                        .build();
                requestObserver.onNext(task);
            }
            requestObserver.onCompleted();
        } catch (Exception e) {
            requestObserver.onError(e);
        }
    }

//SERVER 3. Bi-Directional Streaming RPC -Gender Analizer Feedback
    private static void runBidirectionalFeedback(ManagedChannel channel) throws InterruptedException {
        GenderAFeedbackGrpc.GenderAFeedbackStub asyncStub = GenderAFeedbackGrpc.newStub(channel);
        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<StudentTask> requestObserver = asyncStub.liveTaskFeedback(new StreamObserver<TaskFeedback>() {
            @Override
            public void onNext(TaskFeedback value) {
                JOptionPane.showMessageDialog(null, "Observations: " + value.getFeedback());
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

            double duration = Double.parseDouble(JOptionPane.showInputDialog("Task duration (in minutes): "));

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
