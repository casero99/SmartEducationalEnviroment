package generated.grpc.domestic;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: DomesticActSimulator.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DomesticActSimulatorGrpc {

  private DomesticActSimulatorGrpc() {}

  public static final String SERVICE_NAME = "DomesticActSimulator.DomesticActSimulator";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.domestic.StudentTask,
      generated.grpc.domestic.StudentTaskCompleted> getStartTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "startTask",
      requestType = generated.grpc.domestic.StudentTask.class,
      responseType = generated.grpc.domestic.StudentTaskCompleted.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.grpc.domestic.StudentTask,
      generated.grpc.domestic.StudentTaskCompleted> getStartTaskMethod() {
    io.grpc.MethodDescriptor<generated.grpc.domestic.StudentTask, generated.grpc.domestic.StudentTaskCompleted> getStartTaskMethod;
    if ((getStartTaskMethod = DomesticActSimulatorGrpc.getStartTaskMethod) == null) {
      synchronized (DomesticActSimulatorGrpc.class) {
        if ((getStartTaskMethod = DomesticActSimulatorGrpc.getStartTaskMethod) == null) {
          DomesticActSimulatorGrpc.getStartTaskMethod = getStartTaskMethod =
              io.grpc.MethodDescriptor.<generated.grpc.domestic.StudentTask, generated.grpc.domestic.StudentTaskCompleted>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "startTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.domestic.StudentTask.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.domestic.StudentTaskCompleted.getDefaultInstance()))
              .setSchemaDescriptor(new DomesticActSimulatorMethodDescriptorSupplier("startTask"))
              .build();
        }
      }
    }
    return getStartTaskMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DomesticActSimulatorStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DomesticActSimulatorStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DomesticActSimulatorStub>() {
        @java.lang.Override
        public DomesticActSimulatorStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DomesticActSimulatorStub(channel, callOptions);
        }
      };
    return DomesticActSimulatorStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DomesticActSimulatorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DomesticActSimulatorBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DomesticActSimulatorBlockingStub>() {
        @java.lang.Override
        public DomesticActSimulatorBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DomesticActSimulatorBlockingStub(channel, callOptions);
        }
      };
    return DomesticActSimulatorBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DomesticActSimulatorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DomesticActSimulatorFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DomesticActSimulatorFutureStub>() {
        @java.lang.Override
        public DomesticActSimulatorFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DomesticActSimulatorFutureStub(channel, callOptions);
        }
      };
    return DomesticActSimulatorFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class DomesticActSimulatorImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *  Unary gRPC - client sends one student task and server confirms completion.
     * </pre>
     */
    public void startTask(generated.grpc.domestic.StudentTask request,
        io.grpc.stub.StreamObserver<generated.grpc.domestic.StudentTaskCompleted> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStartTaskMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStartTaskMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                generated.grpc.domestic.StudentTask,
                generated.grpc.domestic.StudentTaskCompleted>(
                  this, METHODID_START_TASK)))
          .build();
    }
  }

  /**
   */
  public static final class DomesticActSimulatorStub extends io.grpc.stub.AbstractAsyncStub<DomesticActSimulatorStub> {
    private DomesticActSimulatorStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DomesticActSimulatorStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DomesticActSimulatorStub(channel, callOptions);
    }

    /**
     * <pre>
     *  Unary gRPC - client sends one student task and server confirms completion.
     * </pre>
     */
    public void startTask(generated.grpc.domestic.StudentTask request,
        io.grpc.stub.StreamObserver<generated.grpc.domestic.StudentTaskCompleted> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStartTaskMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DomesticActSimulatorBlockingStub extends io.grpc.stub.AbstractBlockingStub<DomesticActSimulatorBlockingStub> {
    private DomesticActSimulatorBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DomesticActSimulatorBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DomesticActSimulatorBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *  Unary gRPC - client sends one student task and server confirms completion.
     * </pre>
     */
    public generated.grpc.domestic.StudentTaskCompleted startTask(generated.grpc.domestic.StudentTask request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStartTaskMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DomesticActSimulatorFutureStub extends io.grpc.stub.AbstractFutureStub<DomesticActSimulatorFutureStub> {
    private DomesticActSimulatorFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DomesticActSimulatorFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DomesticActSimulatorFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *  Unary gRPC - client sends one student task and server confirms completion.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.grpc.domestic.StudentTaskCompleted> startTask(
        generated.grpc.domestic.StudentTask request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStartTaskMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_START_TASK = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DomesticActSimulatorImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DomesticActSimulatorImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_START_TASK:
          serviceImpl.startTask((generated.grpc.domestic.StudentTask) request,
              (io.grpc.stub.StreamObserver<generated.grpc.domestic.StudentTaskCompleted>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DomesticActSimulatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DomesticActSimulatorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.domestic.DomesticActSimulatorImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DomesticActSimulator");
    }
  }

  private static final class DomesticActSimulatorFileDescriptorSupplier
      extends DomesticActSimulatorBaseDescriptorSupplier {
    DomesticActSimulatorFileDescriptorSupplier() {}
  }

  private static final class DomesticActSimulatorMethodDescriptorSupplier
      extends DomesticActSimulatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DomesticActSimulatorMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DomesticActSimulatorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DomesticActSimulatorFileDescriptorSupplier())
              .addMethod(getStartTaskMethod())
              .build();
        }
      }
    }
    return result;
  }
}
