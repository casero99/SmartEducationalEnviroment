
package distsys.smarteducationalenviroment;

import generated.grpc.domestic.DomesticActSimulatorGrpc;
import generated.grpc.domestic.StudentTask;
import generated.grpc.domestic.StudentTaskCompleted;
import generated.grpc.analyzer.ParticipationAnalizerGrpc;
import generated.grpc.analyzer.ParticipationEntry;
import generated.grpc.analyzer.ParticipationStatistics;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.logging.Level;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.CountDownLatch;

import javax.swing.JOptionPane;

/*@author Carolina*/

public class StudentClient {
    
    private static final Logger logger = Logger.getLogger(StudentClient.class.getName());

    //Unary rpc StrudentClient
     public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 50051;
        
        //creating channel
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();
        
        runUnaryDomesticTask(channel);
        runClientStreamingParticipationAnalizer(channel);
        runServerStreamingInsight(channel);
        runBidirectionalGenderAFeedback(channel);
        
        channel.shutdownNow().awaitTermination(5,TimeUnit.SECONDS);
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
    ParticipationAnalizerGrpc.ParticipationAnalizerBlockingStub asyncStub = ParticipationAnalizerGrpc.newBlockingStub(channel);
    
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
            String gender = JOptionPane.showInputDialog("Gender Male/Female/Other: ");
            String task = JOptionPane.showInputDialog("Task performed: ");
            String durationString = JOptionPane.showInputDialog("Duration (minutes): ");
            double taskDuration = Double.parseDouble(durationString);
            
            ParticipationEntry entry = ParticipationEntry.newBuilder()
                    .setStudentName(name)
                    .setGender(gender)
                    .setTaskName(task)
                    .setTaskDuration(taskDuration)
                    .setSessionID(1)
                    .build();
            requestObserver.onNext(entry);
        
        }
        requestObserver.onCompleted();
    }catch (Exception e){
        requestObserver.onError(e);
    }
            
}
}