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

public class DomesticActSimulatorServer extends DomesticActSimulatorImplBase {

    private static final Logger logger = Logger.getLogger(DomesticActSimulatorServer.class.getName());

    List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
            The purpose of jmDNS is for the server to announce itself on the network so the client
         can find it without knowing the IP or the Port.
        
        */
        
        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
        //Service - jmDNS Type - Service Name - Port
        ServiceInfo serviceInfo = ServiceInfo.create("grpc.tcp.local.", "DomesticService", 50051, "gRPC service for Domestic Activities Simulator");
        jmdns.registerService(serviceInfo);
        System.out.println("**********************************");
        System.out.println("**************jmDNS: Domestic Service Registered");
        System.out.println("**********************************");
        
        int port = 50051;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(new DomesticActSimulatorServer())
                    .build()
                    .start();
            System.out.println("**********************************");
            logger.log(Level.INFO, "DomesticActSimulator started, listening on {0}", port);
            System.out.println("************ Server started, listening on " + port);
            System.out.println("**********************************");
            server.awaitTermination();

        } catch (IOException e) {
            e.printStackTrace(); // TODO Auto-generated catch block
        } catch (InterruptedException e) {
            e.printStackTrace();// TODO Auto-generated catch block
        }
    }
    @Override
    public void registerStudents(RegisterStudentsRequest request, StreamObserver<ResisterStudentsReply> responseObserver) {

        System.out.println("Inside registerStudents");

        List<Student> students = new ArrayList<>();

        StringBuilder result = new StringBuilder();

        for (Student student : request.getStudentsList()) {
            String studentName = student.getStudentName();
            int studentAge = student.getStudentAge();
            String gender = student.getGender();
            String taskName = student.getTaskName();

            System.out.println("Name: " + studentName);
            System.out.println("Age: " + studentAge);
            System.out.println("Gender: " + gender);
            System.out.println("Task to do: " + taskName);

            if (!studentName.isEmpty() && studentAge != 0 && !taskName.isEmpty()) {
                students.add(student); //adding student info to the list

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

        String summary = "Class created sucesfully! " + students.size() + " students were added to the list.\n" + result.toString();

        ResisterStudentsReply reply = ResisterStudentsReply.newBuilder()
                .setMessage(summary)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}

