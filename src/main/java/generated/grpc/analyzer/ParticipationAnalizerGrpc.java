package generated.grpc.analyzer;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: ParticipationAnalizer.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ParticipationAnalizerGrpc {

  private ParticipationAnalizerGrpc() {}

  public static final String SERVICE_NAME = "ParticipationAnalizer.ParticipationAnalizer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.analyzer.ParticipationRequest,
      generated.grpc.analyzer.ParticipationStatistics> getAnalyzerParticipationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "analyzerParticipation",
      requestType = generated.grpc.analyzer.ParticipationRequest.class,
      responseType = generated.grpc.analyzer.ParticipationStatistics.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.analyzer.ParticipationRequest,
      generated.grpc.analyzer.ParticipationStatistics> getAnalyzerParticipationMethod() {
    io.grpc.MethodDescriptor<generated.grpc.analyzer.ParticipationRequest, generated.grpc.analyzer.ParticipationStatistics> getAnalyzerParticipationMethod;
    if ((getAnalyzerParticipationMethod = ParticipationAnalizerGrpc.getAnalyzerParticipationMethod) == null) {
      synchronized (ParticipationAnalizerGrpc.class) {
        if ((getAnalyzerParticipationMethod = ParticipationAnalizerGrpc.getAnalyzerParticipationMethod) == null) {
          ParticipationAnalizerGrpc.getAnalyzerParticipationMethod = getAnalyzerParticipationMethod =
              io.grpc.MethodDescriptor.<generated.grpc.analyzer.ParticipationRequest, generated.grpc.analyzer.ParticipationStatistics>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "analyzerParticipation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.analyzer.ParticipationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.analyzer.ParticipationStatistics.getDefaultInstance()))
              .setSchemaDescriptor(new ParticipationAnalizerMethodDescriptorSupplier("analyzerParticipation"))
              .build();
        }
      }
    }
    return getAnalyzerParticipationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.analyzer.CustomFeedbackRequest,
      generated.grpc.analyzer.CustomFeedbackReply> getSubmitCustomFeedbackMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "submitCustomFeedback",
      requestType = generated.grpc.analyzer.CustomFeedbackRequest.class,
      responseType = generated.grpc.analyzer.CustomFeedbackReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.analyzer.CustomFeedbackRequest,
      generated.grpc.analyzer.CustomFeedbackReply> getSubmitCustomFeedbackMethod() {
    io.grpc.MethodDescriptor<generated.grpc.analyzer.CustomFeedbackRequest, generated.grpc.analyzer.CustomFeedbackReply> getSubmitCustomFeedbackMethod;
    if ((getSubmitCustomFeedbackMethod = ParticipationAnalizerGrpc.getSubmitCustomFeedbackMethod) == null) {
      synchronized (ParticipationAnalizerGrpc.class) {
        if ((getSubmitCustomFeedbackMethod = ParticipationAnalizerGrpc.getSubmitCustomFeedbackMethod) == null) {
          ParticipationAnalizerGrpc.getSubmitCustomFeedbackMethod = getSubmitCustomFeedbackMethod =
              io.grpc.MethodDescriptor.<generated.grpc.analyzer.CustomFeedbackRequest, generated.grpc.analyzer.CustomFeedbackReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "submitCustomFeedback"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.analyzer.CustomFeedbackRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.analyzer.CustomFeedbackReply.getDefaultInstance()))
              .setSchemaDescriptor(new ParticipationAnalizerMethodDescriptorSupplier("submitCustomFeedback"))
              .build();
        }
      }
    }
    return getSubmitCustomFeedbackMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ParticipationAnalizerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ParticipationAnalizerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ParticipationAnalizerStub>() {
        @java.lang.Override
        public ParticipationAnalizerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ParticipationAnalizerStub(channel, callOptions);
        }
      };
    return ParticipationAnalizerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ParticipationAnalizerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ParticipationAnalizerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ParticipationAnalizerBlockingStub>() {
        @java.lang.Override
        public ParticipationAnalizerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ParticipationAnalizerBlockingStub(channel, callOptions);
        }
      };
    return ParticipationAnalizerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ParticipationAnalizerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ParticipationAnalizerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ParticipationAnalizerFutureStub>() {
        @java.lang.Override
        public ParticipationAnalizerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ParticipationAnalizerFutureStub(channel, callOptions);
        }
      };
    return ParticipationAnalizerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ParticipationAnalizerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *  Server streaming
     * </pre>
     */
    public void analyzerParticipation(generated.grpc.analyzer.ParticipationRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.analyzer.ParticipationStatistics> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAnalyzerParticipationMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.grpc.analyzer.CustomFeedbackRequest> submitCustomFeedback(
        io.grpc.stub.StreamObserver<generated.grpc.analyzer.CustomFeedbackReply> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getSubmitCustomFeedbackMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAnalyzerParticipationMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                generated.grpc.analyzer.ParticipationRequest,
                generated.grpc.analyzer.ParticipationStatistics>(
                  this, METHODID_ANALYZER_PARTICIPATION)))
          .addMethod(
            getSubmitCustomFeedbackMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                generated.grpc.analyzer.CustomFeedbackRequest,
                generated.grpc.analyzer.CustomFeedbackReply>(
                  this, METHODID_SUBMIT_CUSTOM_FEEDBACK)))
          .build();
    }
  }

  /**
   */
  public static final class ParticipationAnalizerStub extends io.grpc.stub.AbstractAsyncStub<ParticipationAnalizerStub> {
    private ParticipationAnalizerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ParticipationAnalizerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ParticipationAnalizerStub(channel, callOptions);
    }

    /**
     * <pre>
     *  Server streaming
     * </pre>
     */
    public void analyzerParticipation(generated.grpc.analyzer.ParticipationRequest request,
        io.grpc.stub.StreamObserver<generated.grpc.analyzer.ParticipationStatistics> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getAnalyzerParticipationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.grpc.analyzer.CustomFeedbackRequest> submitCustomFeedback(
        io.grpc.stub.StreamObserver<generated.grpc.analyzer.CustomFeedbackReply> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getSubmitCustomFeedbackMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class ParticipationAnalizerBlockingStub extends io.grpc.stub.AbstractBlockingStub<ParticipationAnalizerBlockingStub> {
    private ParticipationAnalizerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ParticipationAnalizerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ParticipationAnalizerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *  Server streaming
     * </pre>
     */
    public java.util.Iterator<generated.grpc.analyzer.ParticipationStatistics> analyzerParticipation(
        generated.grpc.analyzer.ParticipationRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getAnalyzerParticipationMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ParticipationAnalizerFutureStub extends io.grpc.stub.AbstractFutureStub<ParticipationAnalizerFutureStub> {
    private ParticipationAnalizerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ParticipationAnalizerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ParticipationAnalizerFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_ANALYZER_PARTICIPATION = 0;
  private static final int METHODID_SUBMIT_CUSTOM_FEEDBACK = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ParticipationAnalizerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ParticipationAnalizerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ANALYZER_PARTICIPATION:
          serviceImpl.analyzerParticipation((generated.grpc.analyzer.ParticipationRequest) request,
              (io.grpc.stub.StreamObserver<generated.grpc.analyzer.ParticipationStatistics>) responseObserver);
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
        case METHODID_SUBMIT_CUSTOM_FEEDBACK:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.submitCustomFeedback(
              (io.grpc.stub.StreamObserver<generated.grpc.analyzer.CustomFeedbackReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ParticipationAnalizerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ParticipationAnalizerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.analyzer.ParticipationAnalizerImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ParticipationAnalizer");
    }
  }

  private static final class ParticipationAnalizerFileDescriptorSupplier
      extends ParticipationAnalizerBaseDescriptorSupplier {
    ParticipationAnalizerFileDescriptorSupplier() {}
  }

  private static final class ParticipationAnalizerMethodDescriptorSupplier
      extends ParticipationAnalizerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ParticipationAnalizerMethodDescriptorSupplier(String methodName) {
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
      synchronized (ParticipationAnalizerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ParticipationAnalizerFileDescriptorSupplier())
              .addMethod(getAnalyzerParticipationMethod())
              .addMethod(getSubmitCustomFeedbackMethod())
              .build();
        }
      }
    }
    return result;
  }
}
