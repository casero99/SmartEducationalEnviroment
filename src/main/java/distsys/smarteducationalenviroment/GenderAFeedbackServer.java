package distsys.smarteducationalenviroment;

import generated.grpc.feedback.TaskFeedbackSummary;
import generated.grpc.feedback.StudentTask;
import generated.grpc.feedback.TaskFeedback;
import generated.grpc.feedback.GenderAFeedbackGrpc.GenderAFeedbackImplBase;
import generated.grpc.feedback.StudentTask;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

/*@author Carolina*/

public class GenderAFeedbackServer extends GenderAFeedbackImplBase {

    private static final Logger logger = Logger.getLogger(GenderAFeedbackServer.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {

        /*
            The purpose of jmDNS is for the server to announce itself on the network so the client
         can find it without knowing the IP or the Port.
        
        */
        
        JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
        //Service - jmDNS Type - Service Name - Port
        ServiceInfo serviceInfo = ServiceInfo.create("grpc.tcp.local.", "GenderFeedback", 50053, "gRPC service for Gender A. Feedback");
        jmdns.registerService(serviceInfo);
        System.out.println("**********************************");
        System.out.println("jmDNS: GenderFeedback Registered");
        System.out.println("**********************************");
        
        int port = 50053;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(new GenderAFeedbackServer())
                    .build()
                    .start();
            System.out.println("**********************************");
            logger.log(Level.INFO, "GenderAFeedbackServer started, listening on {0}", port);
            System.out.println(" Server started, listening on" + port);
            System.out.println("**********************************");
            server.awaitTermination();

        } catch (IOException e) {
            e.printStackTrace();// TODO Auto-generated catch block

        } catch (InterruptedException e) {
            e.printStackTrace();// TODO Auto-generated catch block

        }

    }

    //public void taskPerformance(StudentTask request, StreamObserver<TaskFeedbackSummary> responseObserver){
    public StreamObserver<StudentTask> taskPerformance(StreamObserver<TaskFeedbackSummary> responseObserver) {
        return new StreamObserver<StudentTask>() {

            int totalDuration = 0;
            int taskCount = 0;
            StringBuilder feedbackBuilder = new StringBuilder();

            @Override
            public void onNext(StudentTask task) {
                String studentName = task.getStudentName();
                String studentTask = task.getStudentTask();
                int taskDuration = task.getTaskDuration();

                totalDuration += taskDuration;
                taskCount++;

                String msg;
                if (taskDuration <= 5) {
                    msg = "Fast, efficient, and done pretty well! (:";
                } else if (taskDuration <= 10) {
                    msg = "Okay. Have the task done.";
                } else {
                    msg = "Too slow. Try to speed up";
                }

                feedbackBuilder.append("Feedback for ").append(studentName).append(" did ").append(studentTask)
                        .append(" in ").append(taskDuration).append(" seconds ").append(msg).append("\n");

                System.out.println("Received task from " + studentName);
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.WARNING, "Encountered error in recordRoute", t);
            }

            @Override
            public void onCompleted() {
                int totalTaskDuration = (taskCount == 0) ? 0 : totalDuration / taskCount;

                TaskFeedbackSummary summary = TaskFeedbackSummary.newBuilder()
                        .setTotalTask(taskCount)
                        .setTotalTime(totalTaskDuration)
                        .setSummary(feedbackBuilder.toString())
                        .build();
                
                responseObserver.onNext(summary);
                responseObserver.onCompleted();
            }
        };
    }
        //Bi-directional RPC
    
    @Override
    public StreamObserver<StudentTask> liveTaskFeedback(StreamObserver<TaskFeedback> responseObserver) {
        return new StreamObserver<StudentTask>() {

            @Override
            public void onNext(StudentTask event) {
                //aqui va la logica de bi-directional streaming
                //mandar respuestas a tiempo real(responseObserver.onNext(...))
                //extract the data sent by the client
                String studentName = event.getStudentName();
                String studentTask = event.getStudentTask();
                int taskDuration = event.getTaskDuration();
                String feedback; //preguntar acerca de este

                //feedback logic based on task duration
                if (taskDuration <= 5) {
                    feedback = "Great Job! Fast and efficient!" + studentTask;
                } else if(taskDuration <=10) {
                    feedback = "Very well! Average speed" + studentTask;
                }else{
                    feedback = "Try to improve speed.";
                }

                //build the reponse message managed by feedback
                TaskFeedback response = TaskFeedback.newBuilder().setStudentName(studentName).setFeedback(feedback).build();

                //real time feedback to client
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.WARNING, "Encountered error in liveTaskFeedback", t);

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();

            }
        };
    }

}

