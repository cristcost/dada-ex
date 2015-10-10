package net.cristcost.data.dailyreport;

import net.cristcost.data.dailyreport.utils.TestLogsGenerator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class LogParserTest {

  private static final int NUMBER_OF_LOG_LINES = 100;
  private static String TEST_LOG_FILE_NAME = "target/requests.log";

  @Before
  public void setUp() throws FileNotFoundException {
    File logFile = new File(TEST_LOG_FILE_NAME);
    if (!logFile.exists()) {
      PrintStream filePrinter = new PrintStream(logFile);
      System.out.println("Generating test input file");
      TestLogsGenerator generator = new TestLogsGenerator(System.currentTimeMillis(),
          100, 512, 0.9, 10);
      generator.generate(NUMBER_OF_LOG_LINES, filePrinter);
    }
  }

  @Test
  public void test() throws IOException {
    LogParser parser = new LogParser(TEST_LOG_FILE_NAME);
    parser.parse();
    assertEquals(NUMBER_OF_LOG_LINES, parser.getRequests().size());
  }

}
