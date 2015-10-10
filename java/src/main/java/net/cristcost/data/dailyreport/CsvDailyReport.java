package net.cristcost.data.dailyreport;

import net.cristcost.data.dailyreport.DailyReport.ReportEntry;

import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

public class CsvDailyReport {

  private final PrintStream out;

  public CsvDailyReport(PrintStream out) {
    this.out = out;
  }

  public void addEntries(List<ReportEntry> entries) {
    printHeader(out);
    for (ReportEntry reportEntry : entries) {
      out.println(formatCsv(reportEntry));
    }
  }

  public void close() {
  }

  private String formatCsv(ReportEntry reportEntry) {
    StringBuilder sb = new StringBuilder();
    sb.append(reportEntry.getHostAddress());
    sb.append(", ");
    sb.append(reportEntry.getRequests());
    sb.append(", ");
    // CSV with comma separation is English locale
    sb.append(String.format(Locale.ENGLISH, "%.2f%%", reportEntry.getRequestsPercentage()));
    sb.append(", ");
    sb.append(reportEntry.getBytes());
    sb.append(", ");
    sb.append(String.format("%.3f", reportEntry.getBytesPercentage()));
    return sb.toString();
  }

  private void printHeader(PrintStream out) {
    out.println("IP Address,Number of Requests,Percentage,Total Bytes, Percentage");
  }

}
