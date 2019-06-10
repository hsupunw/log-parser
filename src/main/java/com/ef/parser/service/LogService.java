package com.ef.parser.service;

import com.ef.parser.domain.Arguments;
import com.ef.parser.domain.entity.Log;
import com.ef.parser.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

  @Autowired private LogRepository logRepository;

  public List<Log> saveList(List<Log> logs) {
    return logRepository.save(logs);
  }

  public List<Log> findForThreshold(Arguments arguments) {
    return logRepository.findForThreshold(
        arguments.getStartDate(), arguments.getEndDate(), arguments.getThreshold());
  }
}
