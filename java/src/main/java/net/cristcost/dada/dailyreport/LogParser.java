package net.cristcost.dada.dailyreport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogParser {

  public static final String ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ssz";
  private final String fileName;

  private final List<Request> requests;

  private final DateFormat format = new SimpleDateFormat(ISO_8601_FORMAT);

  public LogParser(String fileName) {
    this.fileName = fileName;
    this.requests = new ArrayList<Request>();
  }

  public List<Request> getRequests() {
    return requests;
  }

  public void parse() throws FileNotFoundException, IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        parseLine(line);
      }
    }
  }

  private void addRequest(Request request) {
    this.requests.add(request);
  }

  private Request createRequest(String timestampAsString, String bytesAsString,
      String statusCode, String host) {
    Date timeStamp = parseTimeStamp(timestampAsString);
    int bytes = Integer.parseInt(bytesAsString);
    return new Request(timeStamp, bytes, statusCode, host);
  }

  private void parseLine(String line) {
    String[] split = line.split(";");
    addRequest(createRequest(split[0], split[1], split[2], split[3]));
  }

  private Date parseTimeStamp(String timestampAsString) {
    try {
      return format.parse(timestampAsString);
    } catch (ParseException e) {
      throw new RuntimeException("Error parsing date: " + timestampAsString, e);
    }
  }

}
