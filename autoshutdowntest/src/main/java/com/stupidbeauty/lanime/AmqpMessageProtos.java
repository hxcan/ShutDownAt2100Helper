// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: LanImeMessage.proto

package com.stupidbeauty.lanime;

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
    internal_static_com_stupidbeauty_lanime_LanImeMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_stupidbeauty_lanime_LanImeMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023LanImeMessage.proto\022\027com.stupidbeauty." +
      "lanime\032\030LanImeFunctionName.proto\032\027Commit" +
      "TextMessage.proto\"\254\001\n\rLanImeMessage\022A\n\014f" +
      "unctionName\030\001 \001(\0162+.com.stupidbeauty.lan" +
      "ime.LanImeFunctionName\022E\n\021commitTextMess" +
      "age\030\002 \001(\0132*.com.stupidbeauty.lanime.Comm" +
      "itTextMessage\022\021\n\ttimeStamp\030\037 \001(\003B.\n\027com." +
      "stupidbeauty.lanimeB\021AmqpMessageProtosP\001" +
      "b\006proto3"
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
          com.stupidbeauty.lanime.AmqpFunctionNameProtos.getDescriptor(),
          com.stupidbeauty.lanime.CommitTextMessageProtos.getDescriptor(),
        }, assigner);
    internal_static_com_stupidbeauty_lanime_LanImeMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_stupidbeauty_lanime_LanImeMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_stupidbeauty_lanime_LanImeMessage_descriptor,
        new java.lang.String[] { "FunctionName", "CommitTextMessage", "TimeStamp", });
    com.stupidbeauty.lanime.AmqpFunctionNameProtos.getDescriptor();
    com.stupidbeauty.lanime.CommitTextMessageProtos.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
