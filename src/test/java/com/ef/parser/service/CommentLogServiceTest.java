package com.ef.parser.service;

import com.ef.parser.domain.entity.CommentLog;
import com.ef.parser.repository.CommentLogRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class CommentLogServiceTest {

  @Mock private CommentLogRepository commentLogRepository;

  @Mock List<CommentLog> commentLogs;

  @InjectMocks private CommentLogService commentLogService;

  @Test
  public void saveListTest() {
    when(commentLogService.saveList(commentLogs)).thenReturn(commentLogs);
    Assert.assertSame(commentLogs, commentLogService.saveList(commentLogs));
  }
}
