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
  private static volatile io.grpc.MethodDescriptor<generated.grpc.analyzer.ParticipationEntry,
      generated.grpc.analyzer.ParticipationStatistics> getTrackerParticipationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "trackerParticipation",
      requestType = generated.grpc.analyzer.ParticipationEntry.class,
      responseType = generated.grpc.analyzer.ParticipationStatistics.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.analyzer.ParticipationEntry,
      generated.grpc.analyzer.ParticipationStatistics> getTrackerParticipationMethod() {
    io.grpc.MethodDescriptor<generated.grpc.analyzer.ParticipationEntry, generated.grpc.analyzer.ParticipationStatistics> getTrackerParticipationMethod;
    if ((getTrackerParticipationMethod = ParticipationAnalizerGrpc.getTrackerParticipationMethod) == null) {
      synchronized (ParticipationAnalizerGrpc.class) {
        if ((getTrackerParticipationMethod = ParticipationAnalizerGrpc.getTrackerParticipationMethod) == null) {
          ParticipationAnalizerGrpc.getTrackerParticipationMethod = getTrackerParticipationMethod =
              io.grpc.MethodDescriptor.<generated.grpc.analyzer.ParticipationEntry, generated.grpc.analyzer.ParticipationStatistics>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "trackerParticipation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.analyzer.ParticipationEntry.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.analyzer.ParticipationStatistics.getDefaultInstance()))
              .setSchemaDescriptor(new ParticipationAnalizerMethodDescriptorSupplier("trackerParticipation"))
              .build();
        }
      }
    }
    return getTrackerParticipationMethod;
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
     *  Client Streaming - The service allow the students to stream multiple participation records in one section.
     * Client stream multiple participation registrations (one by one) and the server responds once
     * by sending participation statistics by gender.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.analyzer.ParticipationEntry> trackerParticipation(
        io.grpc.stub.StreamObserver<generated.grpc.analyzer.ParticipationStatistics> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getTrackerParticipationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getTrackerParticipationMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                generated.grpc.analyzer.ParticipationEntry,
                generated.grpc.analyzer.ParticipationStatistics>(
                  this, METHODID_TRACKER_PARTICIPATION)))
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
     *  Client Streaming - The service allow the students to stream multiple participation records in one section.
     * Client stream multiple participation registrations (one by one) and the server responds once
     * by sending participation statistics by gender.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.analyzer.ParticipationEntry> trackerParticipation(
        io.grpc.stub.StreamObserver<generated.grpc.analyzer.ParticipationStatistics> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getTrackerParticipationMethod(), getCallOptions()), responseObserver);
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

  private static final int METHODID_TRACKER_PARTICIPATION = 0;

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
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TRACKER_PARTICIPATION:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.trackerParticipation(
              (io.grpc.stub.StreamObserver<generated.grpc.analyzer.ParticipationStatistics>) responseObserver);
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
              .addMethod(getTrackerParticipationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
