package net.cristcost.data.dailyreport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DailyReport {

  public class RequestEntry {

    private int requests = 0;
    private int bytes = 0;

    public RequestEntry(int bytes, int requests) {
      this.requests = requests;
      this.bytes = bytes;
    }

    public void logRequest(int newBytes) {
      requests++;
      bytes += newBytes;
    }
  }

  private Map<String, RequestEntry> entries = new HashMap<>();

  public int totalRequests = 0;
  public int totalBytes = 0;

  public void parse(String fileName) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        parseLine(line);
      }
    }
  }

  public void print(PrintStream out) {
    printHeader(out);
    for (Entry<String, RequestEntry> entry : entries.entrySet()) {

      int requests = entry.getValue().requests;
      int bytes = entry.getValue().bytes;

      StringBuilder sb = new StringBuilder();
      sb.append(entry.getKey());
      sb.append(",");
      sb.append(requests);
      sb.append(",");
      sb.append(requests * 100.0 / totalRequests);
      sb.append(",");
      sb.append(bytes);
      sb.append(",");
      sb.append(bytes * 100.0 / totalBytes);

      out.println(sb.toString());
    }
  }

  private void printHeader(PrintStream out) {
    System.out.println("IP Address,Number of Requests,Percentage,Total Bytes, Percentage");
  }

  private void logRequest(String remoteAddress, int bytes) {
    if (entries.containsKey(remoteAddress)) {
      entries.get(remoteAddress).logRequest(bytes);
    } else {
      entries.put(remoteAddress, new RequestEntry(bytes, 1));
    }
  }

  private void parseLine(String line) {
    String[] split = line.split(";");
    parseLine(split[0], split[1], split[2], split[3]);
  }

  private void parseLine(String timestamp, String bytesAsString, String status,
      String remoteAddress) {
    if (status.equals("200 OK")) {
      int bytes = Integer.parseInt(bytesAsString);
      totalBytes += bytes;
      totalRequests++;
      logRequest(remoteAddress, bytes);
    }
  }
}