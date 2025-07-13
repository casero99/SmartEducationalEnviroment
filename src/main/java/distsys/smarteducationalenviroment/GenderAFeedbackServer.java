
package distsys.smarteducationalenviroment;

import generated.grpc.feedback.ClassInsight;
import generated.grpc.feedback.ClassRequest;
import generated.grpc.feedback.FeedbackResponse;
import generated.grpc.feedback.GenderAFeedbackImpl;
import generated.grpc.feedback.StudentEvent;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*@author Carolina*/

public class GenderAFeedbackServer {
    private static final Logger logger = Logger.getLogger(DomesticActSimulatorServer.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        

        int port = 50053;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(new GenderAFeedbackImpl())
                    .build()
                    .start();
            logger.log(Level.INFO, "ParticipationAnalizer started, listening on {0}", port);
            System.out.println(" Server started, listening on" + port);
            server.awaitTermination();

        } catch (IOException e) {
            e.printStackTrace();// TODO Auto-generated catch block
            

        } catch (InterruptedException e) {
            e.printStackTrace();// TODO Auto-generated catch block

        }

    }
    
    public void getClassIngsight(ClassRequest request, StreamObserver<ClassInsight> responseObserver){
        
        String className = request.getClassName();
        
        String[] insights = {
        "The class" + className + " is showing gender inbalance in participation.",
        "Consider rotating household task equally between the students.",
        "Encourage boys to engage more in care-work related tasks.",
        "Encourage girls to engage more in care-work related tasks.",
        "Provide more example demostrations for a better understanding."
    };
        for(String message : insights){
            ClassInsight insight = ClassInsight.newBuilder().setMessage(message).build();
            responseObserver.onNext(insight);
            
            try{
                Thread.sleep(500); //pause between messages
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        responseObserver.onCompleted();
    }
    
    //Bi-directional RPC
    public StreamObserver<StudentEvent> participateLive(final StreamObserver<FeedbackResponse> responseObserver){
        return new StreamObserver<StudentEvent>(){
            
            @Override
            public void onNext(StudentEvent event){
                //aqui va la logica de bi-directional streaming
                //mandar respuestas a tiempo real(responseObserver.onNext(...))
                String taskName = event.getTaskName();
                double taskDuration = event.getTaskDuration();
                String feedback;
                
                if(taskDuration > 5.0){
                    feedback = "Great Job! But try to be a little bit faster completing the task" + taskName;
                }else{
                    feedback = "You've done amazing! Keep it that way!" + taskName;
                }
                
                FeedbackResponse response = FeedbackResponse.newBuilder().setFeedback(feedback).build();
                
                responseObserver.onNext(response);
            }
            
            @Override
            public void onError(Throwable t){
                logger.log(Level.WARNING,"Encountered error in routeChat", t);
                
            }
            
            @Override
            public void onCompleted(){
                responseObserver.onCompleted();
                
            }
        };
    }
    
}
