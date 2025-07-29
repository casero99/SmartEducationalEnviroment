package generated.grpc.feedback;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: GenderAFeedback.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GenderAFeedbackGrpc {

  private GenderAFeedbackGrpc() {}

  public static final String SERVICE_NAME = "GenderAFeedback.GenderAFeedback";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.feedback.StudentTask,
      generated.grpc.feedback.TaskFeedbackSummary> getTaskPerformanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "taskPerformance",
      requestType = generated.grpc.feedback.StudentTask.class,
      responseType = generated.grpc.feedback.TaskFeedbackSummary.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.feedback.StudentTask,
      generated.grpc.feedback.TaskFeedbackSummary> getTaskPerformanceMethod() {
    io.grpc.MethodDescriptor<generated.grpc.feedback.StudentTask, generated.grpc.feedback.TaskFeedbackSummary> getTaskPerformanceMethod;
    if ((getTaskPerformanceMethod = GenderAFeedbackGrpc.getTaskPerformanceMethod) == null) {
      synchronized (GenderAFeedbackGrpc.class) {
        if ((getTaskPerformanceMethod = GenderAFeedbackGrpc.getTaskPerformanceMethod) == null) {
          GenderAFeedbackGrpc.getTaskPerformanceMethod = getTaskPerformanceMethod =
              io.grpc.MethodDescriptor.<generated.grpc.feedback.StudentTask, generated.grpc.feedback.TaskFeedbackSummary>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "taskPerformance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.feedback.StudentTask.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.feedback.TaskFeedbackSummary.getDefaultInstance()))
              .setSchemaDescriptor(new GenderAFeedbackMethodDescriptorSupplier("taskPerformance"))
              .build();
        }
      }
    }
    return getTaskPerformanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.feedback.StudentTask,
      generated.grpc.feedback.TaskFeedback> getLiveTaskFeedbackMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "liveTaskFeedback",
      requestType = generated.grpc.feedback.StudentTask.class,
      responseType = generated.grpc.feedback.TaskFeedback.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.feedback.StudentTask,
      generated.grpc.feedback.TaskFeedback> getLiveTaskFeedbackMethod() {
    io.grpc.MethodDescriptor<generated.grpc.feedback.StudentTask, generated.grpc.feedback.TaskFeedback> getLiveTaskFeedbackMethod;
    if ((getLiveTaskFeedbackMethod = GenderAFeedbackGrpc.getLiveTaskFeedbackMethod) == null) {
      synchronized (GenderAFeedbackGrpc.class) {
        if ((getLiveTaskFeedbackMethod = GenderAFeedbackGrpc.getLiveTaskFeedbackMethod) == null) {
          GenderAFeedbackGrpc.getLiveTaskFeedbackMethod = getLiveTaskFeedbackMethod =
              io.grpc.MethodDescriptor.<generated.grpc.feedback.StudentTask, generated.grpc.feedback.TaskFeedback>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "liveTaskFeedback"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.feedback.StudentTask.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.feedback.TaskFeedback.getDefaultInstance()))
              .setSchemaDescriptor(new GenderAFeedbackMethodDescriptorSupplier("liveTaskFeedback"))
              .build();
        }
      }
    }
    return getLiveTaskFeedbackMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GenderAFeedbackStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenderAFeedbackStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenderAFeedbackStub>() {
        @java.lang.Override
        public GenderAFeedbackStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenderAFeedbackStub(channel, callOptions);
        }
      };
    return GenderAFeedbackStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GenderAFeedbackBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenderAFeedbackBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenderAFeedbackBlockingStub>() {
        @java.lang.Override
        public GenderAFeedbackBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenderAFeedbackBlockingStub(channel, callOptions);
        }
      };
    return GenderAFeedbackBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GenderAFeedbackFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenderAFeedbackFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenderAFeedbackFutureStub>() {
        @java.lang.Override
        public GenderAFeedbackFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenderAFeedbackFutureStub(channel, callOptions);
        }
      };
    return GenderAFeedbackFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class GenderAFeedbackImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *  Client Streaming - The client starts the duration of tasks from each students. 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.feedback.StudentTask> taskPerformance(
        io.grpc.stub.StreamObserver<generated.grpc.feedback.TaskFeedbackSummary> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getTaskPerformanceMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.grpc.feedback.StudentTask> liveTaskFeedback(
        io.grpc.stub.StreamObserver<generated.grpc.feedback.TaskFeedback> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getLiveTaskFeedbackMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getTaskPerformanceMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                generated.grpc.feedback.StudentTask,
                generated.grpc.feedback.TaskFeedbackSummary>(
                  this, METHODID_TASK_PERFORMANCE)))
          .addMethod(
            getLiveTaskFeedbackMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                generated.grpc.feedback.StudentTask,
                generated.grpc.feedback.TaskFeedback>(
                  this, METHODID_LIVE_TASK_FEEDBACK)))
          .build();
    }
  }

  /**
   */
  public static final class GenderAFeedbackStub extends io.grpc.stub.AbstractAsyncStub<GenderAFeedbackStub> {
    private GenderAFeedbackStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenderAFeedbackStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenderAFeedbackStub(channel, callOptions);
    }

    /**
     * <pre>
     *  Client Streaming - The client starts the duration of tasks from each students. 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.feedback.StudentTask> taskPerformance(
        io.grpc.stub.StreamObserver<generated.grpc.feedback.TaskFeedbackSummary> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getTaskPerformanceMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.grpc.feedback.StudentTask> liveTaskFeedback(
        io.grpc.stub.StreamObserver<generated.grpc.feedback.TaskFeedback> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getLiveTaskFeedbackMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class GenderAFeedbackBlockingStub extends io.grpc.stub.AbstractBlockingStub<GenderAFeedbackBlockingStub> {
    private GenderAFeedbackBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenderAFeedbackBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenderAFeedbackBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class GenderAFeedbackFutureStub extends io.grpc.stub.AbstractFutureStub<GenderAFeedbackFutureStub> {
    private GenderAFeedbackFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenderAFeedbackFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenderAFeedbackFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_TASK_PERFORMANCE = 0;
  private static final int METHODID_LIVE_TASK_FEEDBACK = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GenderAFeedbackImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GenderAFeedbackImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TASK_PERFORMANCE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.taskPerformance(
              (io.grpc.stub.StreamObserver<generated.grpc.feedback.TaskFeedbackSummary>) responseObserver);
        case METHODID_LIVE_TASK_FEEDBACK:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.liveTaskFeedback(
              (io.grpc.stub.StreamObserver<generated.grpc.feedback.TaskFeedback>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GenderAFeedbackBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GenderAFeedbackBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.feedback.GenderAFeedbackImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GenderAFeedback");
    }
  }

  private static final class GenderAFeedbackFileDescriptorSupplier
      extends GenderAFeedbackBaseDescriptorSupplier {
    GenderAFeedbackFileDescriptorSupplier() {}
  }

  private static final class GenderAFeedbackMethodDescriptorSupplier
      extends GenderAFeedbackBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GenderAFeedbackMethodDescriptorSupplier(String methodName) {
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
      synchronized (GenderAFeedbackGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GenderAFeedbackFileDescriptorSupplier())
              .addMethod(getTaskPerformanceMethod())
              .addMethod(getLiveTaskFeedbackMethod())
              .build();
        }
      }
    }
    return result;
  }
}
