
package distsys.smarteducationalenviroment;

import generated.grpc.domestic.DomesticActSimulatorGrpc;
import generated.grpc.domestic.StudentTask;
import generated.grpc.domestic.StudentTaskCompleted;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.logging.Logger;
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
        
        //stub
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
}
