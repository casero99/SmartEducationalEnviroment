
package distsys.smarteducationalenviroment;
/*
import generated.grpc.domestic.DomesticActSimulatorGrpc.DomesticActSimulatorImplBase;
import generated.grpc.domestic.StudentTask;
import generated.grpc.domestic.StudentTaskCompleted;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/* @author Carolina*/
/*
public class DomesticActSimulatorServer extends DomesticActSimulatorImplBase{
    private static final Logger logger = Logger.getLogger(DomesticActSimulatorServer.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        


        int port = 50051;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(new DomesticActSimulatorServer())
                    .build()
                    .start();
            logger.log(Level.INFO, "DomesticActSimulator started, listening on {0}", port);
            System.out.println(" Server started, listening on" + port);
            server.awaitTermination();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

}

    public void registerStudents(RegisterStudentsRequest request, StreamObserver<ResisterStudentsReply> responseObserver) {

        List <Student> studentList request.getStudentList;
        System.out.println("Inside registerStudents");

        String studentName = request.getStudentName();
        int studentAge = request.getStudentAge();
        String gender = request.getGender();
        String taskName = request.getTaskName();
        
        System.out.println("Student name: " + studentName);
        System.out.println("Student age: " + studentAge);
        
        StudentTaskCompleted.Builder response = StudentTaskCompleted.newBuilder();
        if(!studentName.isEmpty() && studentAge != 0 && !taskName.isEmpty() && taskDuration != 0){
            response.setTaskTime(0).setMessage("The student " + studentName + " , age:  " + studentAge +
                    " , is doing : " + taskName + " ,with a time of: " + taskDuration + " was added succesfully!");
            
        }else{
            response.setTaskTime(100).setMessage("Enter student name again");
        }
        
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
*/