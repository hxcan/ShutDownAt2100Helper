// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Sda2Message.proto

package com.stupidbeauty.shutdownat2100;

public final class AmqpMessageProtos {
  private AmqpMessageProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_stupidbeauty_shutdownat2100_AmqpMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_stupidbeauty_shutdownat2100_AmqpMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021Sda2Message.proto\022\037com.stupidbeauty.sh" +
      "utdownat2100\032\026Sda2FunctionName.proto\032(Sh" +
      "utDownAt2100ConfigurationMessage.proto\"\332" +
      "\001\n\013AmqpMessage\022G\n\014functionName\030\001 \001(\01621.c" +
      "om.stupidbeauty.shutdownat2100.Sda2Funct" +
      "ionName\022o\n\"shutDownAt2100ConfigurationMe" +
      "ssage\030\002 \001(\0132C.com.stupidbeauty.shutdowna" +
      "t2100.ShutDownAt2100ConfigurationMessage" +
      "\022\021\n\ttimeStamp\030\037 \001(\003B6\n\037com.stupidbeauty." +
      "shutdownat2100B\021AmqpMessageProtosP\001b\006pro" +
      "to3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.stupidbeauty.shutdownat2100.AmqpFunctionNameProtos.getDescriptor(),
          com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessageProtos.getDescriptor(),
        }, assigner);
    internal_static_com_stupidbeauty_shutdownat2100_AmqpMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_stupidbeauty_shutdownat2100_AmqpMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_stupidbeauty_shutdownat2100_AmqpMessage_descriptor,
        new java.lang.String[] { "FunctionName", "ShutDownAt2100ConfigurationMessage", "TimeStamp", });
    com.stupidbeauty.shutdownat2100.AmqpFunctionNameProtos.getDescriptor();
    com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessageProtos.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
