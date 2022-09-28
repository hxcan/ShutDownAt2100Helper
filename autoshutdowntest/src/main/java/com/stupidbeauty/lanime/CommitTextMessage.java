// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CommitTextMessage.proto

package com.stupidbeauty.lanime;

/**
 * Protobuf type {@code com.stupidbeauty.lanime.CommitTextMessage}
 */
public  final class CommitTextMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.stupidbeauty.lanime.CommitTextMessage)
    CommitTextMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CommitTextMessage.newBuilder() to construct.
  private CommitTextMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CommitTextMessage() {
    text_ = "";
    transactionId_ = 0L;
    beep_ = false;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CommitTextMessage(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            text_ = s;
            break;
          }
          case 16: {

            transactionId_ = input.readInt64();
            break;
          }
          case 24: {

            beep_ = input.readBool();
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.stupidbeauty.lanime.CommitTextMessageProtos.internal_static_com_stupidbeauty_lanime_CommitTextMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.stupidbeauty.lanime.CommitTextMessageProtos.internal_static_com_stupidbeauty_lanime_CommitTextMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.stupidbeauty.lanime.CommitTextMessage.class, com.stupidbeauty.lanime.CommitTextMessage.Builder.class);
  }

  public static final int TEXT_FIELD_NUMBER = 1;
  private volatile java.lang.Object text_;
  /**
   * <pre>
   *文字内容。
   * </pre>
   *
   * <code>string text = 1;</code>
   */
  public java.lang.String getText() {
    java.lang.Object ref = text_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      text_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *文字内容。
   * </pre>
   *
   * <code>string text = 1;</code>
   */
  public com.google.protobuf.ByteString
      getTextBytes() {
    java.lang.Object ref = text_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      text_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TRANSACTIONID_FIELD_NUMBER = 2;
  private long transactionId_;
  /**
   * <pre>
   *事务编号。
   * </pre>
   *
   * <code>int64 transactionId = 2;</code>
   */
  public long getTransactionId() {
    return transactionId_;
  }

  public static final int BEEP_FIELD_NUMBER = 3;
  private boolean beep_;
  /**
   * <pre>
   *是否要响声
   * </pre>
   *
   * <code>bool beep = 3;</code>
   */
  public boolean getBeep() {
    return beep_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getTextBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, text_);
    }
    if (transactionId_ != 0L) {
      output.writeInt64(2, transactionId_);
    }
    if (beep_ != false) {
      output.writeBool(3, beep_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getTextBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, text_);
    }
    if (transactionId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, transactionId_);
    }
    if (beep_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(3, beep_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.stupidbeauty.lanime.CommitTextMessage)) {
      return super.equals(obj);
    }
    com.stupidbeauty.lanime.CommitTextMessage other = (com.stupidbeauty.lanime.CommitTextMessage) obj;

    boolean result = true;
    result = result && getText()
        .equals(other.getText());
    result = result && (getTransactionId()
        == other.getTransactionId());
    result = result && (getBeep()
        == other.getBeep());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TEXT_FIELD_NUMBER;
    hash = (53 * hash) + getText().hashCode();
    hash = (37 * hash) + TRANSACTIONID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTransactionId());
    hash = (37 * hash) + BEEP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getBeep());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.stupidbeauty.lanime.CommitTextMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.stupidbeauty.lanime.CommitTextMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.stupidbeauty.lanime.CommitTextMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.stupidbeauty.lanime.CommitTextMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.stupidbeauty.lanime.CommitTextMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.stupidbeauty.lanime.CommitTextMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.stupidbeauty.lanime.CommitTextMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.stupidbeauty.lanime.CommitTextMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.stupidbeauty.lanime.CommitTextMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.stupidbeauty.lanime.CommitTextMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.stupidbeauty.lanime.CommitTextMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.stupidbeauty.lanime.CommitTextMessage parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.stupidbeauty.lanime.CommitTextMessage prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.stupidbeauty.lanime.CommitTextMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.stupidbeauty.lanime.CommitTextMessage)
      com.stupidbeauty.lanime.CommitTextMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.stupidbeauty.lanime.CommitTextMessageProtos.internal_static_com_stupidbeauty_lanime_CommitTextMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.stupidbeauty.lanime.CommitTextMessageProtos.internal_static_com_stupidbeauty_lanime_CommitTextMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.stupidbeauty.lanime.CommitTextMessage.class, com.stupidbeauty.lanime.CommitTextMessage.Builder.class);
    }

    // Construct using com.stupidbeauty.lanime.CommitTextMessage.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      text_ = "";

      transactionId_ = 0L;

      beep_ = false;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.stupidbeauty.lanime.CommitTextMessageProtos.internal_static_com_stupidbeauty_lanime_CommitTextMessage_descriptor;
    }

    @java.lang.Override
    public com.stupidbeauty.lanime.CommitTextMessage getDefaultInstanceForType() {
      return com.stupidbeauty.lanime.CommitTextMessage.getDefaultInstance();
    }

    @java.lang.Override
    public com.stupidbeauty.lanime.CommitTextMessage build() {
      com.stupidbeauty.lanime.CommitTextMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.stupidbeauty.lanime.CommitTextMessage buildPartial() {
      com.stupidbeauty.lanime.CommitTextMessage result = new com.stupidbeauty.lanime.CommitTextMessage(this);
      result.text_ = text_;
      result.transactionId_ = transactionId_;
      result.beep_ = beep_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.stupidbeauty.lanime.CommitTextMessage) {
        return mergeFrom((com.stupidbeauty.lanime.CommitTextMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.stupidbeauty.lanime.CommitTextMessage other) {
      if (other == com.stupidbeauty.lanime.CommitTextMessage.getDefaultInstance()) return this;
      if (!other.getText().isEmpty()) {
        text_ = other.text_;
        onChanged();
      }
      if (other.getTransactionId() != 0L) {
        setTransactionId(other.getTransactionId());
      }
      if (other.getBeep() != false) {
        setBeep(other.getBeep());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.stupidbeauty.lanime.CommitTextMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.stupidbeauty.lanime.CommitTextMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object text_ = "";
    /**
     * <pre>
     *文字内容。
     * </pre>
     *
     * <code>string text = 1;</code>
     */
    public java.lang.String getText() {
      java.lang.Object ref = text_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        text_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *文字内容。
     * </pre>
     *
     * <code>string text = 1;</code>
     */
    public com.google.protobuf.ByteString
        getTextBytes() {
      java.lang.Object ref = text_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        text_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *文字内容。
     * </pre>
     *
     * <code>string text = 1;</code>
     */
    public Builder setText(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      text_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *文字内容。
     * </pre>
     *
     * <code>string text = 1;</code>
     */
    public Builder clearText() {
      
      text_ = getDefaultInstance().getText();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *文字内容。
     * </pre>
     *
     * <code>string text = 1;</code>
     */
    public Builder setTextBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      text_ = value;
      onChanged();
      return this;
    }

    private long transactionId_ ;
    /**
     * <pre>
     *事务编号。
     * </pre>
     *
     * <code>int64 transactionId = 2;</code>
     */
    public long getTransactionId() {
      return transactionId_;
    }
    /**
     * <pre>
     *事务编号。
     * </pre>
     *
     * <code>int64 transactionId = 2;</code>
     */
    public Builder setTransactionId(long value) {
      
      transactionId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *事务编号。
     * </pre>
     *
     * <code>int64 transactionId = 2;</code>
     */
    public Builder clearTransactionId() {
      
      transactionId_ = 0L;
      onChanged();
      return this;
    }

    private boolean beep_ ;
    /**
     * <pre>
     *是否要响声
     * </pre>
     *
     * <code>bool beep = 3;</code>
     */
    public boolean getBeep() {
      return beep_;
    }
    /**
     * <pre>
     *是否要响声
     * </pre>
     *
     * <code>bool beep = 3;</code>
     */
    public Builder setBeep(boolean value) {
      
      beep_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *是否要响声
     * </pre>
     *
     * <code>bool beep = 3;</code>
     */
    public Builder clearBeep() {
      
      beep_ = false;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.stupidbeauty.lanime.CommitTextMessage)
  }

  // @@protoc_insertion_point(class_scope:com.stupidbeauty.lanime.CommitTextMessage)
  private static final com.stupidbeauty.lanime.CommitTextMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.stupidbeauty.lanime.CommitTextMessage();
  }

  public static com.stupidbeauty.lanime.CommitTextMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CommitTextMessage>
      PARSER = new com.google.protobuf.AbstractParser<CommitTextMessage>() {
    @java.lang.Override
    public CommitTextMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CommitTextMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CommitTextMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CommitTextMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.stupidbeauty.lanime.CommitTextMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

