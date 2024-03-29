package net.cristcost.dada.dailyreport;

import net.cristcost.dada.dailyreport.DailyReport.ReportEntry;

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

  private String formatCsv(ReportEntry reportEntry) {
    StringBuilder sb = new StringBuilder();
    sb.append(reportEntry.getHostAddress());
    sb.append(";");
    sb.append(reportEntry.getRequests());
    sb.append(";");
    sb.append(String.format(Locale.ITALIAN, "%.2f%%", reportEntry.getRequestsPercentage()));
    sb.append(";");
    sb.append(reportEntry.getBytes());
    sb.append(";");
    sb.append(String.format("%.2f%%", reportEntry.getBytesPercentage()));
    return sb.toString();
  }

  private void printHeader(PrintStream out) {
    out.println("IP Address;Requests;Total Requests %;Bytes;Total Bytes %");
  }

}
