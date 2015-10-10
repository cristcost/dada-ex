package net.cristcost.data.dailyreport;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Options;

public class GenerationOptions {

  public static GenerationOptions parseCommandLine(String[] args) {

    GenerationOptions launcherOptions = new GenerationOptions();

    Options options = new Options();
    options.addOption("I", "reqInterval", true,
        "Average interval between requests in seconds");
    options.addOption("B", "bytes", true, "Average bytes of each request");
    options.addOption("O", "okRate", true,
        "Rate of succesfull (200 OK) requests (between 0.0 and 1.0)");
    options.addOption("H", "hosts", true, "Number of hosts");

    CommandLineParser parser = new DefaultParser();
    try {
      CommandLine cmd = parser.parse(options, args);

      String[] leftOverOptions = cmd.getArgs();
      if (leftOverOptions == null || leftOverOptions.length < 1) {
        printHelpAndExit(options);
      } else {
        launcherOptions.requests = Integer.parseInt(leftOverOptions[0]);
        if (leftOverOptions.length >= 2) {
          launcherOptions.fileName = leftOverOptions[1];
        }
      }

      if (cmd.hasOption('I')) {
        launcherOptions.averageRequestInterval =
            1000 * Long.parseLong(cmd.getOptionValue('I'));
      }
      if (cmd.hasOption('B')) {
        launcherOptions.averageBytes = Integer.parseInt(cmd.getOptionValue('B'));
      }
      if (cmd.hasOption('O')) {
        launcherOptions.rateOf200Ok = Double.parseDouble(cmd.getOptionValue('O'));
      }
      if (cmd.hasOption('H')) {
        launcherOptions.numberOfRemoteHosts = Integer.parseInt(cmd.getOptionValue('H'));
      }

    } catch (MissingOptionException e) {
      System.err.print("Error! Missing required options: ");
      for (Object o : e.getMissingOptions()) {
        System.err.print(o.toString() + " ");
      }
      System.err.println();
      printHelpAndExit(options);

    } catch (org.apache.commons.cli.ParseException e) {
      System.err.println("Parsing failed.  Reason: " + e.getMessage());
      printHelpAndExit(options);
    }
    return launcherOptions;
  }

  private static void printHelpAndExit(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp(74,
        "generator [OPTIONS] FILENAME",
        "Generate sample log\n",
        options, "\nEx. generator requests.log");
    System.exit(1);
  }

  public int requests;
  public String fileName;
  public long averageRequestInterval = 1000;
  public int averageBytes = 256;
  public double rateOf200Ok = 0.9;
  public int numberOfRemoteHosts = 100;
}