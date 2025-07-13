
package distsys.smarteducationalenviroment;


import generated.grpc.analyzer.ParticipationAnalizerImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

/* @author Carolina*/
public class ParticipationAnalizerServer {
    public static void main(String[] args) throws IOException, InterruptedException{
        
        Server server = ServerBuilder
                .forPort(50052)
                .addService(new ParticipationAnalizerImpl())
                .build();
        
        server.start();
        
        System.out.println("Server started at " + server.getPort());
        server.awaitTermination();
        
    }
    
}
