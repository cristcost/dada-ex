package net.cristcost.data.dailyreport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyReport {

  public class ReportEntry {

    private final String hostAddress;
    int requests = 0;
    int bytes = 0;

    public ReportEntry(String hostAddress, int bytes, int requests) {
      this.hostAddress = hostAddress;
      this.requests = requests;
      this.bytes = bytes;
    }

    public int getBytes() {
      return bytes;
    }

    public double getBytesPercentage() {
      return bytes * 100.0 / DailyReport.this.totalBytes;
    }

    public String getHostAddress() {
      return hostAddress;
    }

    public int getRequests() {
      return requests;
    }

    public double getRequestsPercentage() {
      return requests * 100.0 / DailyReport.this.totalRequests;
    }

    public void logRequest(int newBytes) {
      requests++;
      bytes += newBytes;
    }
  }

  private Map<String, ReportEntry> entriesByIp = new HashMap<>();

  public int totalRequests = 0;
  public int totalBytes = 0;

  public List<ReportEntry> getEntries() {

    List<ReportEntry> ret = new ArrayList<>(entriesByIp.values());
    Comparator<ReportEntry> c = new Comparator<DailyReport.ReportEntry>() {

      @Override
      public int compare(ReportEntry o2, ReportEntry o1) {
        return (o1.requests < o2.requests) ? -1 : ((o1.requests == o2.requests) ? 0 : 1);
      }
    };
    Collections.<ReportEntry> sort(ret, c);
    return ret;
  }

  public void logAll(Collection<Request> requests) {
    for (Request request : requests) {
      if (request.getStatusCode().equals("200 OK")) {
        logRequest(request.getHost(), request.getBytes());
      }
    }
  }

  private void logRequest(String remoteAddress, int bytes) {
    if (entriesByIp.containsKey(remoteAddress)) {
      entriesByIp.get(remoteAddress).logRequest(bytes);
    } else {
      entriesByIp.put(remoteAddress, new ReportEntry(remoteAddress, bytes, 1));
    }
    totalBytes += bytes;
    totalRequests++;
  }

}