package com.ef.parser.service;

import com.ef.parser.domain.Arguments;
import com.ef.parser.domain.entity.Log;
import com.ef.parser.repository.LogRepository;
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
public class LogServiceTest {

  @Mock private LogRepository logRepository;

  @Mock List<Log> logList;

  @Mock Arguments arguments;

  @InjectMocks private LogService logService;

  @Test
  public void saveListTest() {
    when(logService.saveList(logList)).thenReturn(logList);
    Assert.assertSame(logList, logService.saveList(logList));
  }

  @Test
  public void findForThresholdTest() {
    when(logService.findForThreshold(arguments)).thenReturn(logList);
    Assert.assertSame(logList, logService.findForThreshold(arguments));
  }
}
