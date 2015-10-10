package net.cristcost.dada.dailyreport;

import java.util.Date;

public class Request {

  private final Date timestamp;
  private final int bytes;
  private final String statusCode;
  private final String host;

  public Request(Date timestamp, int bytes, String statusCode, String host) {
    this.timestamp = timestamp;
    this.bytes = bytes;
    this.statusCode = statusCode;
    this.host = host;
  };

  public int getBytes() {
    return bytes;
  }

  public String getHost() {
    return host;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public Date getTimestamp() {
    return timestamp;
  }

}
