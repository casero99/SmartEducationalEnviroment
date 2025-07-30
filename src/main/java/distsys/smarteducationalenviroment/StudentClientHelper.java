package distsys.smarteducationalenviroment;

import generated.grpc.analyzer.ParticipationAnalizerGrpc;
import generated.grpc.analyzer.ParticipationRequest;
import generated.grpc.analyzer.ParticipationStatistics;
import generated.grpc.domestic.DomesticActSimulatorGrpc;
import generated.grpc.domestic.RegisterStudentsRequest;
import generated.grpc.domestic.ResisterStudentsReply;
import generated.grpc.domestic.Student;
//import generated.grpc.analyzer.Student;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Iterator;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

/* @author Carolina*/

 /*
    This class basically is a 'helper class' 
    that contains the gRPC connection logic.

 */
public class StudentClientHelper {

    public static String runUnaryDomesticTask(String name, int age, String gender, String task) {

        try {
            //discover the domestic via jmDns
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            ServiceInfo serviceInfo = jmdns.getServiceInfo("grpc.tcp.local.", "DomesticService", 50051);

            if (serviceInfo == null) {
                return "****************Could not find DomesticService via jmDNS";
            }

            String host1 = serviceInfo.getInetAddresses()[0].getHostAddress();
            int port1 = serviceInfo.getPort();

            //Creating gRPC channel and stub
            ManagedChannel channel = ManagedChannelBuilder.forAddress(host1, port1)
                    .usePlaintext()
                    .build();

            DomesticActSimulatorGrpc.DomesticActSimulatorBlockingStub stub
                    = DomesticActSimulatorGrpc.newBlockingStub(channel);

            //create request --- get reply
            Student students = Student.newBuilder()
                    .setStudentName(name)
                    .setStudentAge(age)
                    .setGender(gender)
                    .setTaskName(task)
                    .build();

            RegisterStudentsRequest request = RegisterStudentsRequest.newBuilder()
                    .addStudents(students)
                    .build();

            ResisterStudentsReply reply = stub.registerStudents(request);

            channel.shutdown();

            return "Feedback: " + reply.getMessage();

        } catch (IOException e) {
            return "*****************Error during gRPC request: " + e.getMessage();
        }
    }

    public static String runServerStreamingParticipationAnalizer(generated.grpc.analyzer.Student student) {
        try {
            //discover the domestic via jmDns
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            ServiceInfo serviceInfo = jmdns.getServiceInfo("grpc.tcp.local.", "ParticipationAnalizer", 50052);

            if (serviceInfo == null) {
                return "****************Could not find ParticipationAnalizer via jmDNS";
            }

            String host2 = serviceInfo.getInetAddresses()[0].getHostAddress();
            int port2 = serviceInfo.getPort();

            //Creating gRPC channel and stub
            ManagedChannel channel = ManagedChannelBuilder.forAddress(host2, port2)
                    .usePlaintext()
                    .build();

            ParticipationAnalizerGrpc.ParticipationAnalizerBlockingStub stub
                    = ParticipationAnalizerGrpc.newBlockingStub(channel);

            //Create the request
            ParticipationRequest request = ParticipationRequest.newBuilder()
                    .addStudents(student) //expecting a list, but it can be only one student
                    .build();
            //send request and receive streamed responses
            Iterator<ParticipationStatistics> replies = stub.analyzerParticipation(request);

            //create the iterator for the server stream
            StringBuilder resultBuilder = new StringBuilder();
            while (replies.hasNext()) {
                ParticipationStatistics reply = replies.next();
                resultBuilder.append(" . ").append(reply.getSummary()).append("\n");
            }

            channel.shutdown();
            return resultBuilder.toString();

        } catch (IOException e) {
            return "*****************Error during gRPC request: " + e.getMessage();
        }
    }
}

