package net.cristcost.data.dailyreport.utils;

import net.cristcost.data.dailyreport.LogParser;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestLogsGenerator {

  private static String[] statusCodes = new String[] { "200 OK", "201 Created", "202 Accepted",
      "203 Non-Authoritative Information", "204 No Content", "205 Reset Content",
      "206 Partial Content", "300 Multiple Choices", "301 Moved Permanently", "302 Found",
      "303 See Other", "304 Not Modified", "305 Use Proxy", "307 Temporary Redirect",
      "400 Bad Request", "401 Unauthorized", "402 Payment Required", "403 Forbidden",
      "404 Not Found", "405 Method Not Allowed", "406 Not Acceptable",
      "407 Proxy Authentication Required", "408 Request Timeout", "409 Conflict", "410 Gone",
      "411 Length Required", "412 Precondition Failed", "413 Request Entity Too Large",
      "414 Request-URI Too Long", "415 Unsupported Media Type",
      "416 Requested Range Not Satisfiable", "417 Expectation Failed",
      "500 Internal Server Error", "501 Not Implemented", "502 Bad Gateway",
      "503 Service Unavailable", "504 Gateway Timeout", "505 HTTP Version Not Supported" };

  private final int averageBytes;
  private final double rateOf200Ok;
  private final String[] remoteHosts;

  private final Random random;

  private long lastEntryTime;

  private final DateFormat format = new SimpleDateFormat(LogParser.ISO_8601_FORMAT);

  private final long averageRequestInterval;

  public TestLogsGenerator(long initialTime, long averageRequestInterval, int averageBytes,
      double rateOf200Ok, int numberOfRemoteHosts) {
    this.random = new Random();

    this.lastEntryTime = initialTime;
    this.averageRequestInterval = averageRequestInterval;
    this.averageBytes = averageBytes;
    this.rateOf200Ok = rateOf200Ok;
    this.remoteHosts = randomizeHosts(numberOfRemoteHosts);
  }

  public void generate(int numEntries, PrintStream out) {
    for (int i = 0; i < numEntries; i++) {
      StringBuilder sb = new StringBuilder();
      sb.append(generateTimeStamp());
      sb.append(";");
      sb.append(generateBytes());
      sb.append(";");
      sb.append(generateStatusCode());
      sb.append(";");
      sb.append(generateHost());

      out.println(sb.toString());
    }
  }

  private int generateBytes() {
    // gaussian distribution around averageBytes, minimum 0
    return Math.max(0, (int) (averageBytes * (1.0 + random.nextGaussian())));
  }

  private String generateHost() {
    return remoteHosts[random.nextInt(remoteHosts.length)];
  }

  private String generateStatusCode() {
    if (random.nextDouble() > rateOf200Ok) {
      return statusCodes[1 + random.nextInt(statusCodes.length - 1)];
    } else {
      return statusCodes[0];
    }
  }

  private String generateTimeStamp() {
    // increase time with gaussian distribution, minimum 1 millisec
    lastEntryTime +=
        Math.max(1, (int) (averageRequestInterval * (1.0 + random.nextGaussian())));

    return format.format(new Date(lastEntryTime));
  }

  private String randomizeHost() {
    StringBuilder sb = new StringBuilder();

    // Only class C networks to ensure valid addresses ;)
    sb.append(192 + random.nextInt(32));
    sb.append(".");
    sb.append(random.nextInt(256));
    sb.append(".");
    sb.append(random.nextInt(256));
    sb.append(".");
    // x.x.x.0 is network address, x.x.x.255 is broadcast so avoid these IP
    sb.append(random.nextInt(254) + 1);
    return sb.toString();
  }

  private String[] randomizeHosts(int numberOfRemoteHosts) {
    String[] ret = new String[numberOfRemoteHosts];
    for (int i = 0; i < numberOfRemoteHosts; i++) {
      ret[i] = randomizeHost();
    }
    return ret;
  }
}
