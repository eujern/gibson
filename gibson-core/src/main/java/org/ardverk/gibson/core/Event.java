package org.ardverk.gibson.core;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Index;
import com.google.code.morphia.annotations.Indexes;

@Entity(Event.COLLECTION)
@Indexes({
  @Index("logger"),
  @Index("level"),
  @Index("message"),
  @Index("signature")
})
public class Event {
  
  public static final String COLLECTION = "Events";
  
  private static final StackTraceElement[] EMPTY = new StackTraceElement[0];
  
  @Id
  private ObjectId id;
  
  private Date creationTime;
  
  private String logger;
  
  private String marker;
  
  private Level level;
  
  private String thread;
  
  private String message;
  
  private Condition condition;
  
  private StackTraceElement[] callerData = EMPTY;
  
  private Map<String, String> mdc;
  
  private String signature;
  
  public ObjectId getId() {
    return id;
  }
  
  public void setId(ObjectId id) {
    this.id = id;
  }

  public Date getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(Date creationTime) {
    this.creationTime = creationTime;
  }

  public String getLogger() {
    return logger;
  }

  public void setLogger(String logger) {
    this.logger = logger;
  }

  public String getMarker() {
    return marker;
  }

  public void setMarker(String marker) {
    this.marker = marker;
  }

  public Level getLevel() {
    return level;
  }

  public void setLevel(Level level) {
    this.level = level;
  }
  
  public String getThread() {
    return thread;
  }

  public void setThread(String thread) {
    this.thread = thread;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Condition getCondition() {
    return condition;
  }

  public void setCondition(Condition condition) {
    this.condition = condition;
  }
  
  public StackTraceElement[] getCallerData() {
    return callerData;
  }

  public void setCallerData(StackTraceElement[] callerData) {
    if (callerData == null) {
      throw new NullPointerException("callerData");
    }
    this.callerData = callerData;
  }

  public Map<String, String> getMdc() {
    return mdc;
  }

  public void setMdc(Map<String, String> mdc) {
    this.mdc = mdc;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this).toString();
  }
}