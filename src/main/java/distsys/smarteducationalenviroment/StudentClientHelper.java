package distsys.smarteducationalenviroment;

import generated.grpc.analyzer.CustomFeedbackReply;
import generated.grpc.analyzer.CustomFeedbackRequest;
import generated.grpc.analyzer.ParticipationAnalizerGrpc;
import generated.grpc.analyzer.ParticipationRequest;
import generated.grpc.analyzer.ParticipationStatistics;
import generated.grpc.domestic.DomesticActSimulatorGrpc;
import generated.grpc.domestic.RegisterStudentsRequest;
import generated.grpc.domestic.ResisterStudentsReply;
import generated.grpc.domestic.Student;
import generated.grpc.feedback.GenderAFeedbackGrpc;
import generated.grpc.feedback.StudentTask;
import generated.grpc.feedback.TaskFeedback;
import generated.grpc.feedback.TaskFeedbackSummary;
//import generated.grpc.analyzer.Student;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

/* @author Carolina*/

 /*
    This class basically is a 'helper class' 
    that contains the gRPC connection logic.
    Discovers services using jmDNS, establishing the channels, building the requests,
    and manages the responses for each service.

 */
public class StudentClientHelper {
    
        //*********************************************************
        //SERVER . UNARY Streaming RPC - Domestic Activity Simulator
        //*********************************************************
    public static String runUnaryDomesticTask(String name, int age, String gender, String task) {

        try {
            //discover the domestic via jmDns
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            ServiceInfo serviceInfo = jmdns.getServiceInfo("grpc.tcp.local.", "DomesticService", 50051);

            //if the service is not found
            if (serviceInfo == null) {
                return "****************Could not find DomesticService via jmDNS";
            }

            //get the IP and port from discovered service
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

            //call the server and get response
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

    //*********************************************************
    //SERVER 2. Server Streaming RPC - Participation Analizer
    //*********************************************************
    public static String runServerStreamingParticipationAnalizer(List<generated.grpc.analyzer.Student> studentList) {
        try {
            //discover the domestic via jmDns
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            ServiceInfo serviceInfo = jmdns.getServiceInfo("grpc.tcp.local.", "ParticipationAnalizer", 50052);

            if (serviceInfo == null) {
                return "****************Could not find ParticipationAnalizer via jmDNS";
            }

            String host2 = serviceInfo.getInetAddresses()[0].getHostAddress();
            int port2 = serviceInfo.getPort();

            StringBuilder feedback = new StringBuilder();
            
            //Creating gRPC channel and stub
            ManagedChannel channel = ManagedChannelBuilder.forAddress(host2, port2)
                    .usePlaintext()
                    .build();

            ParticipationAnalizerGrpc.ParticipationAnalizerBlockingStub stub
                    = ParticipationAnalizerGrpc.newBlockingStub(channel);

            //Create the request
            ParticipationRequest request = ParticipationRequest.newBuilder()
                    .addAllStudents(studentList) //expecting a list, but it can be only one student
                    .build();
            //send request and receive streamed responses
            Iterator<ParticipationStatistics> stats = stub.analyzerParticipation(request);

            //create the iterator for the server stream
            
            while(stats.hasNext()){
                ParticipationStatistics percentage = stats.next();
                feedback.append(" MALE %: ").append(percentage.getMalePercentage())
                    .append(", FEMALE %: ").append(percentage.getFemalePercentage())
                    .append("\n").append(percentage.getSummary()).append("\n");
        }
            

            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            return feedback.toString();
           

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "*****************Error during gRPC request: " + e.getMessage();
        }
             
    }

    //*********************************************************
    //SERVER 2. Client Streaming RPC - Participation Analizer
    //*********************************************************
    public static String runClientStreamingCustomFeedback(List<CustomFeedbackRequest> feedbackList) {
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

            ParticipationAnalizerGrpc.ParticipationAnalizerStub asyncStub
                    = ParticipationAnalizerGrpc.newStub(channel);

            //prepare a latch to wait for the server response
            StringBuilder result = new StringBuilder();
            CountDownLatch latch = new CountDownLatch(1);

            //stream observer for receiving the reply
            StreamObserver<CustomFeedbackReply> responseObserver = new StreamObserver<CustomFeedbackReply>() {
                @Override
                public void onNext(CustomFeedbackReply value) {
                    result.append(value.getMessage());
                }

                @Override
                public void onError(Throwable t) {
                    result.append("Error receiving the server reply: ").append(t.getMessage());
                    latch.countDown();
                }

                @Override
                public void onCompleted() {
                    latch.countDown();
                }
            };
            //Stream observer for sending the requests
            StreamObserver<CustomFeedbackRequest> requestObserver = asyncStub.submitCustomFeedback(responseObserver);
            //sending all the request in the list
            for (CustomFeedbackRequest request : feedbackList) {
                requestObserver.onNext(request);
            }

            requestObserver.onCompleted();      //Mark the end of the requests
            latch.await(3, TimeUnit.SECONDS);   //wait for the response
            channel.shutdown();                 //close channel

            return result.toString();
        } catch (Exception e) {
            return "Error during client streaming gRPC request: " + e.getMessage();
        }

    }

    //*********************************************************
    //SERVER 3. Client Streaming RPC - Gender Feedback
    //*********************************************************
    public static String runClientStreamingTaskPerformance(List<StudentTask> taskList) {
        try {
            //discover the domestic via jmDns
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            ServiceInfo serviceInfo = jmdns.getServiceInfo("grpc.tcp.local.", "GenderFeedback", 50053);

            if (serviceInfo == null) {
                return "****************Could not find GenderFeedback via jmDNS";
            }

            String host3 = serviceInfo.getInetAddresses()[0].getHostAddress();
            int port3 = serviceInfo.getPort();

            //Creating gRPC channel and stub
            ManagedChannel channel = ManagedChannelBuilder.forAddress(host3, port3)
                    .usePlaintext()
                    .build();

            GenderAFeedbackGrpc.GenderAFeedbackStub asyncStub
                    = GenderAFeedbackGrpc.newStub(channel);

            //prepare a latch to wait for the server response
            StringBuilder result = new StringBuilder();
            CountDownLatch latch = new CountDownLatch(1);

            //stream observer for receiving the reply
            StreamObserver<TaskFeedbackSummary> responseObserver = new StreamObserver<TaskFeedbackSummary>() {
                @Override
                public void onNext(TaskFeedbackSummary summary) {
                    result.append("Task summary: \n")
                            .append("Total Tasks: ").append(summary.getTotalTask()).append("\n")
                            .append("Total Time: ").append(summary.getTotalTime()).append(" seconds\n")
                            .append("summary:  ").append(summary.getSummary()).append("\n");

                }

                @Override
                public void onError(Throwable t) {
                    result.append("Error receiving the summary: ").append(t.getMessage());
                    latch.countDown();
                }

                @Override
                public void onCompleted() {
                    latch.countDown();
                }
            };
            //Stream observer for sending the requests
            StreamObserver<StudentTask> requestObserver = asyncStub.taskPerformance(responseObserver);
            //sending all the request in the list
            for (StudentTask task : taskList) {
                requestObserver.onNext(task);
            }
            requestObserver.onCompleted();      //Mark the end of the requests
            latch.await(3, TimeUnit.SECONDS);   //wait for the response
            channel.shutdown();                 //close channel

            return result.toString();
        } catch (Exception e) {
            return "Error in task performance client stream: " + e.getMessage();
        }
    }

    //*********************************************************
    //SERVER 3. bi-directional Streaming RPC - Gender Feedback
    //*********************************************************
    public static String runBidirectionalFeedback(List<StudentTask> studentTasks) {
        try {
            //discover the service via jmDns
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            ServiceInfo serviceInfo = jmdns.getServiceInfo("grpc.tcp.local.", "GenderFeedback", 50053);

            if (serviceInfo == null) {
                return "****************Could not find GenderFeedback via jmDNS";
            }

            String host3 = serviceInfo.getInetAddresses()[0].getHostAddress();
            int port3 = serviceInfo.getPort();

            //Creating gRPC channel and stub
            ManagedChannel channel = ManagedChannelBuilder.forAddress(host3, port3)
                    .usePlaintext()
                    .build();

            GenderAFeedbackGrpc.GenderAFeedbackStub asyncStub
                    = GenderAFeedbackGrpc.newStub(channel);

            //prepare a latch to wait for the server response
            StringBuilder result = new StringBuilder();
            CountDownLatch latch = new CountDownLatch(1);

            //stream observer for receiving the reply
            StreamObserver<TaskFeedback> responseObserver = new StreamObserver<TaskFeedback>() {
                @Override
                public void onNext(TaskFeedback value) {
                    result.append("Feedback from server: \n")
                            .append(value.getStudentName()).append(" - ")
                            .append(value.getFeedback())
                            .append("\n");

                }

                @Override
                public void onError(Throwable t) {
                    result.append("Error from server: ").append(t.getMessage());
                    latch.countDown();
                }

                @Override
                public void onCompleted() {
                    result.append("!!!Server completed, sending feedback (; ");
                    latch.countDown();
                }
            };
            //Stream observer to send messages from client to server
            StreamObserver<StudentTask> requestObserver = asyncStub.liveTaskFeedback(responseObserver);
            //sending all the request in the list
            for (StudentTask task : studentTasks) {
                requestObserver.onNext(task);
                Thread.sleep(500); //simulate delay between messages
            }

            requestObserver.onCompleted();      //Mark the end of the requests
            latch.await(3, TimeUnit.SECONDS);   //wait for the response
            channel.shutdown();                 //close channel

            return result.toString();
        } catch (Exception e) {
            return "Error in bidirectional gRPC: " + e.getMessage();
        }

    }
}
