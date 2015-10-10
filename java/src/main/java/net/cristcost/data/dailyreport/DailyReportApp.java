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
package net.cristcost.data.dailyreport;

import net.cristcost.data.dailyreport.DailyReport.ReportEntry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class DailyReportApp {

  public static void main(String[] args) throws FileNotFoundException, IOException {

    // String fileName = args[0];
    String fileName = "target/requests.log";

    LogParser parser = new LogParser(fileName);
    parser.parse();

    DailyReport report = new DailyReport();
    report.logAll(parser.getRequests());

    List<ReportEntry> entries = report.getEntries();

    CsvDailyReport csv = new CsvDailyReport(System.out);
    csv.addEntries(entries);
    csv.close();
  }
}
