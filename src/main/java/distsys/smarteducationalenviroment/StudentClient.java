/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smarteducationalenviroment;

import generated.grpc.domestic.DomesticActSimulatorGrpc;
import generated.grpc.domestic.StudentTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

/**
 *
 * @author Carolina
 */
public class StudentClient {
     public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 50051;
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();
        DomesticActSimulatorGrpc.DomesticActSimulatorBlockingStub blockingStub = DomesticActSimulatorGrpc.newBlockingStub(channel);
        try {
            String name = "Paul";
            StudentTask request = StudentTask.newBuilder().setStudentName("cam{")
                    .setStudentAge(port);

            HelloReply response = blockingStub.sayHello(request);

            logger.info("Greeting: " + response.getMessage());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());

            return;
        } finally {
            //shutdown channel
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
