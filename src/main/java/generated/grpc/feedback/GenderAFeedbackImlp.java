package generated.grpc.feedback;

import io.grpc.stub.StreamObserver;
import java.util.logging.Level;
import java.util.logging.Logger;

/* @author Carolina*/
public class GenderAFeedbackImlp extends GenderAFeedbackGrpc.GenderAFeedbackImplBase{
    
    private static final Logger logger = Logger.getLogger(GenderAFeedbackImlp.class.getName());
    
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
