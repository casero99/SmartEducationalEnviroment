package distsys.smarteducationalenviroment;

import generated.grpc.analyzer.CustomFeedbackReply;
import generated.grpc.analyzer.CustomFeedbackRequest;
import generated.grpc.analyzer.ParticipationAnalizerGrpc.ParticipationAnalizerImplBase;
import generated.grpc.analyzer.ParticipationRequest;
import generated.grpc.analyzer.ParticipationStatistics;
import generated.grpc.analyzer.Student;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

/* @author Carolina*/

public class ParticipationAnalizerServer extends ParticipationAnalizerImplBase {

    //logger to helo print server logs in a clean way
    private static final Logger logger = Logger.getLogger(ParticipationAnalizerServer.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {

        /*
            The purpose of jmDNS is for the server to announce itself on the network so the client
         can find it without knowing the IP or the Port.
        
        */
        
        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
        //Service - Service Name - Port - Description
        ServiceInfo serviceInfo = ServiceInfo.create("grpc.tcp.local.", "ParticipationAnalizer", 50052, "gRPC service for Participation Analizer");
        jmdns.registerService(serviceInfo);
        System.out.println("**********************************");
        System.out.println("jmDNS: Participation Analizer Registered");
        System.out.println("**********************************");
        int port = 50052;

        try {
            //create and start the gRPC server on port 50052
            Server server = ServerBuilder.forPort(port)
                    .addService(new ParticipationAnalizerServer())
                    .build()
                    .start();
            System.out.println("**********************************");
            logger.log(Level.INFO, "ParticipationAnalizer started, listening on {0}", port);
            System.out.println(" Server started, listening on" + port);
            System.out.println("**********************************");
            server.awaitTermination(); //keep the server running

        } catch (IOException e) {
            e.printStackTrace();// TODO Auto-generated catch block

        } catch (InterruptedException e) {
            e.printStackTrace();// TODO Auto-generated catch block

        }

    }
 
    /*
    SERVER STREAMING -- Receives a full list of students
    and returns a summary of male/female of the participant students
    
    */
    @Override
     public void analyzerParticipation(ParticipationRequest request, StreamObserver<ParticipationStatistics> responseObserver) {

        int maleCount = 0;
        int femaleCount = 0;

        //loop of each student and count gender
        for (Student student : request.getStudentsList()) {
            String gender = student.getGender();

            if (gender.equalsIgnoreCase("male")) {
                maleCount++;
            } else if (gender.equalsIgnoreCase("female")) {
                femaleCount++;
            }
        }

        int totalNumStudents = maleCount + femaleCount;
        
        //calculating the percentage of each gender
        float malePercentage = totalNumStudents > 0 ? (maleCount * 100f) / totalNumStudents : 0;
        float femalePercentage = totalNumStudents > 0 ? (femaleCount * 100f) / totalNumStudents : 0;

        
        //summary message
        String summary = "Students participation summary:\n"
                + "Total MALE students: " + maleCount + "\n"
                + "Total FEMALE students: " + femaleCount + "\n"
                + "Total: " + totalNumStudents + " students.";

        
        //response object created
        ParticipationStatistics response = ParticipationStatistics.newBuilder()
                .setMalePercentage(malePercentage)
                .setFemalePercentage(femalePercentage)
                .setSummary(summary)
                .build();

        //sending the response
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    /*
     CLIENT STREAMING -- Allows client to send multimple feedback messages
     and then the server replies once with a full feedback summary
     
     */
    @Override
    public StreamObserver<CustomFeedbackRequest> submitCustomFeedback(
            StreamObserver<CustomFeedbackReply> responseObserver) {

        //creating the space where the feedback will be store
        return new StreamObserver<CustomFeedbackRequest>() {

            // storing feedback messages
            StringBuilder storeFeedback = new StringBuilder();

            @Override
            public void onNext(CustomFeedbackRequest request) {
                //extract student name and the feedbacks
                String studentName = request.getStudentName();
                String feedback = request.getFeedback();

                //append to feedback summary
                storeFeedback.append("Feedback for ").append(studentName).append(": ").append(feedback).append("\n");
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.WARNING, "Error during receiving the custom feedback", t);
            }

            @Override
            public void onCompleted() {
                //printing the feedback
                System.out.println("Custom feedback received:\n" + storeFeedback.toString());

                CustomFeedbackReply reply = CustomFeedbackReply.newBuilder()
                        .setMessage("Feedback received and saved succesfully!" + storeFeedback.toString())
                        .build();

                responseObserver.onNext(reply);
                responseObserver.onCompleted();

            }

        };
    }

}