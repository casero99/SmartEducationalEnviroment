
package distsys.smarteducationalenviroment;


import generated.grpc.analyzer.ParticipationAnalizerImpl;
import generated.grpc.analyzer.ParticipationEntry;
import generated.grpc.analyzer.ParticipationStatistics;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* @author Carolina*/
public class ParticipationAnalizerServer {
    private static final Logger logger = Logger.getLogger(DomesticActSimulatorServer.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        

        int port = 50052;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(new ParticipationAnalizerImpl())
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
    
    public StreamObserver<ParticipationEntry> recordRoute(final StreamObserver<ParticipationStatistics> responseObserver){
        return new StreamObserver<ParticipationEntry>(){
            
            int maleCount =0;
            int femaleCount = 0;
            double totalDuration = 0;
            long startTime = System.nanoTime();
            
            @Override
            public void onNext(ParticipationEntry entry){
                String gender = entry.getGender();
                double taskDuration = entry.getTaskDuration();
                
                if(gender.equalsIgnoreCase("male")){
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
