// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Sda2Message.proto

package com.stupidbeauty.shutdownat2100;

public interface AmqpMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.stupidbeauty.shutdownat2100.AmqpMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *要执行的函数名字。
   * </pre>
   *
   * <code>.com.stupidbeauty.shutdownat2100.Sda2FunctionName functionName = 1;</code>
   */
  int getFunctionNameValue();
  /**
   * <pre>
   *要执行的函数名字。
   * </pre>
   *
   * <code>.com.stupidbeauty.shutdownat2100.Sda2FunctionName functionName = 1;</code>
   */
  com.stupidbeauty.shutdownat2100.Sda2FunctionName getFunctionName();

  /**
   * <pre>
   *配置文件信息。
   * </pre>
   *
   * <code>.com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessage shutDownAt2100ConfigurationMessage = 2;</code>
   */
  boolean hasShutDownAt2100ConfigurationMessage();
  /**
   * <pre>
   *配置文件信息。
   * </pre>
   *
   * <code>.com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessage shutDownAt2100ConfigurationMessage = 2;</code>
   */
  com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessage getShutDownAt2100ConfigurationMessage();
  /**
   * <pre>
   *配置文件信息。
   * </pre>
   *
   * <code>.com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessage shutDownAt2100ConfigurationMessage = 2;</code>
   */
  com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessageOrBuilder getShutDownAt2100ConfigurationMessageOrBuilder();

  /**
   * <pre>
   *消息的时间戳。
   * </pre>
   *
   * <code>int64 timeStamp = 31;</code>
   */
  long getTimeStamp();
}
