package com.ef.parser.domain;

import lombok.Data;
import org.springframework.boot.ApplicationArguments;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data
public class Arguments {
  private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd.hh:mm:ss");

  private Date startDate;

  private Date endDate;

  private Long threshold;

  private String accessLog;

  private ApplicationArguments args;

  public Arguments(ApplicationArguments args) throws ParseException {
    this.args = args;
    loadFromArgs();
  }

  public Arguments(ApplicationArguments args, DateFormat dateFormat) throws ParseException {
    this.args = args;
    this.dateFormat = dateFormat;
    loadFromArgs();
  }

  public boolean requiredPresent() {
    return (this.args.containsOption("startDate")
        && this.args.containsOption("duration")
        && this.args.containsOption("threshold"));
  }

  private void loadFromArgs() throws ParseException {
    // start date
    if (args.containsOption("startDate")) {
      String date = args.getOptionValues("startDate").get(0);
      this.startDate = this.dateFormat.parse(date);
      this.endDate = startDate;
    }

    // end date
    if (args.containsOption("duration")) {
      String duration = args.getOptionValues("duration").get(0);
      if (duration.equals("hourly")) {
        this.endDate = new Date(this.startDate.getTime() + TimeUnit.HOURS.toMillis(1));
      } else if (duration.equals("daily")) {
        this.endDate = new Date(this.startDate.getTime() + TimeUnit.DAYS.toMillis(1));
      }
    }

    // threshold
    if (args.containsOption("threshold")) {
      this.threshold = new Long(args.getOptionValues("threshold").get(0));
    }

    // file location
    if (args.containsOption("accesslog")) {
      this.accessLog = args.getOptionValues("accesslog").get(0);
    }
  }
}
