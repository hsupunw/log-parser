package com.ef.parser;

import com.ef.parser.component.LogFileReader;
import com.ef.parser.domain.Arguments;
import com.ef.parser.domain.entity.CommentLog;
import com.ef.parser.domain.entity.Log;
import com.ef.parser.service.CommentLogService;
import com.ef.parser.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Application implements ApplicationRunner {

  @Autowired LogFileReader logFileReader;
  @Autowired LogService logService;
  @Autowired CommentLogService commentLogService;

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Arguments arguments = new Arguments(args);

    if (arguments.getAccessLog() != null) {
      try {
        log.info("Loading data....");
        logService.saveList(logFileReader.getLogFromFile(arguments.getAccessLog()));
        log.info("Data loaded successfully.");
      } catch (Exception e) {
        log.error("Problem while loading data. " + e.getMessage());
      }
    } else {
      log.info("Parameter 'accesslog' not present.");
    }

    if (arguments.requiredPresent()) {
      List<Log> logs = logService.findForThreshold(arguments);
      List<CommentLog> comments = commentLogService.saveComments(arguments, logs);
      comments.forEach(comment -> log.info(comment.getComment()));
    } else {
      log.error("Please specify startDate, duration and threshold.");
    }
  }
}
