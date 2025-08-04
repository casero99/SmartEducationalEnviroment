package distsys.smarteducationalenviroment;

import generated.grpc.domestic.Student;
import generated.grpc.domestic.DomesticActSimulatorGrpc.DomesticActSimulatorImplBase;
import generated.grpc.domestic.RegisterStudentsRequest;
import generated.grpc.domestic.ResisterStudentsReply;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

/* @author Carolina*/

//Importing requiered gRPC, logging, networking and service libraries
public class DomesticActSimulatorServer extends DomesticActSimulatorImplBase {

    //logger to print server logs to the console
    private static final Logger logger = Logger.getLogger(DomesticActSimulatorServer.class.getName());

    //list to store the registered students temporarily
    List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
            The purpose of jmDNS is for the server to announce itself on the network so the client
         can find it without knowing the IP or the Port.
        
        */
        
        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
        //Service - Service Name - Port - Description
        ServiceInfo serviceInfo = ServiceInfo.create("grpc.tcp.local.", "DomesticService", 50051, "gRPC service for Domestic Activities Simulator");
        jmdns.registerService(serviceInfo);
        System.out.println("**********************************");
        System.out.println("**************jmDNS: Domestic Service Registered");
        System.out.println("**********************************");
        
        int port = 50051; //the port were service will run

        try {
            //start the gRPC server and attach the DomesticActSimulator server
            Server server = ServerBuilder.forPort(port)
                    .addService(new DomesticActSimulatorServer()) //atach the server
                    .build()
                    .start();
            //printing server status
            System.out.println("**********************************");
            logger.log(Level.INFO, "DomesticActSimulator started, listening on {0}", port);
            System.out.println("************ Server started, listening on " + port);
            System.out.println("**********************************");
            server.awaitTermination(); //keeping the server running
            

        } catch (IOException e) {
            e.printStackTrace(); // TODO Auto-generated catch block
        } catch (InterruptedException e) {
            e.printStackTrace();// TODO Auto-generated catch block
        }
    }
    
    /*
    UNARY RPC implementation ---  This method is called by the client
    to register a list of students and assing them task.
    
    */
    @Override
    public void registerStudents(RegisterStudentsRequest request, StreamObserver<ResisterStudentsReply> responseObserver) {

        System.out.println("Inside registerStudents");

        List<Student> students = new ArrayList<>();

        StringBuilder result = new StringBuilder();

        //loop through each student send in the request
        for (Student student : request.getStudentsList()) {
            String studentName = student.getStudentName();
            int studentAge = student.getStudentAge();
            String gender = student.getGender();
            String taskName = student.getTaskName();

            //printing the data to console
            System.out.println("Name: " + studentName);
            System.out.println("Age: " + studentAge);
            System.out.println("Gender: " + gender);
            System.out.println("Task to do: " + taskName);

            //only add the data if the student is complete
            if (!studentName.isEmpty() && studentAge != 0 && !taskName.isEmpty()) {
                students.add(student); //adding student info to the list

                //build the result string for feedback
                result.append("The student ")
                        .append(studentName)
                        .append(" (").append(gender).append(", ")
                        .append(studentAge).append(" y/o)")
                        .append(" is assigned to: ").append(taskName)
                        .append("\n");

            } else {
                result.append("Missing data from studen registry.");
            }
        }

        //summary message with all students and tasks
        String summary = "Class created sucesfully! " + students.size() + " students were added to the list.\n" + result.toString();

        //build and send the response back to the client
        ResisterStudentsReply reply = ResisterStudentsReply.newBuilder()
                .setMessage(summary)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}

