package net.cristcost.dada.dailyreport;

import net.cristcost.dada.dailyreport.DailyReport;
import net.cristcost.dada.dailyreport.Request;
import net.cristcost.dada.dailyreport.DailyReport.ReportEntry;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

public class DailyReportTest {

  private enum Status {
    OK("200 OK"),
    BAD_REQUEST("400 Bad Request");

    private final String status;

    private Status(String status) {
      this.status = status;
    }

    public String getStatus() {
      return status;
    }
  }

  private static final String HOST_1 = "127.0.0.1";
  private static final String HOST_2 = "127.0.0.2";
  private static final String HOST_3 = "127.0.0.3";
  private static final String HOST_4 = "127.0.0.4";
  private static final String HOST_5 = "127.0.0.5";

  private Request req(String host, int bytes, Status status) {
    Request ret = new Request(new Date(), bytes, status.getStatus(), host);
    return ret;
  }

  @Test
  public void emptyReport() {
    DailyReport report = new DailyReport();
    assertEquals(0, report.getTotalBytes());
    assertEquals(0, report.getTotalRequests());
    assertTrue(report.getEntries().isEmpty());
  }

  @Test
  public void oneLogReport() {
    DailyReport report = new DailyReport();
    report.log(req(HOST_1, 100, Status.OK));
    assertEquals(100, report.getTotalBytes());
    assertEquals(1, report.getTotalRequests());
    assertEquals(1, report.getEntries().size());
  }

  @Test
  public void oneBadLogReport() {
    DailyReport report = new DailyReport();
    report.log(req(HOST_1, 100, Status.BAD_REQUEST));
    assertEquals(0, report.getTotalBytes());
    assertEquals(0, report.getTotalRequests());
    assertTrue(report.getEntries().isEmpty());
  }

  @Test
  public void tenLogsSameHostReport() {
    DailyReport report = new DailyReport();
    report.log(req(HOST_1, 100, Status.OK));
    report.log(req(HOST_1, 100, Status.OK));
    report.log(req(HOST_1, 100, Status.OK));
    report.log(req(HOST_1, 100, Status.OK));
    report.log(req(HOST_1, 100, Status.OK));
    assertEquals(500, report.getTotalBytes());
    assertEquals(5, report.getTotalRequests());
    assertEquals(1, report.getEntries().size());
  }

  @Test
  public void fiveLogsDifferentHostReport() {
    DailyReport report = new DailyReport();
    report.log(req(HOST_1, 100, Status.OK));
    report.log(req(HOST_2, 100, Status.OK));
    report.log(req(HOST_3, 100, Status.OK));
    report.log(req(HOST_4, 100, Status.OK));
    report.log(req(HOST_5, 100, Status.OK));
    assertEquals(500, report.getTotalBytes());
    assertEquals(5, report.getTotalRequests());
    assertEquals(5, report.getEntries().size());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void fiveHostsSortedByRequestsReport() {
    DailyReport report = new DailyReport();

    report.log(req(HOST_1, 100, Status.OK));
    report.log(req(HOST_2, 100, Status.OK));
    report.log(req(HOST_3, 100, Status.OK));
    report.log(req(HOST_4, 100, Status.OK));
    report.log(req(HOST_5, 100, Status.OK));

    report.log(req(HOST_2, 100, Status.OK));
    report.log(req(HOST_3, 100, Status.OK));
    report.log(req(HOST_4, 100, Status.OK));
    report.log(req(HOST_5, 100, Status.OK));

    report.log(req(HOST_3, 100, Status.OK));
    report.log(req(HOST_4, 100, Status.OK));
    report.log(req(HOST_5, 100, Status.OK));

    report.log(req(HOST_4, 100, Status.OK));
    report.log(req(HOST_5, 100, Status.OK));

    report.log(req(HOST_5, 100, Status.OK));

    assertEquals(5, report.getEntries().size());

    // Check the order
    List<ReportEntry> entries = report.getEntries();
    assertThat(entries,
        contains(
            hasProperty("hostAddress", equalTo(HOST_5)),
            hasProperty("hostAddress", equalTo(HOST_4)),
            hasProperty("hostAddress", equalTo(HOST_3)),
            hasProperty("hostAddress", equalTo(HOST_2)),
            hasProperty("hostAddress", equalTo(HOST_1))));
  }

}
