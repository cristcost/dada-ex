package net.cristcost.data.dailyreport;

import net.cristcost.data.dailyreport.utils.TestLogsGenerator;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class DailyReportTest {

  private static String TEST_LOG_FILE_NAME = "target/requests.log";

  @Before
  public void setUp() throws FileNotFoundException {
    File logFile = new File(TEST_LOG_FILE_NAME);
    if (!logFile.exists()) {
      PrintStream filePrinter = new PrintStream(logFile);
      System.out.println("Generating test input file");
      TestLogsGenerator generator = new TestLogsGenerator(System.currentTimeMillis(),
          100, 512, 0.9, 10);
      generator.generate(100, filePrinter);
    }
  }

  @Test
  public void test() throws IOException {
    // DailyReport report = new DailyReport();
    // report.parse(TEST_LOG_FILE_NAME);
    // report.print(System.out);
  }

}
