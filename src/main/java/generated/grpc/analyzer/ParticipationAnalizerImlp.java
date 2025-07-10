package generated.grpc.analyzer;

import io.grpc.stub.StreamObserver;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;

/*@author Carolina*/
public class ParticipationAnalizerImlp extends ParticipationAnalizerGrpc.ParticipationAnalizerImplBase {

    private static final Logger logger = Logger.getLogger(ParticipationAnalizerImlp.class.getName());
    
        public StreamObserver<ParticipationEntry> recordRoute(final StreamObserver<ParticipationStatistics> responseObserver) {
        return new StreamObserver<ParticipationEntry>(){
            
            int maleCount = 0;
            int femaleCount = 0;
            double totalDuration = 0;
            long startTime = System.nanoTime();
            
            @Override
            public void onNext(ParticipationEntry entry){
                String gender = entry.getGender();
                double taskDuration = entry.getTaskDuration();
                
                if(gender.equals("male")){
                    maleCount++;                    
                }else if(gender.equalsIgnoreCase("female")){
                    femaleCount++;
                }
                totalDuration += taskDuration;
            }
            
            @Override
            public void onError(Throwable t){
                logger.log(Level.WARNING, "Error during participation tracking", t);
            }
            
            @Override
            public void onCompleted(){
                long elapsedSeconds = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime);
                int total = maleCount + femaleCount;
                
                float malePercentage = total > 0 ? (maleCount * 100f) / total : 0;
                float femalePercentage = total > 0 ? (femaleCount * 100f) / total : 0;
                
                String summary = "Total males: " + maleCount + 
                        "\nFemales: " + femaleCount + 
                        "\nDuration Total: " + totalDuration +
                        "\nSession time (seconds): " + elapsedSeconds;
                
                ParticipationStatistics response = ParticipationStatistics.newBuilder()
                        .setMalePercentage(malePercentage)
                        .setFemalePercentage(femalePercentage)
                        .setSummary(summary)
                        .build();
                
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }
}

