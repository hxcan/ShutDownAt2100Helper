package com.stupidbeauty.shutdownat2100;

import com.upokecenter.cbor.CBORException;
import com.upokecenter.cbor.CBORObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import java.io.ByteArrayOutputStream;

/**
 * Protobuf type {@code com.stupidbeauty.shutdownat2100.Sda2Message}
 */
public  final class Sda2Message 
{
  private static final long serialVersionUID = 0L;
  
  public void setTimeStamp(long timeStamp)
  {
    timeStamp_=timeStamp;
  } // public void setTimeStamp(long timeStamp)
  
  /**
  * 解析消息。
  */
  public static Sda2Message parseFrom(byte[] messageContent)
  {
    Sda2Message result=null; // Result;

    try
    {
      CBORObject videoStreamMessage= CBORObject.DecodeFromBytes(messageContent); //解析消息。

      int voiceCommand=videoStreamMessage.get("functionName").AsInt32();
      
      CBORObject timestampObject = videoStreamMessage.get("timeStamp");
      long timestampeUrl = 0;
      
      if (timestampObject!=null) // The object exists
      {
        timestampeUrl = timestampObject.AsInt64();
      } // if (timestampObject!=null) // The object exists
      
      CBORObject versionNameObject=videoStreamMessage.get("shutDownAt2100ConfigurationMessage");
      
      ShutDownAt2100ConfigurationMessage shutDownAt2100ConfigurationMessage=ShutDownAt2100ConfigurationMessage.parseFrom(versionNameObject); 

      
      result=new Sda2Message(); // Result;
      result.setFunctionName(voiceCommand);
      result.setShutDownAt2100ConfigurationMessage(shutDownAt2100ConfigurationMessage);
      result.setTimeStamp(timestampeUrl);
    
    } // try
    catch (CBORException e)
    {
      e.printStackTrace();
    } // catch (CBORException e)

    return result;
  } // public static Sda2Message parseFrom(byte[] messageContent)

  /**
  *  设置函数名字，配置文件。
  */
  public  void setFunctionName(int functionNameShutDownAt2100Configuration)
  {
    functionName_=functionNameShutDownAt2100Configuration;
  } // public  void setFunctionName(String functionNameShutDownAt2100Configuration)
  
  /**
  * 设置关机消息。
  */
  public void setShutDownAt2100ConfigurationMessage(ShutDownAt2100ConfigurationMessage shutDownAt2100ConfigurationBuilder)
  {
    this.shutDownAt2100ConfigurationMessage_=shutDownAt2100ConfigurationBuilder; // Remember.
  } // public void setShutDownAt2100ConfigurationMessage(ShutDownAt2100ConfigurationMessage shutDownAt2100ConfigurationBuilder)

  public static final int FUNCTIONNAME_FIELD_NUMBER = 1;
  private int functionName_;
  /**
   * <pre>
   *要执行的函数名字。
   * </pre>
   *
   * <code>.com.stupidbeauty.shutdownat2100.Sda2FunctionName functionName = 1;</code>
   */
  public int getFunctionNameValue() {
    return functionName_;
  }
  /**
   * <pre>
   *要执行的函数名字。
   * </pre>
   *
   * <code>.com.stupidbeauty.shutdownat2100.Sda2FunctionName functionName = 1;</code>
   */
  public int getFunctionName() 
  {
    int result = (functionName_);
    return result;
  } // public com.stupidbeauty.shutdownat2100.Sda2FunctionName getFunctionName() 

  public static final int SHUTDOWNAT2100CONFIGURATIONMESSAGE_FIELD_NUMBER = 2;
  private com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessage shutDownAt2100ConfigurationMessage_;
  /**
   * <pre>
   *配置文件信息。
   * </pre>
   *
   * <code>.com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessage shutDownAt2100ConfigurationMessage = 2;</code>
   */
  public boolean hasShutDownAt2100ConfigurationMessage() {
    return shutDownAt2100ConfigurationMessage_ != null;
  }
  /**
   * <pre>
   *配置文件信息。
   * </pre>
   *
   * <code>.com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessage shutDownAt2100ConfigurationMessage = 2;</code>
   */
  public com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessage getShutDownAt2100ConfigurationMessage() {
    return shutDownAt2100ConfigurationMessage_ == null ? com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessage.getDefaultInstance() : shutDownAt2100ConfigurationMessage_;
  }

  public static final int TIMESTAMP_FIELD_NUMBER = 31;
  private long timeStamp_;
  /**
   * <pre>
   *消息的时间戳。
   * </pre>
   *
   * <code>int64 timeStamp = 31;</code>
   */
  public long getTimeStamp() {
    return timeStamp_;
  }

  private byte memoizedIsInitialized = -1;

  // @@protoc_insertion_point(class_scope:com.stupidbeauty.shutdownat2100.Sda2Message)
  private static final com.stupidbeauty.shutdownat2100.Sda2Message DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.stupidbeauty.shutdownat2100.Sda2Message();
  }

  public static com.stupidbeauty.shutdownat2100.Sda2Message getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
}

