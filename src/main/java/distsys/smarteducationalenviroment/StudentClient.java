
package distsys.smarteducationalenviroment;
/*
import generated.grpc.domestic.DomesticActSimulatorGrpc;
import generated.grpc.domestic.StudentTask;
import generated.grpc.domestic.StudentTaskCompleted;
import generated.grpc.analyzer.ParticipationAnalizerGrpc;
import generated.grpc.analyzer.ParticipationEntry;
import generated.grpc.analyzer.ParticipationStatistics;
import generated.grpc.feedback.ClassInsight;
import generated.grpc.feedback.ClassRequest;
import generated.grpc.feedback.FeedbackResponse;
import generated.grpc.feedback.GenderAFeedbackGrpc;
import generated.grpc.feedback.StudentEvent;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.logging.Level;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.awt.HeadlessException;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

import javax.swing.JOptionPane;

/*@author Carolina*/
 
/*
public class StudentClient {
    
    private static final Logger logger = Logger.getLogger(StudentClient.class.getName());

    //Unary rpc StrudentClient
     public static void main(String[] args) throws Exception {
        String host = "localhost";
        
        
        //Channel 1. Port 50051 - Unary Server. Domestic Activity Simulator
        int port1 = 50051;
        ManagedChannel channel1 = ManagedChannelBuilder
                .forAddress(host, port1)
                .usePlaintext()
                .build();
        
       runUnaryDomesticTask(channel1);
        
        
        channel1.shutdownNow().awaitTermination(5,TimeUnit.SECONDS);
        
        //Channel 2. Port 50052 - Client Streaming. Participation Analizer
        int port2 = 50052;
        ManagedChannel channel2 = ManagedChannelBuilder
                .forAddress(host, port2)
                .usePlaintext()
                .build();
        runClientStreamingParticipationAnalizer(channel2);
        channel2.shutdownNow().awaitTermination(5,TimeUnit.SECONDS);
        
        //Channel 3. Port 50053 - Server Streaming & Bi-directional Streaming server. Gender A. Feedback
        int port3 = 50053;
        ManagedChannel channel3 = ManagedChannelBuilder
                .forAddress(host, port3)
                .usePlaintext()
                .build();
        runServerStreamingInsight(channel3);
        runBidirectionalFeedback(channel3);
        channel3.shutdownNow().awaitTermination(5,TimeUnit.SECONDS);
     }
        
        //SERVICE 1. UNARY - Domestic Activity Simulator
     private static void runUnaryDomesticTask(ManagedChannel channel) throws InterruptedException{
        DomesticActSimulatorGrpc.DomesticActSimulatorBlockingStub blockingStub = DomesticActSimulatorGrpc.newBlockingStub(channel);
        try {
            String name = JOptionPane.showInputDialog("Please enter name: ");
            String ageString = JOptionPane.showInputDialog("Enter age: ");
            int age = Integer.parseInt(ageString); //convert 'ageString' to an integer.
            
            String [] tasks = {"Washing Dishes","Sweeping", "Mooping", "Laundry", "Cooking", "Ironing","Make the bed"};
            String taskName = (String)JOptionPane.showInputDialog(null,
                    "Select Task To-Do: ",
                    "Household Task",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tasks,
                    tasks[0]);
            
            String durationString = JOptionPane.showInputDialog("Enter duration of the task (minutes): ");
            double duration = Double.parseDouble(durationString); //convert 'durationString' to double
            
            // Building the request of the messege for student
            StudentTask request = StudentTask.newBuilder()
                    .setStudentName(name)
                    .setStudentAge(age)
                    .setTaskName(taskName)
                    .setTaskDuration(duration)
                    .build();
            
            //send request to server
            StudentTaskCompleted response = blockingStub.startTask(request);
            
            //Show response
            JOptionPane.showMessageDialog(null, "Server message:\n"
            + response.getMessage() + "\nRegistered in: "
            + response.getTaskTime());

            // logger.info("Greeting: " + response.getMessage());
        
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"ERROR: Input invalid number.");
        } finally {
            //shutdown channel
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
     
    
    }


//Service 2. Client Streaming RPC - Participation Analizer
private static void runClientStreamingParticipationAnalizer(ManagedChannel channel){
    ParticipationAnalizerGrpc.ParticipationAnalizerStub asyncStub = ParticipationAnalizerGrpc.newStub(channel);
    
    StreamObserver<ParticipationEntry> requestObserver = asyncStub.trackerParticipation(new StreamObserver<ParticipationStatistics>(){
        @Override
        public void onNext(ParticipationStatistics statistics){
            JOptionPane.showMessageDialog(null, "Participation Summary: "
            + "\n Percentage Male: " + statistics.getMalePercentage()
            + "\n Percentage Female: " + statistics.getFemalePercentage()
            + "\n Summary: " + statistics.getSummary());
        }
        
        @Override
        public void onError(Throwable t){
            JOptionPane.showMessageDialog(null, "Error: " + t.getMessage());
        }
        
        @Override
        public void onCompleted(){
            JOptionPane.showMessageDialog(null, "Participation analysis completed");
        }
    });
    try{
        int entryCount = Integer.parseInt(JOptionPane.showInputDialog("How many students participated?"));
        for(int i =0; i < entryCount; i++){
            String name = JOptionPane.showInputDialog("Student name: ");
            
            
            String [] genders = {"Male","Female", "Other"};
            String gender = (String)JOptionPane.showInputDialog(null,
                    "Gender Male/Female/Other:  ",
                    "Household Task",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    genders,
                    genders[0]);
            
            String [] tasks = {"Washing Dishes","Sweeping", "Mooping", "Laundry", "Cooking", "Ironing","Make the bed"};
            String taskName = (String)JOptionPane.showInputDialog(null,
                    "Select Task performed: ",
                    "Household Task",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tasks,
                    tasks[0]);
            String durationString = JOptionPane.showInputDialog("Duration (minutes): ");
            double taskDuration = Double.parseDouble(durationString);
            
            ParticipationEntry entry = ParticipationEntry.newBuilder()
                    .setStudentName(name)
                    .setGender(gender)
                    .setTaskName(taskName)
                    .setTaskDuration(taskDuration)
                    .setSessionID(1)
                    .build();
            requestObserver.onNext(entry);
        
        }
        requestObserver.onCompleted();
    }catch (HeadlessException | NumberFormatException e){
        requestObserver.onError(e);
    }
}
    //SERVER 3. Server Streaming RPC -Gender Analizer Feedback
    private static void runServerStreamingInsight(ManagedChannel channel){
        GenderAFeedbackGrpc.GenderAFeedbackBlockingStub blockingStub = GenderAFeedbackGrpc.newBlockingStub(channel);
        
        String className = JOptionPane.showInputDialog("Enter class name for feedback: ");
        ClassRequest request = ClassRequest.newBuilder().setClassName(className).build();
        
        Iterator<ClassInsight> insights =blockingStub.getClassInsight(request);
        StringBuilder feedback = new StringBuilder("Class feedback: \n");
        insights.forEachRemaining(insight -> feedback.append("- ").append(insight.getMessage()).append("\n"));
        
        JOptionPane.showMessageDialog(null,feedback.toString());
    }
    
    //SERVER 3. Bi-Directional Streaming RPC -Gender Analizer Feedback
    private static void runBidirectionalFeedback(ManagedChannel channel) throws InterruptedException {
        GenderAFeedbackGrpc.GenderAFeedbackStub asyncStub = GenderAFeedbackGrpc.newStub(channel);
        CountDownLatch latch = new CountDownLatch(1);
        
        StreamObserver<StudentEvent> requestObserver = asyncStub.liveFeedbackExchange(new StreamObserver<FeedbackResponse>(){
            @Override
            public void onNext(FeedbackResponse value){
                JOptionPane.showMessageDialog(null,"Observations: " + value.getFeedback());
            }
            @Override
            public void onError(Throwable t){
                JOptionPane.showMessageDialog(null, "Error: " + t.getMessage());
                latch.countDown();
            }
            @Override
            public void onCompleted(){
                JOptionPane.showMessageDialog(null, "Observations IRL session ended.");
                latch.countDown();
            }
        });
        
        int eventCount = Integer.parseInt(JOptionPane.showInputDialog("How many events to simulate?"));
        for (int i = 0; i < eventCount; i++){
            String name = JOptionPane.showInputDialog("Student name: ");
            
            String [] tasks = {"Washing Dishes","Sweeping", "Mooping", "Laundry", "Cooking", "Ironing","Make the bed"};
            String taskName = (String)JOptionPane.showInputDialog(null,
                    "Name of task done: ",
                    "Household Task",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tasks,
                    tasks[0]);
            
            
            double duration = Double.parseDouble(JOptionPane.showInputDialog("Task duration (in minutes): "));
            
            StudentEvent event = StudentEvent.newBuilder()
                    .setStudentName(name)
                    .setTaskName(taskName)
                    .setTaskDuration(duration)
                    .build();
            
            requestObserver.onNext(event);
            
        }
        
        requestObserver.onCompleted();
        latch.await(3,TimeUnit.SECONDS);
    }
}
*/