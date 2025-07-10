package generated.grpc.domestic;

import io.grpc.stub.StreamObserver;

/* @author Carolina*/
public class DomesticActSimulatorImlp extends DomesticActSimulatorGrpc.DomesticActSimulatorImplBase {

    @Override
    public void startTask(StudentTask request, StreamObserver<StudentTaskCompleted> responseObserver) {

        System.out.println("Inside StartTask");

        String studentName = request.getStudentName();
        int studentAge = request.getStudentAge();
        String taskName = request.getTaskName();
        double taskDuration = request.getTaskDuration();
        
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
