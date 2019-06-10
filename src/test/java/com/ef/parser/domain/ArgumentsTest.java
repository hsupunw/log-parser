package com.ef.parser.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class ArgumentsTest {
  @Mock ApplicationArguments applicationArguments;

  @Mock DateFormat formatter;

  @Mock List<String> sampleArgValues;

  @Mock List<String> sampleOtherArgValues;

  @Mock Date sampleDate;

  String START_DATE = "startDate";

  String DURATION = "duration";

  String THRESHOLD = "threshold";

  String ACCESS_LOG = "accesslog";

  @Test
  public void mapCorrectStartDateTest() throws ParseException {
    when(applicationArguments.containsOption(START_DATE)).thenReturn(true);
    when(applicationArguments.getOptionValues(START_DATE)).thenReturn(sampleArgValues);
    when(formatter.parse(sampleArgValues.get(0))).thenReturn(sampleDate);

    Arguments arguments = new Arguments(applicationArguments, formatter);
    Assert.assertSame(sampleDate, arguments.getStartDate());
  }

  @Test
  public void calculateCorrectEndDateTestHourly() throws ParseException {
    when(applicationArguments.containsOption(START_DATE)).thenReturn(true);
    when(applicationArguments.getOptionValues(START_DATE)).thenReturn(sampleArgValues);
    when(formatter.parse(sampleArgValues.get(0))).thenReturn(sampleDate);
    when(applicationArguments.containsOption(DURATION)).thenReturn(true);
    when(applicationArguments.getOptionValues(DURATION)).thenReturn(sampleOtherArgValues);
    when(sampleOtherArgValues.get(0)).thenReturn("hourly");
    when(sampleDate.getTime()).thenReturn((long) 0);

    Arguments arguments = new Arguments(applicationArguments, formatter);
    Assert.assertEquals(
        new Date(sampleDate.getTime() + TimeUnit.HOURS.toMillis(1)), arguments.getEndDate());
  }

  @Test
  public void calculateCorrectEndDateTestDaily() throws ParseException {
    when(applicationArguments.containsOption(START_DATE)).thenReturn(true);
    when(applicationArguments.getOptionValues(START_DATE)).thenReturn(sampleArgValues);
    when(formatter.parse(sampleArgValues.get(0))).thenReturn(sampleDate);
    when(applicationArguments.containsOption(DURATION)).thenReturn(true);
    when(applicationArguments.getOptionValues(DURATION)).thenReturn(sampleOtherArgValues);
    when(sampleOtherArgValues.get(0)).thenReturn("daily");
    when(sampleDate.getTime()).thenReturn((long) 0);

    Arguments arguments = new Arguments(applicationArguments, formatter);
    Assert.assertEquals(
        new Date(sampleDate.getTime() + TimeUnit.DAYS.toMillis(1)), arguments.getEndDate());
  }

  @Test
  public void mapCorrectAccessThresholdTest() throws ParseException {
    when(applicationArguments.containsOption(THRESHOLD)).thenReturn(true);
    when(applicationArguments.getOptionValues(THRESHOLD)).thenReturn(sampleArgValues);
    when(sampleArgValues.get(0)).thenReturn("0");

    Arguments arguments = new Arguments(applicationArguments);
    Assert.assertEquals(new Long(0), arguments.getThreshold());
  }

  @Test
  public void mapCorrectAccessLogTest() throws ParseException {
    when(applicationArguments.containsOption(ACCESS_LOG)).thenReturn(true);
    when(applicationArguments.getOptionValues(ACCESS_LOG)).thenReturn(sampleArgValues);

    Arguments arguments = new Arguments(applicationArguments);
    Assert.assertSame(sampleArgValues.get(0), arguments.getAccessLog());
  }
}
