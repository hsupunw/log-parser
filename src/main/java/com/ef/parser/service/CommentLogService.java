package com.ef.parser.service;

import com.ef.parser.domain.Arguments;
import com.ef.parser.domain.entity.CommentLog;
import com.ef.parser.domain.entity.Log;
import com.ef.parser.repository.CommentLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentLogService {

  @Autowired private CommentLogRepository commentLogRepository;

  public List<CommentLog> saveList(List<CommentLog> commentLogs) {
    return commentLogRepository.save(commentLogs);
  }

  public List<CommentLog> saveComments(Arguments arguments, List<Log> logs) {
    return saveList(makeCommentLogs(arguments, logs));
  }

  private List<CommentLog> makeCommentLogs(Arguments arguments, List<Log> logs) {
    return logs.stream()
        .map(
            log -> {
              CommentLog commentLog = new CommentLog();
              commentLog.setIp(log.getIp());
              commentLog.setComment(
                  "The IP: "
                      + log.getIp()
                      + " has exceeded the threshold no of "
                      + arguments.getThreshold().toString()
                      + " requests from starting time "
                      + arguments.getStartDate().toString()
                      + " to ending time "
                      + arguments.getEndDate().toString());
              return commentLog;
            })
        .collect(Collectors.toList());
  }
}
