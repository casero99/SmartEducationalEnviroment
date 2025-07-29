
package distsys.smarteducationalenviroment;

import generated.grpc.domestic.DomesticActSimulatorGrpc;
import generated.grpc.domestic.RegisterStudentsRequest;
import generated.grpc.domestic.ResisterStudentsReply;
import generated.grpc.domestic.Student;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import java.net.InetAddress;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

/* @author Carolina*/

/*
    This class basically is a 'helper class' 
    that contains the gRPC connection logic.

*/
public class StudentClientHelper {
    
    public static String runUnaryDomesticTask(String name, int age, String gender, String task){
        
        try{
            //discover the domestic via jmDns
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            ServiceInfo serviceInfo = jmdns.getServiceInfo("grpc.tcp.local.", "DomesticService", 50051);
        
            if(serviceInfo == null){
                return "****************Could not find DomesticService via jmDNS";
            }
            
            String host1 = serviceInfo.getInetAddresses()[0].getHostAddress();
            int port1 = serviceInfo.getPort();
            
            //Creating gRPC channel and stub
            
            ManagedChannel channel = ManagedChannelBuilder.forAddress(host1, port1)
                    .usePlaintext()
                    .build();
            
            DomesticActSimulatorGrpc.DomesticActSimulatorBlockingStub stub = 
                    DomesticActSimulatorGrpc.newBlockingStub(channel);
            
            //create request --- get reply
            Student student = Student.newBuilder()
                    .setStudentName(name)
                    .setStudentAge(age)
                    .setGender(gender)
                    .setTaskName(task)
                    .build();
            
             RegisterStudentsRequest request = RegisterStudentsRequest.newBuilder()
                     .addStudents(student)
                     .build();
        
            ResisterStudentsReply reply = stub.registerStudents(request);
            
            channel.shutdown();
            
            return "Feedback: "+ reply.getMessage();
            
        }catch(IOException e){
            return "*****************Error during gRPC request: " + e.getMessage();
        }
    }
}
