package com.ef.parser.component;

import com.ef.parser.domain.entity.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@Slf4j
public class LogFileReader {

  @Autowired DateFormat dateFormat;

  private final char DELIMITER = '|';

  public List<Log> getLogFromFile(String file) throws IOException {

    Reader in = new FileReader(file);
    Iterable<CSVRecord> records = CSVFormat.RFC4180.withDelimiter(DELIMITER).parse(in);

    return StreamSupport.stream(records.spliterator(), true)
        .map(
            record -> {
              Log logLine = new Log();

              String dateString = record.get(0);
              dateString = dateString.replace("\uFEFF", ""); // remove UTF
              Date date = null;
              try {
                date = dateFormat.parse(dateString);
              } catch (ParseException e) {
                log.error(new StringBuffer("Error in parsing " + dateString).toString(), e);
              }
              logLine.setDate(date);
              logLine.setIp(record.get(1));
              logLine.setMethod(record.get(2));
              logLine.setResponse(record.get(3));
              logLine.setUserAgent(record.get(4));
              return logLine;
            })
        .collect(Collectors.toList());
  }
}
