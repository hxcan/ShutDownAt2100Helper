// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: LanImeFunctionName.proto

package com.stupidbeauty.lanime;

public final class AmqpFunctionNameProtos {
  private AmqpFunctionNameProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030LanImeFunctionName.proto\022\027com.stupidbe" +
      "auty.lanime*\252\t\n\022LanImeFunctionName\022\016\n\nCo" +
      "mmitText\020\000\022\r\n\tCheckTime\020\001\022\017\n\013SetLanguage" +
      "\020\002\022$\n ReportVoiceRecognizeProcessError\020\003" +
      "\022\032\n\026ReportAudioSoundSource\020\004\022\020\n\014CancelPh" +
      "enem\020\005\022\034\n\030SetAvatarBackgroundImage\020\006\022%\n!" +
      "Set3DEngineCameraPositionRotation\020\007\022\024\n\020S" +
      "etAvatarVisible\020\010\022\025\n\021SetAvatarGeometry\020\t" +
      "\022\031\n\025ExecutePhenemSequence\020\n\022\030\n\024ReloadWeb" +
      "Application\020\013\022\017\n\013InputAction\020\014\022\023\n\017InputE" +
      "xpression\020\r\022\023\n\017TerminateAction\020\016\022\033\n\027SetA" +
      "vatarCameraPosition\020\017\022\032\n\026ExecuteAvatarEf" +
      "fection\020\020\022\025\n\021HideCameraPreview\020\021\022\026\n\022Exec" +
      "uteAvatarMacro\020\022\022\025\n\021ShowCameraPreview\020\023\022" +
      "\023\n\017StartFaceDetect\020\024\022\026\n\022StartFaceRecogni" +
      "ze\020\025\022\022\n\016StartFrameVoip\020\026\022\023\n\017StartQrCodeS" +
      "can\020\027\022\033\n\027StartExtQrOcrHybridScan\020\030\022\032\n\026St" +
      "opExtQrOcrHybridScan\020\031\022\022\n\016StopQrCodeScan" +
      "\020\032\022\010\n\004Quit\020\035\022\031\n\025ReportVoiceRecognized\020\037\022" +
      "\037\n\033ReportQrOcrHybridScanResult\020 \022\032\n\026Repo" +
      "rtQrCodeScanResult\020!\022\035\n\031ReportFaceRecogn" +
      "izeResult\020\"\022\022\n\016ReportFaceGone\020#\022\026\n\022Repor" +
      "tFaceDetected\020$\022\032\n\026ReportFaceDetectUpdat" +
      "e\020%\022\030\n\024ReportSpeechFinished\020&\022\027\n\023ReportS" +
      "peechStarted\020\'\022\037\n\033ReportHumanBodyDetectS" +
      "tatus\020(\022\034\n\030GetHumanBodyDetectStatus\020)\022!\n" +
      "\035ReplyGetHumanBodyDetectStatus\020*\022\033\n\027SetV" +
      "oiceRecognizeEngine\020+\022\035\n\031CardReaderGetDe" +
      "viceStatus\020,\022\031\n\025CardReaderGetCardInfo\020-\022" +
      "\032\n\026CardReaderStopReadCard\020.\022\024\n\020CancelPen" +
      "dingTts\020/\022\t\n\005Speak\0200\022\032\n\026StopArchiveSound" +
      "Record\0201B3\n\027com.stupidbeauty.lanimeB\026Amq" +
      "pFunctionNameProtosP\001b\006proto3"
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
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}