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
  private static volatile io.grpc.MethodDescriptor<generated.grpc.feedback.ClassRequest,
      generated.grpc.feedback.ClassInsight> getGetClassInsightMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getClassInsight",
      requestType = generated.grpc.feedback.ClassRequest.class,
      responseType = generated.grpc.feedback.ClassInsight.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.feedback.ClassRequest,
      generated.grpc.feedback.ClassInsight> getGetClassInsightMethod() {
    io.grpc.MethodDescriptor<generated.grpc.feedback.ClassRequest, generated.grpc.feedback.ClassInsight> getGetClassInsightMethod;
    if ((getGetClassInsightMethod = GenderAFeedbackGrpc.getGetClassInsightMethod) == null) {
      synchronized (GenderAFeedbackGrpc.class) {
        if ((getGetClassInsightMethod = GenderAFeedbackGrpc.getGetClassInsightMethod) == null) {
          GenderAFeedbackGrpc.getGetClassInsightMethod = getGetClassInsightMethod =
              io.grpc.MethodDescriptor.<generated.grpc.feedback.ClassRequest, generated.grpc.feedback.ClassInsight>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getClassInsight"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.feedback.ClassRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.feedback.ClassInsight.getDefaultInstance()))
              .setSchemaDescriptor(new GenderAFeedbackMethodDescriptorSupplier("getClassInsight"))
              .build();
        }
      }
    }
    return getGetClassInsightMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.feedback.StudentEvent,
      generated.grpc.feedback.FeedbackResponse> getLiveFeedbackExchangeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "liveFeedbackExchange",
      requestType = generated.grpc.feedback.StudentEvent.class,
      responseType = generated.grpc.feedback.FeedbackResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.feedback.StudentEvent,
      generated.grpc.feedback.FeedbackResponse> getLiveFeedbackExchangeMethod() {
    io.grpc.MethodDescriptor<generated.grpc.feedback.StudentEvent, generated.grpc.feedback.FeedbackResponse> getLiveFeedbackExchangeMethod;
    if ((getLiveFeedbackExchangeMethod = GenderAFeedbackGrpc.getLiveFeedbackExchangeMethod) == null) {
      synchronized (GenderAFeedbackGrpc.class) {
        if ((getLiveFeedbackExchangeMethod = GenderAFeedbackGrpc.getLiveFeedbackExchangeMethod) == null) {
          GenderAFeedbackGrpc.getLiveFeedbackExchangeMethod = getLiveFeedbackExchangeMethod =
              io.grpc.MethodDescriptor.<generated.grpc.feedback.StudentEvent, generated.grpc.feedback.FeedbackResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "liveFeedbackExchange"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.feedback.StudentEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.feedback.FeedbackResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenderAFeedbackMethodDescriptorSupplier("liveFeedbackExchange"))
              .build();
        }
      }
    }
    return getLiveFeedbackExchangeMethod;
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
     */
    public void getClassInsight(generated.grpc.feedback.ClassRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.feedback.ClassInsight> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetClassInsightMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.grpc.feedback.StudentEvent> liveFeedbackExchange(
        io.grpc.stub.StreamObserver<generated.grpc.feedback.FeedbackResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getLiveFeedbackExchangeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetClassInsightMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                generated.grpc.feedback.ClassRequest,
                generated.grpc.feedback.ClassInsight>(
                  this, METHODID_GET_CLASS_INSIGHT)))
          .addMethod(
            getLiveFeedbackExchangeMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                generated.grpc.feedback.StudentEvent,
                generated.grpc.feedback.FeedbackResponse>(
                  this, METHODID_LIVE_FEEDBACK_EXCHANGE)))
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
     */
    public void getClassInsight(generated.grpc.feedback.ClassRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.feedback.ClassInsight> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetClassInsightMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.grpc.feedback.StudentEvent> liveFeedbackExchange(
        io.grpc.stub.StreamObserver<generated.grpc.feedback.FeedbackResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getLiveFeedbackExchangeMethod(), getCallOptions()), responseObserver);
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

    /**
     */
    public java.util.Iterator<generated.grpc.feedback.ClassInsight> getClassInsight(
        generated.grpc.feedback.ClassRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetClassInsightMethod(), getCallOptions(), request);
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

  private static final int METHODID_GET_CLASS_INSIGHT = 0;
  private static final int METHODID_LIVE_FEEDBACK_EXCHANGE = 1;

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
        case METHODID_GET_CLASS_INSIGHT:
          serviceImpl.getClassInsight((generated.grpc.feedback.ClassRequest) request,
              (io.grpc.stub.StreamObserver<generated.grpc.feedback.ClassInsight>) responseObserver);
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
        case METHODID_LIVE_FEEDBACK_EXCHANGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.liveFeedbackExchange(
              (io.grpc.stub.StreamObserver<generated.grpc.feedback.FeedbackResponse>) responseObserver);
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
              .addMethod(getGetClassInsightMethod())
              .addMethod(getLiveFeedbackExchangeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
