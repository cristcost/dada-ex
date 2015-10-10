/*
 * Copyright 2015, Cristiano Costantini
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.cristcost.dada.dailyreport;

import net.cristcost.dada.dailyreport.DailyReport.ReportEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class DailyReportApp {

  public static void main(String[] args) throws FileNotFoundException, IOException {

    if (args.length < 1) {
      dumpUsage(null);
    }
    if (!validateInputFile(args[0])) {
      dumpUsage("Input file does not exist");
    }

    makeReport(args[0], args[1]);
  }

  private static boolean validateInputFile(String fileName) {
    File file = new File(fileName);
    return file.exists();
  }

  private static void dumpUsage(String error) {
    if (error != null) {
      System.out.println("Error: " + error);
      System.out.println();
    }
    System.out.println("Usage:   dailyreport.sh inputfile [outputfile]");
    System.out.println("Example: dailyreport.sh  /logfiles/requests.log /reports/ipaddr.csv");
    System.exit(0);
  }

  private static void makeReport(String inputFileName, String outputFileName)
      throws FileNotFoundException, IOException {
    LogParser parser = new LogParser(inputFileName);
    parser.parse();

    DailyReport report = new DailyReport();
    report.logAll(parser.getRequests());

    List<ReportEntry> entries = report.getEntries();

    CsvDailyReport csv;
    if (outputFileName == null) {
      csv = new CsvDailyReport(System.out);
    } else {
      File file = new File(outputFileName);
      PrintStream filePrinter = new PrintStream(file);
      csv = new CsvDailyReport(filePrinter);
    }
    csv.addEntries(entries);
  }
}
