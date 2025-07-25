// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ParticipationAnalizer.proto

package generated.grpc.analyzer;

public interface ParticipationStatisticsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ParticipationAnalizer.ParticipationStatistics)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Percentage of task done by males
   * </pre>
   *
   * <code>float malePercentage = 1;</code>
   * @return The malePercentage.
   */
  float getMalePercentage();

  /**
   * <pre>
   * Percentage of task done by female
   * </pre>
   *
   * <code>float femalePercentage = 2;</code>
   * @return The femalePercentage.
   */
  float getFemalePercentage();

  /**
   * <pre>
   * Summary of percentages of both genders
   * </pre>
   *
   * <code>string summary = 3;</code>
   * @return The summary.
   */
  java.lang.String getSummary();
  /**
   * <pre>
   * Summary of percentages of both genders
   * </pre>
   *
   * <code>string summary = 3;</code>
   * @return The bytes for summary.
   */
  com.google.protobuf.ByteString
      getSummaryBytes();
}
